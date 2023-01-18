package io.codelex.gif_searcher.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.codelex.gif_searcher.models.GifModel;
import io.codelex.gif_searcher.models.PaginationModel;

public class GifSearchResponse {
    @SerializedName("data")
    @Expose
    private List<GifModel> gifs;

    @SerializedName("pagination")
    @Expose
    private PaginationModel paginationModel;


    public PaginationModel getPaginationModel() {
        return paginationModel;
    }

    public List<GifModel> getGifs() {
        return gifs;
    }

    @Override
    public String toString() {
        return "GifSearchResponse{" + "gifs=" + gifs + ", offset=" + paginationModel + '}';
    }
}
