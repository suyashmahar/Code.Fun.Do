package com.android.example.neighbours;
/**
 * Created by Suyash on 31-01-2017.
 */
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.onClick;

public class MainActivityComplaintsAdapter extends RecyclerView.Adapter<MainActivityComplaintsAdapter.MoviesViewHolder> {
    private List<ComplaintsItem> complaints = new ArrayList<ComplaintsItem>();

    private ClickListener clickListener=null;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        public TextView title, relTime, description, byUser;
        public RelativeLayout main;
        public ImageView image;

        public MoviesViewHolder(View view){
            super(view);
            main=(RelativeLayout)view.findViewById(R.id.complaint_card_layout);
            title = (TextView) view.findViewById(R.id.complaint_card_title);
            relTime = (TextView) view.findViewById(R.id.complaint_card_date);
            description = (TextView) view.findViewById(R.id.complaint_card_description);
            byUser = (TextView) view.findViewById(R.id.complaint_card_sender);
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

    public MainActivityComplaintsAdapter(List<ComplaintsItem> complaint){
        this.complaints = complaint;
    }


    public void setList(List<ComplaintsItem> complaint){
        this.complaints = complaint;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaint_card, parent, false);

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
        ComplaintsItem complaint = complaints.get(position);
        holder.title.setText(complaint.getTitle());
        holder.description.setText(complaint.getDescription());
        holder.relTime.setText(complaint.getTime());
        holder.byUser.setText(complaint.getByUser());
    }

    @Override
    public int getItemCount(){
        return complaints.size();
    }

}
