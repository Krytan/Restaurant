package com.example.profile;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private List<Button> buttons;
    private static final int[] BTN_IDS = {
            R.id.btnback,
            R.id.btnsend

    };

     private Intent intent;
     private EditText editable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        editable = findViewById(R.id.editable);
        Spinner spinner= findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);


        buttons = new ArrayList<>(BTN_IDS.length);

        for(int number: BTN_IDS){
            Button button = findViewById(number);
            button.setOnClickListener(this);
            buttons.add(button);
        }

        intent = getIntent();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnback:
                intent.putExtra("msg", "Hilsen fra second");
                setResult(AppConstants.RESULT_CODE_SECOND, intent);
                finish();
                break;

            case R.id.btnsend:
                String str = editable.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("message_key", str);

                startActivity(intent);

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}