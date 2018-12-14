package com.example.owner.deliverybox;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
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

public class finish extends AppCompatActivity {
    TextView textView;
    String phone, password;
    int num, success;
    private final String TAG = "MainActivity";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        intent=new Intent(getApplicationContext(), MainActivity.class);

        textView = (TextView) findViewById(R.id.result);
        Bundle bundle = getIntent().getExtras();
        phone= bundle.getString("phone");
        Bundle bundle2 = getIntent().getExtras();
        password= bundle2.getString("password");
        Bundle bundle3 = getIntent().getExtras();
        num=bundle3.getInt("num");

        ConnectServer();

        startActivity(intent);
        finish();
    }
    private void ConnectServer() {

        final String SIGNIN_URL = "http://203.244.145.214:8084/deliveryQR/deleteProduct.jsp";
        final String urlSuffix = "?phoneNumber=" + phone + "&password="+ password+"&num="+num;



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
                           success = jsonObject.getInt("success");
                           if(success == 0){
                               Toast.makeText(finish.this, "이전의 정보가 삭제되지 않았습니다.", Toast.LENGTH_SHORT).show();
                           }else{
                               Toast.makeText(finish.this, "이전의 정보가 보안을위해\n안전히 삭제처리 되었습니다.", Toast.LENGTH_SHORT).show();
                           }
                        }


                    } catch (Exception e) {
                        Toast.makeText(finish.this, "이전의 정보가 보안을위해\n안전히 삭제처리 되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(finish.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();

                    finish();
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

