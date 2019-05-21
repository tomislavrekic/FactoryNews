package hr.tomislavrekic.factorynews;


import retrofit2.Callback;

public abstract class CallbackDelegate<T> implements Callback<T> {
    NewsArticleResponseDelegate mDelegate;
    public CallbackDelegate(NewsArticleResponseDelegate delegate){
        super();
        mDelegate=delegate;
    }
}
