package com.lijo.nasapictures.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lijo.nasapictures.R;
import com.lijo.nasapictures.adapter.DetailsAdapater;
import com.lijo.nasapictures.helper.SharedPrefManager;
import com.lijo.nasapictures.model.ImageResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PictureDetailActivity extends AppCompatActivity {

    private RecyclerView rv_images;
    private ArrayList<ImageResponse> imageResponses;
    DetailsAdapater imageAdapater;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        getSupportActionBar().setTitle("Details");

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        rv_images = findViewById(R.id.rv_details);
        imageResponses = new ArrayList<>();
        imageAdapater = new DetailsAdapater(this, imageResponses);
        rv_images.setAdapter(imageAdapater);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv_images);

        Gson gson = new Gson();
        String data = SharedPrefManager.getInstance(this).getKeyData();
        if (data.isEmpty()) {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show();
        } else {
            /*Type type = new TypeToken<List<String>>() {
            }.getType();
            List<String> arrData = gson.fromJson(json, type);
            */

            // The above method is not working because of the invlid json format
            // Exception found
            try {

                //Get the instance of JSONArray that contains JSONObjects
                JSONArray jsonArray = new JSONArray(data);

                //Iterate the jsonArray and print the info of JSONObjects
                for(int i=0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String copyright = jsonObject.optString("copyright");
                    String date = jsonObject.optString("date");
                    String explanation = jsonObject.optString("explanation");
                    String hdurl = jsonObject.optString("hdurl");
                    String media_type = jsonObject.optString("media_type");
                    String service_version = jsonObject.optString("service_version");
                    String title = jsonObject.optString("title");
                    String url = jsonObject.optString("url");

                    ImageResponse imageResponse = new ImageResponse(copyright, date, explanation, hdurl, media_type, service_version, title, url);
                    imageResponses.add(imageResponse);
                }
                imageAdapater.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        rv_images.getLayoutManager().scrollToPosition(position);
    }
}
