package hr.tomislavrekic.factorynews.NewsDatabase;

import android.graphics.Bitmap;

import java.sql.Date;

public class NDBSingleUnit {
    private long id;
    private String author;
    private String title;
    private String desc;
    private String url;
    private String urlImg;
    private Date date;
    private Bitmap image;

    public NDBSingleUnit(long id, String author, String title, String desc, String url, String urlImg, Date date, Bitmap image) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.desc = desc;
        this.url = url;
        this.urlImg = urlImg;
        this.date = date;
        this.image = image;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
