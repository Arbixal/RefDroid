package com.arbixal.refphone;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Caleb on 23/08/13.
 * Collection of Business Objects that are required by the app
 */
public final class CBusinessObjects
{
    public static final String AUTHORITY = "com.arbixal.provider.RefPhone";

    private CBusinessObjects() { }

    public static final class CDivision implements BaseColumns
    {
        private static final String SCHEME = "content://";
        private static final String PATH_DIVISIONS = "/divisions";
        private static final String PATH_DIVISION_ID = "/divisions/";

        private CDivision() {}

        public static final String TABLE_NAME = "divisions";

        public static final int DIVISION_ID_PATH_POSITION = 1;

        public static final Uri CONTENT_URI = Uri.parse(CDivision.SCHEME + CBusinessObjects.AUTHORITY + CDivision.PATH_DIVISIONS);
        public static final Uri CONTENT_ID_URI_BASE = Uri.parse(CDivision.SCHEME + CBusinessObjects.AUTHORITY + CDivision.PATH_DIVISION_ID);
        public static final Uri CONTENT_ID_URI_PATTERN = Uri.parse(CDivision.SCHEME + CBusinessObjects.AUTHORITY + CDivision.PATH_DIVISION_ID + "/#");

        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.arbixal.refphone.division";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.arbixal.refphone.division";

        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_GAME_TIME = "GameTime";
        public static final String COLUMN_NAME_EXTRA_TIME = "ExtraTime";
    }
}
