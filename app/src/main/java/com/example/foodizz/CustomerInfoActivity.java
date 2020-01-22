package com.example.foodizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerInfoActivity extends AppCompatActivity {

    private EditText nameEditText, addressEditText, phoneNoEditText;
    private  Button saveDataButton, cancelButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        databaseReference = FirebaseDatabase.getInstance().getReference("OrderInfo");

        nameEditText = findViewById(R.id.name_edit_text_id);
        addressEditText = findViewById(R.id.address_edit_text_id);
        phoneNoEditText = findViewById(R.id.phone_edit_text_id);
        saveDataButton = findViewById(R.id.button_id);
        cancelButton = findViewById(R.id.cancel_button_id);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerInfoActivity.this, RestaurentTypeActivity.class);
                startActivity(intent);
            }
        });

        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });
    }

    public void savedata(){
        String name = nameEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();
        String phoneNo = phoneNoEditText.getText().toString().trim();

        Intent get_intent = getIntent();
        String resname = get_intent.getExtras().getString("resname_key_3");
        String itemname = get_intent.getExtras().getString("item_key_2");
        int quantity = get_intent.getIntExtra("quantity_key", 0);

        if(name.equalsIgnoreCase("")){
            nameEditText.setError("Please Enter Your Name!!");
        }
        else if(address.equalsIgnoreCase("")){
            addressEditText.setError("Please Enter Your Adress!!");
        }
        else if(phoneNo.equalsIgnoreCase("")){
            phoneNoEditText.setError("Please Enter Your Phone Number!!");
        }
        else {
            String key = databaseReference.push().getKey();

            CustomerInfo customerInfo = new CustomerInfo(name, address, phoneNo, resname, itemname, quantity);

            databaseReference.child(key).setValue(customerInfo);
            Toast.makeText(getApplicationContext(),"Your order is added successfully!!",Toast.LENGTH_LONG).show();
        }


    }

}
