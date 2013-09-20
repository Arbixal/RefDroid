package com.arbixal.refphone;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Caleb on 18/08/13.
 * ContentProvider for the App
 */
public class CRefPhoneProvider extends ContentProvider
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "refphone.db";

    private static final int MATCH_DIVISIONS = 1;
    private static final int MATCH_DIVISION_ID = 2;

    private static final String DIVISION_TABLE_CREATE =
            "CREATE TABLE " + CBusinessObjects.CDivision.TABLE_NAME + " (" +
                    CBusinessObjects.CDivision._ID + " INT PRIMARY KEY, " +
                    CBusinessObjects.CDivision.COLUMN_NAME_NAME + " TEXT, " +
                    CBusinessObjects.CDivision.COLUMN_NAME_GAME_TIME + " INT, " +
                    CBusinessObjects.CDivision.COLUMN_NAME_EXTRA_TIME + " INT);";

    private CRefPhoneOpenHelper mDatabaseOpenHelper;

    private static final HashMap<String,String> mColumnMap = buildColumnMap();
    private static final UriMatcher mUriMatcher = buildUriMatcher();
    private static final String TAG = "CRefPhoneProvider";

    private static UriMatcher buildUriMatcher()
    {
        UriMatcher newMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        newMatcher.addURI(CBusinessObjects.AUTHORITY, "divisions", CRefPhoneProvider.MATCH_DIVISIONS);
        newMatcher.addURI(CBusinessObjects.AUTHORITY, "divisions/#", CRefPhoneProvider.MATCH_DIVISION_ID);

        return (newMatcher);
    }

    private static HashMap<String,String> buildColumnMap()
    {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put(CBusinessObjects.CDivision._ID, CBusinessObjects.CDivision._ID);
        map.put(CBusinessObjects.CDivision.COLUMN_NAME_NAME, CBusinessObjects.CDivision.COLUMN_NAME_NAME);
        map.put(CBusinessObjects.CDivision.COLUMN_NAME_GAME_TIME, CBusinessObjects.CDivision.COLUMN_NAME_GAME_TIME);
        map.put(CBusinessObjects.CDivision.COLUMN_NAME_EXTRA_TIME, CBusinessObjects.CDivision.COLUMN_NAME_EXTRA_TIME);
        return map;
    }

    @Override
    public boolean onCreate()
    {
        Log.v(CRefPhoneProvider.TAG, CRefPhoneProvider.TAG + ":OnCreate()");
        this.mDatabaseOpenHelper = new CRefPhoneOpenHelper(this.getContext());
        return true;
    }

    @Override
    public Cursor query(Uri pUri, String[] pProjection, String pSelection, String[] pSelectionArgs, String pSortOrder)
    {
        Log.v(CRefPhoneProvider.TAG, CRefPhoneProvider.TAG + ":query()");
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        switch (CRefPhoneProvider.mUriMatcher.match(pUri))
        {
            case CRefPhoneProvider.MATCH_DIVISIONS:
                qb.setTables(CBusinessObjects.CDivision.TABLE_NAME);
                qb.setProjectionMap(CRefPhoneProvider.mColumnMap);
                break;
            case CRefPhoneProvider.MATCH_DIVISION_ID:
                qb.setTables(CBusinessObjects.CDivision.TABLE_NAME);
                qb.setProjectionMap(CRefPhoneProvider.mColumnMap);
                qb.appendWhere(CBusinessObjects.CDivision._ID + "=" + pUri.getPathSegments().get(CBusinessObjects.CDivision.DIVISION_ID_PATH_POSITION));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + pUri);
        }

        SQLiteDatabase db = this.mDatabaseOpenHelper.getReadableDatabase();
        if (db == null) return (null);

        Cursor c = qb.query(db, pProjection, pSelection, pSelectionArgs, null, null, pSortOrder);

        c.setNotificationUri(getContext().getContentResolver(), pUri);
        return (c);
    }

    @Override
    public String getType(Uri pUri)
    {
        Log.v(CRefPhoneProvider.TAG, CRefPhoneProvider.TAG + ":getType()");
        switch (CRefPhoneProvider.mUriMatcher.match(pUri))
        {
            case CRefPhoneProvider.MATCH_DIVISIONS:
                return (CBusinessObjects.CDivision.CONTENT_TYPE);
            case CRefPhoneProvider.MATCH_DIVISION_ID:
                return (CBusinessObjects.CDivision.CONTENT_ITEM_TYPE);
            default:
                throw new IllegalArgumentException("Unknown URI " + pUri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues)
    {
        Log.v(CRefPhoneProvider.TAG, CRefPhoneProvider.TAG + ":insert()");
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings)
    {
        Log.v(CRefPhoneProvider.TAG, CRefPhoneProvider.TAG + ":delete()");
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings)
    {
        Log.v(CRefPhoneProvider.TAG, CRefPhoneProvider.TAG + ":update()");
        return 0;
    }

    private class CRefPhoneOpenHelper extends SQLiteOpenHelper
    {
        private static final String TAG = "CRefPhoneOpenHelper";

        CRefPhoneOpenHelper(Context pContext)
        {
            super(pContext, CRefPhoneProvider.DATABASE_NAME, null, CRefPhoneProvider.DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase pSQLiteDatabase)
        {
            Log.v(CRefPhoneOpenHelper.TAG, CRefPhoneOpenHelper.TAG + ":OnCreate()");
            pSQLiteDatabase.execSQL(CRefPhoneProvider.DIVISION_TABLE_CREATE);
            pSQLiteDatabase.execSQL("INSERT INTO " + CBusinessObjects.CDivision.TABLE_NAME + " (" + CBusinessObjects.CDivision.COLUMN_NAME_NAME + "," + CBusinessObjects.CDivision.COLUMN_NAME_GAME_TIME + "," + CBusinessObjects.CDivision.COLUMN_NAME_EXTRA_TIME + ") VALUES ('Under 12/13', 30, 10)");
            pSQLiteDatabase.execSQL("INSERT INTO " + CBusinessObjects.CDivision.TABLE_NAME + " (" + CBusinessObjects.CDivision.COLUMN_NAME_NAME + "," + CBusinessObjects.CDivision.COLUMN_NAME_GAME_TIME + "," + CBusinessObjects.CDivision.COLUMN_NAME_EXTRA_TIME + ") VALUES ('Under 14/15', 35, 10)");
            pSQLiteDatabase.execSQL("INSERT INTO " + CBusinessObjects.CDivision.TABLE_NAME + " (" + CBusinessObjects.CDivision.COLUMN_NAME_NAME + "," + CBusinessObjects.CDivision.COLUMN_NAME_GAME_TIME + "," + CBusinessObjects.CDivision.COLUMN_NAME_EXTRA_TIME + ") VALUES ('AAM Youth Grade', 45, 10)");
            pSQLiteDatabase.execSQL("INSERT INTO " + CBusinessObjects.CDivision.TABLE_NAME + " (" + CBusinessObjects.CDivision.COLUMN_NAME_NAME + "," + CBusinessObjects.CDivision.COLUMN_NAME_GAME_TIME + "," + CBusinessObjects.CDivision.COLUMN_NAME_EXTRA_TIME + ") VALUES ('AAW Premier League', 45, 10)");
            pSQLiteDatabase.execSQL("INSERT INTO " + CBusinessObjects.CDivision.TABLE_NAME + " (" + CBusinessObjects.CDivision.COLUMN_NAME_NAME + "," + CBusinessObjects.CDivision.COLUMN_NAME_GAME_TIME + "," + CBusinessObjects.CDivision.COLUMN_NAME_EXTRA_TIME + ") VALUES ('AAM Reserve Grade', 45, 10)");
            pSQLiteDatabase.execSQL("INSERT INTO " + CBusinessObjects.CDivision.TABLE_NAME + " (" + CBusinessObjects.CDivision.COLUMN_NAME_NAME + "," + CBusinessObjects.CDivision.COLUMN_NAME_GAME_TIME + "," + CBusinessObjects.CDivision.COLUMN_NAME_EXTRA_TIME + ") VALUES ('AAM Premier League', 45, 10)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2)
        {
            Log.v(CRefPhoneOpenHelper.TAG, CRefPhoneOpenHelper.TAG + ":onUpgrade()");
        }
    }
}
