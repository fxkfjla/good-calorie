package com.example.goodcalorie.Activities;

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
    EditText titleInput, authorInput, pagesInput;
    Button updateButton;

    Book book;

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
            Book intentBook = (Book)intent.getSerializableExtra("book");

            book = new Book
            (
                intentBook.getId(), intentBook.getTitle(),
                intentBook.getAuthor(), intentBook.getPages()
            );

            titleInput.setText(book.getTitle());
            authorInput.setText(book.getAuthor());
            pagesInput.setText(book.getPages());
        }
        else Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
    }

    private void initialize()
    {
        setContentView(R.layout.activity_update);

        titleInput = findViewById(R.id.titleText2);
        authorInput = findViewById(R.id.authorText2);
        pagesInput = findViewById(R.id.pagesInteger2);

        updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(view ->
        {
                BookLibraryRepository database = new BookLibraryRepository(UpdateActivity.this);
                database.updateData(book);
        });
    }
}

//        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages"))
//        {
//            book.setId(getIntent().);
//            id = getIntent().getStringExtra("id");
//            title = getIntent().getStringExtra("title");
//            author = getIntent().getStringExtra("author");
//            pages = getIntent().getStringExtra("pages");
//
//            titleInput.setText(title);
//            authorInput.setText(author);
//            pagesInput.setText(pages);
//        }
//        else
//            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();