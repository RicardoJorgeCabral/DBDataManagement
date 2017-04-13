package com.example.dbdatamanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ricardoc on 20-02-2017.
 */

public class MovementDBHelper extends SQLiteOpenHelper {

  // If you change the database schema, you must increment the database version.
  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "movement.db";

  public static final String TABLE_NAME = "movement";
  public static final String COLUMN_NAME_ID = "_id";
  public static final String COLUMN_NAME_DATE = "date";
  public static final String COLUMN_NAME_OPERATION = "operation";  // (D)ebit or (C)redit
  public static final String COLUMN_NAME_VALUE = "value";
  public static final String COLUMN_NAME_OBS = "obs";

  private static final String DATABASE_CREATE = "create table "
      + TABLE_NAME + "( "
      + COLUMN_NAME_ID        + " integer primary key autoincrement, "
      + COLUMN_NAME_DATE      + " date not null, "
      + COLUMN_NAME_OPERATION + " varchar(1) not null, "
      + COLUMN_NAME_VALUE     + " numeric (18,2) not null, "
      + COLUMN_NAME_OBS       + " text"
      + " );";

  public MovementDBHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MovementDBHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
  }

}
