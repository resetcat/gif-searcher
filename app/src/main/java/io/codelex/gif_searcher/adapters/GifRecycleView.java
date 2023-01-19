package io.codelex.gif_searcher.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.codelex.gif_searcher.R;
import io.codelex.gif_searcher.models.GifModel;

public class GifRecycleView  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GifModel> mGifs;
    private OnGifListener onGifListener;

    public GifRecycleView(OnGifListener onGifListener) {
        this.onGifListener = onGifListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gif_list_item,
                                                                     parent, false);
        return new GifViewHolder(view, onGifListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ((GifViewHolder)holder).title.setText(mGifs.get(i).getTitle());

        Glide.with(holder.itemView.getContext())
                .load("https://media2.giphy.com/media/"+mGifs.get(i).getId()+"/giphy.gif")
                .into((((GifViewHolder)holder).imageView));
    }

    @Override
    public int getItemCount() {
        if(mGifs != null){
        return mGifs.size();
        }
        return 0;
    }
// todo watch for change and deletion
    public void setmGifs(List<GifModel> mGifs) {
        this.mGifs = mGifs;
        notifyDataSetChanged();
    }
}
