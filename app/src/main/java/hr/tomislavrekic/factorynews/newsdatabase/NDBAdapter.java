package hr.tomislavrekic.factorynews.newsdatabase;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import hr.tomislavrekic.factorynews.model.NewsItem;

public class NDBAdapter {
    private NDBUpdateManager updateManager;

    public NDBAdapter(Context context){
        updateManager = new NDBUpdateManager(context);
    }

    public void updateDB(List<NewsItem> input){

        for(NewsItem item : input){
            NDBSingleUnit temp = new NDBSingleUnit(item.getId(), item.getAuthor(), item.getTitle(),
                    item.getNewsBody(), item.getUrl(), item.getUrlImg(), item.getDate() , item.getThumbnail());

            updateManager.updateRow(temp);
        }
    }

    public List<NewsItem> readDB(){
        List<NDBSingleUnit> response = updateManager.readAll();
        List<NewsItem> out = new ArrayList<>();

        for(NDBSingleUnit item : response){
            out.add(new NewsItem(item.getId(), item.getAuthor(), item.getTitle(), item.getDesc(),
                    item.getUrl(), item.getUrlImg(), item.getImage(), item.getDate()));
        }
        return out;
    }
}
