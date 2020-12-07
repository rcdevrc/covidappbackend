package com.example.teafproject.springbootmongodbsecurity.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.example.teafproject.springbootmongodbsecurity.domain.TupuDevice;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
@Service
public class Utility {

    // Method to send Notifications from server to client end.
    public final static String AUTH_KEY_FCM = "AAAA0KQbGbU:APA91bEg8AX3Ix8p4ivGAWjob637sv9AvAOilJBe2jbALr9sWxf6i3C6nbnm4MOOMoD_l30oUitsjLv2EwaC1er1qrY2onodsCB5qTiINhrzNFAJW95HM4UM3uepQGDPjE2KyLKFpVq2";
    public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	public TupuDevice processJson(String strTupuDevice) {

		strTupuDevice = strTupuDevice.replace("SID", "sid");
		strTupuDevice= strTupuDevice.replace("UID", "uid");
		System.out.println(strTupuDevice);
		JsonObject json = stringToJson(strTupuDevice);
		JsonObject person = json.get("person").getAsJsonObject();
		json.remove("person");
		json.add("personId", person.get("personId").getAsJsonPrimitive());
		json.add("name", person.get("name").getAsJsonPrimitive());
		json.add("temperature", person.get("temperature").getAsJsonPrimitive());
		json.add("avatarURL", person.get("avatarURL").getAsJsonPrimitive());
		json.add("description", person.get("description").getAsJsonPrimitive());
		TupuDevice tupuDevice = new Gson().fromJson(json, TupuDevice.class);

		return tupuDevice;
	}


	private JsonObject stringToJson(String jsonDocument) {
		JsonParser parser = new JsonParser();
		return (JsonObject) parser.parse(jsonDocument);

	}
	
	 public static void pushFCMNotification(String DeviceIdKey, String title, String message) throws Exception {

	        String authKey = AUTH_KEY_FCM; // You FCM AUTH key
	        String FMCurl = API_URL_FCM;

	        URL url = new URL(FMCurl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	        conn.setUseCaches(false);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);

	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Authorization", "key=" + authKey);
	        conn.setRequestProperty("Content-Type", "application/json");

	        JsonObject data = new JsonObject();
	        data.addProperty("to", DeviceIdKey.trim());
	        JsonObject info = new JsonObject();
	        //info.addProperty("title", "FCM Notificatoin Title"); // Notification title
	        //info.addProperty("text", "Hello First Test notification"); // Notification body
	        info.addProperty("title", title);
	        info.addProperty("text", message); // Notification body
	        data.add("notification", info);

	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(data.toString());
	        wr.flush();
	        wr.close();

	        int responseCode = conn.getResponseCode();
	        System.out.println("Response Code : " + responseCode);

	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();

	    }
	 
	 
}
