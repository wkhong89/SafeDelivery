package com.example.owner.deliverybox;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {
    String android_ID;
    String r_phone, password;
    TextView tv;
    int clickTime = 0;
    Intent intent, falseintent;
    TextView r_phonee;
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android_ID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        r_phonee = (TextView)findViewById(R.id.r_phone);


        intent = new Intent(this, listViewSelect.class);
        falseintent = new Intent(this, deliveryMain.class);

        ConnectServer();




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
                        jsonObject=jArr.getJSONObject(0);


                        intent.putExtra("r_phone", jsonObject.getString("r_phone"));
                        intent.putExtra("idCheck", 0);
                        intent.putExtra("password", jsonObject.getString("password"));
                        startActivity(intent);
                        finish();

                    } catch (Exception e) {
                        falseintent.putExtra("idCheck", 1);

                        startActivity(falseintent);

                        finish();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();

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
                    clickTime ++;
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