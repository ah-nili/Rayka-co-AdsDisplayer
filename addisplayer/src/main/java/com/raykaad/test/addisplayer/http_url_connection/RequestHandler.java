package com.raykaad.test.addisplayer.http_url_connection;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class RequestHandler {

    public static String sendPost(String r_url, JSONObject postDataParams) throws Exception {
        URL url = new URL(r_url);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(20000);
        urlConnection.setConnectTimeout(20000);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);

        OutputStream os = urlConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(postDataParams.toString());
        writer.flush();
        writer.close();
        os.close();

        int responseCode = urlConnection.getResponseCode(); // To Check for 200
        InputStream inputStream;
        if (responseCode == HttpsURLConnection.HTTP_OK)
            inputStream = urlConnection.getInputStream();
        else
            inputStream = urlConnection.getErrorStream();


        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer("");
        String line = "";
        while ((line = in.readLine()) != null) {
            sb.append(line);
            break;
        }
        in.close();
        return sb.toString();
    }

    public static String sendPut(String r_url, JSONObject postDataParams) throws Exception {
        URL url = new URL(r_url);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setReadTimeout(20000);
        urlConnection.setConnectTimeout(20000);
        urlConnection.setRequestMethod("PUT");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);

        OutputStream os = urlConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
        writer.write(postDataParams.toString());
        writer.flush();
        writer.close();
        os.close();

        int responseCode = urlConnection.getResponseCode(); // To Check for 200
        InputStream inputStream;
        if (responseCode == HttpsURLConnection.HTTP_OK)
            inputStream = urlConnection.getInputStream();
        else
            inputStream = urlConnection.getErrorStream();


        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer("");
        String line = "";
        while ((line = in.readLine()) != null) {
            sb.append(line);
            break;
        }
        in.close();
        return sb.toString();
    }

    public static String sendGet(String urlAddress) throws IOException {
        URL url = new URL(urlAddress);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        int responseCode = urlConnection.getResponseCode();
        System.out.println("Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // connection ok
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } finally {
                urlConnection.disconnect();
            }
        } else {
            return String.valueOf(responseCode);
        }
    }

    private static String encodeParams(JSONObject params) throws Exception {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> itr = params.keys();
        while (itr.hasNext()) {
            String key = itr.next();
            Object value = params.get(key);
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));
        }
        return result.toString();
    }
}