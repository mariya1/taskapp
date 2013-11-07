package com.example.tasksapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTools extends SQLiteOpenHelper {

	public DatabaseTools(Context appContext) {
		super(appContext, "tasksbook.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE tasks ( taskId INTEGER PRIMARY KEY, task TEXT, place TEXT, description TEXT, date1 DATE)";
		db.execSQL(query);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String query = "DROP TABLE IF EXISTS tasks";
		db.execSQL(query);
		onCreate(db);
	}

	public void insertTask(HashMap<String, String> values) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentV = new ContentValues();
		contentV.put("task", values.get("task"));
		contentV.put("place", values.get("place"));
		contentV.put("description", values.get("description"));
		contentV.put("date1", values.get("date1"));
		db.insert("tasks", null, contentV);
		db.close();
	}

	public int updateTask(HashMap<String, String> queryValues) {

		SQLiteDatabase database = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("task", queryValues.get("task"));
		values.put("place", queryValues.get("place"));
		values.put("description", queryValues.get("description"));
		values.put("date1", queryValues.get("date1"));
		return database.update("tasks", values, "taskId" + " = ?",
				new String[] { queryValues.get("taskId") });
	}

	public void deleteTask(String id) {
		SQLiteDatabase database = this.getWritableDatabase();
		String deleteQuery = "DELETE FROM tasks WHERE taskId='" + id + "'";
		database.execSQL(deleteQuery);

	}

	public ArrayList<HashMap<String, String>> getAllTasks() {
		ArrayList<HashMap<String, String>> taskArrayList = new ArrayList<HashMap<String, String>>();

		String selectQuery = "SELECT * FROM tasks ORDER BY task";
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				HashMap<String, String> taskMap = new HashMap<String, String>();

				taskMap.put("taskId", cursor.getString(0));
				taskMap.put("task", cursor.getString(1));
				taskMap.put("place", cursor.getString(2));
				taskMap.put("description", cursor.getString(3));
				taskMap.put("date1", cursor.getString(4));
				taskArrayList.add(taskMap);

			} while (cursor.moveToNext());

		}

		return taskArrayList;

	}

	public HashMap<String, String> getTaskInfo(String id) {

		HashMap<String, String> taskMap = new HashMap<String, String>();
		SQLiteDatabase database = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM tasks WHERE taskId='" + id + "'";
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {

			do {

				taskMap.put("taskId", cursor.getString(0));
				taskMap.put("task", cursor.getString(1));
				taskMap.put("place", cursor.getString(2));
				taskMap.put("description", cursor.getString(3));
				taskMap.put("date1", cursor.getString(4));

			} while (cursor.moveToNext());

		}

		return taskMap;

	}

}
