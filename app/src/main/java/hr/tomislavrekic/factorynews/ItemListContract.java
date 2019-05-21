package hr.tomislavrekic.factorynews;

import java.util.List;

public interface ItemListContract {
    interface View{
        List<NewsItem> getData();
        int getDataCount();
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
        void initData();
        void updateData();
    }
}
