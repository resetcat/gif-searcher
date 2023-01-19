package io.codelex.gif_searcher.request;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import io.codelex.gif_searcher.AppExecutors;
import io.codelex.gif_searcher.models.GifModel;
import io.codelex.gif_searcher.responses.GifSearchResponse;
import io.codelex.gif_searcher.utils.Credentials;
import retrofit2.Call;
import retrofit2.Response;

public class GifApiClient {
    private MutableLiveData<List<GifModel>> mGifs;
    private static GifApiClient instance;

    private RetrieveGifsRunnable retrieveGifsRunnable;



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

        if(retrieveGifsRunnable != null){
            retrieveGifsRunnable = null;
        }

        retrieveGifsRunnable = new RetrieveGifsRunnable(query, offset);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveGifsRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);

    }

    private class RetrieveGifsRunnable implements Runnable {
        //todo put in its own class
        private String query;
        private int offset;
        boolean cancelRequest;

        public RetrieveGifsRunnable(String query, int offset) {
            this.query = query;
            this.offset = offset;
            this.cancelRequest = false;
        }

        @Override
        public void run() {
            try{
                Response<GifSearchResponse> response = getGifs(query, offset).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200){
                    List<GifModel> list =
                            new ArrayList<>(((GifSearchResponse)response.body()).getGifs());
                    if(offset == 0){
                        mGifs.postValue(list);
                    } else {
                        List<GifModel> currentGifs = mGifs.getValue() == null ?
                                new ArrayList<>() : mGifs.getValue();
                        currentGifs.addAll(list);
                        mGifs.postValue(currentGifs);

                    }
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error "+error);
                    mGifs.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mGifs.postValue(null);
            }


        }

        private Call<GifSearchResponse> getGifs(String query, int offset) {
            //todo change key access method
            return RequestService.getGifApi().searchGif(Credentials.API_KEY, query, offset);
        }

        private void cancelRequest(){
            Log.v("Tag", "canceling request");
            cancelRequest = true;
        }

    }
}
