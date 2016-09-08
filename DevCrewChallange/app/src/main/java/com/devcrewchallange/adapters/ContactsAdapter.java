package com.devcrewchallange.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.devcrewchallange.R;
import com.devcrewchallange.activities.BaseActivity;
import com.devcrewchallange.data.Contact;
import com.devcrewchallange.viewholders.ContactRowViewHolder;

import java.util.ArrayList;

/**
 * Created by asifj on 9/9/2016.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactRowViewHolder> {

    private BaseActivity context;
    private ArrayList<Contact> mContactArrayList;

    public ContactsAdapter(BaseActivity context, ArrayList<Contact> mContactArrayList) {
        this.context = context;
        this.mContactArrayList = mContactArrayList;
    }

    @Override
    public ContactRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout_contact, null);
        ContactRowViewHolder viewHolder = new ContactRowViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactRowViewHolder holder, int position) {

        if (mContactArrayList != null) {
            Contact contact = mContactArrayList.get(position);
            if(contact != null)
            {
                holder.getTextViewFirstName().setText(contact.getFirstName());
                holder.getTextViewLastName().setText(contact.getLastName());
            }
        }


    }

    @Override
    public int getItemCount() {
        if (mContactArrayList != null) {
            return mContactArrayList.size();
        }

        return 0;
    }
}
