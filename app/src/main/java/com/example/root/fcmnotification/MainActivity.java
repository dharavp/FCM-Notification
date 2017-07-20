package com.example.root.fcmnotification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static Intent getIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        Intent i = getIntent();
        if (i.getExtras() != null) {
            String notificationValue = i.getStringExtra("value");
            Intent intent = SecondActivity.getIntent(this, notificationValue);
            startActivity(intent);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAnotherActivity();
                finish();

            }
        });
    }

    private void openAnotherActivity() {
        Intent i = new Intent(this, SecondActivity.class);
        startActivity(i);
    }
}
