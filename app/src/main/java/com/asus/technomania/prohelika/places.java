package com.asus.technomania.prohelika;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asus.technomania.prohelika.models.blogpost;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;


public class places extends AppCompatActivity {
    private RecyclerView mBloglist;
    private DatabaseReference mdatabase;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        mdatabase= FirebaseDatabase.getInstance().getReference().child("places");
        mdatabase.keepSynced(true);
        mBloglist= findViewById(R.id.listview);
        mBloglist.setHasFixedSize(true);
        mBloglist.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<blogpost,BlogViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<blogpost,BlogViewHolder>(
                        blogpost.class,R.layout.listview_item, BlogViewHolder.class,mdatabase) {
                    @Override
                    protected void populateViewHolder(final BlogViewHolder viewHolder, blogpost model, int position) {
                        viewHolder.setImage(getApplicationContext(),model.getImage());
                        viewHolder.setTitle(model.getTitle());
                        viewHolder.setLocation(model.getLocation());
                        viewHolder.setDescription(model.getShortDescription());
                        viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                TextView mtitle=v.findViewById(R.id.title);
                                TextView mlocation=v.findViewById(R.id.location);
                                TextView mdescription=v.findViewById(R.id.shortDescription);
                                ImageView mImageView = v.findViewById(R.id.image);
                                String title =mtitle.getText().toString();
                                String location=mlocation.getText().toString();
                                String description=mdescription.getText().toString();
                                Drawable mdrawable = mImageView.getDrawable();
                                Bitmap mbitmap= ((BitmapDrawable)mdrawable).getBitmap();

                                    Intent intent =new Intent(v.getContext(),postView.class);
                                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                                    mbitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                                    byte[] bytes=stream.toByteArray();
                                    intent.putExtra("image",bytes);
                                    intent.putExtra("title",title);
                                    intent.putExtra("location",location);
                                    intent.putExtra("description",description);
                                    startActivity(intent);


                            }
                        });

                    }
                };
        mBloglist.setAdapter(firebaseRecyclerAdapter);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        View mview;
        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
        }
        void setTitle(String title){
            TextView post_title= mview.findViewById(R.id.title);
            post_title.setText(title);
        }
        void setLocation(String location){
            TextView post_location= mview.findViewById(R.id.location);
            post_location.setText(location);
        }
        void setDescription(String shortDescription){
            TextView post_Description= mview.findViewById(R.id.shortDescription);
            post_Description.setText(shortDescription);
        }
        void setImage(Context ctx, String image){
            ImageView post_image= mview.findViewById(R.id.image);
            Picasso.
                    with(ctx).
                    load(image).
                    fit().
                    placeholder(R.drawable.ajaxloading)
                    .into(post_image);
        }

    }



}