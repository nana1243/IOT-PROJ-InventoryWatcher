package com.example.topmenuexample.network;

import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class OrderDetailHttpHandler {

    public static String getString(URL url, JSONArray odja) {
        String result = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        OutputStream os = null;
         //  url = new URL("http://70.12.224.85/top/posorderdetail.top");
        try {
            Thread.sleep(500);
            conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(odja.toString());
            out.flush();
            Log.d("---", "message out write: " + odja);
            conn.getInputStream();
           // result = convertStr(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn.getOutputStream() != null) {
                    conn.getOutputStream().close();
                    conn.disconnect();
                }
                if (conn != null) {
                    conn.disconnect();
                    conn = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        conn.disconnect();

        return result;
    }

    public static String convertStr(InputStream is) {
        BufferedReader bi = null;
        StringBuilder sb = new StringBuilder();
        try {
            // 인풋 스트림리더를 버퍼드스트림으로 바꾸어 더 빨리 읽는다. //
            bi = new BufferedReader(new InputStreamReader(is));
            String temp = "";
            // 스트링 빌더를 이용하여 라인별로 읽고 쌓은 후 //
            while ((temp = bi.readLine()) != null) {
                sb.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // String 으로 변환한다. //
        return sb.toString();
    }


}

