package com.example.foodizz;

import android.content.Intent;
import android.os.Bundle;
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

public class ChineseActivity extends AppCompatActivity implements RestaurentAdapter.OnResClickListener{

    ListView listView;
    DatabaseReference databaseReference;
    private List<Restaurent> restaurentList;
    private RestaurentAdapter restaurentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese);

        databaseReference = FirebaseDatabase.getInstance().getReference("Category/ChineeseFood");
        restaurentList = new ArrayList<>();
        restaurentAdapter = new RestaurentAdapter(ChineseActivity.this,restaurentList);
        restaurentAdapter.listener = this;

        //Log.d(TAG, "onCreate: " + restaurentAdapter.hello);
        listView = (ListView) findViewById(R.id.chinese_res_list_id);
    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.d(TAG, "onDataChange: " + dataSnapshot.toString());
                restaurentList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Restaurent restaurent = dataSnapshot1.getValue(Restaurent.class);
                    restaurentList.add(restaurent);
                }
                listView.setAdapter(restaurentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }

    @Override
    public void onResClick(Restaurent restaurent, int position) {
        if(position == 0){
            String str = "Tiger Garden Int";
            Intent intent = new Intent(ChineseActivity.this, TigerGardenActivity.class);
            intent.putExtra("resname_key_1",str);
            startActivity(intent);
        }
        else if(position == 1){
            String str = "City Inn";
            Intent intent = new Intent(ChineseActivity.this, CityInnActivity.class);
            intent.putExtra("resname_key_1",str);
            startActivity(intent);
        }
        else {
            String str = "Mughal's Dyne";
            Intent intent = new Intent(ChineseActivity.this, MughalsDineActivity.class);
            intent.putExtra("resname_key_1",str);
            startActivity(intent);
        }
        //intent.putExtra("myLatLng", restaurent);
        //setResult(-10, intent);
        //finish();
    }
}
