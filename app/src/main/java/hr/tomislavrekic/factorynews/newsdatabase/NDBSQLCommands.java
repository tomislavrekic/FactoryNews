package hr.tomislavrekic.factorynews.newsdatabase;

public final class NDBSQLCommands {

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NDBContract.NDBEntry.TABLE_NAME + " (" +
                    NDBContract.NDBEntry._ID + " INTEGER PRIMARY KEY," +
                    NDBContract.NDBEntry.COLUMN_NAME_AUTHOR + " VARCHAR(50), " +
                    NDBContract.NDBEntry.COLUMN_NAME_TITLE + " VARCHAR(70), " +
                    NDBContract.NDBEntry.COLUMN_NAME_DESC + " VARCHAR(512)," +
                    NDBContract.NDBEntry.COLUMN_NAME_URL + " VARCHAR(100), " +
                    NDBContract.NDBEntry.COLUMN_NAME_IMGURL + " VARCHAR(100), " +
                    NDBContract.NDBEntry.COLUMN_NAME_DATE + " VARCHAR(20), " +
                    NDBContract.NDBEntry.COLUMN_NAME_IMG + " BLOB)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NDBContract.NDBEntry.TABLE_NAME;
}
