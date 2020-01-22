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

public class RestaurentAdapter extends ArrayAdapter<Restaurent>{
    private Activity context;
    private List<Restaurent> restaurents;

    interface OnResClickListener {
        void onResClick(Restaurent restaurent, int position);
    }

    public OnResClickListener listener = null;
    public String hello = null;

    public RestaurentAdapter( Activity context,List<Restaurent> restaurents) {
        super(context, R.layout.restaurent, restaurents);
        this.context = context;
        this.restaurents = restaurents;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.restaurent,null,true);

        final Restaurent res = restaurents.get(position);

        TextView t1 = view.findViewById(R.id.res_name_id);
        TextView t2 = view.findViewById(R.id.res_location_id);
        ImageView image  = view.findViewById(R.id.res_image_id);

        t1.setText(res.Name);
        t2.setText(res.Location);
        Picasso.get()
                .load(res.url)
                .into(image);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onResClick(res, position);
                }
            }
        });

        return view;
    }
}
