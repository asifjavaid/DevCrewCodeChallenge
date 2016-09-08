package com.devcrewchallange.activities;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.devcrewchallange.R;
import com.devcrewchallange.adapters.ContactsAdapter;
import com.devcrewchallange.data.Contact;
import com.devcrewchallange.fragments.AddContactDialogFragment;
import com.devcrewchallange.presenter.ContactPresenter;
import com.devcrewchallange.view.ContactView;
import com.devcrewchallange.viewholders.ContactViewHolder;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements ContactView, AddContactDialogFragment.OnAddContactDialogFragmentInteractionListener{

    ContactViewHolder mHolder;
    private ContactPresenter mPresenter;
    private ContactsAdapter mContactsAdapter;
    private ArrayList<Contact> mContactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHolder = new ContactViewHolder(this);
        mPresenter = new ContactPresenter(this,this);
        mPresenter.createContacts();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setListeners();

    }

    /***
     * This method will registers all the listeners or events.
     */
    private void setListeners() {
        mHolder.getFabAddContact().setOnClickListener(addContactOnClickListener);
    }

    private View.OnClickListener addContactOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showAddContactDialog();
        }
    };

    //It open the Add Contact Dialog.
    void showAddContactDialog() {
        try {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag("dialog");
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            // Create and show the dialog.
            DialogFragment newFragment = AddContactDialogFragment.newInstance();
            newFragment.show(ft, "dialog");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * The response from presenter will be set in the method. Whether the list is dummy or it fetching from the  server in presenter.
     * @param contactList
     */
    @Override
    public void setDefaultContactList(ArrayList<Contact> contactList) {
        mContactList = contactList;
        if(mHolder != null)
        {
            mContactsAdapter = new ContactsAdapter(this,contactList);
            mHolder.getRecyclerViewContacts().setLayoutManager(new LinearLayoutManager(this));
            mHolder.getRecyclerViewContacts().setAdapter(mContactsAdapter);
        }
    }

    /***
     * After selecting contact, this method will be called to add the new contact in the list and refresh it.
     * @param contact
     */
    @Override
    public void onContactAdded(Contact contact) {
        if(mContactList != null)
        {
            mContactList.add(contact);
            if(mContactsAdapter != null)
            {
                mContactsAdapter.notifyDataSetChanged();
                Snackbar.make(mHolder.getFabAddContact(), R.string.msg_contact_add, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }
}
