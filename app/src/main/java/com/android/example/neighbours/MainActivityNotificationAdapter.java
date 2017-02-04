package com.android.example.neighbours;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suyash on 31-01-2017.
 */

public class MainActivityNotificationAdapter extends RecyclerView.Adapter<MainActivityNotificationAdapter.MoviesViewHolder> {
    private List<NotificationItem> notifications = new ArrayList<NotificationItem>();

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        public TextView title, relTime, description, totalFunds;
        ProgressBar notificationProgress;

        public ImageView image;

        public MoviesViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.notification_card_title);
            relTime = (TextView) view.findViewById(R.id.notification_card_date);
            description = (TextView) view.findViewById(R.id.notification_card_description);
        }
    }

    public MainActivityNotificationAdapter(List<NotificationItem> notifications){
        this.notifications = notifications;
    }


    public MainActivityNotificationAdapter(){
        this.notifications = notifications;
    }

    public void setList(List<NotificationItem> notifications){
        this.notifications = notifications;
    }
    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_card, parent, false);
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
