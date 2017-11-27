package com.example.admin.loginapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private EditText Name, Surname, Username, Password, CPassword;
    private String name, surname, email, pass1, pass2;
    private Button bRegister;
    private Users user;
    DatabaseHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        actionBar.setTitle(" ");

        setContentView(R.layout.activity_register);

        Name = (EditText) findViewById(R.id.editText3);
        Surname = (EditText) findViewById(R.id.editText4);
        Username = (EditText) findViewById(R.id.editText5);
        Password = (EditText) findViewById(R.id.editText6);
        CPassword = (EditText) findViewById(R.id.editText7);

        dbHandler = new DatabaseHelper(this);
        bRegister = (Button) findViewById(R.id.btn_reg);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = Name.getText().toString();
                surname = Surname.getText().toString();
                email = Username.getText().toString();
                pass1 = Password.getText().toString();
                pass2 = CPassword.getText().toString();
                user = new Users();

                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setPassword(pass1);


                if(name.isEmpty() || surname.isEmpty() || email.isEmpty() || pass1.isEmpty() || pass1.isEmpty()){
                    Toast.makeText(RegisterActivity.this , "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
                else if(!pass1.equals(pass2)){
                    Toast.makeText(RegisterActivity.this , "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(dbHandler.insertUser(user)==true){

                        Toast.makeText(RegisterActivity.this , "You have been registered!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, MainPageActivity.class);
                        startActivity(intent);}
                    else{
                        Username.setTextColor(Color.RED);
                        Toast.makeText(RegisterActivity.this , "Email address is already used!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
