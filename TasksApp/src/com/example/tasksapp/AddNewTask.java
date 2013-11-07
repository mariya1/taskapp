package com.example.tasksapp;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewTask extends Activity {

	EditText task;
	EditText place;
	EditText description;
	DatabaseTools dbtool = new DatabaseTools(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_new_task);
		task = (EditText) findViewById(R.id.taskNameEditText);
		place = (EditText) findViewById(R.id.placeEditText);
		description = (EditText) findViewById(R.id.descriptionEditText);
	}

	public void addNewTask(View view) {
		HashMap<String, String> queryValuesMap = new HashMap<String, String>();
		queryValuesMap.put("task", task.getText().toString());
		queryValuesMap.put("place", place.getText().toString());
		queryValuesMap.put("description", description.getText().toString());
		dbtool.insertTask(queryValuesMap);
		this.callMainActivity(view);
	}

	public void callMainActivity(View view) {
		Intent theIntent = new Intent(getApplication(), MainActivity.class);
		startActivity(theIntent);
	}
}