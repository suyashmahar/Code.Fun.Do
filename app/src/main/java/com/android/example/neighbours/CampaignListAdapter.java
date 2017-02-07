package com.android.example.neighbours;

import android.support.v7.widget.RecyclerView;
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

/**
 * Created by Suyash on 31-01-2017.
 */

public class CampaignListAdapter extends RecyclerView.Adapter<CampaignListAdapter.MoviesViewHolder> {
    private List<Campaign> campaigns = new ArrayList<Campaign>();

    private ClickListener clickListener = null;
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        public TextView title, relTime, description, totalFunds;
        ProgressBar campaignProgress;

        public ImageView image;

        public MoviesViewHolder(View view) {
            super(view);
            RelativeLayout main = (RelativeLayout) view.findViewById(R.id.campaign_tile_layout);
            title = (TextView) view.findViewById(R.id.campaign_tile_title);
            relTime = (TextView) view.findViewById(R.id.campaign_tile_rel_time);
            description = (TextView) view.findViewById(R.id.campaign_tile_description);
            campaignProgress = (ProgressBar) view.findViewById(R.id.campaign_tile_progress_bar);
            totalFunds = (TextView) view.findViewById(R.id.campaign_tile_total_fund);
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
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.campaign_tile, parent, false);
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
        Campaign campaign = campaigns.get(position);
        holder.title.setText(campaign.getTitle());
        holder.description.setText(campaign.getDescription());
        holder.relTime.setText(campaign.getTime());
        holder.totalFunds.setText(campaign.getFunds() +  " of " + campaign.getTotalFunds());
        holder.campaignProgress.setMax(100);
        holder.campaignProgress.setProgress((int)((Integer.parseInt(campaign.getFunds())/(float)Integer.parseInt(campaign.getTotalFunds()))*100));

        //holder.image.getImage(campaign.getDescription());
    }

    @Override
    public int getItemCount(){
        return campaigns.size();
    }

}
