package io.codelex.gif_searcher.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.codelex.gif_searcher.models.GifModel;
import io.codelex.gif_searcher.request.GifApiClient;

public class GifRepository {

    private static GifRepository instance;
    private GifApiClient  gifApiClient;

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

    public void searchGifApi(String query, int offset){
        gifApiClient.searchGifsApi(query, offset);
    }
}
