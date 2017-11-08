package com.firstapp.joel.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText usrInput;
   // EditText emailInput;
    EditText pwdInput;
    Button login;
    Button register;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //LoginDataBaseAdapter loginDataBaseAdapter;

        usrInput = (EditText) findViewById(R.id.lPageUsername);
        //emailInput = (EditText) findViewById(R.id.lPageEmail);
        pwdInput= (EditText) findViewById(R.id.lPagePassword);
        login = (Button) findViewById(R.id.lButtonL);
        register = (Button) findViewById(R.id.lButtonR);

        dbHelper = DatabaseHelper.createInstance(this);

//------------------------------------------------------------------------------------------------------------------------------
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String lusername = usrInput.getText().toString();
                String lpassword = pwdInput.getText().toString();
                if (lusername != null && !lusername.isEmpty() && lpassword != null && !lpassword.isEmpty())
                {
                    String password = dbHelper.searchPass(lusername);
                    if (lpassword.equals(password)) {
                        Intent intent = new Intent(LoginPage.this, Questionaire.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginPage.this, "Incorrect Username/Password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginPage.this, "Login Credential Fields Can not Be Empty", Toast.LENGTH_SHORT).show();

                }
            }
        });
//------------------------------------------------------------------------------------------------------------------------------
        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(intent1);
            }
        });
//------------------------------------------------------------------------------------------------------------------------------
    }
}
