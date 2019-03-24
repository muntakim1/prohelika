package com.asus.technomania.prohelika;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asus.technomania.prohelika.models.blogpost;
import com.asus.technomania.prohelika.models.user;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageButton img;
    private RecyclerView mBloglist;
    private DatabaseReference mdatabase;

    private TextView username,useremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mdatabase= FirebaseDatabase.getInstance().getReference().child("blog");
        mdatabase.keepSynced(true);
        mBloglist=(RecyclerView)findViewById(R.id.listview);
        mBloglist.setHasFixedSize(true);
        mBloglist.setLayoutManager(new LinearLayoutManager(this));

        
        img=findViewById(R.id.booking);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    startActivity(new Intent(getApplicationContext(),BookingView.class));
                } else {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }

            }
        });

        FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
        username=findViewById(R.id.UserName);
        useremail=findViewById(R.id.UserEmail);
        if (User !=null){
            FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    user newUser=dataSnapshot.getValue(user.class);
                    assert newUser != null;
//                    username.setText(newUser.name);
//                    useremail.setText(newUser.emai);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else {
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
       FirebaseRecyclerAdapter<blogpost,BlogViewHolder>firebaseRecyclerAdapter=
               new FirebaseRecyclerAdapter<blogpost, BlogViewHolder>(
                       blogpost.class,R.layout.listview_item,BlogViewHolder.class,mdatabase) {
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
            TextView post_title=(TextView)mview.findViewById(R.id.title);
            post_title.setText(title);
        }
        void setLocation(String location){
            TextView post_location=(TextView)mview.findViewById(R.id.location);
            post_location.setText(location);
        }
        void setDescription(String shortDescription){
            TextView post_Description=(TextView)mview.findViewById(R.id.shortDescription);
            post_Description.setText(shortDescription);
        }
        void setImage(Context ctx, String image){
            ImageView post_image=(ImageView)mview.findViewById(R.id.image);

            Picasso.
                    with(ctx).
                    load(image).
                    fit().
                    placeholder(R.drawable.ajaxloading)
                    .into(post_image);
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(getApplicationContext(),Gallery.class));


        } else if (id == R.id.nav_places) {
            startActivity(new Intent(getApplicationContext(),places.class));
        } else if (id == R.id.nav_manage) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(),"Logged out",Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_request){
            startActivity(new Intent(getApplicationContext(),Request.class));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
