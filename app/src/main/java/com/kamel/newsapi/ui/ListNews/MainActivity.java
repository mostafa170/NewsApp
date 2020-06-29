package com.kamel.newsapi.ui.ListNews;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kamel.newsapi.Base.BaseActivity;
import com.kamel.newsapi.R;
import com.kamel.newsapi.databinding.ActivityMainBinding;
import com.kamel.newsapi.ui.DetailsNewsActivity;
import com.kamel.newsapi.ui.ListNews.Adapter.AdapterItemNews;
import com.kamel.newsapi.ui.ListNews.model.ArticlesItem;

import java.util.List;

public class MainActivity extends BaseActivity {

    private AdapterItemNews adapter;
    ActivityMainBinding activityMainBinding;
    private ViewModelNews viewModelNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModelNews = ViewModelProviders.of(this).get(ViewModelNews.class);
        adapter = new AdapterItemNews(null);
        activityMainBinding.recNews.setLayoutManager(new LinearLayoutManager(this));

        viewModelNews.getNews("74c280b8252f47cb9f05587e1e5e7738", "us", "business");

        ////////////////////////////////
        viewModelNews.getErrorMessage().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == 1) {
                    showMessageOk("Error", "error in response data", "OK");
                } else {
                    showMessageOk("Error", "error in Network", "OK");

                }
            }
        });

        viewModelNews.getShowNews().observe(this, new Observer<List<ArticlesItem>>() {
            @Override
            public void onChanged(@Nullable List<ArticlesItem> articlesItems) {
                if (articlesItems == null) {
                    //errorMessage if data coming is null;
                    activityMainBinding.tvListEmpty.setVisibility(View.VISIBLE);
                } else {
                    //show data in recyclerView
                    adapter = new AdapterItemNews(articlesItems);
                    activityMainBinding.recNews.setAdapter(adapter);
                    adapter.setOnItemClickListener(new AdapterItemNews.OnItemClickListener() {
                        @Override
                        public void onItemClick(int pos, ArticlesItem Message) {
                            Intent intent = new Intent(MainActivity.this, DetailsNewsActivity.class);
                            intent.putExtra("Photo", String.valueOf(Message.getUrlToImage()));
                            intent.putExtra("Title", String.valueOf(Message.getTitle()));
                            Log.d("xxxxxxxxxxxxxx",String.valueOf(Message.getTitle()));
                            intent.putExtra("publishedAt", String.valueOf(Message.getPublishedAt()));
                            intent.putExtra("author", String.valueOf(Message.getAuthor()));
                            intent.putExtra("description", String.valueOf(Message.getDescription()));
                            intent.putExtra("content", String.valueOf(Message.getContent()));
                            intent.putExtra("source", String.valueOf(Message.getSource().getName()));
                            intent.putExtra("url", String.valueOf(Message.getUrl()));
                            startActivity(intent);
                        }
                    });
                }
            }
        });


    }
}