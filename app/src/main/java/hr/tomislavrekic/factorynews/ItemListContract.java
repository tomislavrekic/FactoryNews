package hr.tomislavrekic.factorynews;

import java.util.List;

import retrofit2.Callback;

public interface ItemListContract {
    interface View{
        List<NewsItem> getData();
        int getDataCount();
        void updateAdapter(List<NewsItem> data);


    }
    interface Presenter{
        List<NewsItem> getData();
        int getDataCount();
        void initData();
        void updateData();

    }
    interface Model {
        List<NewsItem> getData();
        int getDataCount();
        void initData(Callback<NewsArticleResponse> callback);
        void updateData();
        List<NewsItem> convertData(List<NewsArticleItem> responseData);
    }
}
