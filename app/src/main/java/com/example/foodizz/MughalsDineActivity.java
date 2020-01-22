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

public class MughalsDineActivity extends AppCompatActivity implements FoodMenuAdapter.OnResClickListener {

    private static final String TAG = "MughalsDineActivity";

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
        setContentView(R.layout.activity_mughals_dine);

        homeButton = (ImageButton) findViewById(R.id.home_id);
        locbutton=findViewById(R.id.loc_id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/MughalsDine");
        foodMenuList = new ArrayList<>();
        foodMenuAdapter = new FoodMenuAdapter(MughalsDineActivity.this,foodMenuList);
        foodMenuAdapter.listener = this;

        Log.d(TAG, "foodCreate: " + foodMenuAdapter.hello);
        listView = (ListView) findViewById(R.id.m_dine_list_id);

        homeButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              Intent intent = new Intent(MughalsDineActivity.this , MainActivity.class);
                                              startActivity(intent);
                                          }

                                      }
        );

        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Category").child("ChineeseFood").child("3");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            lattitude = (double) dataSnapshot.child("Latt").getValue();
                            longitude = (double) dataSnapshot.child("Long").getValue();
                            name= dataSnapshot.child("Name").getValue().toString();
                            Log.d(TAG, "Result1:: " + lattitude+longitude);
                            Intent i = new Intent(MughalsDineActivity.this, ResLocationActivity.class);
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
        if(position == 0){
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FMughlas%20Dine%2Fchicken%20onion%20256.png?alt=media&token=869ec54f-3537-49a7-9355-5b52bb1075f0";
            Intent intent = new Intent(MughalsDineActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 256);
            startActivity(intent);
        }
        else if(position == 1) {
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FMughlas%20Dine%2Fegg%20chawmin%2088.png?alt=media&token=6f4d1d87-8aef-4249-9307-0dfadbfb65c3";
            Intent intent = new Intent(MughalsDineActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 88);
            startActivity(intent);
        }
        else if(position == 2) {
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FMughlas%20Dine%2Ffried%20chicken%20128.png?alt=media&token=d29eef6f-5540-4317-81a0-45d12dfabc5d";
            Intent intent = new Intent(MughalsDineActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 128);
            startActivity(intent);
        }
        else if(position == 3) {
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FMughlas%20Dine%2Fszecuan%20salad%20232.png?alt=media&token=093ad49c-5b1f-4c97-bdd8-ff6b2168e1f2";
            Intent intent = new Intent(MughalsDineActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 232);
            startActivity(intent);
        }
        else {
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FMughlas%20Dine%2Fthai%20soup%2064.png?alt=media&token=f8580c22-0f1d-48a2-a43c-b347b84d291f";
            Intent intent = new Intent(MughalsDineActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 64);
            startActivity(intent);
        }

        //intent.putExtra("myLatLng", foodMenu);
        //setResult(-10, intent);
        //finish();
    }
}
