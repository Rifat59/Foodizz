package com.example.foodizz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemActivity extends AppCompatActivity {

    private EditText resEditText, nameEditText, priceEditText, ratingEditText;
    private Button saveItemButton;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        resEditText = findViewById(R.id.res_id);
        nameEditText = findViewById(R.id.food_name_id);
        priceEditText = findViewById(R.id.price_id);
        ratingEditText = findViewById(R.id.rating_id);
        saveItemButton = findViewById(R.id.add_item_id);

        saveItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
            }
        });
    }

    public void savedata(){
        String res = resEditText.getText().toString().trim();
        String Name = nameEditText.getText().toString().trim();
        String Price = priceEditText.getText().toString().trim();
        String Rating = ratingEditText.getText().toString().trim();
        String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/RestaurentList%2Ffoodizz.png?alt=media&token=0946168e-90ed-4d86-bf13-3329c7f41bbd";


        if(res.equals("Top In Town")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/TopInTown");
        }
        else if(res.equals("City Inn")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/CityInn");
        }
        else if(res.equals("Tiger Garden")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/TigerGarden");
        }
        else if(res.equals("Cafe Jujuba")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/Jujuba");
        }
        else if(res.equals("Mughals Dine")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/MughalsDine");
        }
        else if(res.equals("Raj Kacuri")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/RajKacuri");
        }
        else if(res.equals("Kacchi Ghor")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/KacchiGhor");
        }
        else if(res.equals("Bejerdanga")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/Bejerdanga");
        }
        else if(res.equals("Rovers Cafe")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/Rovers");
        }
        else if(res.equals("Mega")){
            databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/Mega");
        }
        else {
            Toast.makeText(this,"Enter a valid restaurent type!!",Toast.LENGTH_LONG).show();
        }

        if(res.equalsIgnoreCase("")){
            resEditText.setError("Please Enter Restaurents Name!!");
        }
        else if(Name.equalsIgnoreCase("")){
            nameEditText.setError("Please Enter Food Item!!");
        }
        else if(Price.equalsIgnoreCase("")){
            priceEditText.setError("Please Enter Price!!");
        }
        else if(Rating.equalsIgnoreCase("")){
            ratingEditText.setError("Please Enter Rating!!");
        }
        else {
            String key = databaseReference.push().getKey();

            AddItem addItem = new AddItem(Name, Price, Rating, url);

            databaseReference.child(key).setValue(addItem);
            Toast.makeText(getApplicationContext(),"Your item is added successfully!!",Toast.LENGTH_LONG).show();
        }
    }
}
