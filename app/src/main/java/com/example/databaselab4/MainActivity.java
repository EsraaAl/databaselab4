package com.example.databaselab4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //refrence to bottun
    Button button , button2 ;
    EditText editTextTextPersonName2 , editTextNumber2;
    Switch switch1;
    ListView listview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        switch1 = findViewById(R.id.switch1);
        listview1 = findViewById(R.id.listview1);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            //View All
            public void onClick(View v){
                try {
                    CostumerModel costumerModel = new CostumerModel (-1,editTextTextPersonName2.getText(),Integer.editTextNumber2.getText().toString(), switch1.isChecked());
                    Toast.makeText(  MainActivity.this, "Add Button" , Toast.LENGTH_SHORT ).show();
                }
                catch(Exception e){
                    Toast.makeText(  MainActivity.this, "Error Creating A Costumer" , Toast.LENGTH_SHORT ).show();

                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            //Add
            public void onClick(View v){
                Toast.makeText(  MainActivity.this, "View All Button" , Toast.LENGTH_SHORT ).show();
            }
        });

    }
}