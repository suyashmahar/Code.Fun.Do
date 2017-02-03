package com.android.example.neighbours;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.onClick;

/**
 * Created by Suyash on 31-01-2017.
 */

public class MainActivityEventAdapter extends RecyclerView.Adapter<MainActivityEventAdapter.MoviesViewHolder> {
    private List<EventItem> events = new ArrayList<EventItem>();

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        public TextView title, relTime, description, commentsCount, hearts;
        public ImageView image;

        public MoviesViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.event_card_title);
            relTime = (TextView) view.findViewById(R.id.event_card_rel_time);
            description = (TextView) view.findViewById(R.id.event_card_description);
            commentsCount = (TextView) view.findViewById(R.id.event_card_comments_count);
            hearts = (TextView) view.findViewById(R.id.event_card_hearts);

        }
    }

    public MainActivityEventAdapter(List<EventItem> events){
        this.events = events;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_card, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add on click listener here
            }
        });
        return new MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position){
        EventItem event = events.get(position);
        holder.title.setText(event.getTitle());
        holder.description.setText(event.getDescription());
        holder.hearts.setText(event.getDescription());
        holder.commentsCount.setText(event.getDescription());
        //holder.image.getImage(event.getDescription());
    }

    @Override
    public int getItemCount(){
        return events.size();
    }

}
