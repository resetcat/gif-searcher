package io.codelex.gif_searcher.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class GifModel implements Parcelable {
    private String title;
    private String embed_url;

    public GifModel(String title, String embed_url) {
        this.title = title;
        this.embed_url = embed_url;
    }

    protected GifModel(Parcel in) {
        title = in.readString();
        embed_url = in.readString();
    }

    public static final Creator<GifModel> CREATOR = new Creator<GifModel>() {
        @Override
        public GifModel createFromParcel(Parcel in) {
            return new GifModel(in);
        }

        @Override
        public GifModel[] newArray(int size) {
            return new GifModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getEmbed_url() {
        return embed_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(embed_url);
    }
}
