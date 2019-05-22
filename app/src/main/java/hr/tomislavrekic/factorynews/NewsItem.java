package hr.tomislavrekic.factorynews;

import android.graphics.Bitmap;

import java.sql.Date;

public class NewsItem {
    private long id;
    private String author;
    private String title;
    private String newsBody;
    private String url;
    private String urlImg;
    private Bitmap thumbnail;
    private String date;

    public NewsItem(long id, String author, String title, String newsBody, String url, String urlImg, Bitmap thumbnail, String date) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.newsBody = newsBody;
        this.url = url;
        this.urlImg = urlImg;
        this.thumbnail = thumbnail;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
