package hr.tomislavrekic.factorynews.NewsDatabase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hr.tomislavrekic.factorynews.Constants;
import hr.tomislavrekic.factorynews.NewsItem;

import static hr.tomislavrekic.factorynews.Constants.TAG;

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


            Log.d(TAG, "PASSEDAD" + temp.getTitle());
        }
    }

    public List<NewsItem> readDB(){
        List<NDBSingleUnit> response = updateManager.readAll();
        List<NewsItem> out = new ArrayList<>();

        for(NDBSingleUnit item : response){
            out.add(new NewsItem(item.getId(), item.getAuthor(), item.getTitle(), item.getDesc(),
                    item.getUrl(), item.getUrlImg(), item.getImage(), item.getDate()));
        }

        Log.d(TAG, "readDB: " + response.size());

        return out;
    }
}
