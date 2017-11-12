package com.example.nectecspeech2.partii2goexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import th.or.nectec.partii.embedded.android.EmbeddedUtils.ModelUtil;
import th.or.nectec.partii.embedded.android.RecognitionListener;
import th.or.nectec.partii.embedded.android.SpeechRecognizer;

public class MainActivity extends AppCompatActivity
        implements RecognitionListener, ModelUtil.OnReceiveStatusListener{

    private SpeechRecognizer recognizer;
    private boolean isSetupRecognizer = false;
    private ModelUtil mUtil =null;
    private Context context = null;
    private EditText edt_apikey=null;
    private TextView txt_result=null;
    private Button btn_download=null;
    private Button btn_start=null;
    private Button btn_stop=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        edt_apikey = (EditText) findViewById(R.id.edt_apikey);
        txt_result = (TextView) findViewById(R.id.text_result);
        btn_download = (Button) findViewById(R.id.btn_download);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        mUtil=new ModelUtil();
        if(mUtil.isPermissionGranted(context)) {
            if(mUtil.isSyncDir(getExternalFilesDir("")) && !isSetupRecognizer) {
                setUpRecognizer();
            }
        }else {
            mUtil.requestPermission(context);
        }

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String apikey = edt_apikey.getText().toString();

                mUtil.setOnReceiveDialogStatus(MainActivity.this);
                mUtil.startDownload(context, MainActivity.this, getExternalFilesDir(""), apikey);
            }
        });

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSetupRecognizer) {
                    recognizer.startListening();
                }
                btn_start.setEnabled(false);
                btn_stop.setEnabled(true);
            }
        });

/*07*/
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSetupRecognizer) {
                    recognizer.stop();
                }
                btn_start.setEnabled(true);
                btn_stop.setEnabled(false);
            }
        });

    }



    public void setUpRecognizer(){
        recognizer=mUtil.getRecognizer(context);
        if (recognizer.getDecoder() == null) {
            finish();
        }
        recognizer.addListener(this);
        isSetupRecognizer = true;
    }

    @Override
    public void onResult(String s) {
        /*08*/
        Toast.makeText(context, "Got result:", Toast.LENGTH_SHORT).show();
        if (s != null) {
            if (!s.equals(SpeechRecognizer.NO_HYP) && !s.equals(SpeechRecognizer.REQUEST_NEXT)) {
                txt_result.setText(s);
            }
        }
        else
        {
            Toast.makeText(context, "s is null!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onReceiveDownloadComplete() {
        /*09*/
        if(isSetupRecognizer) {
            recognizer.cancel();
            recognizer.shutdown();
        }
        setUpRecognizer();
    }

    @Override
    public void onReceiveDownloadFailed() {

    }


    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onProgress(int i) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onPartialResult(String s) {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onTimeout() {

    }
}
