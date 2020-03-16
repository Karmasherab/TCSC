package com.kashey.conference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {
    EditText form;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        form=findViewById(R.id.form);

        //Toast.makeText(this, ""+getIntent().getStringExtra("name"), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    public void show(View view) {
        Toast.makeText(this, ""+form.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}
