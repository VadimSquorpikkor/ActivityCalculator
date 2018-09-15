package com.squorpikkor.app.activitycalculator;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import java.util.List;

public class  SourceAdapter extends ArrayAdapter<RA_Source> {

    private LayoutInflater inflater;
    private int layout;
    private List<RA_Source> sourceList;

    SourceAdapter(Context context, int resource, List<RA_Source> sourceList) {
        super(context, resource, sourceList);
        this.sourceList = sourceList;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        @SuppressLint("ViewHolder")
        View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = view.findViewById(R.id.name);
        TextView activityView = view.findViewById(R.id.activity);
        TextView elementView = view.findViewById(R.id.element);

        RA_Source state = sourceList.get(position);

        nameView.setText(state.getName());
        activityView.setText(String.format("%.5f" , state.getActivity()) + " кБк");
        //activityView.setText(String.valueOf(state.getActivity()));
        elementView.setText(state.getElement());

        return view;
    }
}