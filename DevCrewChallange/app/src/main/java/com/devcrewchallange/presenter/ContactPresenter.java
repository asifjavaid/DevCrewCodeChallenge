package com.devcrewchallange.presenter;

import android.content.Context;

import com.devcrewchallange.data.Contact;
import com.devcrewchallange.view.ContactView;

import java.util.ArrayList;

/**
 * Created by asifj on 9/9/2016.
 */

public class ContactPresenter
{
    Context context;
    ContactView contactView;

    public ContactPresenter(Context context, ContactView contactView) {
        this.context = context;
        this.contactView = contactView;
    }

    /***
     * This method will create dummy default contacts.
     */
    public void createContacts() {

        contactView.showProgressIndicator();

        ArrayList<Contact> contactArrayList = new ArrayList<>();
        for (int index = 0; index < 5; index++)
        {
            Contact contact = new Contact();
            contact.setFirstName("Name");
            contact.setLastName(String.valueOf(index));
            contactArrayList.add(contact);
        }

        contactView.setDefaultContactList(contactArrayList);
        contactView.hideProgressIndicator();

    }
}
