package io.codelex.gif_searcher.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PaginationModel implements Parcelable {
    private final int offset;

    protected PaginationModel(Parcel in) {
        offset = in.readInt();
    }

    public static final Creator<PaginationModel> CREATOR = new Creator<PaginationModel>() {
        @Override
        public PaginationModel createFromParcel(Parcel in) {
            return new PaginationModel(in);
        }

        @Override
        public PaginationModel[] newArray(int size) {
            return new PaginationModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(offset);
    }
}
