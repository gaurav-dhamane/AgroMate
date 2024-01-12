package com.saurabhjadhav.farmhelper.Contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saurabhjadhav.farmhelper.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.EventViewHolder> {
    private final List<Contact> ContactList;
    LayoutInflater layoutInflaterContacts;
    Context context;

    public ContactAdapter(Context context, List<Contact> ContactList) {
        this.context = context;
        this.layoutInflaterContacts = LayoutInflater.from(context);
        this.ContactList = ContactList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflaterContacts.inflate(R.layout.custom_call_layout, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.name.setText(ContactList.get(position).getName());
        holder.number.setText(ContactList.get(position).getNumber());
        holder.call_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = ContactList.get(holder.getAdapterPosition()).getNumber();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(context, "Initiating Call", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (ContactList == null) return 0;
        return ContactList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView name, number;
        ImageView call_img;

        public EventViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_faculty);
            number = view.findViewById(R.id.faculty_number);
            call_img=view.findViewById(R.id.call_img);
        }
    }
}
