package hr.tomislavrekic.factorynews.model;


import java.util.List;

import hr.tomislavrekic.factorynews.model.tasks.ConvertDataTask;
import hr.tomislavrekic.factorynews.model.tasks.FetchFromDBTask;
import hr.tomislavrekic.factorynews.util.ItemListContract;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleItem;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleResponse;
import hr.tomislavrekic.factorynews.model.networking.NewsArticleService;
import hr.tomislavrekic.factorynews.model.tasks.StoreToDBTask;
import hr.tomislavrekic.factorynews.newsdatabase.NDBAdapter;
import hr.tomislavrekic.factorynews.ui.MainActivity;
import retrofit2.Callback;


public class ItemListModel implements ItemListContract.Model {
    private NDBAdapter mAdapter;
    private NewsArticleService mService;

    public ItemListModel(){
        mAdapter = new NDBAdapter(MainActivity.getContext());
        mService = new NewsArticleService();
    }

    @Override
    public void updateData(Callback<NewsArticleResponse> callback){
        mService.getNewsResponse(callback);
    }

    @Override
    public void convertData(List<NewsArticleItem> responseData, NewsItemDelegate delegate) {
        new ConvertDataTask(delegate).execute(responseData);
    }

    @Override
    public void storeToDB(List<NewsItem> input) {
        new StoreToDBTask(mAdapter).execute(input);
    }

    @Override
    public void fetchFromDB(NewsItemDelegate delegate) {
        new FetchFromDBTask(delegate, mAdapter).execute();
    }


}
