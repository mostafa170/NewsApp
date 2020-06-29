package com.kamel.newsapi.ui.ListNews;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kamel.newsapi.Network.MyApplication;
import com.kamel.newsapi.ui.ListNews.model.ArticlesItem;
import com.kamel.newsapi.ui.ListNews.model.ResponseNews;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ViewModelNews extends AndroidViewModel {
    protected MutableLiveData<Integer> errorMessage ;
    protected MutableLiveData<List<ArticlesItem>> showNews;
    private CompositeDisposable compositeDisposable ;

    public ViewModelNews(@NonNull Application application) {
        super(application);

        showNews = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<Integer> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<List<ArticlesItem>> getShowNews() {
        return showNews;
    }
    public void getNews(String apiKey,String country ,String category ){
        Disposable disposable = MyApplication.getApis().getNews(apiKey,country, category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<ResponseNews>(){

                    @Override
                    public void onSuccess(ResponseNews response){

                        if (response.getStatus().equals("ok")){
                            showNews.postValue(response.getArticles());
                        }else {
                            errorMessage.postValue(1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorMessage.postValue(0);
                    }
                });


        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
