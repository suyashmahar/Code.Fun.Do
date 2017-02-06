package com.android.example.neighbours;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.fillAfter;
import static android.R.attr.onClick;

/**
 * Created by Suyash on 31-01-2017.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.MoviesViewHolder> {
    private List<Contact> contacts = new ArrayList<Contact>();

    private ClickListener clickListener = null;
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        public TextView name , address ,bloodGroup;

        public ImageView image;

        public MoviesViewHolder(View view) {
            super(view);
            LinearLayout main = (LinearLayout) view.findViewById(R.id.contact_tile_layout);
            name = (TextView) view.findViewById(R.id.contact_name);
            address = (TextView) view.findViewById(R.id.contact_address);
            bloodGroup = (TextView) view.findViewById(R.id.contact_blood_group);
            image=(ImageView)view.findViewById(R.id.contact_tile_image);
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

    public ContactListAdapter(List<Contact> campaigns){
        this.contacts = campaigns;
    }


    public ContactListAdapter(){
        this.contacts = contacts;
    }

    public void setList(List<Contact> contacts){
        this.contacts = contacts;
    }
    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_card, parent, false);
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
        Contact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.address.setText(contact.getAddress());
        holder.bloodGroup.setText(contact.getBloodGroup());

        //holder.image.getImage(campaign.getDescription());
    }

    @Override
    public int getItemCount(){
        return contacts.size();
    }

}
