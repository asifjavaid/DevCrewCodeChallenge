package com.devcrewchallange.fragments;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;


import com.devcrewchallange.R;
import com.devcrewchallange.activities.BaseActivity;
import com.devcrewchallange.data.Contact;
import com.devcrewchallange.viewholders.ContactDialogViewHolder;

import java.util.ArrayList;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AddContactDialogFragment extends DialogFragment implements View.OnClickListener{

    private OnAddContactDialogFragmentInteractionListener mListener;
    private View mView;
    private BaseActivity context;
    private ContactDialogViewHolder mHolder;

    public static AddContactDialogFragment newInstance() {
        AddContactDialogFragment fragment = new AddContactDialogFragment();
        return fragment;
    }
    public AddContactDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().setTitle("Add Contact");

        mView = inflater.inflate(R.layout.fragment_add_contact_dialog, container, false);
        validateFragmentListener(getActivity());
        setUI();
        setListeners();

        return mView;
    }

    private void setUI() {
        mHolder = new ContactDialogViewHolder(mView);
    }


    private void setListeners() {
        mView.findViewById(R.id.buttonAddContact).setOnClickListener(this);
        mView.findViewById(R.id.buttonCancel).setOnClickListener(this);
    }


    public void onButtonPressed(Contact contact) {
        if (mListener != null) {
            mListener.onContactAdded(contact);
            dismiss();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = (BaseActivity) context;

        validateFragmentListener(context);
    }

    private void validateFragmentListener(Context context) {
        if (context instanceof OnAddContactDialogFragmentInteractionListener) {
            mListener = (OnAddContactDialogFragmentInteractionListener) context;
            this.context = (BaseActivity) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.buttonAddContact:

                if(validateContact())
                {
                    Contact contact = new Contact();
                    String firstName = mHolder.getEditTextFirstName().getText().toString();
                    String lastName = mHolder.getEditTextLastName().getText().toString();
                    contact.setFirstName(capitalizeFirstLetter(firstName.trim()));
                    contact.setLastName(capitalizeFirstLetter(lastName.trim()));
                    onButtonPressed(contact);
                }


                break;
            case R.id.buttonCancel:
                dismiss();
                break;
        }
    }

    private String capitalizeFirstLetter(String text)
    {
        char[] array = text.toCharArray();

        array[0] = Character.toUpperCase(array[0]);

        String result = new String(array);

        return result;
    }

    private boolean validateContact() {
        String firstName = mHolder.getEditTextFirstName().getText().toString();
        String lastName = mHolder.getEditTextLastName().getText().toString();
        if(TextUtils.isEmpty(firstName.trim()))
        {
            mHolder.getEditTextFirstName().setError(getString(R.string.no_first_name));
            return false;
        }

        if(TextUtils.isEmpty(lastName.trim()))
        {
            mHolder.getEditTextLastName().setError(getString(R.string.no_last_name));
            return false;
        }

        return true;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnAddContactDialogFragmentInteractionListener {

        void onContactAdded(Contact contact);
    }
}
