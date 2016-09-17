package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import utils.Datas;


public class MyDatabase extends SQLiteAssetHelper {

    private static MyDatabase db;


    public static synchronized MyDatabase getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (db == null) {
            db = new MyDatabase(context.getApplicationContext());
        }
        return db;
    }

    public MyDatabase(Context context) {
       super(context, Datas.DB_name, null, Datas.DB_ver); //This constructor for default path {Use this for production}
    }

    //This method is for inserting user

    public long inserUser(
            String name,
            String surname,
            String email,
            int age,
            int salary

    ) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Datas.C_name, name);
        contentValues.put(Datas.C_email, email);
        contentValues.put(Datas.C_surname, surname);
        contentValues.put(Datas.C_age, age);
        contentValues.put(Datas.C_salary, salary);

        long ids = db.insert(Datas.T_name, null, contentValues);
        db.close();
        return ids;
    }

    //This method is for returning the user data

    public Cursor retrieveUser() {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {
                Datas.C_id,
                Datas.C_name,
                Datas.C_surname,
                Datas.C_email,
                Datas.C_age,
                Datas.C_salary,

        };
        String sqlTables = Datas.T_name;
        Cursor c;
        qb.setTables(sqlTables);

        c = qb.query(db, sqlSelect, null, null, null, null, null);

        c.moveToFirst();
        db.close();
        return c;
    }

    //This method is for deleting specific data

    public long delete(int id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = Datas.C_id + "=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        long i = db.delete(Datas.T_name, whereClause, whereArgs);
        db.close();
        return i;
    }

    //This method is for getting specific data using limit and offset

    public Cursor limitedData(int limit,int offset){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c;

        c = db.query(Datas.T_name, new String[] { Datas.C_id,
                        Datas.C_name, Datas.C_surname, Datas.C_email, Datas.C_age,
                        Datas.C_salary},
                null, null, null, null, null, offset+","+limit);
        c.moveToFirst();
        db.close();
        return c;
    }

    //This method id for update user

    public int updateUser(int userid,String name, String surname, String email, int age, int salary){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Datas.C_name, name);
        values.put(Datas.C_surname, surname);
        values.put(Datas.C_email, email);
        values.put(Datas.C_age, age);
        values.put(Datas.C_salary, salary);
        int i = db.update(Datas.T_name, values, Datas.C_id + "=" + userid, null);
        return i;
    }
}
