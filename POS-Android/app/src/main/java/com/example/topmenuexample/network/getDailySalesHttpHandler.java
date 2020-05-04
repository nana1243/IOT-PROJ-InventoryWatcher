package com.example.topmenuexample.network;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getDailySalesHttpHandler {

    public static String requestData(URL url) {

        String result = null;
        HttpURLConnection conn = null;
        InputStream is;


        try {
            conn = (HttpURLConnection) url.openConnection();
            //Log.d("---", "url: " + url.toString());
            conn.setDoInput(true);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "string; utf-8");
            is = new BufferedInputStream(conn.getInputStream());
            result = convertJsonToStr(is);

            Log.d("---" , "get Result Data : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String convertJsonToStr(InputStream is) {

        BufferedReader bi = null;
        StringBuilder sb = new StringBuilder();
       try {
           bi = new BufferedReader(new InputStreamReader(is));
           String temp = "";
           // 스트링 빌더를 이용하여 라인별로 읽고 쌓은 후 //
           while ((temp = bi.readLine()) != null) {
               sb.append(temp);
           }
           Log.d("---","sb: "+sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }


}

