package com.warodom.codesnippet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final private int MILLISECONDS = 3000;
    final private int CAMERA_CALLBACK_CODE = 125;   // app-defined int constant. can be any number

    private TextView tvPhone;
    private ImageView mImageView;
    private TextView tvHead;
    private Button btnWifi;
    private boolean toggleWifi = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHead = (TextView) findViewById(R.id.tvHead);
        btnWifi = (Button) findViewById(R.id.btnWifi);
        htmlTextView();
    }


    protected void openBrowser(View v) {
        String url = "http://www.google.com/";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    protected void enableWifi(View v) {
        WifiManager wifi = (WifiManager) getApplicationContext()
                .getSystemService(getApplicationContext().WIFI_SERVICE);
        wifi.setWifiEnabled(toggleWifi);
        String str;
        if (  toggleWifi )
            str = "Disable Wifi";
        else
            str = "Enable Wifi";
        btnWifi.setText(str);
        Toast.makeText(this, "Press button again to " + str, Toast.LENGTH_SHORT).show();
        toggleWifi = !toggleWifi;
    }

    protected void vibratePhone(View v) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(MILLISECONDS);
        Toast.makeText(this, "Phone is vibrated", Toast.LENGTH_SHORT).show();
    }

    protected void takePhoto(View v) {
        mImageView = (ImageView) findViewById(R.id.imageView);
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_CALLBACK_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_CALLBACK_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    private void htmlTextView() {
        tvPhone = (TextView) findViewById(R.id.tvHead);
        tvPhone.setText(Html.fromHtml("<font color=\"red\"><big><b>Code snippet example</b></big></font>"));
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}
