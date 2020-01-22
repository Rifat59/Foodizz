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

public class TopInTownActivity extends AppCompatActivity implements FoodMenuAdapter.OnResClickListener {

    private static final String TAG = "TopInTownActivity";

    private ImageButton homeButton;
    private ImageButton locbutton;
    private double longitude;
    private double lattitude;
    private String name;

    ListView listView;
    DatabaseReference databaseReference;
    private List<FoodMenu> foodMenuList;
    private FoodMenuAdapter foodMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_in_town);

        homeButton = (ImageButton) findViewById(R.id.home_id);
        locbutton=findViewById(R.id.loc_id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/TopInTown");
        foodMenuList = new ArrayList<>();
        foodMenuAdapter = new FoodMenuAdapter(TopInTownActivity.this,foodMenuList);
        foodMenuAdapter.listener = this;

        Log.d(TAG, "foodCreate: " + foodMenuAdapter.hello);
        listView = (ListView) findViewById(R.id.topintown_list_id);

        homeButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              Intent intent = new Intent(TopInTownActivity.this , MainActivity.class);
                                              startActivity(intent);
                                          }

                                      }
        );

        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Category").child("FastFood").child("1");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            lattitude = (double) dataSnapshot.child("Latt").getValue();
                            longitude = (double) dataSnapshot.child("Long").getValue();
                            name= dataSnapshot.child("Name").getValue().toString();
                            Log.d(TAG, "Result1:: " + lattitude+longitude);
                            Intent i = new Intent(TopInTownActivity.this, ResLocationActivity.class);
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
            String item = "Takee Bole Dio 2 Plate 290 Tk";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTop%20In%20Town%2Ftakee%20ble%20dio%202%20plt%20290.png?alt=media&token=51bffc9e-c2c5-4c06-a38b-21c041dbcbe4";

            Intent intent = new Intent(TopInTownActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 290);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 1) {
            String item = "Offer Plate 10";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTop%20In%20Town%2Foffer%20plate%2010%20270.png?alt=media&token=665e76a0-9f9e-459e-aa6a-ca32b09c1d85";

            Intent intent = new Intent(TopInTownActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 270);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 2) {
            String item = "Spicy Rice Bowl";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTop%20In%20Town%2Fspicy%20rice%20bowl%2099.png?alt=media&token=a9a5b7c8-2ad7-4593-9dcd-93acb01e6615";

            Intent intent = new Intent(TopInTownActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 99);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 3) {
            String item = "Spicy Sub Sandwitch";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTop%20In%20Town%2Fspicy%20sub%20sandwich%20110.png?alt=media&token=f15e3bd2-fea7-42bf-acb8-37d3138ce3f8";

            Intent intent = new Intent(TopInTownActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 110);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 4) {
            String item = "Little Burger";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTop%20In%20Town%2Flittle%20burger%2070.png?alt=media&token=16f7075e-1291-4a0a-ae78-0c327c6cd8b5";

            Intent intent = new Intent(TopInTownActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 70);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else {
            String item = "Tarpor";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTop%20In%20Town%2Ftarpor%20199.png?alt=media&token=40902bea-0600-4529-838a-b71e332f457d";

            Intent intent = new Intent(TopInTownActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 199);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }

    }
}
