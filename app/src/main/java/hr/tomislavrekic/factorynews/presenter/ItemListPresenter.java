package hr.tomislavrekic.factorynews.presenter;

import android.os.SystemClock;

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

public class ItemListPresenter implements ItemListContract.Presenter {

    private ItemListContract.View view;
    private ItemListContract.Model model;



    public ItemListPresenter(ItemListContract.View view) {
        this.view = view;
        model = new ItemListModel();
    }

    @Override
    public void updateData(){
        model.fetchFromDB(new NewsItemDelegate() {
            @Override
            public void processFinished(List<NewsItem> response) {
                view.updateAdapter(response);
            }
        });

        if(!model.checkDataExpired(SystemClock.elapsedRealtime())) return;

        view.showLoading();
        model.updateData(new Callback<NewsArticleResponse>() {
            @Override
            public void onResponse(Call<NewsArticleResponse> call, Response<NewsArticleResponse> response) {

                if(!response.isSuccessful()) return;

                model.convertData(response.body().getArticles(),
                        new NewsItemDelegate() {
                            @Override
                            public void processFinished(List<NewsItem> response) {
                                view.hideLoading();
                                view.updateAdapter(response);

                                model.storeToDB(response);

                                model.saveTimestamp(SystemClock.elapsedRealtime());
                            }
                        });
            }

            @Override
            public void onFailure(Call<NewsArticleResponse> call, Throwable t) {


                System.out.println(t);
            }
        });
    }

}
