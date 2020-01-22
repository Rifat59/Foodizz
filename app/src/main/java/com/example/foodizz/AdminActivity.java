package com.example.foodizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addRes, deleteRes, addItem, deleteItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addRes = (Button) findViewById(R.id.add_res_id);
        deleteRes = (Button) findViewById(R.id.delete_res_id);
        addItem = (Button) findViewById(R.id.add_item_id);
        deleteItem = (Button) findViewById(R.id.delete_item_id);

        addRes.setOnClickListener((View.OnClickListener) this);
        deleteRes.setOnClickListener((View.OnClickListener) this);
        addItem.setOnClickListener((View.OnClickListener) this);
        deleteItem.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_res_id){
            Intent intent = new Intent(AdminActivity.this, AddResActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.delete_res_id){

        }
        else if(v.getId() == R.id.add_item_id){
            Intent intent = new Intent(AdminActivity.this, AddItemActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.delete_item_id){

        }
    }


}
