package hr.tomislavrekic.factorynews.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

import hr.tomislavrekic.factorynews.model.NewsItem;
import hr.tomislavrekic.factorynews.util.Constants;

public class SingleNewsAdapter extends FragmentStatePagerAdapter {

    private List<NewsItem> mData;
    private SingleNewsActivity mView;

    public SingleNewsAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setmData(List<NewsItem> data){
        mData=data;
        notifyDataSetChanged();
    }

    public void setView(SingleNewsActivity view){
        mView=view;
    }

    @Override
    public Fragment getItem(int i) {
        return SingleNewsFragment.newInstance(mData.get(i));
    }

    public String getTitle(int pos){
        return mData.get(pos).getTitle();
    }

    @Override
    public int getCount() {
        return mData.size();
    }


}
