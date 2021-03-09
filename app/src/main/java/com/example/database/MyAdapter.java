package com.example.database;

import android.content.Context;
import android.media.Image;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.CheckedOutputStream;

import static android.widget.Toast.LENGTH_LONG;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    @NonNull
    private List<ContactDB> contactlist;
    DBhandler db;

    public MyAdapter(List<ContactDB> contactlist, DBhandler db)
    {
        this.contactlist = contactlist;
        this.db = db;
    }

    public void addelement(ContactDB contactDB)
    {
        contactlist.add(contactDB);
        notifyDataSetChanged();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        //final ContactDB myListData = list[position];

        holder.name.setText(contactlist.get(position).getName());
        holder.contact.setText(contactlist.get(position).getPhoneNumber());
        holder.ssn.setText(contactlist.get(position).get_ssn());
        holder.address.setText(contactlist.get(position).get_address());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String nameDelete = contactlist.get(position).getName();
                    db.deleteContact(contactlist.get(position));
                    contactlist.remove(position);
                    notifyDataSetChanged();

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContactDB editContact = contactlist.get(position);
                MainActivity.edituser(editContact);
            }
        });

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(),"click on item: "+contactlist.get(position).getName(),Toast.LENGTH_LONG).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return contactlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView contact;
        public TextView ssn;
        public TextView address;
        public ImageView delete;
        public ImageView edit;
        public RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.contact = (TextView) itemView.findViewById(R.id.contact);
            this.ssn = (TextView) itemView.findViewById(R.id.ssn);
            this.address = (TextView) itemView.findViewById(R.id.address);
            this.delete = (ImageView) itemView.findViewById(R.id.delete);
            this.edit = (ImageView) itemView.findViewById(R.id.edit);
            relativeLayout = (RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }

    }
}
