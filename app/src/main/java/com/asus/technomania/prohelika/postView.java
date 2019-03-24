package com.asus.technomania.prohelika;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class postView extends AppCompatActivity {

    TextView tile, mdescription,mlocation;
    ImageView postImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_view);
        ActionBar actionBar =getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Post Details");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        tile= findViewById(R.id.ptitle);
        mlocation=findViewById(R.id.plocation);
        mdescription=findViewById(R.id.Description);
        postImage=findViewById(R.id.pimage);


        byte[] bytes=getIntent().getByteArrayExtra("image");
        String title=getIntent().getStringExtra("title");
        String location=getIntent().getStringExtra("location");
        String description=getIntent().getStringExtra("description");
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        postImage.setImageBitmap(bitmap);
        tile.setText(title);
        mlocation.setText(location);
        mdescription.setText(description);




    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
