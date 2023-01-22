package io.codelex.gif_searcher.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class GifModel implements Parcelable {
    private final String title;
    private final String id;

    protected GifModel(Parcel in) {
        title = in.readString();
        id = in.readString();
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

    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(id);
    }
}
