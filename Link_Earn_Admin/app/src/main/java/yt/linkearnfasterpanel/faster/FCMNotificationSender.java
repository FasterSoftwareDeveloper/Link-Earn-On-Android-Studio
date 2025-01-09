package yt.linkearnfasterpanel.faster;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;
import android.util.Log;

public class FCMNotificationSender {

    public interface OnResponse {
        void onSuccess(String response);
        void onError(String error);
    }

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void sendNotification(HashMap<String, Object> notificationMap, String topic, String token, String projectId, String accessToken, OnResponse callback) {
        executor.execute(() -> {
            try {
                String url = "https://fcm.googleapis.com/v1/projects/" + projectId + "/messages:send";

                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Authorization", "Bearer " + accessToken);
                con.setRequestProperty("Content-Type", "application/json; UTF-8");
                con.setDoOutput(true);

                HashMap<String, Object> message = new HashMap<>();
                if (topic != null && !topic.isEmpty()) {
                    message.put("topic", topic);
                } else if (token != null && !token.isEmpty()) {
                    message.put("token", token);
                }
                message.put("data", notificationMap);

                HashMap<String, Object> root = new HashMap<>();
                root.put("message", message);

                String jsonData = new Gson().toJson(root);

                OutputStream os = con.getOutputStream();
                os.write(jsonData.getBytes("UTF-8"));
                os.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                if (callback != null) {
                    callback.onSuccess(response.toString());
                }
            } catch (Exception e) {
                String errorMessage = "Error: " + e.getMessage();
                Log.e("FCMNotificationSender", errorMessage);
                if (callback != null) {
                    callback.onError(errorMessage);
                }
            }
        });
    }
}
