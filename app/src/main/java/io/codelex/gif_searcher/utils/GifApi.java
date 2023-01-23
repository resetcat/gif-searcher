package io.codelex.gif_searcher.utils;

import io.codelex.gif_searcher.responses.GifSearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GifApi {
    @GET("search")
    Call<GifSearchResponse> searchGif(
            @Query("api_key") String key,
            @Query("q") String query,
            @Query("offset") int offset
    );
}
