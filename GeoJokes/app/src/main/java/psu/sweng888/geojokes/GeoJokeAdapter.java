package psu.sweng888.geojokes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GeoJokeAdapter extends RecyclerView.Adapter<GeoJokeAdapter.GeoJokeViewHolder> {

    private final GeoJokes geoJokes;
    private Context context;

    public GeoJokeAdapter(Context context) {
        this.geoJokes = new GeoJokes();
        this.context = context;
    }

    @NonNull
    @Override
    public GeoJokeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent, false);
        return new GeoJokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeoJokeViewHolder holder, int position) {
        GeoJokes.GeoJoke geoJoke = geoJokes.list.get(position);
        holder.imageView.setImageResource(geoJoke.getImageResourceId());
        holder.introTextView.setText(geoJoke.getIntro());
        holder.punchlineTextView.setText(geoJoke.getPunchline());
    }

    @Override
    public int getItemCount() {
        return geoJokes.list.size();
    }

    public static class GeoJokeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView introTextView;
        TextView punchlineTextView;

        public GeoJokeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.geo_joke_image);
            introTextView = itemView.findViewById(R.id.geo_joke_intro);
            punchlineTextView = itemView.findViewById(R.id.geo_joke_punchline);
        }
    }
}