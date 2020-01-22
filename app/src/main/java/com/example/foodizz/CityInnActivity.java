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

public class CityInnActivity extends AppCompatActivity implements FoodMenuAdapter.OnResClickListener {

    private static final String TAG = "CityInnActivity";
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
        setContentView(R.layout.activity_city_inn);

        homeButton = (ImageButton) findViewById(R.id.home_id);
        locbutton=findViewById(R.id.loc_id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/CityInn");
        foodMenuList = new ArrayList<>();
        foodMenuAdapter = new FoodMenuAdapter(CityInnActivity.this,foodMenuList);
        foodMenuAdapter.listener = this;

        Log.d(TAG, "foodCreate: " + foodMenuAdapter.hello);
        listView = (ListView) findViewById(R.id.cityinn_list_id);

        homeButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              Intent intent = new Intent(CityInnActivity.this , MainActivity.class);
                                              startActivity(intent);
                                          }

                                      }
        );

        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Category").child("ChineeseFood").child("2");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            lattitude = (double) dataSnapshot.child("Latt").getValue();
                            longitude = (double) dataSnapshot.child("Long").getValue();
                            name= dataSnapshot.child("Name").getValue().toString();
                            Log.d(TAG, "Result1:: " + lattitude+longitude);
                            Intent i = new Intent(CityInnActivity.this, ResLocationActivity.class);
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
            String item = "Buffet Food";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FCity%20Inn%2Fbuffet%20food%20180.png?alt=media&token=cdf4a3cb-919d-442e-ac44-4cc23d7998a0";

            Intent intent = new Intent(CityInnActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 180);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 1) {
            String item = "Buffet Platter";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FCity%20Inn%2Fbuffet%20platter%20540.png?alt=media&token=31fffbc3-0e33-49d9-bfc1-703e4dcb1c6f";

            Intent intent = new Intent(CityInnActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 540);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 2) {
            String item = "Cake";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FCity%20Inn%2Fcake%20160.png?alt=media&token=ed7f28d1-49ea-493f-90ac-7606bdbf3bce";

            Intent intent = new Intent(CityInnActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 160);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 3) {
            String item = "Coffee with Cake";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FCity%20Inn%2Fcake%2Bcoffee%20260.png?alt=media&token=b5d75681-5243-4d04-ba71-fc87e21ad2ca";

            Intent intent = new Intent(CityInnActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 260);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 4) {
            String item = "Seafood Pizza";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FCity%20Inn%2Fseafood%20pizza%20825.png?alt=media&token=e4036916-5037-424c-a7ff-2bd30540b3b0";

            Intent intent = new Intent(CityInnActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 825);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else {
            String item = "Spaghetti Meatball";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FCity%20Inn%2Fspaghetti%20meatball%20350.png?alt=media&token=410bf8e2-300a-4d23-8f88-14a9d4d369e0";

            Intent intent = new Intent(CityInnActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 350);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }

    }
}
