package hr.tomislavrekic.factorynews.NewsDatabase;

import android.content.Context;

public class NDBUpdateManager {
    private NDBController controller;

    NDBUpdateManager(Context context){
        controller = new NDBController(context);
    }

    public void updateRow(NDBSingleUnit input){

        if(controller.readDb(String.valueOf(input.getId())).get(0) == null){
            controller.insertRow(input);
        }
        else{
            controller.updateRow(input);
        }


    }
}
