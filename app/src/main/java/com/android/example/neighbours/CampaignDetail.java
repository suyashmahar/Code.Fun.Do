// CampaignImage,CampaignName,CampaignHearts,CampaignComments,CampaignTime,CampaignDescription

package com.android.example.neighbours;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static com.android.example.neighbours.ImageTo64BitString.convertToBase64;

public class CampaignDetail extends AppCompatActivity {

ImageView campaignDetailImage;
TextView campaignDetailEventName,campaignDetailFund,campaignDetailTime,campaignDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);

        campaignDetailImage=(ImageView)findViewById(R.id.activity_campaign_detail_cover_image);
        campaignDetailEventName=(TextView)findViewById(R.id.campaign_detail_event_name);
        campaignDetailFund=(TextView)findViewById(R.id.campaign_detail_fund);
        campaignDetailTime=(TextView)findViewById(R.id.campaign_detail_rel_time);
        campaignDetailDescription=(TextView)findViewById(R.id.campaign_detail_description);

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            String image=extras.getString("CampaignImage");
            String campaignName=extras.getString("CampaignName");
            String campaignFund=extras.getString("CampaignFund");
            String campaignTime=extras.getString("CampaignTime");
            String campaignDescription=extras.getString("CampaignDescription");
            String encodedImage;
            try{
                encodedImage=convertToBase64(image);
                campaignDetailImage.setImageResource(Integer.parseInt(encodedImage));
            }
            catch (Exception e){

            }

            campaignDetailEventName.setText(campaignName);
            campaignDetailFund.setText(campaignFund);
            campaignDetailTime.setText(campaignTime);
            campaignDetailDescription.setText(campaignDescription);

        }
    }
}
