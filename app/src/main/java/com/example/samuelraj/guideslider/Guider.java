package com.example.samuelraj.guideslider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;

public class Guider extends AppCompatActivity {

    ViewPager viewPager;
    PrefManager prefManager;
    SharedPreferences.Editor assistDot_User;
    Button button;
    Bundle bundle;
    EditText editText;
    Intent intent;
    int pos,len;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_guider);
        intent = new Intent(this, MainActivity.class);
        //For OneTimeUserInput
        editText = (EditText) findViewById(R.id.editText);
        assistDot_User = getSharedPreferences("assistDot_User", MODE_PRIVATE).edit();

        prefManager = new PrefManager(this);
        if (!prefManager.IsOneTime()) {
            launchHomeScreen();
            finish();
        }
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        button = (Button) findViewById(R.id.button);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SliderAdapter(getApplicationContext(),viewPager));
        pos = 0;
        len = viewPager.getAdapter().getCount();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                while(true)
                {
                    if(pos >= (len-1))
                    {
                        Log.d("App:",""+SliderAdapter.getUsername());
                        launchHomeScreen();
                        finish();
                    }
                    else
                    {
                        pos += 1;
                        if (pos == 4){
                            button.setText("FINISH");

                        }
                    }
                    Log.d("App:",""+pos);
                    viewPager.setCurrentItem(pos);
                    break;
                }
            }
        });
    }
    private void launchHomeScreen() {
        prefManager.IsOneTime(false);
        Intent intent = new Intent(this,MainActivity.class).putExtra("username",SliderAdapter.username);
        startActivity(intent);
        finish();
    }
}
