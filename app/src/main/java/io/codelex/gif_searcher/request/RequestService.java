package io.codelex.gif_searcher.request;

import io.codelex.gif_searcher.utils.GifApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestService {
    public static final String URL = "https://api.giphy.com/v1/gifs/";

    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();

    private  static final GifApi gifApi = retrofit.create(GifApi.class);

    public static GifApi getGifApi(){
        return gifApi;
    }
}
