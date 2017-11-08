package com.firstapp.joel.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    EditText name;
    EditText username;
    EditText email;
    EditText password;
    EditText password1;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        Button register = (Button) findViewById(R.id.rPageRegister);

        dbHelper = DatabaseHelper.getInstance();

        name = (EditText)findViewById(R.id.rPageName);
        username = (EditText) findViewById(R.id.rPageUsername);
        email = (EditText)findViewById(R.id.rPageEmail);
        password = (EditText) findViewById(R.id.rPagePassword);
        password1 = (EditText) findViewById(R.id.rPagePassword2);
//----------------------------------------------------------------------------------------------------------------------------

        register.setOnClickListener(new View.OnClickListener()
     {
         @Override
         public void onClick(View view)
         {
            String newname = name.getText().toString();
            String newusername = username.getText().toString();
            String newemail = email.getText().toString();
            String newpassword = password.getText().toString();
            String newpassmatch = password1.getText().toString();

            if(newusername.isEmpty() || newemail.isEmpty() || newpassword.isEmpty() || !newpassmatch.equals(newpassword))
            {
                Toast.makeText(RegisterPage.this, "Fill in All Details Correctly", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //store data into database
                Contact c = new Contact();
                c.setName(newname);
                c.setUsername(newusername);
                c.setEmail(newemail);
                c.setPassword(newpassword);
                if (dbHelper != null) {
                    dbHelper.insertContact(c);
                } else {
                    Log.i("dbHelper is not created", " NOT created");
                }

                Toast.makeText(RegisterPage.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(RegisterPage.this, Questionaire.class);
                startActivity(intent3);
            }
         }
     });
//----------------------------------------------------------------------------------------------------------------------------
    }
}
