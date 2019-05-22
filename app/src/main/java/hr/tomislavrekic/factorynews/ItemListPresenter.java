package hr.tomislavrekic.factorynews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemListPresenter implements ItemListContract.Presenter {

    private ItemListContract.View view;
    private ItemListContract.Model model;

    public ItemListPresenter(ItemListContract.View view) {
        this.view = view;
        model = new ItemListModel();

        initData();
    }

    @Override
    public void initData(){
        model.initData(new Callback<NewsArticleResponse>() {
            @Override
            public void onResponse(Call<NewsArticleResponse> call, Response<NewsArticleResponse> response) {
                view.updateAdapter(model.convertData(response.body().getArticles()));
            }

            @Override
            public void onFailure(Call<NewsArticleResponse> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    @Override
    public void updateData(){
        model.updateData();
    }

    @Override
    public List<NewsItem> getData() {
        return model.getData();
    }

    @Override
    public int getDataCount() {
        return model.getDataCount();
    }
}
