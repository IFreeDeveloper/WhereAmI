package com.example.john.whereami;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Final extends AppCompatActivity {
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        Intent i = getIntent();
        score = i.getIntExtra("score",0);
        ((TextView)findViewById(R.id.score)).setText("Score:"+Integer.toString(score));
    }
    public void restart(View view){
        Intent intent = new Intent(this,GameActivity1.class);
        startActivity(intent);
        finish();
    }
    public void exit(View view){
        finish();
    }
}
