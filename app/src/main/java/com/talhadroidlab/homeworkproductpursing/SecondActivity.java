package com.talhadroidlab.homeworkproductpursing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("title");
            String description = extras.getString("description");
            String price = extras.getString("price");
            String discountPercentage = extras.getString("discountPercentage");
            String id = extras.getString("id");
            String rating = extras.getString("rating");
            String stock = extras.getString("stock");
            String brand = extras.getString("brand");
            String category = extras.getString("category");
            String thumbnailUrl = extras.getString("thumbnail");

            // Update TextViews with the received data
            TextView textViewTitle = findViewById(R.id.textViewTitle);
            TextView textViewDescription = findViewById(R.id.textViewDescription);
            TextView textViewPrice = findViewById(R.id.textViewPrice);
            TextView textViewDiscount = findViewById(R.id.textViewDiscount);
            TextView textViewId = findViewById(R.id.textViewId);
            TextView textViewRating = findViewById(R.id.textViewRating);
            TextView textViewStock = findViewById(R.id.textViewStock);
            TextView textViewBrand = findViewById(R.id.textViewBrand);
            TextView textViewCategory = findViewById(R.id.textViewCategory);
            ImageView imageViewThumbnail = findViewById(R.id.imageViewThumbnail);


            textViewTitle.setText(title);
            textViewDescription.setText(description);
            textViewPrice.setText("Price: " + price);
            textViewDiscount.setText("Discount: " + discountPercentage);
            textViewId.setText("ID: " + id);
            textViewRating.setText("Rating: " + rating);
            textViewStock.setText("Stock: " + stock);
            textViewBrand.setText("Brand: " + brand);
            textViewCategory.setText("Category: " + category);


            Picasso.get().load(thumbnailUrl).into(imageViewThumbnail);

        }




    }
}