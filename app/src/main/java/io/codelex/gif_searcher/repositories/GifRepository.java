package io.codelex.gif_searcher.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.codelex.gif_searcher.models.GifModel;
import io.codelex.gif_searcher.request.GifApiClient;

public class GifRepository {

    private static GifRepository instance;
    private final GifApiClient gifApiClient;

    private String mQuery;
    private int mOffset;

    public static GifRepository getInstance() {
        if (instance == null) {
            instance = new GifRepository();
        }
        return instance;
    }

    private GifRepository() {
        gifApiClient = GifApiClient.getInstance();
    }

    public LiveData<List<GifModel>> getGifs() {
        return gifApiClient.getGifs();
    }

    public void searchGifApi(String query, int offset) {
        mQuery = query;
        mOffset = offset;
        gifApiClient.searchGifsApi(query, offset);

    }

    public void searchNextPage(){
        // todo make 50 dynamic

        searchGifApi(mQuery,mOffset+50);
    }
}
