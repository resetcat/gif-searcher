package io.codelex.gif_searcher.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.codelex.gif_searcher.models.GifModel;
import io.codelex.gif_searcher.repositories.GifRepository;

public class GifListViewModel extends ViewModel {

    private final GifRepository gifRepository;

    public GifListViewModel() {
        gifRepository = GifRepository.getInstance();
    }

    public LiveData<List<GifModel>> getGifs(){
        return gifRepository.getGifs();
    }

    public void searchGifApi(String query, int offset){
        gifRepository.searchGifApi(query, offset);
    }

    public void searchNextPage(){
        gifRepository.searchNextPage();
    }
}
