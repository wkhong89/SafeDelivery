package com.example.owner.deliverybox;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class listViewSelect extends AppCompatActivity {
    String phone;
    String password;
    String product;
    int num;
    ArrayAdapter<String> adapter;
    int idCheck;
    ArrayList<String> items = new ArrayList<String>();
    ListView listView;
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);


        Bundle bundle = getIntent().getExtras();
        phone= bundle.getString("r_phone");
        Bundle bundle2 = getIntent().getExtras();
        password= bundle2.getString("password");
        Bundle bundle3 = getIntent().getExtras();
        idCheck= bundle.getInt("idCheck");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);


        final Button tbs = (Button) this.findViewById(R.id.btn_other);
        tbs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), deliveryMain.class);
                intent.putExtra("idCheck",1);
                startActivity(intent);
                finish();
            }
        });



        ConnectServer();

    }
    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(idCheck == 0){
                Intent intent = new Intent(getApplicationContext(), phoneCheck.class);
                String product = (String) parent.getAdapter().getItem(position);
                intent.putExtra("product", product);
                intent.putExtra("r_phone", phone);
                intent.putExtra("password", password);
                intent.putExtra("num", num);
                startActivity(intent);
            }else if(idCheck == 1) {
                Intent intent = new Intent(getApplicationContext(), otherUserCheck.class);
                String product = (String) parent.getAdapter().getItem(position);
                intent.putExtra("product", product);
                intent.putExtra("r_phone", phone);
                intent.putExtra("password", password);
                startActivity(intent);
            }
        }
    };

    private void ConnectServer() {

        final String SIGNIN_URL = "http://203.244.145.214:8084/deliveryQR/selectPage.jsp";
        final String urlSuffix = "?phoneNumber=" + phone + "&password="+ password;



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
                            product = jsonObject.getString("product");
                            items.add(product);
                            num = jsonObject.getInt("num");
                            listView =(ListView)findViewById(R.id.List_view);
                            listView.setAdapter(adapter);
                            listView.setTextFilterEnabled(true);
                            listView.setOnItemClickListener(mItemClickListener);
                        }


                    } catch (Exception e) {


                    }
                } else {
                    Toast.makeText(listViewSelect.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();

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
