package com.sample.loadimages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sample.loadimages.model.Photo;
import com.squareup.picasso.Picasso;

public class PhotosListAdapter extends PagedListAdapter<Photo, PhotosListAdapter.PhotoViewHolder> {

    static class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView photoView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            photoView = itemView.findViewById(R.id.photo);
        }
    }

    protected PhotosListAdapter(@NonNull DiffUtil.ItemCallback<Photo> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.layout_photo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = getItem(position);
        assert photo != null;
        String photoUrl = "https://farm" + photo.getFarm() + ".staticflickr.com/"
                + photo.getServer() + "/"+photo.getId() + "_"+photo.getSecret() + ".jpg";
        Picasso.get()
                .load(photoUrl)
                .placeholder(R.drawable.loading)
                .fit()
                .centerCrop()
                .into(holder.photoView);
    }

}
