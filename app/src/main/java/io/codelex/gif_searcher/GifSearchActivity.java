package io.codelex.gif_searcher;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.util.Log;
import android.widget.EditText;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.codelex.gif_searcher.adapters.GifRecycleView;
import io.codelex.gif_searcher.adapters.OnGifListener;
import io.codelex.gif_searcher.viewmodels.GifListViewModel;

public class GifSearchActivity extends AppCompatActivity implements OnGifListener {

    private RecyclerView recyclerView;
    private GifRecycleView gifRecycleAdapter;
    private GifListViewModel gifListViewModel;
    private final Handler mHandler = new Handler();
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setupSearchView();

        recyclerView = findViewById(R.id.recyclerView);
        gifListViewModel = new ViewModelProvider(this).get(GifListViewModel.class);

        configureRecyclerView();
        observeAnyChange();


    }


    private void observeAnyChange() {
        gifListViewModel.getGifs().observe(this, gifModels -> {
            if (gifModels != null) {
                gifRecycleAdapter.setmGifs(gifModels);
                Log.v("Tag", "size: " + gifModels.size());
            }
        });

    }


    private void configureRecyclerView() {
        GifRecycleView gifRecycleAdapter = new GifRecycleView(this);
        recyclerView.setAdapter(gifRecycleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)) {
                    gifListViewModel.searchNextPage();
                }
            }
        });

    }


    private void setupSearchView() {
        final SearchView searchView = findViewById(R.id.search_view);
        searchLengthLimit(searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mHandler.removeCallbacks(mRunnable);
                mRunnable = () -> gifListViewModel.searchGifApi(newText, 0);
                mHandler.postDelayed(mRunnable, 300);
                return false;
            }
        });
    }
    private void searchLengthLimit(SearchView searchView){
        int limit = 50;
        @SuppressLint("DiscouragedApi") EditText et = (EditText) searchView.findViewById(
                searchView.getContext()
                          .getResources()
                          .getIdentifier("android:id/search_src_text", null, null));
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(limit)});
    }

}