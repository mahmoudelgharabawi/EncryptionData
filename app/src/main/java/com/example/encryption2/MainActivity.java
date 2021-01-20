package com.example.encryption2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button save, refresh;
    EditText name, salary;
    ArrayAdapter arrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper helper = new DatabaseHelper(this);
        final ArrayList array_list = helper.getAllCotacts();
        name = findViewById(R.id.name);
        salary = findViewById(R.id.salary);
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, array_list);
        listView.setAdapter(arrayAdapter);
        findViewById(R.id.Delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (helper.delete()) {
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "NOT Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !salary.getText().toString().isEmpty()) {
                    if (helper.update(name.getText().toString(), salary.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "NOT Updated",
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    name.setError("Enter NAME");
                    salary.setError("Enter Salary");
                }
            }
        });

        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array_list.clear();
                array_list.addAll(helper.getAllCotacts());
                arrayAdapter.notifyDataSetChanged();
                listView.invalidateViews();
                listView.refreshDrawableState();
            }
        });

        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !salary.getText().toString().isEmpty()) {
                    if (helper.insert(name.getText().toString(), salary.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "NOT Inserted", Toast.LENGTH_LONG).show();
                    }
                } else {
                    name.setError("Enter NAME");
                    salary.setError("Enter Salary");
                }
            }
        });
    }
}