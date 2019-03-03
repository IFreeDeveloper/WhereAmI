package com.example.john.whereami;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

public class GameActivity3 extends AppCompatActivity {
    int second = 20;
    int score = 0;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        Intent i = getIntent();
        score = i.getIntExtra("score",0);
        ((TextView)findViewById(R.id.game1_score)).setText("Score:"+Integer.toString(score));
        TextView textView = (TextView)findViewById(R.id.game1_edit);
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if(s.equals("left")){
                    flag = false;
                    score++;
                    Intent intent = new Intent(GameActivity3.this,GameActivity4.class);
                    intent.putExtra("score",score);
                    startActivity(intent);
                    finish();
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new MyThread()).start();
    }

    final Handler handler = new Handler(){          // handle
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            TextView tv = (TextView)findViewById(R.id.game1_timer);
            tv.setText(Integer.toString(msg.what));
        }
    };
    class MyThread implements Runnable {      // thread
        @Override
        public void run() {
            while (flag) {
                try {
                    Message msg = new Message();
                    msg.what = second;
                    handler.sendMessage(msg);
                    second--;
                    Thread.sleep(1000);     // sleep 1000ms
                    if(second==-1){
                        Intent intent = new Intent(GameActivity3.this,GameActivity4.class);
                        startActivity(intent);
                        finish();
                        return;
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}
