package hr.tomislavrekic.factorynews;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.spec.ECField;
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
