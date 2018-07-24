package com.example.a16022895.firebasetodoapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "";
    private TextView tvTitle, tvDate, tvDays, tvComplete;
    private EditText etTitle, etDate, etDays, etComplete;
    private CheckBox cb;
    private Button btnAdd;
    private boolean isCheck;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference messagePOJOReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = (TextView)findViewById(R.id.textViewTitle);
        tvDate = (TextView)findViewById(R.id.textViewDate);
        tvDays = (TextView)findViewById(R.id.textViewDays);
        tvComplete = (TextView)findViewById(R.id.textViewCompleted);
        etTitle = (EditText)findViewById(R.id.editTextMessageTitle);
        etDate = (EditText)findViewById(R.id.editTextMessageDate);
        etDays = (EditText)findViewById(R.id.editTextMessageDay);
        cb = (CheckBox)findViewById(R.id.checkBox1);
        btnAdd = (Button)findViewById(R.id.buttonAdd);

        firebaseDatabase = FirebaseDatabase.getInstance();
        messagePOJOReference = firebaseDatabase.getReference("messagePOJO");

        messagePOJOReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Message message = dataSnapshot.getValue(Message.class);
                if (message != null) {
                    tvTitle.setText("Title: " + message.getTitle());
                    tvDate.setText("Date: " + message.getDate());
                    tvDays.setText("NumOfDays: " + message.getNumOfDays());
                    tvComplete.setText("Completed?: " + message.getCompleted());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "Database error occurred", databaseError.toException());
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int days = Integer.parseInt(etDays.getText().toString());
                if (cb.isChecked() == true){
                    isCheck = true;
                }else{
                    isCheck = false;
                }
                Message message = new Message(etTitle.getText().toString(), etDate.getText().toString(), days, isCheck);
                messagePOJOReference.setValue(message);
            }
        });

    }
}
