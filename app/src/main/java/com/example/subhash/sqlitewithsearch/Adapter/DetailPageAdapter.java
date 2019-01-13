package com.example.subhash.sqlitewithsearch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.subhash.sqlitewithsearch.Activity.GetDetailPage;
import com.example.subhash.sqlitewithsearch.Model.DetailGetSet;
import com.example.subhash.sqlitewithsearch.R;

import java.util.List;

public class DetailPageAdapter extends RecyclerView.Adapter<DetailPageAdapter.EventHolder>{
    Context context;
    List<DetailGetSet> detailGetSets;


    public DetailPageAdapter(GetDetailPage getDetailPage, List<DetailGetSet> data_list) {
        this.context = getDetailPage;
        this.detailGetSets = data_list;
    }



    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_row, parent, false);

        return new EventHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder holder, final int position) {

        final DetailGetSet detailGetSet = detailGetSets.get(position);
        holder.t_name.setText(detailGetSet.getName());
        holder.t_age.setText(detailGetSet.getAge());
        holder.t_contact.setText(detailGetSet.getConatct());
        holder.t_email.setText(detailGetSet.getEmail());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetDetailPage.deleteRowItem(detailGetSet.getConatct());
                detailGetSets.remove(position);
                notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return detailGetSets.size();
    }



    public class EventHolder extends RecyclerView.ViewHolder {
        TextView t_name, t_age, t_contact, t_email,delete;
        public EventHolder(View itemView) {
            super(itemView);

            t_name = itemView.findViewById(R.id.name_text);
            t_age = itemView.findViewById(R.id.age_text);
            t_contact = itemView.findViewById(R.id.contact_text);
            t_email = itemView.findViewById(R.id.email_text);
            delete = itemView.findViewById(R.id.delete_button);
        }
    }
}
