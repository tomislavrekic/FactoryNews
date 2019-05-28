package hr.tomislavrekic.factorynews.util;

import java.util.List;

import hr.tomislavrekic.factorynews.model.NewsItem;
import hr.tomislavrekic.factorynews.model.NewsItemDelegate;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleItem;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleResponse;
import retrofit2.Callback;

public interface ItemListContract {
    interface View{
        void updateAdapter(List<NewsItem> data);
        void showLoading();
        void hideLoading();
        void showAlert();


    }
    interface Presenter{
        void updateData();
        void addView(ItemListContract.View view);
        void removeView();


    }
    interface Model {
        void updateData(Callback<NewsArticleResponse> callback);
        void convertData(List<NewsArticleItem> responseData, NewsItemDelegate delegate);
        void storeToDB(List<NewsItem> input);
        void fetchFromDB(NewsItemDelegate delegate);
        boolean checkDataExpired(long currentTime);
        void saveTimestamp(long currentTime);
    }
}
