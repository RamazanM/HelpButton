package com.nerds.helpbutton;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by ramazan on 2/14/18.
 */

public class Actions {

    private Context ctx;

    public Actions(Context ctx){
        this.ctx=ctx;
    }

    public void makeCall(String number){
        Intent intent = new Intent(Intent.ACTION_CALL);

        intent.setData(Uri.parse("tel:" + number));
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        ctx.startActivity(intent);
    }

    public void sendMessage(String number,String messageText){
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, messageText, null, null);
            Toast.makeText(ctx.getApplicationContext(), "Mesajınız gönderildi...",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(ctx.getApplicationContext(), ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}
