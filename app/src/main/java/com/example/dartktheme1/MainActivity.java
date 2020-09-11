package com.example.dartktheme1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.shape.MaterialShapeUtils;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static int checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        final SharedPreferences.Editor myEdit = sharedpreferences.edit();
        int default1;
        switch (Configuration.UI_MODE_NIGHT_MASK & AppCompatDelegate.getDefaultNightMode()){
            case AppCompatDelegate.MODE_NIGHT_NO:
                default1=0;
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                default1=1;
                break;
            default:
                default1=2;
        }
        checked=sharedpreferences.getInt("checked",default1);
        switch (checked){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 1:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
        }
        Button button=findViewById(R.id.button);
        final String[] strings={"Light","Dark","System Default"};
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder builder=new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setTitle("Theme");
                //builder.setMessage("Test dialog");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("Which", String.valueOf(which)+" "+ checked);

                        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                       if(checked==0){
                           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                       }
                       if(checked==1){
                           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                       }
                       if(checked==2){
                           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                       }
                        restart();

                    }
                });
                builder.setSingleChoiceItems(strings, checked, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checked=which;
                        myEdit.putInt("checked",checked);
                        myEdit.apply();

                    }
                });
                builder.setNeutralButton("Cancel",null);
                builder.show();


            }
        });

    }
    public void restart(){
        finish();
        startActivity(new Intent(this,MainActivity.class));
    }
}