package com.tchallenge.titansoft.planningpoker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tchallenge.titansoft.planningpoker.R;

import java.util.List;

public class RoundResultAdapter extends ArrayAdapter<String> {

    public RoundResultAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.view_item_round_result, null);
        }

        TextView result_view = convertView.findViewById(R.id.tv_result);
        result_view.setText(getItem(position));

        return convertView;
    }
}
