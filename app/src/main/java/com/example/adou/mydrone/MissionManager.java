package com.example.adou.mydrone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by rostom on 13/03/18.
 */

public class MissionManager extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_manager);
    }

    public void mesMissions(View view) {
        Intent mesMissions = new Intent(this, MesMissions.class);
        startActivity(mesMissions);
    }

    public void newMission(View view) {
        Intent newMissions = new Intent(this, NewMission.class);
        startActivity(newMissions);
    }

}
