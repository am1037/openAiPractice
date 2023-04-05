package com.example.openaipractice;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Papago {
    static StringBuffer response;
    static String clientId = System.getenv("papago_id");//애플리케이션 클라이언트 아이디값";
    static String clientSecret = System.getenv("papago_pw");//애플리케이션 클라이언트 시크릿값";
    static Gson gson = new Gson();
    static JsonObject jsonObject;

    public static String translateK2E(String str) {
        try {
            String text = URLEncoder.encode(str, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=ko&target=en&text=" + text;
            return getString(con, postParams);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static String translateE2K(String str) {
        try {
            String text = URLEncoder.encode(str, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            // post request
            String postParams = "source=en&target=ko&text=" + text;
            return getString(con, postParams);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    private static String getString(HttpURLConnection con, String postParams) throws IOException {
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        return getString(br);
    }

    private static String getString(BufferedReader br) throws IOException {
        String inputLine;
        response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();
        //System.out.println(response.toString());
        jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        return jsonObject.get("message").getAsJsonObject().get("result").getAsJsonObject().get("translatedText").getAsString();
    }
}
