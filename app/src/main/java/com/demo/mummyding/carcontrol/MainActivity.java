package com.demo.mummyding.carcontrol;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String HOST="192.168.3.100";
    private Button control;
    private Button video_monitor;
    private Button info;
    private Intent intent = null;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control = (Button) findViewById(R.id.control);
        video_monitor = (Button) findViewById(R.id.video_monitor);
        info = (Button) findViewById(R.id.info);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        control.setOnClickListener(this);
        video_monitor.setOnClickListener(this);
        info.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("智能小车控制系统");
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.video_monitor:
                intent = new Intent(MainActivity.this,UnDoActivity.class);
                bundle.putString("title", "视频监控");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.info:
                intent = new Intent(MainActivity.this,UnDoActivity.class);
                bundle.putString("title", "报警信息");
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.control:
                intent = new Intent(MainActivity.this,ControlActivity.class);
                startActivity(intent);
        }


    }
}
