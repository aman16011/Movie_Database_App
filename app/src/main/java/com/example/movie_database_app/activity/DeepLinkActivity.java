package com.example.movie_database_app.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.movie_database_app.utils.Constants;
import com.example.movie_database_app.utils.Utils;

import java.net.URI;

public class DeepLinkActivity extends AppCompatActivity {

    private String deepLinkURL;
    private String TAG = "DeepLinkActivity";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deepLinkURL = getIntent().getData().toString();
        Uri uri = Uri.parse(deepLinkURL);
        String id = uri.getQueryParameter(Constants.ID);
        Intent intent = Utils.getIntentForMovieDetailsActivity(String.valueOf(id));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Log.d(TAG,deepLinkURL);
        finish();
    }
}
