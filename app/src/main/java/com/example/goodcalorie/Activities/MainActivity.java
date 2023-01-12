package com.example.goodcalorie.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        Log.i("MainActivity", "Function onStart");
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.i("MainActivity", "Function onRestart");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.i("MainActivity", "Function onResume");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.i("MainActivity", "Function onPause");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.i("MainActivity", "Function onStop");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.i("MainActivity", "Function onDestroy");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menu)
    {
        if(menu.getItemId() == R.id.delete_all)
        {
            database.deleteAll();
            Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else if(menu.getItemId() == R.id.show_video)
        {
            Intent intent = new Intent(this, VideoActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menu);
    }
}