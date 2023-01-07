package com.example.goodcalorie.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.goodcalorie.Databases.BookLibraryRepository;
import com.example.goodcalorie.Models.Book;
import com.example.goodcalorie.R;

public class UpdateActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initialize();
        getIntentData();
    }

    void getIntentData()
    {
        Intent intent = getIntent();

        if(intent.hasExtra("book"))
        {
            intentBook = (Book)intent.getSerializableExtra("book");

            titleInput.setText(intentBook.getTitle());
            authorInput.setText(intentBook.getAuthor());
            pagesInput.setText(String.valueOf(intentBook.getPages()));
        }
        else Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
    }

    void updateBook()
    {
        BookLibraryRepository database = new BookLibraryRepository(UpdateActivity.this);

        String title = titleInput.getText().toString().trim();
        String author = authorInput.getText().toString().trim();
        int pages = Integer.parseInt(pagesInput.getText().toString().trim());

        Book book = new Book(intentBook.getId(), title, author, pages);

        database.updateData(book);
    }

    void deleteBook()
    {
        BookLibraryRepository database = new BookLibraryRepository(UpdateActivity.this);

        database.deleteBook(intentBook.getId());
    }

    private void initialize()
    {
        setContentView(R.layout.activity_update);

        titleInput = findViewById(R.id.titleText2);
        authorInput = findViewById(R.id.authorText2);
        pagesInput = findViewById(R.id.pagesInteger2);

        Button updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(view -> updateBook());

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(view -> deleteBook());
    }

    private EditText titleInput, authorInput, pagesInput;

    private Book intentBook;
}