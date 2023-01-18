package io.codelex.gif_searcher.request;

import io.codelex.gif_searcher.utils.Credentials;
import io.codelex.gif_searcher.utils.GifApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestService {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(Credentials.URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private  static GifApi gifApi = retrofit.create(GifApi.class);

    public static GifApi getGifApi(){
        return gifApi;
    }
}
