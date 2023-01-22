package io.codelex.gif_searcher.request;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.codelex.gif_searcher.BuildConfig;
import io.codelex.gif_searcher.models.GifModel;
import io.codelex.gif_searcher.responses.GifSearchResponse;
import retrofit2.Call;
import retrofit2.Response;

public class RetrieveGifsRunnable implements Runnable {
    private final String query;
    private final int offset;
    private final MutableLiveData<List<GifModel>> mGifs;
    boolean cancelRequest;

    public RetrieveGifsRunnable(String query, int offset, MutableLiveData<List<GifModel>> mGifs) {
        this.query = query;
        this.offset = offset;
        this.mGifs = mGifs;
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
                assert response.body() != null;
                List<GifModel> list =
                        new ArrayList<>(response.body().getGifs());
                if(offset == 0){
                    mGifs.postValue(list);
                } else {
                    List<GifModel> currentGifs = mGifs.getValue() == null ?
                            new ArrayList<>() : mGifs.getValue();
                    currentGifs.addAll(list);
                    mGifs.postValue(currentGifs);

                }
            } else {
                assert response.errorBody() != null;
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
        return RequestService.getGifApi().searchGif(BuildConfig.API_KEY, query, offset);
    }

}

