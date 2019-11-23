package com.depromeet.hackthon7th.main;

import static com.depromeet.hackthon7th.database.TaskUtil.deleteTask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.depromeet.hackthon7th.AppConstantKt;
import com.depromeet.hackthon7th.R;
import com.depromeet.hackthon7th.TaskDetailActivity;
import com.depromeet.hackthon7th.database.RealmCallback;
import com.depromeet.hackthon7th.database.Task;
import com.depromeet.hackthon7th.database.TaskUtil;
import com.depromeet.hackthon7th.util.StringUtilKt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;


public class ToDoListMainActivity extends AppCompatActivity {

  private ArrayList<ToDoListItem> mList;
  private ToDoListAdapter mAdapter;
  private ImageView menu;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_to_do_list_main);
    mList = new ArrayList<>();
    mAdapter = new ToDoListAdapter(mList);
    menu = (ImageView) findViewById(R.id.menu);

    Realm.init(getApplicationContext());

    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview_todolist);
    LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
        false);
    recyclerView.setLayoutManager(manager); // LayoutManager 등록
    recyclerView.setAdapter(mAdapter);  // Adapter 등록

    allData();
    mAdapter.notifyDataSetChanged();

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_button);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivityForResult(
            TaskDetailActivity.Companion.getStartIntent(ToDoListMainActivity.this),
            AppConstantKt.START_ADD_TASK);
      }
    });

    menu.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(ToDoListMainActivity.this, v);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.menu_option, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
          public boolean onMenuItemClick(MenuItem item) {
            Toast.makeText(ToDoListMainActivity.this, "You Clicked : " + item.getTitle(),
                Toast.LENGTH_SHORT).show();
            return true;
          }
        });

        popup.show();//showing popup menu
      }

    });

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
      @Override
      public boolean onMove(@NonNull RecyclerView recyclerView,
          @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
      }

      @Override
      public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        final int position = viewHolder.getAdapterPosition();
        deleteTask(mList.get(position).getId(), new RealmCallback() {
          @Override
          public void onSuccess() {
            Log.d("성공!!", "삭제");
          }

          @Override
          public void onError(Throwable error) {
            Log.d("실패!!", error.getMessage());

          }
        });
        mList.remove(position);
        mAdapter.notifyItemRemoved(position);
      }
    };

    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
    itemTouchHelper.attachToRecyclerView(recyclerView);
  }

  public void specificData() {

  }

  public void routineData() {

  }

  public void allData() {

    List<Task> task = TaskUtil.getTasks();
    int size = task.size();
    for (int i = 0; i < size; i++) {
      int id = task.get(i).getId();
      String alarmType = task.get(i).getType();
      String taskType = task.get(i).getType();
      String title = task.get(i).getTitle();
      String desc = task.get(i).getDescription();
      String date = StringUtilKt.getDateTime(task.get(i).getDeadLine());
      String priority = task.get(i).getPriority();
      ToDoListItem data = new ToDoListItem(id, alarmType, taskType, title, desc, date, "",
          priority);
      mList.add(data);
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    mAdapter.clear();
    allData();
    mAdapter.notifyDataSetChanged();
  }
}
