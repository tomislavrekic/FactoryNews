package hr.tomislavrekic.factorynews.presenter;

import android.os.SystemClock;
import android.util.Log;

import java.util.List;

import hr.tomislavrekic.factorynews.util.Constants;
import hr.tomislavrekic.factorynews.util.ItemListContract;
import hr.tomislavrekic.factorynews.model.ItemListModel;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleResponse;
import hr.tomislavrekic.factorynews.model.NewsItem;
import hr.tomislavrekic.factorynews.model.NewsItemDelegate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hr.tomislavrekic.factorynews.util.Constants.TAG;

public class ItemListPresenter implements ItemListContract.Presenter {
    private ItemListContract.Model model;
    private ItemListContract.View mView;

    private static ItemListContract.Presenter instance;

    public static ItemListContract.Presenter getInstance(){
        if(instance == null){
            instance = new ItemListPresenter();
        }
        return instance;
    }

    @Override
    public void addView(ItemListContract.View view){
        mView = view;
    }

    @Override
    public void removeView() {
        mView = null;
    }

    private ItemListPresenter() {
        model = new ItemListModel();
    }

    /*
        Give data to the views.
     */
    @Override
    public void updateData(){

        mView.showLoading();


        if(!model.checkDataExpired(SystemClock.elapsedRealtime())) {
            uploadFromDB();
        }
        else{
            model.updateData(new Callback<NewsArticleResponse>() {
                @Override
                public void onResponse(Call<NewsArticleResponse> call, Response<NewsArticleResponse> response) {

                    if(!response.isSuccessful()) return;

                    model.convertData(response.body().getArticles(),
                            new NewsItemDelegate() {
                                @Override
                                public void processFinished(List<NewsItem> response) {

                                    mView.hideLoading();
                                    mView.updateAdapter(response);

                                    model.storeToDB(response);

                                    model.saveTimestamp(SystemClock.elapsedRealtime());
                                }
                            });
                }

                @Override
                public void onFailure(Call<NewsArticleResponse> call, Throwable t) {
                    mView.showAlert();
                    uploadFromDB();
                    System.out.println(t);
                }
            });
        }


    }

    private void uploadFromDB(){
        model.fetchFromDB(new NewsItemDelegate() {
            @Override
            public void processFinished(List<NewsItem> response) {
                mView.updateAdapter(response);
                mView.hideLoading();
            }
        });
    }

}
