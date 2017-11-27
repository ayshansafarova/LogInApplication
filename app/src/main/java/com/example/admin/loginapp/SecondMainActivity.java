package com.example.admin.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);
    }
    public void showInfo(View view){
        Intent intent = new Intent(SecondMainActivity.this, PlatformInfoActivity.class);
        SecondMainActivity.this.startActivity(intent);
    }
}
