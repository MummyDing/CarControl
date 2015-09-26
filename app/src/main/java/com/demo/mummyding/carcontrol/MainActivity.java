package com.demo.mummyding.carcontrol;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener{

    private final String HOST="192.168.3.100";
    private final int PORT=3456;
    private Button btn_forward;
    private Button btn_backward;
    private Button btn_right;
    private Button btn_left;
    private Button btn_stop;
    private Button btn_reLink;
    private Socket client;
    private PrintWriter outputData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }
    private void link(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new Socket(HOST,PORT);
                    outputData = new PrintWriter(client.getOutputStream(), true);
                    Log.e("ok","连接成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                }
            }
        }).start();
    }
    private void initView(){
        btn_forward = (Button) findViewById(R.id.btn_forward);
        btn_backward = (Button) findViewById(R.id.btn_backward);
        btn_right = (Button) findViewById(R.id.btn_right);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_reLink = (Button) findViewById(R.id.reLink);

        btn_forward.setOnClickListener(this);
        btn_backward.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_reLink.setOnClickListener(this);
        link();
    }

    private void send(String command){

        try {
            outputData.println(command);
            Log.e("ok",command);
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"发送异常"+e.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_forward:
                send("forward");
                break;
            case R.id.btn_backward:
                send("backward");
                break;
            case R.id.btn_right:
                send("right");
                break;
            case R.id.btn_left:
                send("left");
                break;
            case R.id.btn_stop:
                send("stop");
                break;
            case R.id.reLink:
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                link();
                break;
        }
    }
}
