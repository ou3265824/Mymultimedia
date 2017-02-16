package com.example.test;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.R.id.message;

public class JsoupActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        textView = (TextView) findViewById(R.id.tv_jsoup);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String s=doGet("http://www.baidu.com");
                Message message=new Message();
                message.obj=s;

            }
        }).start();

//        textView.setText(s);
//        Log.i("test","html:"+s);
    }
    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            textView.setText((String) msg.obj);
        }
    };

    public String doGet(String urlStr)  {
        URL url;
        String html = "";
        try {
            url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (connection.getResponseCode() == 200) {
                InputStream in = connection.getInputStream();
                html = inToStringByByte(in);
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return html;
    }

    public String inToStringByByte(InputStream in) throws Exception {
        ByteArrayOutputStream outStr = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        StringBuilder content = new StringBuilder();
        while ((len = in.read(buffer)) != -1) {
            content.append(new String(buffer, 0, len, "UTF-8"));
        }
        outStr.close();
        return content.toString();
    }

}
