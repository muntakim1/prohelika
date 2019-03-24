package com.asus.technomania.prohelika;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Gallery extends AppCompatActivity {

    WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        ActionBar actionBar =getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Gallery");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        mWebview =findViewById(R.id.gallery);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadUrl("http://prohelika.tk/");

    }
}
