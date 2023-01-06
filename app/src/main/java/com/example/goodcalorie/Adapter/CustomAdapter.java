package com.example.goodcalorie.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.goodcalorie.Activities.UpdateActivity;
import com.example.goodcalorie.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    private Context context;
    private ArrayList bookId, bookTitle, bookAuthor, bookPages;
    Activity activity;

    public CustomAdapter(Activity activity,
                         Context context,
                         ArrayList bookId,
                         ArrayList bookTitle,
                         ArrayList bookAuthor,
                         ArrayList bookPages)
    {
        this.activity = activity;
        this.context = context;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPages = bookPages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.bookId_text.setText(String.valueOf(bookId.get(position)));
        holder.bookTitle_text.setText(String.valueOf(bookTitle.get(position)));
        holder.bookAuthor_text.setText(String.valueOf(bookAuthor.get(position)));
        holder.bookPages_text.setText(String.valueOf(bookPages.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(bookId.get(position)));
                intent.putExtra("title", String.valueOf(bookTitle.get(position)));
                intent.putExtra("author", String.valueOf(bookAuthor.get(position)));
                intent.putExtra("pages", String.valueOf(bookPages.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookId.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView bookId_text, bookTitle_text, bookAuthor_text, bookPages_text;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            bookId_text = itemView.findViewById(R.id.bookId_text);
            bookTitle_text = itemView.findViewById(R.id.bookTitle_text);
            bookAuthor_text = itemView.findViewById(R.id.bookAuthor_text);
            bookPages_text = itemView.findViewById(R.id.bookPages_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
