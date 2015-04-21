package edu.washington.wsmay1.lifecounter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import java.util.*;


public class MainActivity extends ActionBarActivity {
    ArrayList<Integer> totals;
    int loser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totals = new ArrayList<Integer>();
        TextView life4 = (TextView) findViewById(R.id.P4life);
        TextView life3 = (TextView) findViewById(R.id.P3life);
        TextView life2 = (TextView) findViewById(R.id.P2life);
        TextView life = (TextView) findViewById(R.id.P1life);
        if (savedInstanceState == null) {
            for (int i = 0; i < 4; i++) {
                totals.add(20);
            }
            life.setText("" + 20);
            life2.setText("" + 20);
            life3.setText("" + 20);
            life4.setText("" + 20);
            loser = 0;
        } else {
            totals = savedInstanceState.getIntegerArrayList("totals");
            loser = savedInstanceState.getInt("loser");
            life.setText("" + totals.get(0));
            life2.setText("" + totals.get(1));
            life3.setText("" + totals.get(2));
            life4.setText("" + totals.get(3));
            if (loser != 0) {
                declareLoser(loser);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculateLife(View v) {
        int foundId = v.getId();
        String idName = v.getResources().getResourceName(foundId);
        boolean addition = false;
        int value = 1;
        int player = 1;
        if (idName.contains("plus")) {
            addition = true;
        }
        if (idName.contains("5")) {
            value = 5;
        }
        TextView lifeCount;
        if (idName.contains("P2")) {
            lifeCount = (TextView) findViewById(R.id.P2life);
            player = 2;
        } else if (idName.contains("P3")) {
            lifeCount = (TextView) findViewById(R.id.P3life);
            player = 3;
        } else if (idName.contains("P4")) {
            lifeCount = (TextView) findViewById(R.id.P4life);
            player = 4;
        } else {
            lifeCount = (TextView) findViewById(R.id.P1life);
        }
        int life = Integer.parseInt(lifeCount.getText().toString());
        if (addition) {
            life += value;
        } else {
            life -= value;
        }
        lifeCount.setText("" + life);
        totals.set(player - 1, life);
        if (life <= 0) {
            declareLoser(player);
        }
    }

    public void declareLoser(int player) {
        TextView loserText = (TextView) findViewById(R.id.loser);
        loserText.setText("Player " + player + " LOSES!");
        loserText.setVisibility(View.VISIBLE);
        loser = player;
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putIntegerArrayList("totals", totals);
        savedInstanceState.putInt("loser", loser);
    }
}
