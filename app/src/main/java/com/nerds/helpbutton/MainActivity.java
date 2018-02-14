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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        ImageButton emergencyBtn=findViewById(R.id.emergencyButton);

        final Actions actions=new Actions(context);

        emergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actions.sendMessage("05368925985","Acil yardımına ihtiyacım var bul beni.");
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                actions.makeCall("05368925985");
            }
        });



    }
}
