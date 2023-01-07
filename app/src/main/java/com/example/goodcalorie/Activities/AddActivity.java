package com.example.goodcalorie.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.goodcalorie.Databases.BookLibraryRepository;
import com.example.goodcalorie.Models.Book;
import com.example.goodcalorie.R;

public class AddActivity extends AppCompatActivity
{
    EditText title, author, pages;
    Button addButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title = findViewById(R.id.titleText);
        author = findViewById(R.id.authorText);
        pages = findViewById(R.id.pagesInteger);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(view ->
        {
            BookLibraryRepository lib = new BookLibraryRepository(this);

            Book book = new Book
            (
                title.getText().toString().trim(),
                author.getText().toString().trim(),
                Integer.parseInt(pages.getText().toString().trim())
            );

            lib.addBook(book);
        });
    }
}