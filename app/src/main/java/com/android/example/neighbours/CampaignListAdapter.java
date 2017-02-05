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

import static com.android.example.neighbours.R.id.view;

/**
 * Created by Harshit Bansal on 2/5/2017.
 */

public class CampaignListAdapter extends RecyclerView.Adapter<CampaignListAdapter.MoviesViewHolder> {
    private List<Campaign> campaigns = new ArrayList<Campaign>();

    public CampaignListAdapter(List<Campaign> campaigns){
        this.campaigns = campaigns;
    }


    public CampaignListAdapter(){
        this.campaigns = campaigns;
    }

    public void setList(List<Campaign> campaigns){
        this.campaigns = campaigns;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.campaign_card, parent, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add on click listener here
            }
        });
        return new CampaignListAdapter.MoviesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Campaign campaign = campaigns.get(position);
        holder.title.setText(campaign.getTitle());
        holder.description.setText(campaign.getDescription());
        holder.relTime.setText(campaign.getTime());
        holder.totalFunds.setText(campaign.getTotalFunds());
    }

    @Override
    public int getItemCount() {
        return campaigns.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        public TextView title, relTime, description, totalFunds;
        ProgressBar campaignProgress;

        public ImageView image;
        public MoviesViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.campaign_tile_title);
            relTime = (TextView) view.findViewById(R.id.campaign_tile_rel_time);
            description = (TextView) view.findViewById(R.id.campaign_tile_description);
            totalFunds = (TextView) view.findViewById(R.id.campaign_tile_total_fund);
        }
    }
}
