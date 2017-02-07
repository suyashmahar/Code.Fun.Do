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

public class MainActivityCampaignAdapter extends RecyclerView.Adapter<MainActivityCampaignAdapter.MoviesViewHolder> {
    private List<Campaign> campaigns = new ArrayList<Campaign>();
    private ClickListener clickListener = null;
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public class MoviesViewHolder extends RecyclerView.ViewHolder{
        public TextView title, relTime, description, totalFunds;
        ProgressBar campaignProgress;

        public ImageView image;
public RelativeLayout relativeLayout;
        public MoviesViewHolder(View view){
            super(view);
            relativeLayout=(RelativeLayout)view.findViewById(R.id.campaign_card_layout);
            title = (TextView) view.findViewById(R.id.campaign_card_title);
            relTime = (TextView) view.findViewById(R.id.campaign_card_rel_time);
            description = (TextView) view.findViewById(R.id.campaign_card_description);
            totalFunds = (TextView) view.findViewById(R.id.campaign_card_total_fund);
            campaignProgress = (ProgressBar) view.findViewById(R.id.campaign_card_progress_bar);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        clickListener.itemClicked(v, getAdapterPosition());
                    }
                }
            });
        }
    }

    public MainActivityCampaignAdapter(List<Campaign> campaigns){
        this.campaigns = campaigns;
    }


    public MainActivityCampaignAdapter(){
        this.campaigns = campaigns;
    }

    public void setList(List<Campaign> campaigns){
        this.campaigns = campaigns;
    }
    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.campaign_card, parent, false);
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
    }

    @Override
    public int getItemCount(){
        return campaigns.size();
    }

}
