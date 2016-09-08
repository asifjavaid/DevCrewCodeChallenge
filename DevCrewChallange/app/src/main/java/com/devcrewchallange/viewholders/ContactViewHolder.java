package com.devcrewchallange.viewholders;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

import com.devcrewchallange.R;
import com.devcrewchallange.activities.BaseActivity;

/**
 * Created by asifj on 9/8/2016.
 */

public class ContactViewHolder extends BaseViewHolder
{
    private final RecyclerView recyclerViewContacts;
    private final FloatingActionButton fabAddContact;

    public ContactViewHolder(BaseActivity view) {
        super(view);
        recyclerViewContacts = (RecyclerView) view.findViewById(R.id.recyclerViewContacts);
        fabAddContact = (FloatingActionButton) view.findViewById(R.id.fabAddContact);
    }

    public RecyclerView getRecyclerViewContacts() {
        return recyclerViewContacts;
    }

    public FloatingActionButton getFabAddContact() {
        return fabAddContact;
    }
}
