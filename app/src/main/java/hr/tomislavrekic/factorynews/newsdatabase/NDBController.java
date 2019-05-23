package hr.tomislavrekic.factorynews.newsdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


import static hr.tomislavrekic.factorynews.util.Constants.TAG;

public class NDBController {
    private SQLiteDatabase db;
    private NDBHelper dbHelper;

    public NDBController(Context context) {
        dbHelper = new NDBHelper(context);
    }

    private void openDbForWrite(){
        db = dbHelper.getWritableDatabase();
    }

    private void openDbForRead(){
        db = dbHelper.getReadableDatabase();
    }

    private void closeDb(){
        db.close();
    }

    public long insertRow(NDBSingleUnit input){
        openDbForWrite();

        long newRowId = db.insert(NDBContract.NDBEntry.TABLE_NAME, null, inputToContentValues(input));

        Log.d(TAG, "insertRow: "+ newRowId);

        closeDb();
        return newRowId;
    }

    public List<NDBSingleUnit> readAll(){ return readDb("%"); }

    public List<NDBSingleUnit> readDb(String search){
        openDbForRead();

        String[] projection = {
                BaseColumns._ID,
                NDBContract.NDBEntry.COLUMN_NAME_AUTHOR,
                NDBContract.NDBEntry.COLUMN_NAME_TITLE,
                NDBContract.NDBEntry.COLUMN_NAME_DESC,
                NDBContract.NDBEntry.COLUMN_NAME_URL,
                NDBContract.NDBEntry.COLUMN_NAME_IMGURL,
                NDBContract.NDBEntry.COLUMN_NAME_IMG,
                NDBContract.NDBEntry.COLUMN_NAME_DATE,
        };

        String selection = NDBContract.NDBEntry._ID + " LIKE ?";
        String[] selectionArgs = {search};
        String sortOrder = NDBContract.NDBEntry._ID + " ASC";

        Cursor cursor = db.query(
                NDBContract.NDBEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List<NDBSingleUnit> outputRows = new ArrayList<>();

        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(NDBContract.NDBEntry._ID));
            String author = cursor.getString(cursor.getColumnIndexOrThrow(NDBContract.NDBEntry.COLUMN_NAME_AUTHOR));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(NDBContract.NDBEntry.COLUMN_NAME_TITLE));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(NDBContract.NDBEntry.COLUMN_NAME_DESC));
            String url = cursor.getString(cursor.getColumnIndexOrThrow(NDBContract.NDBEntry.COLUMN_NAME_URL));
            String urlImg = cursor.getString(cursor.getColumnIndexOrThrow(NDBContract.NDBEntry.COLUMN_NAME_IMGURL));
            byte[] imgByte = cursor.getBlob(cursor.getColumnIndexOrThrow(NDBContract.NDBEntry.COLUMN_NAME_IMG));
            String date = cursor.getString(cursor.getColumnIndexOrThrow(NDBContract.NDBEntry.COLUMN_NAME_DATE));


            Bitmap img = null;
            if(imgByte != null){
                img = BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
            }

            NDBSingleUnit tempUnit = new NDBSingleUnit(itemId, author, title, desc, url, urlImg, date, img);

            outputRows.add(tempUnit);

            Log.d(TAG, "readDb: " + itemId + " " + author + " " + title);
        }
        cursor.close();
        closeDb();

        return outputRows;
    }

    public void updateRow(NDBSingleUnit input){
        openDbForWrite();

        String selection = NDBContract.NDBEntry._ID + " LIKE ?";
        String[] selectionArgs = { String.valueOf(input.getId())};

        ContentValues values = inputToContentValues(input);

        db.update(
                NDBContract.NDBEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);

    }


    private ContentValues inputToContentValues(NDBSingleUnit input){
        ContentValues values = new ContentValues();

        values.put(NDBContract.NDBEntry._ID, input.getId());
        values.put(NDBContract.NDBEntry.COLUMN_NAME_AUTHOR, input.getAuthor());
        values.put(NDBContract.NDBEntry.COLUMN_NAME_TITLE, input.getTitle());
        values.put(NDBContract.NDBEntry.COLUMN_NAME_DESC, input.getDesc());
        values.put(NDBContract.NDBEntry.COLUMN_NAME_URL, input.getUrl());
        values.put(NDBContract.NDBEntry.COLUMN_NAME_IMGURL, input.getUrlImg());
        values.put(NDBContract.NDBEntry.COLUMN_NAME_IMG, getBitmapAsByteArray(input.getImage()));
        values.put(NDBContract.NDBEntry.COLUMN_NAME_DATE, input.getDate());

        return values;
    }

    private static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        if(bitmap == null) return null;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }
}
