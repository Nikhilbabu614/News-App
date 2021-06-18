package com.example.news;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Article> localDataSet;
    TextView text ,text1;
    ImageView myimage;
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.myview);
        }
        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(ArrayList<Article> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.newslist, viewGroup, false);
        text = view.findViewById(R.id.myview);
        text1 = view.findViewById(R.id.textView2);
        myimage = view.findViewById(R.id.imageView2);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.getTextView().setText(localDataSet[position]);
        text.setText(localDataSet.get(position).title);
        text1.setText("Author :"+localDataSet.get(position).author);
        Glide.with(viewHolder.itemView.getContext()).load(localDataSet.get(position).imagelink).into(myimage);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public void updatenews(ArrayList<Article> updated){
        localDataSet.clear();
        localDataSet.addAll(updated);

        notifyDataSetChanged();
    }
}
