package hr.tomislavrekic.factorynews;

import java.util.List;

import retrofit2.Callback;

public interface ItemListContract {
    interface View{
        void updateAdapter(List<NewsItem> data);
        void showLoading();
        void hideLoading();


    }
    interface Presenter{
        void updateData();


    }
    interface Model {
        void updateData(Callback<NewsArticleResponse> callback);
        void convertData(List<NewsArticleItem> responseData, NewsItemDelegate delegate);
        void storeToDB(List<NewsItem> input);
        List<NewsItem> fetchFromDB();
    }
}
