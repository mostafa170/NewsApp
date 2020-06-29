package com.kamel.newsapi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.kamel.newsapi.R;
import com.kamel.newsapi.databinding.ActivityDetailsNewsBinding;
import com.squareup.picasso.Picasso;

public class DetailsNewsActivity extends AppCompatActivity {

    ActivityDetailsNewsBinding activityDetailsNewsBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityDetailsNewsBinding = DataBindingUtil.setContentView(this, R.layout.activity_details_news);

        activityDetailsNewsBinding.idTvShowTitle.setText(getIntent().getStringExtra("Title"));
        activityDetailsNewsBinding.idTvShowPublishedAt.setText("Date : "+getIntent().getStringExtra("publishedAt"));
        activityDetailsNewsBinding.idTvShowAuthor.setText("Author : "+getIntent().getStringExtra("author"));
        activityDetailsNewsBinding.idTvShowDescription.setText("Description : "+getIntent().getStringExtra("description"));
        activityDetailsNewsBinding.idTvShowContent.setText("content : "+getIntent().getStringExtra("content"));
        activityDetailsNewsBinding.idTvShowSource.setText("Source : "+getIntent().getStringExtra("source"));
        activityDetailsNewsBinding.idTvShowUrl.setText("Url source : "+getIntent().getStringExtra("url"));

        Picasso.get()
                .load(getIntent().getStringExtra("Photo"))
                .placeholder(R.drawable.user_defult)
                .error(android.R.drawable.stat_notify_error)
                .into(activityDetailsNewsBinding.idIvShowImage);
    }
}