package com.warodom.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.warodom.retrofit.model.GitHubUser;
import com.warodom.retrofit.service.GitHubService;
import com.warodom.retrofit.util.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit;
    private GitHubService service;
    private GitHubUser gitHubUser;
    private TextView tvDisplay;
    private EditText edtName;
    private Retrofit retrofit;
    private HttpManager httpManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeView();
        httpManager = HttpManager.getInstance();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitLoadGitHubUser(httpManager.getService(), edtName.getText().toString() );
            }
        });
    }

    private void initializeView() {
        edtName = (EditText) findViewById(R.id.edtName);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
    }

    private void retrofitLoadGitHubUser(GitHubService service, String user) {
        Call<GitHubUser> call = service.loadUser(user);

        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                if ( response.isSuccessful() ) {
                    gitHubUser = response.body();
                    Toast.makeText(getApplicationContext(), gitHubUser.toString() , Toast.LENGTH_SHORT).show();
                    tvDisplay.setText(gitHubUser.toString());
                }
                else {
                    try {
                        Toast.makeText(getApplicationContext(), response.errorBody().string() , Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                Toast.makeText( getApplicationContext() , t.toString() , Toast.LENGTH_SHORT).show();
            }

        });
    }
}
