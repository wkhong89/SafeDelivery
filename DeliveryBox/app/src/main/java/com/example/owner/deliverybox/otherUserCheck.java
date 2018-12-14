package com.example.owner.deliverybox;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class otherUserCheck extends AppCompatActivity {
    TextView textView;
    String phone, password,products, user, ophone;
    TextView o_user,o_phone,r_user,r_phone,r_address,s_user,s_address,s_phone,company,product, waybill;
    String coms = "택배물 집하 전입니다.";
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_other_user_check);  Bundle bundle = getIntent().getExtras();






        phone= bundle.getString("r_phone");
        Bundle bundle2 = getIntent().getExtras();
        password= bundle2.getString("password");
        Bundle bundle3 = getIntent().getExtras();
        products= bundle3.getString("product");
        textView = (TextView) findViewById(R.id.checkPhone);
        textView.setText("주문정보");

        o_user = (TextView) findViewById(R.id.o_user);
        o_phone = (TextView) findViewById(R.id.o_phone);


        r_user = (TextView) findViewById(R.id.r_user);
        r_phone = (TextView) findViewById(R.id.r_phone);
        r_address = (TextView) findViewById(R.id.r_address);
        s_user = (TextView) findViewById(R.id.s_user);
        s_address = (TextView) findViewById(R.id.s_address);
        s_phone = (TextView) findViewById(R.id.s_phone);
        waybill=(TextView) findViewById(R.id.waybill);
        company=(TextView) findViewById(R.id.company);
        product=(TextView) findViewById(R.id.product);
        ConnectServer();


        Button button = (Button)findViewById(R.id.btn_message);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputText = ophone;
                String inputText2 = user+"로 보내는 택배 정보가 열람되었습니다." +
                        "- 운송장 번호 : "+coms;
                if(inputText.length()>0 && inputText2.length()>0) {
                    sendSMS(inputText, inputText2);
                    Toast.makeText(getBaseContext(), "문자를 성공적으로 전송하였습니다.", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getBaseContext(), "전화번호와 메시지를 입력해주세요.", Toast.LENGTH_SHORT).show();
            }});
    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }



    private void ConnectServer() {

        final String SIGNIN_URL = "http://203.244.145.214:8084/deliveryQR/selectProductPage.jsp";
        final String urlSuffix = "?phoneNumber=" + phone + "&password="+ password + "&product="+products;

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
                            textView.setText(jsonObject.getString("o_user")+"님의 주문정보");
                            user = jsonObject.getString("o_user");
                            o_user.setText(user);
                            o_phone.setText(jsonObject.getString("o_phone"));
                            ophone = o_phone.getText().toString();
                            r_user.setText(jsonObject.getString("r_user"));
                            r_phone.setText(jsonObject.getString("r_phone"));
                            r_address.setText(jsonObject.getString("r_address"));
                            s_user.setText(jsonObject.getString("s_user"));
                            s_address.setText(jsonObject.getString("s_address"));
                            s_phone.setText(jsonObject.getString("s_phone"));
                            product.setText(jsonObject.getString("product"));
                            waybill.setText(jsonObject.getString("waybill"));
                            String company_name = jsonObject.getString("company_name");
                            if(company_name==null || company_name.equals("null")){
                                company.setText("택배물 집하 전입니다.");
                            }else{
                                coms = "(" + jsonObject.getString("company_name") + ")" + jsonObject.getString("company");
                                company.setText(coms);
                            }
                        }


                    } catch (Exception e) {
                        o_user.setText(s);

                    }
                } else {
                    //Toast.makeText(otherUserCheck.this, "서버와의 통신에 문제가 발생했습니다", Toast.LENGTH_SHORT).show();
                    o_user.setText(s);
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
