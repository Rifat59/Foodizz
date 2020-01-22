package com.example.foodizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurentTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ffood_button, biriyani_button, chineese_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_type);

        ffood_button = (Button) findViewById(R.id.ffood_button_id);
        biriyani_button = (Button) findViewById(R.id.biriyani_button_id);
        chineese_button = (Button) findViewById(R.id.chinese_button_id);

        ffood_button.setOnClickListener((View.OnClickListener) this);
        biriyani_button.setOnClickListener((View.OnClickListener) this);
        chineese_button.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public  void onClick(View v){
        if (v.getId() == R.id.ffood_button_id) {
            Intent intent = new Intent(RestaurentTypeActivity.this, RestaurentListActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(intent);
            //finish();
        }

        else if (v.getId() == R.id.biriyani_button_id) {
            Intent intent = new Intent(RestaurentTypeActivity.this, BiriyaniActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(intent);
            //finish();
        }

        else if (v.getId() == R.id.chinese_button_id) {
            Intent intent = new Intent(RestaurentTypeActivity.this, ChineseActivity.class);
            //intent.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
            startActivity(intent);
            //finish();
        }
    }
}
