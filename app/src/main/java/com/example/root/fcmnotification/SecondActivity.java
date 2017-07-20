package com.example.root.fcmnotification;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static Intent getIntent(Context context, String notificationValue) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra("notificationValue", notificationValue);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView = (TextView) findViewById(R.id.text1);
        Intent i = getIntent();
        if (i.getExtras() != null) {
            String notificationValue = i.getStringExtra("notificationValue");
            Log.d("notification", "onCreate: "+notificationValue);
            textView.setText(notificationValue);
        }
    }
}
