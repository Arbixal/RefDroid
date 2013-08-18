package com.arbixal.refphone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Caleb on 18/08/13.
 */
public class CDivisionOpenHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DIVISION_TABLE_NAME = "division";

    private static final String DIVISION_FIELD_ID = "ID";
    private static final String DIVISION_FIELD_NAME = "Name";
    private static final String DIVISION_FIELD_GAME_TIME = "GameTime";
    private static final String DIVISION_FIELD_EXTRA_TIME = "ExtraTime";

    private static final String DIVISION_TABLE_CREATE =
            "CREATE TABLE + " + DIVISION_TABLE_NAME + " (" +
            DIVISION_FIELD_ID + " INT, " +
            DIVISION_FIELD_NAME + " TEXT, " +
            DIVISION_FIELD_GAME_TIME + " INT, " +
            DIVISION_FIELD_EXTRA_TIME + " INT, " +
            "PRIMARY KEY (" + DIVISION_FIELD_ID + " ASC) AUTOINCREMENT);";

    CDivisionOpenHelper(Context pContext)
    {
        super(pContext, DIVISION_TABLE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase pSQLiteDatabase)
    {
        pSQLiteDatabase.execSQL(DIVISION_TABLE_CREATE);
        pSQLiteDatabase.execSQL("INSERT INTO " + DIVISION_TABLE_NAME + " (" + DIVISION_FIELD_NAME + "," + DIVISION_FIELD_GAME_TIME + "," + DIVISION_FIELD_EXTRA_TIME + ") VALUES ('Under 12/13', 30, 10)");
        pSQLiteDatabase.execSQL("INSERT INTO " + DIVISION_TABLE_NAME + " (" + DIVISION_FIELD_NAME + "," + DIVISION_FIELD_GAME_TIME + "," + DIVISION_FIELD_EXTRA_TIME + ") VALUES ('Under 14/15', 35, 10)");
        pSQLiteDatabase.execSQL("INSERT INTO " + DIVISION_TABLE_NAME + " (" + DIVISION_FIELD_NAME + "," + DIVISION_FIELD_GAME_TIME + "," + DIVISION_FIELD_EXTRA_TIME + ") VALUES ('AAM Youth Grade', 45, 10)");
        pSQLiteDatabase.execSQL("INSERT INTO " + DIVISION_TABLE_NAME + " (" + DIVISION_FIELD_NAME + "," + DIVISION_FIELD_GAME_TIME + "," + DIVISION_FIELD_EXTRA_TIME + ") VALUES ('AAW Premier League', 45, 10)");
        pSQLiteDatabase.execSQL("INSERT INTO " + DIVISION_TABLE_NAME + " (" + DIVISION_FIELD_NAME + "," + DIVISION_FIELD_GAME_TIME + "," + DIVISION_FIELD_EXTRA_TIME + ") VALUES ('AAM Reserve Grade', 45, 10)");
        pSQLiteDatabase.execSQL("INSERT INTO " + DIVISION_TABLE_NAME + " (" + DIVISION_FIELD_NAME + "," + DIVISION_FIELD_GAME_TIME + "," + DIVISION_FIELD_EXTRA_TIME + ") VALUES ('AAM Premier League', 45, 10)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2)
    {
    }
}
