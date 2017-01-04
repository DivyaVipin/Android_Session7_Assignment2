package assignment.android.acadgild.autocompletetextview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DivyaVipin on 1/4/2017.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Product.db";
    private static final int DB_VERSION_NUMBER = 1;
    private static final String DB_TABLE_NAME = "ProductDetails";
    private static final String DB_COLUMN_PRODUCT_NAME = "product_name";
    private static final String DB_CREATE_SCRIPT = "create table " + DB_TABLE_NAME +"(_id integer primary key autoincrement,product_name text not null);)";
    private SQLiteDatabase sqDBInstance = null;
    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION_NUMBER);
        }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("onCreate", "Creating the database...");
        sqLiteDatabase.execSQL(DB_CREATE_SCRIPT);


    }
    public void openDB() throws SQLException{
        Log.i("openDB", "Checking sqliteDBInstance...");
        if(this.sqDBInstance == null) {
            Log.i("openDB", "Creating sqliteDBInstance...");
            this.sqDBInstance = this.getWritableDatabase();
            }
    }
    public void closeDB() {
        if(this.sqDBInstance != null) {
            if(this.sqDBInstance.isOpen())
                this.sqDBInstance.close();
            }
        }
    public long insertProduct(String productName) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_COLUMN_PRODUCT_NAME, productName);
        Log.i(this.toString() + " - insertCountry", "Inserting: " +productName);
        return this.sqDBInstance.insert(DB_TABLE_NAME, null, contentValues);
    }
    public String[] getAllProducts() {
        Cursor cursor=this.sqDBInstance.query(true,DB_TABLE_NAME,new String[] {DB_COLUMN_PRODUCT_NAME},null,null,DB_COLUMN_PRODUCT_NAME,null,null,null);
       // Cursor cursor = this.sqDBInstance.query(true,DB_TABLE_NAME, new String[] {DB_COLUMN_PRODUCT_NAME}, null, null, null, null, null);
        if(cursor.getCount() > 0)
        {
            String[] product_name = new String[cursor.getCount()];
            int i = 0;
            while (cursor.moveToNext())
            {
                product_name[i] = cursor.getString(cursor.getColumnIndex(DB_COLUMN_PRODUCT_NAME));
                i++;
                }
            return product_name;
            }
        else{
            return new String[] {};
            }
        }
    @Override
    public void onUpgrade(SQLiteDatabase sqDatabase, int i, int i1) {

    }
}
