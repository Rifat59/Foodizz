package com.example.foodizz;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class FoodMenuAdapter extends ArrayAdapter<FoodMenu>{
    private Activity context;
    private List<FoodMenu> foodMenus;
    //private static final String TAG = "foodmenuadapter";

    interface OnResClickListener {
        void onResClick(FoodMenu foodMenu, int position);
    }

    public OnResClickListener listener = null;
    public String hello = null;

    public FoodMenuAdapter( Activity context,List<FoodMenu> foodMenus) {
        super(context, R.layout.foodmenu, foodMenus);
        this.context = context;
        this.foodMenus = foodMenus;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.foodmenu,null,true);

        final FoodMenu food = foodMenus.get(position);

        //Log.d(TAG, "FoodMenuadapter: ");

        TextView t1 = view.findViewById(R.id.food_menu_name_id);
        TextView t2 = view.findViewById(R.id.food_menu_rating_id);
        TextView t3 = view.findViewById(R.id.food_menu_price_id);
        ImageView image  = view.findViewById(R.id.food_menu_image_id);

        t1.setText(food.Name);
        t2.setText(food.Rating);
        t3.setText(food.Price);
        Picasso.get()
                .load(food.url)
                .into(image);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onResClick(food, position);
                }
            }
        });

        return view;
    }
}
