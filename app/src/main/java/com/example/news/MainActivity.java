package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Article> article;
    private CustomAdapter adaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        article = fetchdata();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                        CustomTabsIntent customTabsIntent = builder.build();
                        customTabsIntent.launchUrl(MainActivity.this, Uri.parse(article.get(position).url));
                        // do whatever
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        adaptor = new CustomAdapter(article);
        recyclerView.setAdapter(adaptor);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    private ArrayList<Article> fetchdata(){
        ArrayList<Article> sample = new ArrayList<>();
        String url = "https://newsaapi.herokuapp.com/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {



                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jarray = response.getJSONArray("articles");
                            ArrayList<Article> inside = new ArrayList<>();
                            for(int i = 0; i<jarray.length(); i++){
                                JSONObject obj = jarray.getJSONObject(i);
                                Article news = new Article(
                                  ""+obj.getString("title"),""+obj.getString("author"),""+obj.getString("urlToImage"),""+obj.getString("url")
                                ,""+obj.getString("url"));
                                inside.add(news);
                                Log.d("image-Link-tag", "onResponse: "+inside.get(0).imagelink);
                            }
                            adaptor.updatenews(inside);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, error -> {
                    // TODO: Handle error
                });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        return sample;
    }
}



//        article = new Article[];
