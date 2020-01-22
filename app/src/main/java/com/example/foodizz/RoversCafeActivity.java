package com.example.foodizz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RoversCafeActivity extends AppCompatActivity implements FoodMenuAdapter.OnResClickListener {

    private static final String TAG = "RoversCafeActivity";

    private ImageButton homeButton;
    private ImageButton locbutton;
    private double lattitude;
    private double longitude;
    private String name;

    ListView listView;
    DatabaseReference databaseReference;
    private List<FoodMenu> foodMenuList;
    private FoodMenuAdapter foodMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rovers_cafe);

        homeButton = (ImageButton) findViewById(R.id.home_id);
        locbutton=findViewById(R.id.home_id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/Rovers");
        foodMenuList = new ArrayList<>();
        foodMenuAdapter = new FoodMenuAdapter(RoversCafeActivity.this,foodMenuList);
        foodMenuAdapter.listener = this;

        Log.d(TAG, "foodCreate: " + foodMenuAdapter.hello);
        listView = (ListView) findViewById(R.id.rovers_list_id);

        homeButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              Intent intent = new Intent(RoversCafeActivity.this , MainActivity.class);
                                              startActivity(intent);
                                          }

                                      }
        );

        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Category").child("FastFood").child("2");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            lattitude = (double) dataSnapshot.child("Latt").getValue();
                            longitude = (double) dataSnapshot.child("Long").getValue();
                            name= dataSnapshot.child("Name").getValue().toString();
                            Log.d(TAG, "Result1:: " + lattitude+longitude);
                            Intent i = new Intent(RoversCafeActivity.this, ResLocationActivity.class);
                            i.putExtra("latt", lattitude);
                            i.putExtra("long", longitude);
                            i.putExtra("Name",name);

                            startActivity(i);
                        }

                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }
        });
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "FoodMenu: " + dataSnapshot.toString());
                foodMenuList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    FoodMenu foodMenu = dataSnapshot1.getValue(FoodMenu.class);
                    foodMenuList.add(foodMenu);
                }
                listView.setAdapter(foodMenuAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }

    @Override
    public void onResClick(FoodMenu foodMenu, int position) {

        Intent get_intent = getIntent();
        String tmp_resname = get_intent.getExtras().getString("resname_key_1");

        if(position == 0){
            String item = "Chicken Steak Meal";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FRovers%2Fsteak_meal290.jpg?alt=media&token=c0da4815-390f-410e-a5aa-22e512432942";

            Intent intent = new Intent(RoversCafeActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 270);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 1) {
            String item = "Chicken Cheese Burger";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FRovers%2Fchiken_chese150.jpg?alt=media&token=c72418d7-c5e9-49f1-9799-3926ff99b3e9";

            Intent intent = new Intent(RoversCafeActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 230);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 2) {
            String item = "Rovers Burger Combo";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FRovers%2Fcombo230.jpg?alt=media&token=81f142a1-86ba-444b-a095-7d179eadadea";

            Intent intent = new Intent(RoversCafeActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 230);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 3) {
            String item = "Mashroom Caramel Chicken/Beef Burger";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FRovers%2Fsteak_meal290.jpg?alt=media&token=c0da4815-390f-410e-a5aa-22e512432942";

            Intent intent = new Intent(RoversCafeActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 270);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else {
            String item = "BBQ Chiken Cheese Delight";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FRovers%2Fbbq270.jpg?alt=media&token=81d7171d-f821-41b4-922d-75710913386f";

            Intent intent = new Intent(RoversCafeActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 270);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }

    }
}
