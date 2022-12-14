package com.example.goodcalorie.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.widget.Toast;

public class BookLibraryRepository extends SQLiteOpenHelper
{
    public BookLibraryRepository(Context context)
    {
        super(context, databaseName, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query =
            "CREATE TABLE " + tableName + " (" +
            columnId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            columnTitle + " TEXT, " +
            columnAuthor + " TEXT, " +
            columnPages + " INTEGER);";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        String query = "DROP TABLE IF EXISTS " + tableName;

        db.execSQL(query);
        onCreate(db);
    }

    public void addBook(String title, String author, int pages)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(columnTitle, title);
        cv.put(columnAuthor, author);
        cv.put(columnPages, pages);

        if(db.insert(tableName, null, cv) == -1)
        {
            Toast.makeText(context, "Failed to add book", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Book added successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData()
    {
        String query = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
            cursor = db.rawQuery(query, null);

        return cursor;
    }

    public void updateData(String rowId, String author, String title, String pages)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(columnTitle, title);
        content.put(columnAuthor, author);
        content.put(columnPages, pages);

        long result = database.update(tableName, content, "id=?", new String[]{rowId});
        
        if(result == -1)
            Toast.makeText(context, "Failed to update!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Successfully updated!", Toast.LENGTH_SHORT).show();
    }

    private final Context context;

    private final static String databaseName = "BookLibrary.db";
    private final static int version = 1;

    private final String tableName = "books";
    private final String columnId = "id";
    private final String columnTitle = "title";
    private final String columnAuthor = "author";
    private final String columnPages = "pages";
}
