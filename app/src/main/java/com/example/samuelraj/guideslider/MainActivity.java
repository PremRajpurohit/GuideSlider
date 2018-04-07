package com.example.samuelraj.guideslider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    PrefManager prefManager;
    String bundle;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().hasExtra("username"))
        {
            Toast.makeText(this,getIntent().getStringExtra("username"),Toast.LENGTH_SHORT).show();
        }
        prefManager = new PrefManager(this);
        textView = (TextView) findViewById(R.id.textView);
    }
    public void replay(View view) {
        prefManager.IsOneTime(true);

        startActivity(new Intent(this, Guider.class));
        finish();
    }


}
