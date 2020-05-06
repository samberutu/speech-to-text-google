package com.example.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.result);
    }

    public void btnMulai(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);// (1)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,// (2)
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);// (3)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,// (4)
                Locale.getDefault());// (5)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,// (6)
                "HAIII");// (7)

        try {
            startActivityForResult(intent,1); // (7)
        }catch (ActivityNotFoundException e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show(); // (8)
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 1 :
                if(resultCode ==RESULT_OK && null!=data){
                    ArrayList<String> list = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textView.setText("HASIL\n"+list.get(0));
                }
        }
    }
    public void btnReset(View view){
        textView.setText("percobaan Speech to Text");
    }
}
