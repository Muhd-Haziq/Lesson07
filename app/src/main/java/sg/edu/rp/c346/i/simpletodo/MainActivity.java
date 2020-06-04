package sg.edu.rp.c346.i.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etToDo;
    Button btnAdd, btnClear, btnDelete;
    ListView actView;
    ArrayList activities;
    ArrayAdapter actAdapter;
    Spinner spinSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etToDo = findViewById(R.id.ediTextToDo);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        btnDelete = findViewById(R.id.buttonDelete);
        actView = findViewById(R.id.lstView);
        spinSelection = findViewById(R.id.spinnerSelect);

        activities = new ArrayList<String>();
        actAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, activities);
        actView.setAdapter(actAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(etToDo.getText().toString().trim().isEmpty())){
                    activities.add(etToDo.getText().toString());
                    actAdapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "Field is blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activities.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }else{
                    activities.clear();
                    actAdapter.notifyDataSetChanged();
                }

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activities.isEmpty()){
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }else if(!(Integer.parseInt(etToDo.getText().toString()) >= 0 && Integer.parseInt(etToDo.getText().toString()) < activities.size())){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                }else if(etToDo.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "Field is blank", Toast.LENGTH_SHORT).show();
                }else{
                    for (int i = 0; i <  activities.size(); i++){
                        if(Integer.parseInt(etToDo.getText().toString()) == i){
                            activities.remove(i);
                            actAdapter.notifyDataSetChanged();
                        }
                    }
                }

            }
        });

        spinSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        etToDo.setHint("Type in a new task here");
                        etToDo.setInputType(InputType.TYPE_CLASS_TEXT);
                        etToDo.setText("");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        btnClear.setEnabled(true);
                        break;
                    case 1:
                        etToDo.setHint("Type in the index of the task to be removed");
                        etToDo.setInputType(InputType.TYPE_CLASS_NUMBER);
                        etToDo.setText("");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        btnClear.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
