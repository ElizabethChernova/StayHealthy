package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.entities.Person;
import com.example.entities.Storage;

public class ProfileActivity extends AppCompatActivity {

    private EditText name,weight;
    private Button save;
    private boolean firstSetUp;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //set connection with fields
        name=(EditText) findViewById(R.id.personName);
        weight=(EditText) findViewById(R.id.editWeigh);
        save=(Button) findViewById(R.id.save_changes);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChanges(view);
            }
        });
        open();


    }

    public void saveChanges(View view){
        person.setName(name.getText().toString());
        person.setWeight(Double.parseDouble(weight.getText().toString()));
        boolean result = Storage.exportToJSON(this, person);
        if(result){
            Toast.makeText(this, "Зміни збережено", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Помилка збереження змін", Toast.LENGTH_LONG).show();
        }
    }

    public void open(){
        person = Storage.importFromJSON(this);
        if(person!=null){
           name.setText(person.getName());
           weight.setText(person.getWeight()+"");
        }
        else{
            person=new Person();
            firstSetUp=true;
        }
    }
}