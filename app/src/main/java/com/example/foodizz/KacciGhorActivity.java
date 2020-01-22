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

public class KacciGhorActivity extends AppCompatActivity implements FoodMenuAdapter.OnResClickListener {

    private static final String TAG = "KacciGhorActivity";

    private ImageButton homeButton;
    private ImageButton locbutton;
    private  double lattitude;
    private  double longitude;
    private String name;

    ListView listView;
    DatabaseReference databaseReference;
    private List<FoodMenu> foodMenuList;
    private FoodMenuAdapter foodMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kacci_ghor);

        homeButton = (ImageButton) findViewById(R.id.home_id);
        locbutton = findViewById(R.id.loc_id);


        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/KacchiGhor");
        foodMenuList = new ArrayList<>();
        foodMenuAdapter = new FoodMenuAdapter(KacciGhorActivity.this,foodMenuList);
        foodMenuAdapter.listener = this;

        Log.d(TAG, "foodCreate: " + foodMenuAdapter.hello);
        listView = (ListView) findViewById(R.id.kacci_list_id);

        homeButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              Intent intent = new Intent(KacciGhorActivity.this , MainActivity.class);
                                              startActivity(intent);
                                          }

                                      }
        );

        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Category").child("Biriyani").child("1");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            lattitude = (double) dataSnapshot.child("Latt").getValue();
                            longitude = (double) dataSnapshot.child("Long").getValue();
                            name= dataSnapshot.child("Name").getValue().toString();
                            Log.d(TAG, "Result1:: " + lattitude+longitude);
                            Intent i = new Intent(KacciGhorActivity.this, ResLocationActivity.class);
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

        if(position == 0) {
            String item = "Kacchi";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FKacchi%20Ghor%2Fkacchi%20145.png?alt=media&token=3a683208-5b5a-481e-8f11-694b8060db9e";

            Intent intent = new Intent(KacciGhorActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 145);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);

        }
        else if(position == 1) {
            String item = "Morog Polao";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FKacchi%20Ghor%2Fmorog%20polao%20120.png?alt=media&token=7c8a42ea-686c-49f6-b3ce-9f69e413f8ea";

            Intent intent = new Intent(KacciGhorActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 120);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else {
            String item = "Biriyani Full Menu";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FKacchi%20Ghor%2Fbiriyani%20full%20menu%20230.png?alt=media&token=469f40ce-9438-4751-aa14-12a930a92bfc";

            Intent intent = new Intent(KacciGhorActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 230);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }

    }
}
