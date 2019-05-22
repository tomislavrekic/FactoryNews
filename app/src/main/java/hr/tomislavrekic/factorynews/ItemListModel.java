package hr.tomislavrekic.factorynews;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static hr.tomislavrekic.factorynews.Constants.TAG;

public class ItemListModel implements ItemListContract.Model {
    //private List<NewsArticleItem> responseData;
    //private List<NewsItem> newsData;

    @Override
    public void initData(Callback<NewsArticleResponse> callback){

        NewsArticleService service = new NewsArticleService();
        service.getNewsResponse(callback);

    }

    @Override
    public List<NewsItem> convertData(List<NewsArticleItem> responseData) {
        List<NewsItem> newsData = new ArrayList<>();

        for(int i=0;i<responseData.size();i++){
           newsData.add(new NewsItem(null, responseData.get(i).getTitle(),responseData.get(i).getDescription()));
        }
        return newsData;
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
