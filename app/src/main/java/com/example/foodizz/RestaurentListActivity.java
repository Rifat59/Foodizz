package com.example.foodizz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class RestaurentListActivity extends AppCompatActivity implements RestaurentAdapter.OnResClickListener {

    private static final String TAG = "RestaurentListActivity";

    ListView listView;
    DatabaseReference databaseReference;
    private List<Restaurent> restaurentList;
    private RestaurentAdapter restaurentAdapter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurent_list);

//        ProgressDialog dialog = ProgressDialog.show(RestaurentListActivity.this, "",
//                "Loading. Please wait...", true);

        databaseReference = FirebaseDatabase.getInstance().getReference("Category/FastFood");
        restaurentList = new ArrayList<>();
        restaurentAdapter = new RestaurentAdapter(RestaurentListActivity.this,restaurentList);
        restaurentAdapter.listener = this;

        Log.d(TAG, "onCreate: " + restaurentAdapter.hello);
        listView = (ListView) findViewById(R.id.res_list_id);

    }

    @Override
    protected void onStart() {
        //dialog.dismiss();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: " + dataSnapshot.toString());
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
            String str = "Top In Town";
            Intent intent = new Intent(RestaurentListActivity.this, TopInTownActivity.class);
            intent.putExtra("resname_key_1",str);
            startActivity(intent);
        }
        else if(position == 1){
            String str = "Rovers Cafe";
            Intent intent = new Intent(RestaurentListActivity.this, RoversCafeActivity.class);
            intent.putExtra("resname_key_1",str);
            startActivity(intent);
        }
        else {
            String str = "Cafe Jujuba";
            Intent intent = new Intent(RestaurentListActivity.this, JujubaActivity.class);
            intent.putExtra("resname_key_1",str);
            startActivity(intent);
        }

    }
}
