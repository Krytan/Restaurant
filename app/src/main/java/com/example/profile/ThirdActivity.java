package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{


    private TextView receiver_msg;
    private List<Button> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.btnsendcolor,


    };

    private TextView colorbox;


    Spinner spinnercolor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        buttons = new ArrayList<Button>(BUTTON_IDS.length);

        for(int id : BUTTON_IDS){
            Button button = findViewById(id);
            button.setOnClickListener(this);
            buttons.add(button);
        }

        colorbox = findViewById(R.id.Colorbox);
        spinnercolor= findViewById(R.id.spinnercolor);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnercolor.setAdapter(adapter);


        receiver_msg = findViewById(R.id.receiver2);

        Intent intent = getIntent();
        String str = intent.getStringExtra("fromMain");
        receiver_msg.setText(str);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsendcolor:

                String str = spinnercolor.getSelectedItem().toString();
                receiver_msg.setBackgroundColor(Color.parseColor(str));

                Intent intent = new Intent(ThirdActivity.this, MainActivity.class);
                intent.putExtra("color_key", str);
                startActivity(intent);

                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }
}