package com.example.dbdatamanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardoc on 27-02-2017.
 */

public class MovementAdapter extends ArrayAdapter {

  List list = new ArrayList();

  public MovementAdapter(Context context, int resource ) {
    super(context,resource);
  }

  public void add(Movement o) {
    list.add(o);
    super.add(o);
  }

  @Override
  public int getCount() {
    return list.size();
  }

  @Override
  public Object getItem(int position) {
    return list.get(position);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View row = convertView;
    MovementHolder movementHolder;
    if (row == null) {
      LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      row = layoutInflater.inflate(R.layout.activity_report_row,parent,false);
      movementHolder = new MovementHolder();
      movementHolder.tx_id = (TextView) row.findViewById(R.id.tvId);
      movementHolder.tx_date = (TextView) row.findViewById(R.id.tvDate);
      movementHolder.tx_value = (TextView) row.findViewById(R.id.tvValue);
      movementHolder.tx_obs = (TextView) row.findViewById(R.id.tvObs);
      row.setTag(movementHolder);
      /* movementHolder.*/
    }
    else {
      movementHolder = (MovementHolder) row.getTag();
    }

    Movement movement = (Movement) getItem(position);
    movementHolder.tx_id.setText(Long.toString(movement.getId()));
    movementHolder.tx_date.setText(movement.getDate().toString());
    movementHolder.tx_value.setText(movement.getSignedValue().toString());
    movementHolder.tx_obs.setText(movement.getObs().toString());

    return row;
    // return super.getView(position,convertView,parent);
  }

  static class MovementHolder {
    TextView tx_id, tx_date, tx_value, tx_obs;
  }

}
