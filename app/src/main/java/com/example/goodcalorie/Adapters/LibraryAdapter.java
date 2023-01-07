package com.example.goodcalorie.Adapters;

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
import com.example.goodcalorie.Models.Book;
import com.example.goodcalorie.R;

import java.util.ArrayList;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder>
{
    public LibraryAdapter(Activity activity, Context context, ArrayList<Book> books)
    {
        this.activity = activity;
        this.context = context;
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        holder.bookId.setText(String.valueOf(books.get(position).getId()));
        holder.bookTitle.setText(String.valueOf(books.get(position).getTitle()));
        holder.bookAuthor.setText(String.valueOf(books.get(position).getAuthor()));
        holder.bookPages.setText(String.valueOf(books.get(position).getPages()));
        holder.mainLayout.setOnClickListener(view ->
        {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("book", books.get(position));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount()
    {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            initialize();
        }

        private void initialize()
        {
            bookId = itemView.findViewById(R.id.bookId);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookPages = itemView.findViewById(R.id.bookPages);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

        TextView bookId, bookTitle, bookAuthor, bookPages;
        private LinearLayout mainLayout;
    }

    private Activity activity;
    private Context context;

    private ArrayList<Book> books;
}

//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position)
//    {
//        holder.bookId.setText(String.valueOf(books.get(position).getId()));
//        holder.bookTitle.setText(String.valueOf(books.get(position).getTitle()));
//        holder.bookAuthor.setText(String.valueOf(books.get(position).getAuthor()));
//        holder.bookPages.setText(String.valueOf(books.get(position).getPages()));
//        holder.mainLayout.setOnClickListener(view ->
//        {
//            Intent intent = new Intent(context, UpdateActivity.class);
//            intent.putExtra("id", String.valueOf(bookId.get(position)));
//            intent.putExtra("title", String.valueOf(bookTitle.get(position)));
//            intent.putExtra("author", String.valueOf(bookAuthor.get(position)));
//            intent.putExtra("pages", String.valueOf(bookPages.get(position)));
//            activity.startActivityForResult(intent, 1);
//        });
//    }


