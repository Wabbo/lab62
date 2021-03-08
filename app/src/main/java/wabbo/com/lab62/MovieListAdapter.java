package wabbo.com.lab62;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private final Context context;
    public List<MovieModel> models;
    MyAsyncTask task;
    private static final String TAG = "RecyclerView";

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titel, rating;
        public ImageView imageView;
        public ConstraintLayout constraintLayout;
        public View layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView;
            constraintLayout = itemView.findViewById(R.id.all);
            imageView = itemView.findViewById(R.id.image);
            titel = itemView.findViewById(R.id.title);
            rating = itemView.findViewById(R.id.rating);
        }
    }

    MovieListAdapter(Context context, List<MovieModel> models) {
        this.context = context;
        this.models = models;
    }


    @NonNull
    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.movie_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        Log.i(TAG, "onCreateViewHolder");
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titel.setText(models.get(position).getTitle());
        holder.rating.setText(models.get(position).getrating().toString());
        task = new MyAsyncTask(holder.imageView, context);
        task.execute(models.get(position).getImgURL());
        //holder.imageView.setImageResource(models.get(position).getImage());
        holder.constraintLayout.setOnClickListener(v -> {
            //Toast.makeText(context, models.get(position).getreleasYear().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context,MainActivity2.class);
            intent.putExtra("title",models.get(position).getTitle());
            intent.putExtra("releaseYear",models.get(position).getreleasYear().toString());
            intent.putExtra("rating",models.get(position).getrating().toString());
            intent.putExtra("imgURL",models.get(position).getImgURL());
            context.startActivity(intent);
        });
        Log.i(TAG, "onBindViewHolder");

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

}

