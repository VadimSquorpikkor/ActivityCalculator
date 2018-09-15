package com.squorpikkor.app.activitycalculator;

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

    public SourceAdapter(Context context, int resource, List<RA_Source> states) {
        super(context, resource, states);
        this.sourceList = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView nameView = view.findViewById(R.id.name);
        TextView activityView = view.findViewById(R.id.activity);
        TextView elementView = view.findViewById(R.id.element);

        RA_Source state = sourceList.get(position);

        nameView.setText(state.getName());
        activityView.setText(String.valueOf(state.getActivity()));
        elementView.setText(state.getElement());

        return view;
    }
}