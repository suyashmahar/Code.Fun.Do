package com.android.example.neighbours;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.onClick;

/**
 * Created by Suyash on 31-01-2017.
 */

public class MainActivityComplaintsAdapter extends RecyclerView.Adapter<MainActivityComplaintsAdapter.MoviesViewHolder> {
    private List<ComplaintsItem> complaint = new ArrayList<ComplaintsItem>();

    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        public TextView title, relTime, description, totalFunds;
        ProgressBar complaintsProgress;

        public ImageView image;

        public MoviesViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.complaints_card_title);
            relTime = (TextView) view.findViewById(R.id.complaints_card_rel_time);
            description = (TextView) view.findViewById(R.id.complaints_card_description);
            complaintsProgress = (ProgressBar) view.findViewById(R.id.complaints_card_progress_bar);
            totalFunds = (TextView) view.findViewById(R.id.complaints_card_total_fund);
        }
    }

    public MainActivityComplaintsItemAdapter(List<ComplaintsItem> complaint){
        this.complaint = complaint;
    }


    public MainActivityComplaintsItemAdapter(){
        this.complaint = complaint;
    }

    public void setList(List<ComplaintsItem> complaint){
        this.complaint = complaint;
    }
    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complaints_card, parent, false);
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
        ComplaintsItem complaints = complaint.get(position);
        holder.title.setText(complaints.getTitle());
        holder.description.setText(complaints.getDescription());
        holder.relTime.setText(complaints.getTime());
        holder.totalFunds.setText(complaints.getTotalFunds());
        //holder.image.getImage(complaints.getDescription());
    }

    @Override
    public int getItemCount(){
        return complaint.size();
    }

}
