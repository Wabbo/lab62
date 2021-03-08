package wabbo.com.lab62;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<MovieModel> list;
    MovieListAdapter adapter;
    Handle handle;
    private static final String MOVIES_API = "https://api.androidhive.info/json/movies.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.listView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        /*list = Arrays.asList(new MovieModel("Survey Corps", 1000, R.drawable.survey_corps_logo),
                new MovieModel("Military Police Brigade", 2000, R.drawable.brigade_logo),
                new MovieModel("Garrison", 10000, R.drawable.garrison_logo)
        );*/
        handle = new Handle();
        new Thread(() -> {
            String response = getData("GET");
            Log.i(">>", response);
            list = decodeJson(response);
            handle.sendEmptyMessage(1);
        }).start();

    }
    private class Handle extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            adapter = new MovieListAdapter(MainActivity.this, list);
            recyclerView.setAdapter(adapter);

        }
    }
    private String getData(String requestMethod ) {
        String response = null;
        try {

            URL url = new URL(MOVIES_API);

            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod(requestMethod);

            InputStream inputStream = new BufferedInputStream(httpsURLConnection.getInputStream());

            response = getJson(inputStream);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
    private String getJson(InputStream inputStream) {
        BufferedReader reader = new BufferedReader((new InputStreamReader(inputStream))) ;
        StringBuilder sb = new StringBuilder() ;
        String line ;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    private List<MovieModel> decodeJson(String json){
        List<MovieModel> movieList = new ArrayList<MovieModel>();
        try {
            JSONArray arr = new JSONArray(json);
            for(int i=0;i<arr.length();i++)
            {
                MovieModel newMovie = new MovieModel();
                JSONObject obj = arr.getJSONObject(i);
                newMovie.title = obj.getString("title");
                newMovie.imgURL = obj.getString("image");
                newMovie.rating = new Double(obj.getDouble("rating"));
                newMovie.releaseYear = new Integer(obj.getInt("releaseYear"));
                movieList.add(newMovie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }
}