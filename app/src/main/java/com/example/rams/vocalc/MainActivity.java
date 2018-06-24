package com.example.rams.VoCalc;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Locale;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mTextSet,mTextSet1;
    private Button clickbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextSet=findViewById(R.id.textView3);
        mTextSet1=findViewById(R.id.textView4);
        clickbutton=findViewById(R.id.button);
        clickbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runcommand();
            }
        });
    }

    public void runcommand(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    mTextSet.setText("");
                    mTextSet1.setText("");
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mTextSet.setText(result.get(0));
                    String val=result.get(0);
                    int w;

                    Float a,c,b;
                    if(val.indexOf('+')!=-1){
                         w=val.indexOf('+');
                         String first=val.substring(0,w-1);
                         String last=val.substring(w+1,val.length());
                         a=Float.parseFloat(first);
                         b=Float.parseFloat(last);
                         c=a+b;
                         System.out.println(c);
                        String ans=c.toString();
                        mTextSet.setText("The Question is : "+val);
                        mTextSet1.setText("The Answer is : "+ans);
                    }
                    if(val.indexOf('-')!=-1){
                        w=val.indexOf('-');
                        String first=val.substring(0,w-1);
                        String last=val.substring(w+1,val.length());
                        a=Float.parseFloat(first);
                        b=Float.parseFloat(last);
                        c=a-b;
                        System.out.println(c);
                        String ans=c.toString();
                        mTextSet.setText("The Question is : "+val);
                        mTextSet1.setText("The Answer  is : "+ans);
                    }
                    if(val.indexOf('x')!=-1){
                        w=val.indexOf('x');
                        String first=val.substring(0,w-1);
                        String last=val.substring(w+1,val.length());
                        a=Float.parseFloat(first);
                        b=Float.parseFloat(last);
                        c=a*b;
                        System.out.println(c);
                        String ans=c.toString();
                        mTextSet.setText("The Question is : "+val);
                        mTextSet1.setText("The Answer is : "+ans);
                    }
                    if(val.indexOf('/')!=-1){
                        w=val.indexOf('/');
                        String first=val.substring(0,w-1);
                        String last=val.substring(w+1,val.length());
                        a=Float.parseFloat(first);
                        b=Float.parseFloat(last);
                        c=a/b;
                        System.out.println(c);
                        String ans=c.toString();
                        mTextSet.setText("The Question is : "+val);
                        mTextSet1.setText("The Answer is : "+ans);
                    }


                }
                break;
            }

        }
    }
}
