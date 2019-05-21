package hr.tomislavrekic.factorynews;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static hr.tomislavrekic.factorynews.Constants.TAG;

public class ItemListModel implements ItemListContract.Model {
    private List<NewsArticleItem> responseData;
    private List<NewsItem> newsData;

    @Override
    public void initData(){
        responseData = new ArrayList<>();
        newsData = new ArrayList<>();

        NewsArticleService service = new NewsArticleService();
        service.getNewsResponse(new NewsArticleResponseDelegate() {
            @Override
            public void processFinished(NewsArticleResponse response) {
                try {
                    Log.d(TAG, "onResponse: " + response.getArticles().get(0).getTitle());
                    responseData = response.getArticles();

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        for(int i=0;i<responseData.size();i++){
            newsData.get(i).setNewsBody(responseData.get(i).getDescription());
            newsData.get(i).setTitle(responseData.get(i).getTitle());
            newsData.get(i).setThumbnail(null);
        }

    }

    @Override
    public void updateData(){

    }

    @Override
    public List<NewsItem> getData() {
        return newsData;
    }

    @Override
    public int getDataCount() {
        return newsData.size();
    }
}
