package io.codelex.gif_searcher.request;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.codelex.gif_searcher.AppExecutors;
import io.codelex.gif_searcher.models.GifModel;

public class GifApiClient {
    private final MutableLiveData<List<GifModel>> mGifs;
    private static GifApiClient instance;


    public static GifApiClient getInstance() {
        if (instance == null) {
            instance = new GifApiClient();
        }
        return instance;
    }

    private GifApiClient() {
        mGifs = new MutableLiveData<>();
    }

    public LiveData<List<GifModel>> getGifs() {
        return mGifs;
    }

    public void searchGifsApi(String query,int offset) {

        RetrieveGifsRunnable retrieveGifsRunnable = new RetrieveGifsRunnable(query, offset, mGifs);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveGifsRunnable);

        AppExecutors.getInstance().networkIO().schedule(() -> {
            myHandler.cancel(true);
        }, 3000, TimeUnit.MILLISECONDS);

    }
}
