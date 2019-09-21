package com.example.jaroga.appempresa;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterStore extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Category> items;

    public AdapterStore(Activity activity, ArrayList<Category> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = view;

        if (view == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_store, null);
        }

        Category dir = items.get(i);

        TextView title = v.findViewById(R.id.category);
        title.setText(dir.getTitle());

        TextView description = v.findViewById(R.id.texto);
        description.setText(dir.getDescripcion());

        return v;

    }

}
