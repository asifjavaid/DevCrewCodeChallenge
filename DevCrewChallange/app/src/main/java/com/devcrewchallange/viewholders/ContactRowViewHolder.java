package com.devcrewchallange.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.devcrewchallange.R;

/**
 * Created by asifj on 9/9/2016.
 */

public class ContactRowViewHolder extends RecyclerView.ViewHolder {

    private final TextView textViewFirstName;
    private final TextView textViewLastName;

    public ContactRowViewHolder(View itemView) {
        super(itemView);
        textViewFirstName = (TextView) itemView.findViewById(R.id.textViewFirstName);
        textViewLastName = (TextView) itemView.findViewById(R.id.textViewLastName);
    }

    public TextView getTextViewFirstName() {
        return textViewFirstName;
    }


    public TextView getTextViewLastName() {
        return textViewLastName;
    }
}
