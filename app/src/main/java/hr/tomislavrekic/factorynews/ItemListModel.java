package hr.tomislavrekic.factorynews;


import java.util.List;

import hr.tomislavrekic.factorynews.NewsDatabase.NDBAdapter;
import retrofit2.Callback;

import static hr.tomislavrekic.factorynews.Constants.TAG;


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
    public List<NewsItem> fetchFromDB() {
        return mAdapter.readDB();
    }


}
