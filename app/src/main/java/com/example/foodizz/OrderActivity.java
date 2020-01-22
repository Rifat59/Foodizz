package com.example.foodizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText editText;
    private TextView foodtextView, vattextView, costtextView, totaltextView;
    private Button button, showButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        imageView = (ImageView) findViewById(R.id.image_view_id);
        editText = (EditText) findViewById(R.id.edit_text_id);
        foodtextView = (TextView) findViewById(R.id.food_price_id);
        vattextView = (TextView) findViewById(R.id.vat_price_id);
        costtextView = (TextView) findViewById(R.id.cost_price_id);
        totaltextView = (TextView) findViewById(R.id.total_price_id);
        button = (Button) findViewById(R.id.button_id);
        showButton = (Button) findViewById(R.id.show_button_id);

        Intent get_intent = getIntent();
        String url = get_intent.getExtras().getString("str");


        Picasso.get().load(url).into(imageView);

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = getIntent();
                int price2 = intent2.getIntExtra("price", 0);

                String string = editText.getText().toString();

                if(string.equalsIgnoreCase("")){
                    editText.setError("Please Enter The Quantity!!");
                }
                else {
                    int num = Integer.parseInt(string);

                    int ans = num * price2;
                    int vat = ans / 20;
                    int total = ans + vat + 50;

                    foodtextView.setText("Price of Food is : "+ans+" tk");
                    vattextView.setText("Vat is : "+vat+" tk");
                    costtextView.setText("Delivery Cost is : 50 tk");
                    totaltextView.setText("Total Price is : "+total+" tk");

                    showButton.setVisibility(View.INVISIBLE);
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent get_intent2 = getIntent();
                String tmp_resname_2 = get_intent2.getExtras().getString("resname_key_2");
                String tmp_item = get_intent2.getExtras().getString("item_key");

                String string = editText.getText().toString();
                int num = Integer.parseInt(string);

                Intent intent = new Intent(OrderActivity.this,CustomerInfoActivity.class);
                intent.putExtra("resname_key_3",tmp_resname_2);
                intent.putExtra("item_key_2",tmp_item);
                intent.putExtra("quantity_key",num);
                startActivity(intent);
            }
        });

    }
}
