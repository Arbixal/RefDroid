package com.arbixal.refphone;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemSelectedListener
{
    private static final int LOADER_DIVISION = 1;
    private static final String TAG = "MainActivity";

    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.v(MainActivity.TAG, MainActivity.TAG + ":OnCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_dropdown_item, null, new String[]{CBusinessObjects.CDivision.COLUMN_NAME_NAME}, new int[]{android.R.id.text1}, 0 );
        Spinner divisionSpinner = (Spinner) findViewById(R.id.spinner);
        divisionSpinner.setAdapter(this.mAdapter);
        divisionSpinner.setOnItemSelectedListener(this);
        this.getLoaderManager().initLoader(MainActivity.LOADER_DIVISION, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.v(MainActivity.TAG, MainActivity.TAG + ":OnCreateOptionsMenu()");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int pLoaderID, Bundle pBundle)
    {
        Log.v(MainActivity.TAG, MainActivity.TAG + ":onCreateLoader()");
        switch (pLoaderID)
        {
            case MainActivity.LOADER_DIVISION:
                return (new CursorLoader(this, CBusinessObjects.CDivision.CONTENT_URI, new String[]{CBusinessObjects.CDivision._ID, CBusinessObjects.CDivision.COLUMN_NAME_NAME}, null, null, null));
            default:
                throw new IllegalArgumentException("Unknown Loader ID " + pLoaderID);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> pLoader, Cursor pCursor)
    {
        Log.v(MainActivity.TAG, MainActivity.TAG + ":onLoadFinished()");
        this.mAdapter.swapCursor(pCursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> pLoader)
    {
        Log.v(MainActivity.TAG, MainActivity.TAG + ":onLoaderReset()");
        this.mAdapter.swapCursor(null);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        Log.v(MainActivity.TAG, MainActivity.TAG + ":onItemSelected()");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {
        Log.v(MainActivity.TAG, MainActivity.TAG + ":onItemSelected()");
    }
}
