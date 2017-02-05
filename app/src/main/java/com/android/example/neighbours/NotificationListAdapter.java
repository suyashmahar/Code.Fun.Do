package com.android.example.neighbours;

/**
 * Created by Jaskirat on 05-02-2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.MoviesViewHolder>
{
    private List<NotificationItem> notifications = new ArrayList<NotificationItem>();

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        public TextView title, relTime, description, totalFunds;
        ProgressBar notificationProgress;

        public ImageView image;

        public MoviesViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.notification_tile_title);
            relTime = (TextView) view.findViewById(R.id.notification_tile_date);
            description = (TextView) view.findViewById(R.id.notification_tile_description);
        }
    }

    public NotificationListAdapter(List<NotificationItem> notifications){
        this.notifications = notifications;
    }


    public NotificationListAdapter(){
        this.notifications = notifications;
    }

    public void setList(List<NotificationItem> notifications){
        this.notifications = notifications;
    }
    @Override
    public NotificationListAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_tile, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add on click listener here
            }
        });
        return new NotificationListAdapter.MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationListAdapter.MoviesViewHolder holder, int position){
        NotificationItem notification = notifications.get(position);
        holder.title.setText(notification.getTitle());
        holder.description.setText(notification.getDescription());
        holder.relTime.setText(notification.getTime());
        //holder.image.getImage(notification.getDescription());
    }

    @Override
    public int getItemCount(){
        return notifications.size();
    }

}

