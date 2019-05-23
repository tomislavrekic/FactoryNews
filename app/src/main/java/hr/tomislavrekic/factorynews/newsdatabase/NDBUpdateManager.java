package hr.tomislavrekic.factorynews.newsdatabase;

import android.content.Context;
import android.util.Log;

import java.util.List;

import static hr.tomislavrekic.factorynews.util.Constants.TAG;

public class NDBUpdateManager {
    private NDBController controller;

    NDBUpdateManager(Context context){
        controller = new NDBController(context);
    }

    public void updateRow(NDBSingleUnit input){

        if(controller.readDb(String.valueOf(input.getId())).size() == 0){
            controller.insertRow(input);
            Log.d(TAG, "updateRow: insert");
        }
        else{
            //controller.insertRow(input);
            controller.updateRow(input);
            Log.d(TAG, "updateRow: update");
        }
    }

    public List<NDBSingleUnit> readAll(){
        return controller.readAll();
    }
}
