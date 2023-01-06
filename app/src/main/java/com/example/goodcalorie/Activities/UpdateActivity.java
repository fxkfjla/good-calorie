package com.example.goodcalorie.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.goodcalorie.Database.BookLibraryRepository;
import com.example.goodcalorie.R;

public class UpdateActivity extends AppCompatActivity {

    EditText titleInput, authorInput, pagesInput;
    Button updateButton;

    String id, title, author, pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        titleInput = findViewById(R.id.titleText2);
        authorInput = findViewById(R.id.authorText2);
        pagesInput = findViewById(R.id.pagesInteger2);
        updateButton = findViewById(R.id.updateButton);

        getIntentData();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BookLibraryRepository database = new BookLibraryRepository(UpdateActivity.this);
                database.updateData(id, title, author, pages);
            }
        });
    }

    void getIntentData()
    {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages"))
        {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            titleInput.setText(title);
            authorInput.setText(author);
            pagesInput.setText(pages);
        }
        else
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
    }
}