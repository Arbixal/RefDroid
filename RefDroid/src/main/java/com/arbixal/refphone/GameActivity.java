package com.arbixal.refphone;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.getpebble.android.kit.PebbleKit;
import com.getpebble.android.kit.util.PebbleDictionary;

import java.util.UUID;

public class GameActivity extends Activity
{
  public static final String EXTRA_GAMETIME = "com.arbixal.refphone.GAMETIME";
  public static final String EXTRA_EXTRATIME = "com.arbixal.refphone.EXTRATIME";
  public static final String EXTRA_HOMETEAM = "com.arbixal.refphone.HOMETEAM";
  public static final String EXTRA_AWAYTEAM = "com.arbixal.refphone.AWAYTEAM";

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
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_game);

      this.mGameTime = this.getIntent().getIntExtra(GameActivity.EXTRA_GAMETIME, 45);
      this.mExtraTime = this.getIntent().getIntExtra(GameActivity.EXTRA_EXTRATIME, 10);
      this.mHomeTeam = this.getIntent().getStringExtra(GameActivity.EXTRA_HOMETEAM);
      this.mAwayTeam = this.getIntent().getStringExtra(GameActivity.EXTRA_AWAYTEAM);

      PebbleDictionary dictionary = new PebbleDictionary();
      dictionary.addUint8(COMM_GAME_TIME, (byte)this.mGameTime);
      dictionary.addUint8(COMM_EXTRA_TIME, (byte)this.mExtraTime);

      dictionary.addString(COMM_HOME_TEAM, this.mHomeTeam);
      dictionary.addString(COMM_AWAY_TEAM, this.mAwayTeam);

      PebbleKit.sendDataToPebble(this.getApplicationContext(), this.APP_UUID, dictionary);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }
    
}
