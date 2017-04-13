package com.example.dbdatamanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void btRegisterMovementClick(View v) {
    Intent intent = new Intent(this, MovementActivity.class);
    startActivity(intent);
  }

  public void btShowReportClick(View v) {
    Intent intent = new Intent(this, ReportActivity.class);
    startActivity(intent);
  }

  public void buttonExitClicked(View v) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        // User clicked OK button
        finish();
      }
    });
    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        // User cancelled the dialog
        return;
      }
    });
    builder.setMessage("Do you want to close the app?.")
        .setTitle("Confirmation required");

    AlertDialog dialog = builder.create();
    dialog.show();

  }
}
