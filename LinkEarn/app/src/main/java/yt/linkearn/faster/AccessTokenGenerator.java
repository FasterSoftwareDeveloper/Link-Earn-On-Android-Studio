package yt.linkearn.faster;

import android.util.Base64;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccessTokenGenerator {

    public interface OnTokenResponse {
        void onSuccess(String token);
        void onError(String error);
    }

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void generateToken(InputStream serviceAccountStream, OnTokenResponse callback) {
        executor.execute(() -> {
            try {
                HashMap<String, Object> map = new Gson().fromJson(
                        SketchwareUtil.copyFromInputStream(serviceAccountStream),
                        new TypeToken<HashMap<String, Object>>() {}.getType()
                );

                String privateKeyPem = map.get("private_key").toString();
                String clientEmail = map.get("client_email").toString();

                String header = "{\"alg\":\"RS256\",\"typ\":\"JWT\"}";
                long now = System.currentTimeMillis();
                long exp = now + 3600 * 1000; // 1 hour expiration
                String payload = String.format(
                        "{\"iss\":\"%s\",\"aud\":\"https://oauth2.googleapis.com/token\",\"exp\":%d,\"iat\":%d,\"scope\":\"https://www.googleapis.com/auth/cloud-platform\"}",
                        clientEmail, exp / 1000, now / 1000
                );

                // Use android.util.Base64 for encoding
                String encodedHeader = Base64.encodeToString(header.getBytes(StandardCharsets.UTF_8), Base64.URL_SAFE | Base64.NO_PADDING);
                String encodedPayload = Base64.encodeToString(payload.getBytes(StandardCharsets.UTF_8), Base64.URL_SAFE | Base64.NO_PADDING);
                String unsignedJwt = encodedHeader + "." + encodedPayload;

                String signature = DataSigner.signWithPrivateKey(unsignedJwt, privateKeyPem);
                String jwt = unsignedJwt + "." + signature;

                String response = requestAccessToken(jwt);

                if (callback != null) {
                    callback.onSuccess(response);
                }
            } catch (Exception e) {
                if (callback != null) {
                    callback.onError("Error: " + e.getMessage());
                }
            }
        });
    }

    private static String requestAccessToken(String jwt) throws Exception {
        String endpoint = "https://oauth2.googleapis.com/token";

        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.setDoOutput(true);

        String body = "grant_type=urn:ietf:params:oauth:grant-type:jwt-bearer&assertion=" + jwt;

        OutputStream os = con.getOutputStream();
        os.write(body.getBytes(StandardCharsets.UTF_8));
        os.close();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static class DataSigner {

        public static String signWithPrivateKey(String data, String privateKeyPem) throws Exception {
            privateKeyPem = privateKeyPem.replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] keyBytes = Base64.decode(privateKeyPem, Base64.DEFAULT);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(spec);

            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] signatureBytes = signature.sign();

            return Base64.encodeToString(signatureBytes, Base64.URL_SAFE | Base64.NO_PADDING);
        }
    }
}
