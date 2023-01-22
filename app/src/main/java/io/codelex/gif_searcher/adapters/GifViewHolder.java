package io.codelex.gif_searcher.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.codelex.gif_searcher.R;

public class GifViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    ImageView imageView;
    OnGifListener onGifListener;

    public GifViewHolder(@NonNull View itemView, OnGifListener onGifListener) {
        super(itemView);

        this.onGifListener = onGifListener;
        title = itemView.findViewById(R.id.gif_title);
        imageView = itemView.findViewById(R.id.gif_img);

    }

}
