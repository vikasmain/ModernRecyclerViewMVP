package com.cogoport.adapter;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.caverock.androidsvg.SVG;
import com.cogoport.R;
import com.cogoport.model.Album;
import com.cogoport.presenter.RecyclerItemClickListener;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AdapterExample extends RecyclerView.Adapter<AdapterExample.ExampleHolder> {

    Context context;
    private int itemLayout;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;
    Album album;
    private RecyclerItemClickListener recyclerItemClickListener;
    ArrayList<Album> albumList=new ArrayList<>();
//    public void setRecyclerItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
//        this.recyclerItemClickListener = recyclerItemClickListener;
//    }




    public AdapterExample(Context context,int itemLayout,ArrayList<Album> albumList) {
        this.context=context;
        this.itemLayout = itemLayout;
        this.albumList=albumList;
    }

    @Override
    public ExampleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ExampleHolder(view);
    }

    @Override
    public void onBindViewHolder(final ExampleHolder holder, final int position) {
         album=albumList.get(position);
        holder.title.setText(album.getSong());
        Glide.with(context).load(album.getCover_image()).into(holder.imageView);
    }







    @Override
    public int getItemCount()
    {
        return albumList.size();
    }


    public class ExampleHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.goi)
        TextView title;
        @BindView(R.id.image)
        ImageView imageView;

        public ExampleHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (recyclerItemClickListener != null)
                recyclerItemClickListener.onItemClickListener(getAdapterPosition());
        }
    }




//    private void reload() {
//        Log.w(TAG, "reloading");
//        loadNet();
//    }
//    private void loadNet() {
//        Uri uri = Uri.parse(image);
//        requestBuilder
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                // SVG cannot be serialized so it's not worth to cache it
//                .load(uri)
//                .into();
//    }

}
