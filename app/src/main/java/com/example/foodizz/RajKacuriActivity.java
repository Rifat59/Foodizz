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

public class RajKacuriActivity extends AppCompatActivity implements FoodMenuAdapter.OnResClickListener {

    private static final String TAG = "RajKacuriActivity";

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
        setContentView(R.layout.activity_raj_kacuri);

        homeButton = (ImageButton) findViewById(R.id.home_id);
        locbutton= findViewById(R.id.loc_id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/RajKacuri");
        foodMenuList = new ArrayList<>();
        foodMenuAdapter = new FoodMenuAdapter(RajKacuriActivity.this,foodMenuList);
        foodMenuAdapter.listener = this;

        Log.d(TAG, "foodCreate: " + foodMenuAdapter.hello);
        listView = (ListView) findViewById(R.id.rajkacuri_list_id);

        homeButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              Intent intent = new Intent(RajKacuriActivity.this , MainActivity.class);
                                              startActivity(intent);
                                          }

                                      }
        );



        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Category").child("Biriyani").child("3");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            lattitude = (double) dataSnapshot.child("Latt").getValue();
                            longitude = (double) dataSnapshot.child("Long").getValue();
                            name= dataSnapshot.child("Name").getValue().toString();
                            Log.d(TAG, "Result1:: " + lattitude+longitude);
                            Intent i = new Intent(RajKacuriActivity.this, ResLocationActivity.class);
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
            String item = "Chicken Biriyani";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FRaj%20Kacuri%2Fchicken%20biriyani%20150.png?alt=media&token=4558a247-2235-46fd-ac56-e8c9841a293d";

            Intent intent = new Intent(RajKacuriActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 150);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else {
            String item = "Hyderabadi Biriyani";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FRaj%20Kacuri%2Fhydrabadi%20230.png?alt=media&token=e964b060-0ca6-4ccf-86fe-cababa8e7ffb";

            Intent intent = new Intent(RajKacuriActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 230);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }

    }
}
