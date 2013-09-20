package com.arbixal.refphone;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.DialogFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends Activity implements GameTimeDialogFragment.GameTimeDialogListener
{
  private static final String TAG = "MainActivity";
  //0xA9, 0x8C, 0x17, 0x93, 0xC7, 0xF7, 0x4D, 0x7A, 0xAF, 0xF0, 0x0A, 0x8B, 0x33, 0xCC, 0x97, 0xDD
  //A98C1793-C7F7-4D7A-AFF0-0A8B33CC97DD
  private static final UUID APP_UUID = UUID.fromString("a98c1793-c7f7-4d7a-aff0-0a8b33cc97dd");

  private static final int COMM_HOME_TEAM = 0;
  private static final int COMM_AWAY_TEAM = 1;
  private static final int COMM_START = 2;
  private static final int COMM_STOP = 3;
  private static final int COMM_GAME_TIME = 4;
  private static final int COMM_EXTRA_TIME = 5;
  private static final int COMM_START_INJURY_TIME = 6;
  private static final int COMM_STOP_INJURY_TIME = 7;
  private static final int COMM_EVENT = 8;

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

      final EditText txtHomeTeam = (EditText)this.findViewById(R.id.txtHomeTeam);
      final EditText txtAwayTeam = (EditText)this.findViewById(R.id.txtAwayTeam);

      Button btnCreateGame = (Button)this.findViewById(R.id.btnCreateGame);
      btnCreateGame.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
          if (txtHomeTeam.getText() != null) MainActivity.this.mHomeTeam = txtHomeTeam.getText().toString();
          if (txtAwayTeam.getText() != null) MainActivity.this.mAwayTeam = txtAwayTeam.getText().toString();

          Intent intent = new Intent(MainActivity.this, GameActivity.class);
          intent.putExtra(GameActivity.EXTRA_GAMETIME, MainActivity.this.mGameTime);
          intent.putExtra(GameActivity.EXTRA_EXTRATIME, MainActivity.this.mExtraTime);
          intent.putExtra(GameActivity.EXTRA_HOMETEAM, MainActivity.this.mHomeTeam);
          intent.putExtra(GameActivity.EXTRA_AWAYTEAM, MainActivity.this.mAwayTeam);
          startActivity(intent);
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
