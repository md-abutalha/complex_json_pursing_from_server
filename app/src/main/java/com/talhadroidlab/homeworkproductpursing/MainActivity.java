package com.talhadroidlab.homeworkproductpursing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    GridView gridViewItem;
    ProgressBar progressBar;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewItem = findViewById(R.id.gridViewItem);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);
        complexJsonParsing();//for the json persing



    }







    public void complexJsonParsing() {
        String url = "https://dummyjson.com/products";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);

                try {
                    JSONArray jsonArray = response.getJSONArray("products");

                    for (int x=0; x<jsonArray.length(); x++){
                        JSONObject jsonObject = jsonArray.getJSONObject(x);

                        int id = jsonObject.getInt("id");
                        double rating = jsonObject.getDouble("rating");
                        int stock = jsonObject.getInt("stock");
                        String brand =jsonObject.getString("brand");
                        String category = jsonObject.getString("category");
                        String thumbnail = jsonObject.getString("thumbnail");

                        //dashboaed--------------------------------------
                        String title = jsonObject.getString("title");
                        String description = jsonObject.getString("description");
                        String price = jsonObject.getString("price");
                        String discountPercentage = jsonObject.getString("discountPercentage");

                        HashMap<String,String> hashMap = new HashMap<>();
                        hashMap.put("title", title);
                        hashMap.put("description", description);
                        hashMap.put("price", price);
                        hashMap.put("discountPercentage", discountPercentage);
                        //dashboard-------------------------------

                        hashMap.put("id", String.valueOf(id));
                        hashMap.put("rating", String.valueOf(rating));
                        hashMap.put("stock", String.valueOf(stock));
                        hashMap.put("brand",brand);
                        hashMap.put("category",category);
                        hashMap.put("thumbnail",thumbnail);
                        arrayList.add(hashMap);
                    }

                    MyAdapter myAdapter = new MyAdapter();
                    gridViewItem.setAdapter(myAdapter);
                } catch (JSONException e) {
                    progressBar.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                error.printStackTrace();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.item, null);

            ImageView imageViewProduct = myView.findViewById(R.id.imageViewProduct);
            TextView textViewTitle = myView.findViewById(R.id.textViewTitle);
            TextView textViewDescription = myView.findViewById(R.id.textViewDescription);
            TextView textViewPrice = myView.findViewById(R.id.textViewPrice);
            TextView textViewDiscount = myView.findViewById(R.id.textViewDiscount);
            LinearLayout layItem = myView.findViewById(R.id.layItem);

            HashMap<String,String> hashMap = arrayList.get(position);




            String title = hashMap.get("title");
            String description = hashMap.get("description");
            String price = hashMap.get("price");
            String discountPercentage = hashMap.get("discountPercentage");
            //dashboard-----------------------------------------------------

            String id = hashMap.get("id");
            String rating = hashMap.get("rating");


            textViewTitle.setText(title);
            textViewDescription.setText(description);
            textViewPrice.setText("price: "+price);
            textViewDiscount.setText("discount: "+discountPercentage);


            // Set click listener for the entire item
            layItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("thumbnail", hashMap.get("thumbnail"));
                    intent.putExtra("title", title);
                    intent.putExtra("description", description);
                    intent.putExtra("price", price);
                    intent.putExtra("discountPercentage", discountPercentage);
                    intent.putExtra("id", hashMap.get("id"));
                    intent.putExtra("rating", hashMap.get("rating"));
                    intent.putExtra("stock", hashMap.get("stock"));
                    intent.putExtra("brand", hashMap.get("brand"));
                    intent.putExtra("category", hashMap.get("category"));
                    startActivity(intent);
                }
            });

            return myView;
        }

    }
}
