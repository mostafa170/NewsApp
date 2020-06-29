package com.kamel.newsapi.ui.ListNews.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kamel.newsapi.R;
import com.kamel.newsapi.databinding.ItemNewsBinding;
import com.kamel.newsapi.ui.ListNews.model.ArticlesItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterItemNews extends RecyclerView.Adapter<AdapterItemNews.ViewHolder> {

    List<ArticlesItem> articlesItemLists;


    public AdapterItemNews(List<ArticlesItem> articlesItemLists) {
        this.articlesItemLists = articlesItemLists;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final ArticlesItem articlesItemList = articlesItemLists.get(position);
        Picasso.get()
                .load(articlesItemList.getUrlToImage())
                .placeholder(R.drawable.user_defult)
                .error(android.R.drawable.stat_notify_error)
                .into(viewHolder.itemNewsBinding.idIvImageNews);

        viewHolder.itemNewsBinding.idTvTitleNews.setText(articlesItemLists.get(position).getTitle());

        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position, articlesItemList);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (articlesItemLists == null)
            return 0;
        return articlesItemLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemNewsBinding itemNewsBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bind();
        }


        void bind() {
            if (itemNewsBinding == null) {
                itemNewsBinding = DataBindingUtil.bind(itemView);
            }
        }

        void unbind() {
            if (itemNewsBinding != null) {
                itemNewsBinding.unbind();
            }
        }

    }

        OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, ArticlesItem articlesItemList);
    }

}