package es.npatarino.android.gotchallenge.old;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GoTDataBase extends SQLiteOpenHelper {

    public String TableNameCharacters = "GoTCharacters";
    public String TableNameHouses = "GoTHouses";

    final String create_table_characters_sql =
            "CREATE TABLE IF NOT EXISTS [Characters] " +
            "([_id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, [description] TEXT, [houseId] TEXT,[imageUrl] TEXT,[name] TEXT)";
    final String create_table_houses_sql =
            "CREATE TABLE IF NOT EXISTS [Houses] " +
            "([_id] TEXT PRIMARY KEY NOT NULL UNIQUE, [houseName] TEXT, [houseImageUrl] TEXT)";
    final String DatabaseName = "GoTDatabase.db";
    final int DatabaseVersion = 1;

    public static SQLiteDatabase db;

    public GoTDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_houses_sql);
        db.execSQL(create_table_characters_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
