package com.example.goodcalorie.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.goodcalorie.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    FloatingActionButton addButton;

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
    }
}