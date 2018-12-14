package com.example.owner.deliverybox;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class deliveryMain extends AppCompatActivity {
    private EditText editTestPhone;
    private EditText editTestPhone2;
    String phoneNumber;
    String phonePasswd;
    private final String TAG = "MainActivity";
    String android_id;
    boolean success;
    Intent intent;
    int idCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_main);

        editTestPhone = (EditText) findViewById(R.id.id_text);
        editTestPhone2 = (EditText) findViewById(R.id.pw_text);
        Bundle bundle = getIntent().getExtras();
        idCheck= bundle.getInt("idCheck");

        editTestPhone.setText(android_id);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        intent = new Intent(getApplicationContext(), listViewSelect.class);
        Button button = (Button) findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phoneNumber = editTestPhone.getText().toString();    //문자열 가져오기
                phonePasswd= editTestPhone2.getText().toString();

                if(phoneNumber.equals("") || phoneNumber.length() < 11 || phoneNumber.length() > 11){
                    Toast.makeText(deliveryMain.this, "휴대폰 번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }else if(phonePasswd.equals("")){
                    Toast.makeText(deliveryMain.this, "비밀번호를 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    ConnectServer();
                  }
            }
        });
    }
    private void ConnectServer() {

        final String SIGNIN_URL = "http://203.244.145.214:8084/deliveryQR/selectPage.jsp";
        final String urlSuffix = "?phoneNumber=" + phoneNumber + "&password="+phonePasswd;

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
                            intent.putExtra("r_phone", phoneNumber);
                            intent.putExtra("password",phonePasswd);
                            intent.putExtra("idCheck", idCheck);
                            startActivity(intent);
                        }

                    } catch (Exception e) {
                        Toast.makeText(deliveryMain.this, "전화번호에 맞는 값을 찾지 못하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(deliveryMain.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
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
