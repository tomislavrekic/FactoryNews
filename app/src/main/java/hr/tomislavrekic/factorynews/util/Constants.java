package hr.tomislavrekic.factorynews.util;

public final class Constants {
    public static final String TAG = "debugTag";
    public static final String NEWS_ARTICLE_PATH = "/v1/articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398";//
    public static final String NEWS_ARTICLE_BASE = "https://newsapi.org/";
    public static final String TIMESTAMP_FILENAME = "timestamp.txt";
    public static final String F_IMAGE_KEY = "FImageKey";
    public static final String F_TITLE_KEY = "FTitleKey";
    public static final String F_TEXT_KEY = "FTextKey";
    public static final String F_DATE_KEY = "FDateKey";
    public static final float IMAGE_SCALE_FACTOR = 0.5f;
    public static final String SINGLE_NEWS_POSITION = "SingleNewsPosition";
    public static final int REFRESH_INTERVAL = 5 * 60 * 1000;
}
