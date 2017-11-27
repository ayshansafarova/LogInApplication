package com.example.admin.loginapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainPageActivity extends AppCompatActivity {

     EditText uname, password;
    private String nameString, passwordString;
    DatabaseHelper dbHandler;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        db = openOrCreateDatabase("UserRegistration", MODE_PRIVATE, null);
        dbHandler = new DatabaseHelper(this);
        final Button btn_login = (Button) findViewById(R.id.login);
        final Button btn_open_reg = (Button) findViewById(R.id.btn_open_reg);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = (EditText) findViewById(R.id.editText);
                password = (EditText) findViewById(R.id.editText2);

                nameString = uname.getText().toString();
                passwordString = password.getText().toString();

                String password = dbHandler.searchPassword(nameString);

                if(passwordString.equals(password)){
                    Intent i = new Intent(MainPageActivity.this, SecondMainActivity.class);
                    startActivity(i);
                    uname.setText("");
//                    password.setText("");
                }
                else{
                    Toast.makeText(MainPageActivity.this , "Username or password is wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_open_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainPageActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

//    public void openRegistrationPage(View view){
//        Intent intent = new Intent(MainPageActivity.this, RegisterActivity.class);
//        MainPageActivity.this.startActivity(intent);
//    }
//    public void toLogIn(View view){
//        Intent intentt=new Intent(MainPageActivity.this, SecondMainActivity.class);
//        MainPageActivity.this.startActivity(intentt);
//    }
}
