package hr.tomislavrekic.factorynews;

import android.graphics.Bitmap;

public class NewsItem {
    private Bitmap thumbnail;
    private String title;
    private String newsBody;

    public NewsItem(Bitmap thumbnail, String title, String newsBody) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.newsBody = newsBody;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNewsBody() {
        return newsBody;
    }

    public void setNewsBody(String newsBody) {
        this.newsBody = newsBody;
    }
}
