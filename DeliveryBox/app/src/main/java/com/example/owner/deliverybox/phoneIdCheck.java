package com.example.owner.deliverybox;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class phoneIdCheck extends AppCompatActivity {
    String android_ID;
    String android_id;
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_id_check);

        android_ID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ConnectServer();

        if (android_ID.equals(android_id)) {
            Intent intent = new Intent(this, phoneCheck.class);
            intent.putExtra("android_id",android_ID);
            startActivity(intent);
            finish();
        } else {
            /*Intent intent = new Intent(this, deliveryMain.class);
            startActivity(intent);
            finish();*/
            Toast.makeText(phoneIdCheck.this, android_ID + android_id, Toast.LENGTH_SHORT).show();
        }


    }

    private void ConnectServer() {

        final String SIGNIN_URL = "http://203.244.145.214:8084/deliveryQR/selectidPage.jsp";
        final String urlSuffix = "?name=" + android_ID;

        class SignupUser extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if (s != null) {
                    try {
                        JSONArray jArr = new JSONArray(s);
                        JSONObject jsonObject = new JSONObject();
                        for (int i = 0; i < jArr.length(); i++) {
                            jsonObject=jArr.getJSONObject(i);
                            android_id = jsonObject.getString("android_id");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(phoneIdCheck.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                BufferedReader bufferedReader = null;

                try {

                    HttpClient client = new DefaultHttpClient();  // 보낼 객체 만들기
                    HttpPost post = new HttpPost(SIGNIN_URL + urlSuffix);  // 주소 뒤에 데이터를 넣기

                    HttpResponse response = client.execute(post); // 데이터 보내기

                    BufferedReader bufreader = new BufferedReader(
                            new InputStreamReader(
                                    response.getEntity().getContent(), "utf-8"));

                    String line = null;
                    String page = "";

                    while ((line = bufreader.readLine()) != null) {
                        page += line;
                    }
                    Log.d(TAG, page);
                    return page;
                } catch (Exception e) {

                    return null;
                }
            }
        }

        SignupUser su = new SignupUser();
        su.execute(urlSuffix);
    }
}
