package com.android.example.neighbours;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.onClick;

/**
 * Created by Suyash on 31-01-2017.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MoviesViewHolder> {
    private List<Events> events = new ArrayList<Events>();
    private ClickListener clickListener=null;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        public TextView title, relTime, description, commentsCount, hearts;
        public ImageView image;
        public RelativeLayout main;

        public MoviesViewHolder(View view){
            super(view);
            main=(RelativeLayout)view.findViewById(R.id.event_tile_view);
            title = (TextView) view.findViewById(R.id.event_tile_title);
            relTime = (TextView) view.findViewById(R.id.event_tile_rel_time);
            description = (TextView) view.findViewById(R.id.event_tile_description);
            commentsCount = (TextView) view.findViewById(R.id.event_tile_comments_count);
            hearts = (TextView) view.findViewById(R.id.event_tile_hearts);

            main.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (clickListener != null) {
                                                clickListener.itemClicked(v, getAdapterPosition());
                                            }
                                        }
                                    }
            );
        }
    }

    public EventListAdapter(List<Events> events){
        this.events = events;
    }


    public EventListAdapter(){
        this.events = events;
    }

    public void setList(List<Events> events){
        this.events = events;
    }
    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_tile, parent, false);
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
        Events event = events.get(position);
        holder.title.setText(event.getTitle());
        holder.description.setText(event.getDescription());
        holder.hearts.setText(event.getVotes());
        holder.commentsCount.setText("100");
        //holder.image.getImage(event.getDescription());
    }

    @Override
    public int getItemCount(){
        return events.size();
    }

}
