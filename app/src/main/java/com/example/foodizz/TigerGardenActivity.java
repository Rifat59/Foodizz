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

public class TigerGardenActivity extends AppCompatActivity implements FoodMenuAdapter.OnResClickListener {

    private static final String TAG = "TigerGardenActivity";

    private ImageButton homeButton;
    private  ImageButton locbutton;
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
        setContentView(R.layout.activity_tiger_garden);

        homeButton = (ImageButton) findViewById(R.id.home_id);
        locbutton=findViewById(R.id.loc_id);

        databaseReference = FirebaseDatabase.getInstance().getReference("Restaurents/TigerGarden");
        foodMenuList = new ArrayList<>();
        foodMenuAdapter = new FoodMenuAdapter(TigerGardenActivity.this,foodMenuList);
        foodMenuAdapter.listener = this;

        Log.d(TAG, "foodCreate: " + foodMenuAdapter.hello);
        listView = (ListView) findViewById(R.id.t_garden_list_id);

        homeButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                              Intent intent = new Intent(TigerGardenActivity.this , MainActivity.class);
                                              startActivity(intent);
                                          }

                                      }
        );


        locbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Category").child("ChineeseFood").child("1");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            lattitude = (double) dataSnapshot.child("Latt").getValue();
                            longitude = (double) dataSnapshot.child("Long").getValue();
                            name= dataSnapshot.child("Name").getValue().toString();
                            Log.d(TAG, "Result1:: " + lattitude+longitude);
                            Intent i = new Intent(TigerGardenActivity.this, ResLocationActivity.class);
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
            String item = "Buffet Breakfast";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTiger%20Garden%2Fbuffet%20brkfast%20129.png?alt=media&token=f2c3aecd-e121-4459-86dc-74f25139a14e";

            Intent intent = new Intent(TigerGardenActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 129);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 1) {
            String item = "Biriyani";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTiger%20Garden%2Fbiriyani%20190.png?alt=media&token=7d2bf0c6-fabd-4760-9a5c-35dfe40010b8";

            Intent intent = new Intent(TigerGardenActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 190);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 2) {
            String item = "Buy One Pizza Get Two Burger";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTiger%20Garden%2Fbuy%20one%20pizza%20get%20two%20burger%20650.png?alt=media&token=a66b4710-4b53-4a19-b80f-b9cf7c25461d";

            Intent intent = new Intent(TigerGardenActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 650);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 3) {
            String item = "Nachos";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTiger%20Garden%2Fnachos%20230.png?alt=media&token=f80566ad-df80-46e7-8610-15c69cb4e19d";

            Intent intent = new Intent(TigerGardenActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 130);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else if(position == 4) {
            String item = "Naga Platter";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTiger%20Garden%2Fnaga%20platter%20299.png?alt=media&token=0d011885-48d7-41cb-85fa-444b73af64e4";

            Intent intent = new Intent(TigerGardenActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 299);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }
        else {
            String item = "Spicy Wings";
            String url = "https://firebasestorage.googleapis.com/v0/b/restaurentlist-fe7dd.appspot.com/o/Food%20Item%2FTiger%20Garden%2Fspicy%20wings%20190.png?alt=media&token=6c152348-c0db-48e9-8e11-cbbcbcb79856";

            Intent intent = new Intent(TigerGardenActivity.this,OrderActivity.class);
            intent.putExtra("str", url);
            intent.putExtra("price", 190);
            intent.putExtra("resname_key_2",tmp_resname);
            intent.putExtra("item_key",item);
            startActivity(intent);
        }

    }
}
