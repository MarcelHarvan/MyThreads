package com.example.marcelharvan.mythreads;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by marcelharvan on 2017-04-13.
 */

public class FeedAdapter extends ArrayAdapter {
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> applications;

    public FeedAdapter(@NonNull Context context, @LayoutRes int resource, List<FeedEntry> applications) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.applications = applications;
    }

    @Override
    public int getCount() {
        return applications.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutResource, parent, false);
        TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
        TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        FeedEntry currentApp = applications.get(position);

        tvTitle.setText(currentApp.getTitle());
        tvDescription.setText(currentApp.getDescription());
        return view;
    }
}
