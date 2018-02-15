package com.nerds.helpbutton;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        final LocalStorage localStorage=new LocalStorage(context);
        final LocationProvider locationProvider=new LocationProvider(context);
        if(localStorage.getString(localStorage.PHONE_1)==""){
            Intent i=new Intent(context,Settings.class);
            startActivity(i);
        }

        ImageButton emergencyBtn=findViewById(R.id.emergencyButton);

        final Actions actions=new Actions(context);


        emergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message=localStorage.getString(localStorage.MESSAGE);
                if(localStorage.getBoolean(localStorage.SEND_LOCATION)){
                    message+="\nKonum: http://maps.google.com/?q="+locationProvider.getLatitude()+","+locationProvider.getLongitude();
                }
                actions.sendMessage(localStorage.getString(localStorage.PHONE_1),message);
                if(localStorage.getString(localStorage.PHONE_2)!="")
                    actions.sendMessage(localStorage.getString(localStorage.PHONE_2),message);

                if (localStorage.getBoolean(localStorage.CALL_AFTER_MESSAGE)){
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    actions.makeCall(localStorage.getString(localStorage.PHONE_1));

                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle()=="Ayarlar"){
            Intent i=new Intent(context,Settings.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Ayarlar");
        return super.onCreateOptionsMenu(menu);
    }
}
