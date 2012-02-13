package com.mediplus.persistence;

import java.security.PublicKey;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager {

	public static final String DATABASE_NAME = "MediPlusDB";
	public static final String DATABASE_TABLE1 = "profile";

	private DBHelper dbHelper;
	private final Context cntxt;
	private SQLiteDatabase db;

	private static class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context) {

			super(context, DATABASE_NAME, null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE IF NOT EXISTS" + DATABASE_TABLE1
					+ "(name TEXT PRIMARY KEY)");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE1);
			onCreate(db);

		}

	}

	public DatabaseManager(Context c) {
		cntxt = c;
	}

}
