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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ComplaintListAdapter extends RecyclerView.Adapter<ComplaintListAdapter.MoviesViewHolder>
{
    private List<ComplaintsItem> complaints = new ArrayList<ComplaintsItem>();

    private ClickListener clickListener=null;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        public TextView title, sender,time, description;
        RelativeLayout main;

        public MoviesViewHolder(View view){
            super(view);
            main=(RelativeLayout)view.findViewById(R.id.complaint_card_tile);
            title = (TextView) view.findViewById(R.id.complaint_tile_title);
            time = (TextView) view.findViewById(R.id.complaint_tile_date);
            sender=(TextView)view.findViewById(R.id.complaint_tile_sender);
            description = (TextView) view.findViewById(R.id.complaint_tile_description);
            main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.itemClicked(v, getAdapterPosition());
                    }
                }
            });
        }
    }

    public ComplaintListAdapter(List<ComplaintsItem> notifications){
        this.complaints = notifications;
    }


    public ComplaintListAdapter(){
        this.complaints= complaints;
    }

    public void setList(List<ComplaintsItem> notifications){
        this.complaints= notifications;
    }
    @Override
    public ComplaintListAdapter.MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_tile, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add on click listener here
            }
        });
        return new ComplaintListAdapter.MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ComplaintListAdapter.MoviesViewHolder holder, int position){
        ComplaintsItem complaint = complaints.get(position);
        holder.title.setText(complaint.getTitle());
        holder.sender.setText(complaint.getByUser());
        holder.description.setText(complaint.getDescription());
        holder.time.setText(complaint.getTime());
        //holder.image.getImage(notification.getDescription());
    }

    @Override
    public int getItemCount(){
        return complaints.size();
    }

}

