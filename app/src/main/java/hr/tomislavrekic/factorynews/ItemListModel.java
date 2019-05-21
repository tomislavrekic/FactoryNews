package hr.tomislavrekic.factorynews;

import java.util.ArrayList;
import java.util.List;

public class ItemListModel implements ItemListContract.Model {
    List<NewsItem> newsData;


    @Override
    public void initData(){
        newsData = new ArrayList<>();

        newsData.add(new NewsItem(null, "FIRST", "THIS IS FIRST"));
        newsData.add(new NewsItem(null, "SEC", "THIS IS SEC"));
        newsData.add(new NewsItem(null, "THRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHRHR" +
                "HRHRHRHRHRHRHRHRHRHRHRHRHRHRHR", "THIS IS THR"));
        newsData.add(new NewsItem(null, "FRT", "THIS IS FRT"));
        newsData.add(new NewsItem(null, "FIF", "THIS IS FIF"));

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
