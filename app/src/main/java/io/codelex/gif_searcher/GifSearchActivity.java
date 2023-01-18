package io.codelex.gif_searcher;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import io.codelex.gif_searcher.models.GifModel;
import io.codelex.gif_searcher.viewmodels.GifListViewModel;

public class GifSearchActivity extends AppCompatActivity {

    Button btn;

    private GifListViewModel gifListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);

        gifListViewModel = new ViewModelProvider(this).get(GifListViewModel.class);

        ObserveAnyChange();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchGifApi("travolta", 0);
            }
        });




        // todo change later
        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void ObserveAnyChange(){
        gifListViewModel.getGetGifs().observe(this, new Observer<List<GifModel>>() {
            @Override
            public void onChanged(List<GifModel> gifModels) {
                if(gifModels != null){
                    for (GifModel gifModel: gifModels){
                        Log.v("Tag", "onChanged: "+ gifModel.getTitle());
                    }
                    Log.v("Tag", "size: "+gifModels.size());
                }

            }
        });

    }

    private void searchGifApi(String query, int offset){
        gifListViewModel.searchGifApi(query, offset);
    }

    // todo delete if everything works

//    private void GetRetrofitResponse() {
//        GifApi gifApi = RequestService.getGifApi();
//        Call<GifSearchResponse> responseCall = gifApi
//                .searchGif(getString(R.string.api_key),"car",5);
//
//        responseCall.enqueue(new Callback<GifSearchResponse>() {
//            @Override
//            public void onResponse(@NonNull Call<GifSearchResponse> call,
//                                   @NonNull Response<GifSearchResponse> response) {
//                if(response.code() == 200){
//                    assert response.body() != null;
//                    Log.v("Tag", "offset is "+ response.body().getPaginationModel().getOffset());
//
//                    List<GifModel> gifs = new ArrayList<>(response.body().getGifs());
//
//
//                        Log.v("Tag", "size of batch "+ gifs.size());
//                } else {
//                    try{
//                        assert response.errorBody() != null;
//                        Log.v("Tag", "Error" + response.errorBody().string());
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<GifSearchResponse> call, Throwable t) {
//
//            }
//        });
//
//    }

}