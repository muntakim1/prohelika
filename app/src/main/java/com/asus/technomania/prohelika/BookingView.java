package com.asus.technomania.prohelika;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.asus.technomania.prohelika.models.eventbooking;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingView extends AppCompatActivity {
    private DatabaseReference mdatabase;
    EditText mName,mPhone,mEventId,mTransactionId;
    final String[] option = new String[1];
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_view);
        mdatabase= FirebaseDatabase.getInstance().getReference().child("EventRequest");

        ActionBar actionBar =getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Book a Trip");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        Spinner spinner = (Spinner) findViewById(R.id.memberSpinner);


        mName=findViewById(R.id.name);
        mPhone=findViewById(R.id.phone);
        mEventId=findViewById(R.id.eventId);
        mTransactionId=findViewById(R.id.transactionID);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data;
                data= parent.getItemAtPosition(position).toString();
                option[0] =data;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn=findViewById(R.id.book);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArrayList();
            }
        });

    }
    private void addArrayList(){
        String name=mName.getText().toString().trim();
        String phone= mPhone.getText().toString().trim();
        String eventID= mEventId.getText().toString().trim();
        String data=option[0];
        String transaction=mTransactionId.getText().toString().trim();
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Field required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Field required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(eventID)){
            Toast.makeText(this,"Field required",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(transaction)){
            Toast.makeText(this,"Field required",Toast.LENGTH_SHORT).show();
        }
        else {
            String id=mdatabase.push().getKey();
            eventbooking event=new eventbooking(name,phone,eventID,data,transaction);
            assert id != null;
            mdatabase.child(id).child("Name").setValue(name);
            mdatabase.child(id).child("Phone").setValue(phone);
            mdatabase.child(id).child("numberOfpeople").setValue(data);
            mdatabase.child(id).child("EventId").setValue(eventID);
            mdatabase.child(id).child("TransactionId").setValue(transaction);
            clearText();
            Toast.makeText(getApplicationContext(),"Booked..!!",Toast.LENGTH_SHORT).show();

        }

    }
    void clearText(){
        mName.setText("");
        mPhone.setText("");
        mEventId.setText("");
        mTransactionId.setText("");
    }
}
