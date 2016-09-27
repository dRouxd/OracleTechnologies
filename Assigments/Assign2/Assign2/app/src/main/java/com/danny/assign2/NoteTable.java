package com.danny.assign2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;

/**
 * Created by 1342097 on 2016-09-27.
 */

public class NoteTable extends Table<Note>
{

    private static final SimpleDateFormat isoISO8601 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");


    private final String COLUMN_TITLE = "Title";
    private final String COLUMN_CONTENT = "Content";
    private final String COLUMN_CATEGORY = "Category";
    private final String COLUMN_REMINDER_DATE = "ReminderDate";
    private final String COLUMN_DATE_CREATED = "DateCreate";

    public NoteTable(SQLiteOpenHelper dbh, String name) {
        super(dbh, name);

        addColumn(new Column(COLUMN_TITLE, "TEXT").notNull());
        addColumn(new Column(COLUMN_CONTENT, "TEXT").notNull());
        addColumn(new Column(COLUMN_CATEGORY, "INTEGER"));
        addColumn(new Column(COLUMN_REMINDER_DATE, "TEXT").notNull());
        addColumn(new Column(COLUMN_DATE_CREATED, "TEXT").notNull());
    }



    @Override
    public ContentValues toContentValues(Note element) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, element.getTitle());
        values.put(COLUMN_CONTENT, element.getBody());
        values.put(COLUMN_CATEGORY, element.getCategory());
        values.put(COLUMN_REMINDER_DATE, element.getReminder() != null ? isoISO8601.format(element.getReminder()) : null);
        values.put(COLUMN_DATE_CREATED, element.getCreated() != null ? isoISO8601.format(element.getCreated()) : null);
        return values;
    }

    @Override
    public Note fromCursor(Cursor cursor) throws DatabaseException {
        Note note = new Note(cursor.getLong(0));

        note.setTitle(cursor.getString(1));
        note.setBody(cursor.getString(2));

        if(!cursor.isNull(3))
            note.setCategory(cursor.getInt(3));

        note.setReminder();
        note.setHasReminder();

        return note;
    }

    @Override
    public String getId(Note element) {
        return String.valueOf(element.getId());
    }

    @Override
    public boolean hasInitialData() {
        return true;
    }

    @Override
    public void initialize(SQLiteDatabase database) {
        for(Note note : NoteData.getData())
            database.insertOrThrow(getName(), null, toContentValues(note));
    }

    @Override
    public Long create(Note element) throws DatabaseException {
        long id = super.create(element);
        element.setId(id);
        return id;
    }
}
