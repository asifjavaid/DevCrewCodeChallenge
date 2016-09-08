package com.devcrewchallange.view;

import com.devcrewchallange.data.Contact;

import java.util.ArrayList;

/**
 * Created by asifj on 9/9/2016.
 */

public interface ContactView extends BaseView {

        void setDefaultContactList(ArrayList<Contact> contactList);
}
