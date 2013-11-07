package com.example.tasksapp;

import java.util.ArrayList;
import java.util.HashMap;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	Intent intent;
	TextView taskId;
	DatabaseTools tools = new DatabaseTools(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<HashMap<String, String>> tasklist = tools.getAllTasks();
		if (tasklist.size() != 0) {
			ListView listView = getListView();
			listView.setOnItemClickListener(new OnItemClickListener() {
				
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					taskId = (TextView) view.findViewById(R.id.taskId);
					String taskIdValue = taskId.getText().toString();
					Intent intent2 = new Intent(getApplication(), EditTask.class);
					intent2.putExtra("taskId", taskIdValue);
					startActivity(intent2);

				}
			});

			ListAdapter adapter = new SimpleAdapter(MainActivity.this,
					tasklist, R.layout.task_entry, new String[] { "taskId", "task", "place" }, new int[] { R.id.taskId, R.id.task, R.id.place });
			setListAdapter(adapter);
		}

	}

	public void showAddTask(View view) {
		Intent theIntent = new Intent(getApplicationContext(), AddNewTask.class);
		startActivity(theIntent);
	}

}
