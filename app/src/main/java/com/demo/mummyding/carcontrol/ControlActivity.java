package com.demo.mummyding.carcontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener{
    private final String HOST="192.168.3.100";
    private final int PORT=3456;
    private ImageButton btn_forward;
    private ImageButton btn_backward;
    private ImageButton btn_right;
    private ImageButton btn_left;
    private ImageButton btn_stop;
    private ImageButton btn_reLink;
    private Socket client;
    private PrintWriter outputData;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void link(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    client = new Socket(HOST,PORT);
                    outputData = new PrintWriter(client.getOutputStream(), true);
                    Log.e("ok", "连接成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("error",e.toString());
                }
            }
        }).start();
    }
    private void initView(){
        btn_forward = (ImageButton) findViewById(R.id.btn_forward);
        btn_backward = (ImageButton) findViewById(R.id.btn_backward);
        btn_right = (ImageButton) findViewById(R.id.btn_right);
        btn_left = (ImageButton) findViewById(R.id.btn_left);
        btn_stop = (ImageButton) findViewById(R.id.btn_stop);
        btn_reLink = (ImageButton) findViewById(R.id.reLink);

        btn_forward.setOnClickListener(this);
        btn_backward.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_reLink.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("智能控制");
        link();
    }

    private void send(String command){

        try {
            outputData.println(command);
            Log.e("ok",command);
        }catch (Exception e){
            Toast.makeText(ControlActivity.this, "发送异常" + e.toString(), Toast.LENGTH_SHORT).show();
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
