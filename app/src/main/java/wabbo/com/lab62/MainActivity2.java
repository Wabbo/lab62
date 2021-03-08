package wabbo.com.lab62;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    TextView title , rating , releaseYear;
    MyAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.image);
        title = findViewById(R.id.title);
        rating = findViewById(R.id.rating);
        releaseYear = findViewById(R.id.releaseYear);

        Intent intent = getIntent();

        task = new MyAsyncTask(imageView, this);
        task.execute(intent.getStringExtra("imgURL"));
        title.setText("Title : " + intent.getStringExtra("title"));
        releaseYear.setText("Release Year : " + intent.getStringExtra("releaseYear"));
        rating.setText("Rating : " +intent.getStringExtra("rating"));

    }
}