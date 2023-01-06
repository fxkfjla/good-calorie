package com.example.goodcalorie.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.goodcalorie.Adapter.CustomAdapter;
import com.example.goodcalorie.Database.BookLibraryRepository;
import com.example.goodcalorie.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    FloatingActionButton addButton;

    BookLibraryRepository database;
    ArrayList<String> bookId, bookTitle, bookAuthor, bookPages;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(view ->
        {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });

        database = new BookLibraryRepository(MainActivity.this);
        bookId = new ArrayList<>();
        bookTitle = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        bookPages = new ArrayList<>();

        displayData();

        customAdapter = new CustomAdapter(MainActivity.this, this, bookId, bookTitle, bookAuthor, bookPages);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
            recreate();
    }

    public void displayData()
    {
        Cursor cursor = database.readAllData();

        if(cursor.getCount() == 0)
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        else
            while(cursor.moveToNext())
            {
                bookId.add(cursor.getString(0));
                bookTitle.add(cursor.getString(1));
                bookAuthor.add(cursor.getString(2));
                bookPages.add(cursor.getString(3));
            }

    }
}