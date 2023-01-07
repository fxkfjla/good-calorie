package com.example.goodcalorie.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.goodcalorie.Adapters.LibraryAdapter;
import com.example.goodcalorie.Databases.BookLibraryRepository;
import com.example.goodcalorie.Models.Book;
import com.example.goodcalorie.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    FloatingActionButton addButton;

    BookLibraryRepository database;
    ArrayList<Book> books;
    LibraryAdapter libraryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initialize();
        readData();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1)
            recreate();
    }

    private void readData()
    {
        Cursor cursor = database.readAllData();

        if(cursor.getCount() == 0)
        {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            return;
        }

        while(cursor.moveToNext())
        {
            books.add(new Book
            (
                Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), Integer.parseInt(cursor.getString(3))
            ));
        }
    }

    private void initialize()
    {
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(view ->
        {
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });

        database = new BookLibraryRepository(MainActivity.this);

        books = new ArrayList<>();

        // displaying data with recycler view and custom adapter
        libraryAdapter = new LibraryAdapter(MainActivity.this, this, books);
        recyclerView.setAdapter(libraryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}