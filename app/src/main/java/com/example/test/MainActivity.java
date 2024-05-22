package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseAdapter databaseAdapter;
    private EditText nameEditText, ageEditText,idEditText;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseAdapter = new DatabaseAdapter(this);
        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        idEditText = findViewById(R.id.idEditText); // Add this line
        listView = findViewById(R.id.listView);
        Button addButton = findViewById(R.id.addButton);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                long id = databaseAdapter.insertData(name, age);
                Toast.makeText(MainActivity.this, "Record added with ID: " + id, Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = databaseAdapter.getData();
                List<String> dataList = new ArrayList<>();

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        int age = cursor.getInt(cursor.getColumnIndex("age"));
                        dataList.add("ID: " + id + ", Name: " + name + ", Age: " + age);
                    } while (cursor.moveToNext());
                }

                cursor.close();

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, dataList);
                listView.setAdapter(adapter);
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                int id = Integer.parseInt(idEditText.getText().toString()); // Assuming you have an EditText for ID
                int rowsAffected = databaseAdapter.updateData(id, name, age);
                if (rowsAffected > 0) {
                    Toast.makeText(MainActivity.this, "Record updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to update record", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(idEditText.getText().toString()); // Assuming you have an EditText for ID
                int rowsAffected = databaseAdapter.deleteData(id);
                if (rowsAffected > 0) {
                    Toast.makeText(MainActivity.this, "Record deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GraphicsActivity.class);
                startActivity(intent);
            }
        });


    }
}
