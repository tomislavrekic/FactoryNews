package hr.tomislavrekic.factorynews.newsdatabase;

import android.provider.BaseColumns;

public final class NDBContract {
        private NDBContract() {}

        public static class NDBEntry implements BaseColumns {
            //

            public static final String TABLE_NAME = "news";
            public static final String COLUMN_NAME_AUTHOR = "author";
            public static final String COLUMN_NAME_TITLE  = "title";
            public static final String COLUMN_NAME_DESC = "desc";
            public static final String COLUMN_NAME_URL = "url";
            public static final String COLUMN_NAME_IMGURL = "urlImg";
            public static final String COLUMN_NAME_DATE = "date";
            public static final String COLUMN_NAME_IMG = "img";


        }
}
