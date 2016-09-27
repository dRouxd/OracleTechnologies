package ca.qc.johnabbott.cs.cs616.contacts.model;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Table class for Contacts
 *
 * @author Ian Clement (ian.clement@johnabbott.qc.ca)
 */
public class ContactTable implements CRUDRepository<Long, Contact> {


    /**
     * ISO 8601 standard date format.
     */
    private static final SimpleDateFormat isoISO8601 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sss");

    private static final String COLUMN_FIRST_NAME = "firstName";
    private static final String COLUMN_LAST_NAME = "lastName";
    private static final String COLUMN_LAST_CALLED = "lastCalled";
    private static final String COLUMN_PHONE_NUMBER = "phoneNumber";
    private static final String COLUMN_CALL_COUNT = "callCount";

    private SQLiteOpenHelper dbh;

    /**
     * Create a ContactTable with the DB handler.
     * @param dbh
     */
    public ContactTable(SQLiteOpenHelper dbh)
    {
        this.dbh = dbh;

    }

    /**
     * Get the SQL CREATE TABLE statement for this table.
     * @return SQL CREATE TABLE statement.
     */
    public String getCreateTableStatement()
    {
        return "CREATE TABLE contact (\n" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "firstName TEXT NOT NULL UNIQUE, \n" +
                "lastName TEXT NOT NULL UNIQUE, \n" +
                "phoneNumber TEXT NOT NULL,\n" +
                "lastCalled TEXT,\n" +
                "callCount INTEGER\n" +
                ");";
    }

    /**
     * Get the SQL DROP TABLE statement for this table.
     * @return SQL DROP TABLE statement.
     */
    public String getDropTableStatement()
    {
        return "DROP TABLE IF EXISTS contact;";
    }

    @Override
    public Long create(Contact element) throws DatabaseException
    {
        SQLiteDatabase database = dbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, element.getFirstName());
        values.put(COLUMN_LAST_NAME, element.getLastName());
        values.put(COLUMN_LAST_CALLED, element.getLastCalled() != null ? isoISO8601.format(element.getLastCalled()) : null);
        values.put(COLUMN_PHONE_NUMBER, element.getPhoneNumber());
        values.put(COLUMN_CALL_COUNT, element.getCallCount());

        // Id of inserted element, -1 if error.
        long insertId = -1;

        // insert into DB
        try {
            insertId = database.insertOrThrow("contact", null, values);
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
        finally {
            // close DB no matter what happens
            database.close();
        }

        return insertId;

    }

    @Override
    public Contact read(Long key) throws DatabaseException
    {
        return null;
    }

    @Override
    public List<Contact> readAll() throws DatabaseException
    {
        return null;
    }

    @Override
    public boolean update(Contact element) throws DatabaseException
    {
        return false;
    }

    @Override
    public boolean delete(Contact element) throws DatabaseException
    {
        return false;
    }

    /**
     * Check that the table has initial data.
     * @return
     */
    public boolean hasInitialData()
    {
        return true;
    }

    /**
     * Populate table with initial data.
     * Precondition: table has been created.
     * Note: this method is called during the handler's onCreate method where a writable database is opne
     *       trying to get a second writable database will throw an error, hence the parameter.
     * @param database
     */
    public void initialize(SQLiteDatabase database)
    {

    }
}
