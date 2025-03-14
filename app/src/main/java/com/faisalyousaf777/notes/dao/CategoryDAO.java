package com.faisalyousaf777.notes.dao;

import static com.faisalyousaf777.notes.DbHelper.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.faisalyousaf777.notes.DbHelper;
import com.faisalyousaf777.notes.entity.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private DbHelper dbHelper;

    public CategoryDAO(Context context) {
        this.dbHelper = DbHelper.getInstance(context);
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL_CATEGORIES, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                categories.add(new Category(id, name));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return categories;
    }

    public boolean insertCategory(final Category category) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, category.getName());
        long result = db.insert(TABLE_CATEGORIES, null, values);
        db.close();
        return result != -1;
    }

    public boolean updateCategory(final Category category) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, category.getName());
        int result = db.update(TABLE_CATEGORIES, values, COLUMN_ID + " = ?", new String[]{String.valueOf(category.getCategoryId())});
        db.close();
        return result > 0;
    }

    public boolean deleteCategory(final Category category) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete(TABLE_CATEGORIES, COLUMN_ID + " = ?", new String[]{String.valueOf(category.getCategoryId())});
        db.close();
        return result > 0;
    }
}
