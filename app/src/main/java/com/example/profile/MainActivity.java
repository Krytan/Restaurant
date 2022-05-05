package com.example.profile;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class MainActivity<textView> extends AppCompatActivity implements View.OnClickListener {

    //Store all buttons in an array for code optimization
    private List<Button> buttons;
    private static final int[] BUTTON_IDS = {
            R.id.btn1,
            R.id.btn2,
            R.id.btn3,
            R.id.btn4,
            R.id.btn5

    };
    private ActivityResultLauncher<Intent> launcher;
    private TextView  receiver_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        receiver_msg = findViewById(R.id.receiver);

        buttons = new ArrayList<Button>(BUTTON_IDS.length);

        // Here is a dynamic way to findviewbyid and set onclicklisterner on all buttons
        for(int id : BUTTON_IDS){
            Button button = findViewById(id);
            button.setOnClickListener(this);
            buttons.add(button);
        }

        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
         receiver_msg.setText(str);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == AppConstants.RESULT_CODE_SECOND) {
                            Intent intent = result.getData();
                            //txt_msg.setText(intent.getStringExtra("msg"));
                            Toast.makeText(MainActivity.this, intent.getStringExtra("msg"), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(result.getResultCode() == AppConstants.RESULT_CODE_THIRD) {
                            //txt_msg.setText(result.getData().getStringExtra("msg"));
                            Toast.makeText(MainActivity.this, "*******", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });


        
    }

    //We use one Onclick method for all buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("fromMain", "Besked fra main");
                launcher.launch(intent);
                //Toast.makeText(this, "button1 pressed", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn2:

                Toast.makeText(this, "button2 pressed", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn3:

                Toast.makeText(this, "button3 pressed", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn4:

                Toast.makeText(this, "button4 pressed", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn5:

                Toast.makeText(this, "button5 pressed", Toast.LENGTH_SHORT).show();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }


    }
}