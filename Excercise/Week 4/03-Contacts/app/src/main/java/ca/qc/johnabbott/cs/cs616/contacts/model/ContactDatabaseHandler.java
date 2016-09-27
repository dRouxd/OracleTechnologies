package ca.qc.johnabbott.cs.cs616.contacts.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Contact Database handler class
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class ContactDatabaseHandler extends SQLiteOpenHelper {

    /**
     * Filename to store the local database (on device).
     */
    private static final String DATABASE_FILE_NAME = "contacts.db";

    /**
     * Update this field for every structural change to the database.
     */
    private static final int DATABASE_VERSION = 1;


    // TODO: maybe these could be replaced with a map of tables...

    /**
     * Contact database tables
     */
    private ContactTable contactTable;

    /**
     * Construct a new database handler.
     * @param context The application context.
     */
    public ContactDatabaseHandler(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        contactTable = new ContactTable(this);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        // create tables
        database.execSQL(contactTable.getCreateTableStatement());

        // populate tables as needed
        if(contactTable.hasInitialData()) {
            contactTable.initialize(database);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

        Log.w(DATABASE_FILE_NAME, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");

        // drop tables
        database.execSQL(contactTable.getDropTableStatement());

        // recreate DB
        onCreate(database);
    }

    /**
     * Getters
     */
    public ContactTable getContactTable() {
        return contactTable;
    }
}
