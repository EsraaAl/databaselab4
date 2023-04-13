package com.example.databaselab4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //refrence to bottun
    Button button , button2 ;
    EditText editTextTextPersonName2 , editTextNumber2;
    Switch switch1;
    ListView listview1;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;
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

        ShowCustomersOnListView(dataBaseHelper);
        dataBaseHelper = new DataBaseHelper(MainActivity.this);

        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            //add
            CostumerModel costumerModel;
            public void onClick(View v){
                try {
                   costumerModel = new CostumerModel (-1,editTextTextPersonName2.getText(),Integer.editTextNumber2.getText().toString(), switch1.isChecked());
                    Toast.makeText(  MainActivity.this, "Add Button" , Toast.LENGTH_SHORT ).show();
                }
                catch(Exception e){
                    Toast.makeText(  MainActivity.this, "Error Creating A Costumer" , Toast.LENGTH_SHORT ).show();
                    costumerModel = new CostumerModel(-1,"error",0,false);
                }

                DataBaseHelper databasehelper = new DataBaseHelper(MainActivity.this);
                boolean success = databasehelper.addOne(costumerModel);
                ShowCustomersOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this,"Success"+ success,Toast.LENGTH_SHORT).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            //view all
            public void onClick(View v){

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
               // List<CostumerModel> everyone = dataBaseHelper.getEveryone();

                ShowCustomersOnListView(dataBaseHelper);

                //Toast.makeText(  MainActivity.this, "View All Button" , Toast.LENGTH_SHORT ).show();
            }
        });
        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CostumerModel clickCustomer = (CostumerModel) adapterView.getItemAtPosition(i);
                dataBaseHelper.deleteOne(clickCustomer);
                ShowCustomersOnListView(dataBaseHelper);
                Toast.makeText(MainActivity.this, " DELETED"+ clickCustomer.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ShowCustomersOnListView(DataBaseHelper dataBaseHelper) {
        customerArrayAdapter = new ArrayAdapter<CostumerModel>(MainActivity.this , android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        listview1.setAdapter(customerArrayAdapter);
    }
}