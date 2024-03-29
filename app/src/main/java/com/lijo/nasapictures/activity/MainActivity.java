package com.lijo.nasapictures.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.SharedMemory;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.lijo.nasapictures.R;
import com.lijo.nasapictures.adapter.ImageAdapater;
import com.lijo.nasapictures.helper.SharedPrefManager;
import com.lijo.nasapictures.model.ImageResponse;
import com.lijo.nasapictures.model.NasaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_images;
    private ArrayList<ImageResponse> imageResponses;
    ImageAdapater imageAdapater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //
        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        //creating an array of type string
        String[] years = {"2010","2018","2019","2020"};
        //creating an array adapter
        ArrayAdapter<CharSequence> customAdapter = new ArrayAdapter<CharSequence>(this, R.layout.layout_spinner_text, years );
        customAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        //binding spinner to customeadapter
        spinner.setAdapter(customAdapter);




        rv_images = findViewById(R.id.rv_image_grid);
        imageResponses = new ArrayList<>();
        imageAdapater = new ImageAdapater(this, imageResponses);
        rv_images.setAdapter(imageAdapater);

        String data = "";
        try {

            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = new JSONArray(NasaData.getNasaJson());

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
            saveData(imageResponses);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void saveData(List data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        SharedPrefManager.getInstance(this).setData(json);
    }
}
