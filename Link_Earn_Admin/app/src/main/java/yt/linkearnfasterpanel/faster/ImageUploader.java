package yt.linkearnfasterpanel.faster;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageUploader {

    public interface UploadCallback {
        void onUploadComplete(String imageUrl);

        void onUploadError(String errorMessage);
    }

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    public static void uploadImage(final String filePath, final UploadCallback callback) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File(filePath);
                    String boundary = "*****";
                    String lineEnd = "\r\n";
                    String twoHyphens = "--";

                    // Set your server URL for image upload
                    URL url = new URL("https://api.imgbb.com/1/upload?expiration=0&key=yourApiKey");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    connection.setUseCaches(false);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Connection", "Keep-Alive");
                    connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

                    DataOutputStream dos = new DataOutputStream(connection.getOutputStream());

                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"image\";filename=\"" + file.getName() + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);

                    FileInputStream fileInputStream = new FileInputStream(file);
                    int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];

                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        dos.write(buffer, 0, bytesRead);
                    }

                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                    fileInputStream.close();
                    dos.flush();
                    dos.close();

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = in.readLine()) != null) {
                            response.append(line);
                        }
                        in.close();
                        handleUploadResponse(response.toString(), callback);
                    } else {
                        String errorMessage = "Error: " + responseCode;
                        notifyError(callback, errorMessage);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    notifyError(callback, "Error: " + e.getMessage());
                }
            }
        });
    }

    private static void handleUploadResponse(final String response, final UploadCallback callback) {
        try {
            JSONObject jsonResponse = new JSONObject(response);

            // Assuming the image URL is under the key "data" and "url"
            JSONObject data = jsonResponse.getJSONObject("data");
            final String imageUrl = data.getString("url");

            notifySuccess(callback, imageUrl);
        } catch (JSONException e) {
            e.printStackTrace();
            notifyError(callback, "Error parsing JSON response");
        }
    }

    private static void notifySuccess(final UploadCallback callback, final String imageUrl) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onUploadComplete(imageUrl);
            }
        });
    }

    private static void notifyError(final UploadCallback callback, final String errorMessage) {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onUploadError(errorMessage);
            }
        });
    }
}
