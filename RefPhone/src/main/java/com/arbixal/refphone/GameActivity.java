package com.arbixal.refphone;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.UUID;

public class GameActivity extends Activity
{
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }
    
}
