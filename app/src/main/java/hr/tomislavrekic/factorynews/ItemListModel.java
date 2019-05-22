package hr.tomislavrekic.factorynews;

import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import hr.tomislavrekic.factorynews.NewsDatabase.NDBAdapter;
import retrofit2.Callback;

import static hr.tomislavrekic.factorynews.Constants.TAG;


public class ItemListModel implements ItemListContract.Model {
    //private List<NewsArticleItem> responseData;
    //private List<NewsItem> newsData;
    private NDBAdapter mAdapter;

    public ItemListModel(){
        mAdapter = new NDBAdapter(MainActivity.getContext());
    }

    @Override
    public void initData(Callback<NewsArticleResponse> callback){

        NewsArticleService service = new NewsArticleService();
        service.getNewsResponse(callback);

    }

    @Override
    public List<NewsItem> convertData(List<NewsArticleItem> responseData) {
        List<NewsItem> newsData = new ArrayList<>();

        for(int i=0;i<responseData.size();i++){
            NewsArticleItem temp = responseData.get(i);

            newsData.add(new NewsItem(i, temp.getAuthor(), temp.getTitle(), temp.getDescription(),
                    temp.getUrl(), temp.getUrlToImage(), null, temp.getPublishedAt()));

            Log.d(TAG, "PASSED" + responseData.size());
        }
        return newsData;
    }

    @Override
    public void storeToDB(List<NewsItem> input) {
        mAdapter.updateDB(input);


    }

    @Override
    public List<NewsItem> fetchFromDB() {
        return mAdapter.readDB();
    }


    @Override
    public void updateData(){

    }

    @Override
    public List<NewsItem> getData() {
        return null;
    }

    @Override
    public int getDataCount() {
        return 0;
    }
}
