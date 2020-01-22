package com.example.foodizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editText1, editText2;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText1 = (EditText) findViewById(R.id.username_id);
        editText2 = (EditText) findViewById(R.id.password_id);
        button = (Button) findViewById(R.id.login_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText1.getText().toString();
                String password = editText2.getText().toString();

                if(username.equalsIgnoreCase("")){
                    editText1.setError("Please Enter Your Adress!!");
                }
                else if(password.equalsIgnoreCase("")){
                    editText2.setError("Please Enter Your Phone Number!!");
                }
                else if(username.equals("admin") && password.equals("admin")){
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
