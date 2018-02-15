package com.nerds.helpbutton;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {
    private EditText number1;
    private EditText number2;
    private EditText message;
    private Context ctx;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx=this;
        setContentView(R.layout.activity_settings);
        final LocalStorage localStorage=new LocalStorage(this);

        number1=findViewById(R.id.number1);
        number2=findViewById(R.id.number2);
        message=findViewById(R.id.message);
        final Switch callAfterMessage=findViewById(R.id.callAfterMessage);
        final Switch sendLocation=findViewById(R.id.sendLocation);
        Button saveBtn=findViewById(R.id.saveBtn);

        number1.setText(localStorage.getString(localStorage.PHONE_1));
        number2.setText(localStorage.getString(localStorage.PHONE_2));
        message.setText(localStorage.getString(localStorage.MESSAGE));
        callAfterMessage.setChecked(localStorage.getBoolean(localStorage.CALL_AFTER_MESSAGE));
        sendLocation.setChecked(localStorage.getBoolean(localStorage.SEND_LOCATION));

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(number1.getText().toString()==""|| message.getText().toString()==""){
                    Toast t=new Toast(ctx);
                    t.setText("Numara veya mesaj boş bırakılamaz");
                    t.setDuration(Toast.LENGTH_LONG);
                    t.show();
                }
                else{
                    localStorage.setString(localStorage.PHONE_1,number1.getText().toString());
                    localStorage.setString(localStorage.PHONE_2,number2.getText().toString());
                    localStorage.setString(localStorage.MESSAGE,message.getText().toString());
                    localStorage.setBool(localStorage.CALL_AFTER_MESSAGE,callAfterMessage.isChecked());
                    localStorage.setBool(localStorage.SEND_LOCATION,sendLocation.isChecked());
                    finish();
                }
            }
        });

        Button ns1=findViewById(R.id.numberSelect1);
        Button ns2=findViewById(R.id.numberSelect2);

        ns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 1);

            }
        });
        ns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    protected void onStop() {

        super.onStop();
        if(number1.getText().toString()==""|| message.getText().toString()==""){
            Toast t=new Toast(ctx);
            t.setText("Numara veya mesaj boş bırakılamaz");
            t.setDuration(Toast.LENGTH_LONG);
            t.show();
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            Uri contactData=data.getData();
            Cursor cursor=getContentResolver().query(contactData,new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,null,null);
            if(cursor.moveToFirst()){
                if(requestCode==1) number1.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                if(requestCode==2) number2.setText(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            }
        }
    }

