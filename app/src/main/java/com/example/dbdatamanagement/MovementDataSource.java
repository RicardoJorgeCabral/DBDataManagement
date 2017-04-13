package com.example.dbdatamanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ricardoc on 20-02-2017.
 */

public class MovementDataSource {
  private SQLiteDatabase database;
  private MovementDBHelper dbHelper;
  private String[] allColumns = {
      MovementDBHelper.COLUMN_NAME_ID,
      MovementDBHelper.COLUMN_NAME_DATE,
      MovementDBHelper.COLUMN_NAME_OPERATION,
      MovementDBHelper.COLUMN_NAME_VALUE,
      MovementDBHelper.COLUMN_NAME_OBS
  };

  public MovementDataSource(Context context) {
    dbHelper = new MovementDBHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }

  private Movement cursorToMovement(Cursor cursor) {

    Movement movement = new Movement();
    movement.setId(cursor.getLong(0));
    movement.setDate(new Date(cursor.getString(1)));
    movement.setOperation(cursor.getString(2));
    movement.setValue(new Double(cursor.getDouble(3)));
    movement.setObs(cursor.getString(4));
    return movement;
  }

  public Movement createMovement(Date date, String operation, Double value, String obs) {
    ContentValues values = new ContentValues();
    values.put(MovementDBHelper.COLUMN_NAME_DATE, date.toString());
    values.put(MovementDBHelper.COLUMN_NAME_OPERATION, operation);
    values.put(MovementDBHelper.COLUMN_NAME_VALUE,value);
    values.put(MovementDBHelper.COLUMN_NAME_OBS,obs);
    long insertId = database.insert(MovementDBHelper.TABLE_NAME, null,
        values);
    Cursor cursor = database.query(MovementDBHelper.TABLE_NAME, allColumns, MovementDBHelper.COLUMN_NAME_ID+"="+insertId, null, null, null, null);
    cursor.moveToFirst();
    Movement newMovement = this.cursorToMovement(cursor);
    cursor.close();
    return  newMovement;
  }

  public void deleteMovement(Movement movement) {
    long id = movement.getId();
    database.delete(MovementDBHelper.TABLE_NAME, MovementDBHelper.COLUMN_NAME_ID+"="+id,null);
    System.out.println("Record deleted with id: " + id);
  }

  public List<Movement> getAllMovmements() {
    List<Movement> movements = new ArrayList<Movement>();
    Cursor cursor = database.query(MovementDBHelper.TABLE_NAME, allColumns, null, null, null, null, null);
    cursor.moveToFirst();
    while (!cursor.isAfterLast()) {
      Movement movement = cursorToMovement(cursor);
      movements.add(movement);
      cursor.moveToNext();
    }
    cursor.close();
    return movements;
  }

  public Double getTotalDebits() {
    return this.getTotalFromOperation("D");
  }

  public Double getTotalCredits() {
    return this.getTotalFromOperation("C");
  }

  private Double getTotalFromOperation(String op) {
    double val = 0;
    String query = "SELECT SUM("+MovementDBHelper.COLUMN_NAME_VALUE+") FROM "+MovementDBHelper.TABLE_NAME+" WHERE "+MovementDBHelper.COLUMN_NAME_OPERATION+"=?";
    String[] whereArgs = new String[] { op };
    Cursor cursor = database.rawQuery( query , whereArgs );
    cursor.moveToFirst();
    if (!cursor.isAfterLast()) {
      val = cursor.getDouble(0);
    }
    return new Double(val);
  }

}
