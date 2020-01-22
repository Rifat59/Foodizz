package com.example.foodizz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class AddResActivity extends AppCompatActivity {

    private EditText cateEditText, nameEditText, locEditText, latEditText, longEditText;
    private Button saveResButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_res);

        cateEditText = findViewById(R.id.category_id);
        nameEditText = findViewById(R.id.name_id);
        locEditText = findViewById(R.id.location_id);
        latEditText = findViewById(R.id.latt_id);
        longEditText = findViewById(R.id.long_id);
        saveResButton = findViewById(R.id.add_restaurent_id);

        saveResButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });
    }

    public void savedata(){
        String category = cateEditText.getText().toString().trim();
        String Name = nameEditText.getText().toString().trim();
        String Location = locEditText.getText().toString().trim();
        String Latt_S = latEditText.getText().toString().trim();
        String Long_S = longEditText.getText().toString().trim();
        String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/RestaurentList%2Ffoodizz.png?alt=media&token=0946168e-90ed-4d86-bf13-3329c7f41bbd";

        Double Latt = Double.parseDouble(Latt_S);
        Double Long = Double.parseDouble(Long_S);

        if(category.equals("Biriyani")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Category/Biriyani");
        }
        else if(category.equals("FastFood") || category.equals("Fast Food")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Category/FastFood");
        }
        else if(category.equals("ChineeseFood") || category.equals("Chineese Food")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Category/ChineeseFood");
        }
        else {
            Toast.makeText(this,"Enter a valid restaurent type!!",Toast.LENGTH_LONG).show();
        }

        if(Name.equalsIgnoreCase("")){
            nameEditText.setError("Please Enter Your Name!!");
        }
        else if(Location.equalsIgnoreCase("")){
            locEditText.setError("Please Enter Your Location!!");
        }
        else if(Latt_S.equalsIgnoreCase("")){
            latEditText.setError("Please Enter Your Lattitude!!");
        }
        else if(Long_S.equalsIgnoreCase("")){
            longEditText.setError("Please Enter Your Longitude!!");
        }
        else {
            String key = databaseReference.push().getKey();

            AddRestaurent addRestaurent = new AddRestaurent(Name, Location, url, Latt, Long);

            databaseReference.child(key).setValue(addRestaurent);
            Toast.makeText(getApplicationContext(),"Your restaurent is added successfully!!",Toast.LENGTH_LONG).show();
        }
    }
}
