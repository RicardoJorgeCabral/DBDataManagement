package com.example.dbdatamanagement;

import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ricardoc on 21-02-2017.
 */

public class MovementActivity extends AppCompatActivity {
  private Calendar calendar;
  private int year, month, day;
  private DatePicker dpResult;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movement);

    calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);

    dpResult = (DatePicker) findViewById(R.id.dp1);

    dpResult.init(year, month, day, null);

  }

  public void btSaveClick(View view) {
    Context context = getApplicationContext();
    MovementDataSource db = new MovementDataSource(context);

    String operation = new String();
    Double value = new Double(0);
    String obs = new String();
    this.getDateFromDatePicker();

    EditText edtTxt = (EditText) findViewById(R.id.editTextValue);
    RadioButton rb = (RadioButton) findViewById(R.id.rbDebit);
    if (rb.isChecked()) {
      operation="D";
    }
    else {
      rb = (RadioButton) findViewById(R.id.rbCredit);
      if (rb.isChecked()) {
        operation="C";
      }
      else {
        operation="U";
      }
    }
    value = new Double(edtTxt.getText().toString());
    edtTxt = (EditText) findViewById(R.id.editTextObs);
    obs = edtTxt.getText().toString();

    /*
    String msg = String.valueOf(new StringBuilder().append(day).append("/").append(month).append("/").append(year)) + ":" +
                 operation + ":" +
                 value + ":" +
                 obs;
    Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
    toast.show();*/
    db.open();
    db.createMovement(new Date(calendar.getTimeInMillis()),operation,value,obs);
    db.close();

    Toast toast = Toast.makeText(context, "Movement registered with success.", Toast.LENGTH_LONG);
    toast.show();

    finish();
  }

  private void getDateFromDatePicker() {
    dpResult = (DatePicker) findViewById(R.id.dp1);
    year = dpResult.getYear();
    month = dpResult.getMonth();
    day = dpResult.getDayOfMonth();

    calendar.set(Calendar.YEAR,year);
    calendar.set(Calendar.MONTH,month);
    calendar.set(Calendar.DAY_OF_MONTH,day);

    month = month + 1;

  }

}
