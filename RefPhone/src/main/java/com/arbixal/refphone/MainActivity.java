package com.arbixal.refphone;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.DialogFragment;
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
import android.widget.NumberPicker;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity implements GameTimeDialogFragment.GameTimeDialogListener
{
    private static final String TAG = "MainActivity";

    private int mGameTime = 45;
    private int mExtraTime = 10;
    private String mHomeTeam = "Home Team";
    private String mAwayTeam = "Away Team";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.v(MainActivity.TAG, MainActivity.TAG + ":OnCreate()");
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        final ActionBar actionBar = this.getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        TextView lblGameTime = (TextView)this.findViewById(R.id.lblGameTime);
        lblGameTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GameTimeDialogFragment dialog = new GameTimeDialogFragment(GameTimeDialogFragment.TimeType.GameTime, MainActivity.this.mGameTime);
                dialog.show(MainActivity.this.getFragmentManager(), "GameTimeDialog");
            }
        });

        TextView lblExtraTime = (TextView)this.findViewById(R.id.lblExtraTime);
        lblExtraTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GameTimeDialogFragment dialog = new GameTimeDialogFragment(GameTimeDialogFragment.TimeType.ExtraTime, MainActivity.this.mExtraTime);
                dialog.show(MainActivity.this.getFragmentManager(), "ExtraTimeDialog");
            }
        });
    }

    @Override
    public void onDialogPositiveClick(int pSelectedValue, GameTimeDialogFragment.TimeType pTimeType)
    {
        if (pTimeType == GameTimeDialogFragment.TimeType.GameTime)
        {
            this.mGameTime = pSelectedValue;
            this.updateGameTime();
        }
        else
        {
            this.mExtraTime = pSelectedValue;
            this.updateExtraTime();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.v(MainActivity.TAG, MainActivity.TAG + ":OnCreateOptionsMenu()");

        // Inflate the menu; this adds items to the action bar if it is present.
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void updateGameTime()
    {
        TextView lblGameTime = (TextView)this.findViewById(R.id.lblGameTime);
        lblGameTime.setText(String.format("%d mins", this.mGameTime));
    }

    private void updateExtraTime()
    {
        TextView lblExtraTime = (TextView)this.findViewById(R.id.lblExtraTime);
        lblExtraTime.setText(String.format("%d mins", this.mExtraTime));
    }
}
