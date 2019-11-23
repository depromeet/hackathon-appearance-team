package com.depromeet.hackthon7th.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.depromeet.hackthon7th.R;

public class ToDoHolder extends RecyclerView.ViewHolder {

  protected ImageView icon;
  protected TextView alarmType;
  protected TextView taskType;
  protected TextView title;
  protected TextView desc;
  protected TextView date;
  protected TextView time;

  public ToDoHolder(@NonNull View itemView) {
    super(itemView);
    this.icon = (ImageView) itemView.findViewById(R.id.icon);
    this.alarmType = (TextView) itemView.findViewById(R.id.alarmType);
    this.taskType = (TextView) itemView.findViewById(R.id.taskType);
    this.title = (TextView) itemView.findViewById(R.id.title);
    this.desc = (TextView) itemView.findViewById(R.id.desc);
    this.date = (TextView) itemView.findViewById(R.id.date);
    this.time = (TextView) itemView.findViewById(R.id.time);
  }
}
