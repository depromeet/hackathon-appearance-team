package com.depromeet.hackthon7th.main;

import static android.view.View.GONE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.depromeet.hackthon7th.R;
import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoHolder> {

  private ArrayList<ToDoListItem> todoList;

  public ToDoListAdapter(ArrayList<ToDoListItem> list) {
    this.todoList = list;
  }

  @NonNull
  @Override
  public ToDoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    Context context = parent.getContext();
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View view = inflater.inflate(R.layout.todo_item, parent, false);

    ToDoHolder viewHolder = new ToDoHolder(view);

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ToDoHolder holder, int position) {
    ToDoListItem item = todoList.get(position);
    Log.d("adapter", item.getAlarmType());
    if (item.getAlarmType().equals("once")) {
      holder.alarmType.setText("특정알림");
      holder.taskType.setVisibility(GONE);
      if (item.getPriority().equals("high")) {
        holder.icon.setImageResource(R.drawable.ic_priority_yellow3);
      } else if (item.getPriority().equals("normal")) {
        holder.icon.setImageResource(R.drawable.ic_priority_yellow2);
      } else {
        holder.icon.setImageResource(R.drawable.ic_priority_yellow1);
      }


    } else {
      holder.alarmType.setText("정기알림"); //주황
      holder.taskType.setText(" | " + item.getAlarmType());
      if (item.getPriority().equals("high")) {
        holder.icon.setImageResource(R.drawable.ic_priority_orange3);
      } else if (item.getPriority().equals("normal")) {
        holder.icon.setImageResource(R.drawable.ic_priority_orange2);
      } else {
        holder.icon.setImageResource(R.drawable.ic_priority_orange1);
      }

    }
    holder.title.setText(item.getTitle());
    if (item.getMemo().length() == 0) {
      holder.desc.setVisibility(GONE);
    } else {
      holder.desc.setText(item.getMemo());
    }
    holder.date.setText(item.getDate());
    holder.time.setText(item.getTime());

  }

  @Override
  public int getItemCount() {
    return todoList.size();
  }
}
