package com.devcrewchallange.viewholders;

import android.view.View;
import android.widget.EditText;

import com.devcrewchallange.R;

/**
 * Created by asifj on 9/9/2016.
 */

public class ContactDialogViewHolder extends BaseViewHolder {

    private final EditText editTextFirstName;
    private final EditText editTextLastName;

    public ContactDialogViewHolder(View view) {
        super(view);
        editTextFirstName = (EditText) view.findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText) view.findViewById(R.id.editTextLastName);
    }

    public EditText getEditTextFirstName() {
        return editTextFirstName;
    }

    public EditText getEditTextLastName() {
        return editTextLastName;
    }
}
