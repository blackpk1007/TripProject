package com.trip.project.service;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public class Ocr {
    public String ocr(MultipartFile file) {
    	String apiURL = "https://3oebfrow1n.apigw.ntruss.com/custom/v1/23016/3cd4a68f4d744ae336ad066ec38f6d6c8f943eecf197177f45afcc0620da14e9/general";
		String secretKey = "TlpSUk1FdXdrdnpyb3lhU1dUR3lneFp4ZmltYmxrVHQ=";
        StringBuffer response = null;

        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setReadTimeout(30000);
            con.setRequestMethod("POST");
            String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-OCR-SECRET", secretKey);

            JSONObject json = new JSONObject();
            json.put("version", "V2");
            json.put("requestId", UUID.randomUUID().toString());
            json.put("timestamp", System.currentTimeMillis());
            JSONObject image = new JSONObject();
            image.put("format", "jpg");
            image.put("name", "demo");
            JSONArray images = new JSONArray();
            images.put(image);
            json.put("images", images);
            String postParams = json.toString();

            con.connect();
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            // Write multipart form-data
            wr.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            wr.write(("Content-Disposition:form-data; name=\"message\"\r\n\r\n").getBytes("UTF-8"));
            wr.write((postParams + "\r\n").getBytes("UTF-8"));
            wr.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
            wr.write(("Content-Disposition:form-data; name=\"file\"; filename=\"" + file.getOriginalFilename() + "\"\r\n").getBytes("UTF-8"));
            wr.write(("Content-Type: application/octet-stream\r\n\r\n").getBytes("UTF-8"));
            wr.write(file.getBytes());
            wr.write(("\r\n--" + boundary + "--\r\n").getBytes("UTF-8"));

            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            System.out.println(response);
        } catch (Exception e) {
            System.out.println(e);
        }

        return response.toString();
    }
}