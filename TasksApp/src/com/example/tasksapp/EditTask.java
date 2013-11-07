package com.example.tasksapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditTask extends Activity {

	EditText task;
	EditText place;
	EditText description;

	DatabaseTools dbTools = new DatabaseTools(this);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_task);
		task = (EditText) findViewById(R.id.taskEditText);
		place = (EditText) findViewById(R.id.placeEditText);
		description = (EditText) findViewById(R.id.descriptionEditText);
		Intent theIntent = getIntent();
		String taskId = theIntent.getStringExtra("taskId");

		HashMap<String, String> taskList = dbTools.getTaskInfo(taskId);

		if (taskList.size() != 0) {
			task.setText(taskList.get("task"));
			place.setText(taskList.get("place"));
			description.setText(taskList.get("description"));
		}
	}

	public void editTask(View view) {

		HashMap<String, String> queryValuesMap = new HashMap<String, String>();
		task = (EditText) findViewById(R.id.taskEditText);
		place = (EditText) findViewById(R.id.placeEditText);
		description = (EditText) findViewById(R.id.descriptionEditText);
		Intent theIntent = getIntent();
		String taskId = theIntent.getStringExtra("taskId");
		queryValuesMap.put("taskId", taskId);
		queryValuesMap.put("task", task.getText().toString());
		queryValuesMap.put("place", place.getText().toString());
		queryValuesMap.put("description", description.getText().toString());
		dbTools.updateTask(queryValuesMap);
		this.callMainActivity(view);
	}

	public void removeTask(View view) {
		Intent intent3 = getIntent();
		String taskId = intent3.getStringExtra("taskId");
		dbTools.deleteTask(taskId);
		this.callMainActivity(view);
	}

	public void callMainActivity(View view) {

		Intent objIntent = new Intent(getApplication(), MainActivity.class);
		startActivity(objIntent);

	}

}
