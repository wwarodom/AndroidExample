package com.warodom.smscallphone;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final private int REQUEST_CODE_ASK_SMS_PERMISSIONS = 123; // app-defined int constant. can be any number
    final private int DND_CALLBACK_CODE = 124;
    final private int REQUEST_CODE_ASK_CALL_PHONE_PERMISSIONS = 126;
    private Button btnSms;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSms = (Button) findViewById(R.id.btnSms);
        btnCall = (Button) findViewById(R.id.btnCall);
        Button btnRing = (Button) findViewById(R.id.btnRing);

        btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionSendSms();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermissionPhoneDial();
            }
        });

        btnRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRingerMode();  // not emulator
            }
        });
    }


    private void checkPermissionSendSms() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                showMessageOKCancel("You need to allow access to Sms",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{Manifest.permission.SEND_SMS},
                                        REQUEST_CODE_ASK_SMS_PERMISSIONS);
                            }
                        });
                return;

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        REQUEST_CODE_ASK_SMS_PERMISSIONS);
            }
        } else {
            sendSms();
        }
    }

    private void checkPermissionPhoneDial() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                showMessageOKCancel("You need to allow access to call phone",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},
                                        REQUEST_CODE_ASK_CALL_PHONE_PERMISSIONS);
                            }
                        });
                return;

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        REQUEST_CODE_ASK_CALL_PHONE_PERMISSIONS);
            }
        } else {
            callPhone();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_SMS_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    sendSms();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "You don't have permission to send SMS", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case REQUEST_CODE_ASK_CALL_PHONE_PERMISSIONS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    callPhone();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "You don't have permission to call phone", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request

        }
    }


    private void sendSms() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        SmsManager m = SmsManager.getDefault();
//        String destination = "+668186708xx";
        String destination = "5556";  // emulator phone number
        String text = "Hello world";
        m.sendTextMessage(destination, null, text, null, null);
    }


    private void callPhone() {
        String url = "tel:5556";
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
        try {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                System.out.println("Not allowed");
                return;
            }
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setRingerMode() {
        NotificationManager n = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (n.isNotificationPolicyAccessGranted()) {
            AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            //            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        } else {
            // Ask the user to grant access
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
            startActivityForResult(intent, DND_CALLBACK_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DND_CALLBACK_CODE) {
            AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
}
