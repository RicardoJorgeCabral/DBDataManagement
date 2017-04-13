package com.example.dbdatamanagement;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ricardoc on 23-02-2017.
 */

public class ReportActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report);


    showTotals();
    showMovements();
  }

  public void btExitClick(View v) {
    finish();
  }

  private void showTotals() {
    Context context = getApplicationContext();
    MovementDataSource db = new MovementDataSource(context);
    db.open();
    Double totalDeb = db.getTotalDebits();
    Double totalCred = db.getTotalCredits();
    db.close();

    Double bal = totalCred - totalDeb;

    TextView edtTxt = (TextView) findViewById(R.id.tvDebit);
    edtTxt.setText(totalDeb.toString());

    edtTxt = (TextView) findViewById(R.id.tvCredit);
    edtTxt.setText(totalCred.toString());

    edtTxt = (TextView) findViewById(R.id.tvBalance);
    edtTxt.setText(bal.toString());

  }

  private void showMovements() {
    Context context = getApplicationContext();
    MovementDataSource db = new MovementDataSource(context);
    MovementAdapter movementAdapter = new MovementAdapter(context,R.layout.activity_report_row);
    db.open();
    List<Movement> movements = db.getAllMovmements();
    for (Movement m : movements) {
      movementAdapter.add(m);
    }
    ListView listView = (ListView) findViewById(R.id.lvMovements);
    listView.setAdapter(movementAdapter);
    db.close();
  }
}
