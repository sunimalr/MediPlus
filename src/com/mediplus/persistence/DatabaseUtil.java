package com.mediplus.persistence;

import com.mediplus.entity.Allergy;
import com.mediplus.entity.MedicalRecord;
import com.mediplus.entity.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseUtil {

	private static final String TAG = "DatabaseUtil";
	private static final String DATABASE_NAME = "mediplusDatabase";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "tb_allergy";
	private static final String DATABASE_TABLE_PROFILE = "tb_profile";
	private static final String DATABASE_TABLE_MEDICAL_HISTORY = "tb_history";
	public static final String KEY_NAME = "name";
	public static final String KEY_GRADE = "grade";
	public static final String KEY_ROWID = "_id";
	// ###################################################
	public static final String KEY_ALLERGY_PROFILE = "profile";
	public static final String KEY_ALLERGY_ALLERGY = "allergy";
	public static final String KEY_ALLERGY_SYMPTOMS = "symptoms";
	public static final String KEY_ALLERGY_TREATMENT = "treatment";
	// ###########################################################################
	private static final String KEY_MEDICAL_HISTORY_PROFILE = "profile";
	private static final String KEY_MEDICAL_HISTORY_DATE = "date";
	private static final String KEY_MEDICAL_HISTORY_DESCRIPTION = "description";
	// ###########################################################################
	private static final String KEY_PROFILE_PROFILE = "profile";
	private static final String KEY_PROFILE_GENDER = "gender";
	private static final String KEY_PROFILE_DOB = "dob";
	private static final String KEY_PROFILE_WEIGHT = "weight";
	private static final String KEY_PROFILE_HEIGHT = "height";
	private static final String KEY_PROFILE_DESCRIPTION = "description";

	private static final String CREATE_ALLERGY_TABLE = "create table "
			+ DATABASE_TABLE + " (" + KEY_ALLERGY_PROFILE + " text not null , "
			+ KEY_ALLERGY_ALLERGY + " text primary key , "
			+ KEY_ALLERGY_SYMPTOMS + " text not null, " + KEY_ALLERGY_TREATMENT
			+ " text not null);";

	// Date stored as ddmmyyyy

	private static final String CREATE_MEDICAL_HISTORY_TABLE = "create table "
			+ DATABASE_TABLE_MEDICAL_HISTORY + " ("
			+ KEY_MEDICAL_HISTORY_PROFILE + " text not null , "
			+ KEY_MEDICAL_HISTORY_DATE + " text primary key , "
			+ KEY_MEDICAL_HISTORY_DESCRIPTION + " text not null);";

	private static final String CREATE_PROFILE_TABLE = "create table "
			+ DATABASE_TABLE_PROFILE + " (" + KEY_PROFILE_PROFILE
			+ " text not null , " + KEY_PROFILE_GENDER + " text , "
			+ KEY_PROFILE_DOB + " text , " + KEY_PROFILE_WEIGHT + " float, "
			+ KEY_PROFILE_HEIGHT + " float , " + KEY_PROFILE_DESCRIPTION
			+ " text );";

	private final Context mCtx;

	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/**
		 * onCreate method is called for the 1st time when database doesn't
		 * exists.
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			Log.i(TAG, "Creating DataBase");
			db.execSQL(CREATE_ALLERGY_TABLE);
			db.execSQL(CREATE_MEDICAL_HISTORY_TABLE);
			db.execSQL(CREATE_PROFILE_TABLE);
		}

		/**
		 * onUpgrade method is called when database version changes.
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
					+ newVersion);
		}
	}

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * 
	 * @param ctx
	 *            the Context within which to work
	 */
	public DatabaseUtil(Context ctx) {
		this.mCtx = ctx;
	}

	/**
	 * This method is used for creating/opening connection
	 * 
	 * @return instance of DatabaseUtil
	 * @throws SQLException
	 */
	public DatabaseUtil open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	/**
	 * This method is used for closing the connection.
	 */
	public void close() {
		mDbHelper.close();
	}

	// +++++++++++Adding Methods+++++++++++++++++++++++++++++++++

	public long addAllergy(Allergy a) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_ALLERGY_PROFILE, a.getUser());
		initialValues.put(KEY_ALLERGY_ALLERGY, a.getAllergy());
		initialValues.put(KEY_ALLERGY_SYMPTOMS, a.getSymptoms());
		initialValues.put(KEY_ALLERGY_TREATMENT, a.getTreatment());

		return mDb.insert(DATABASE_TABLE, null, initialValues);
	}

	public long addMedicalHistoryRecord(MedicalRecord m) {
		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_MEDICAL_HISTORY_PROFILE, m.getUser());
		initialValues.put(KEY_MEDICAL_HISTORY_DATE, m.getDate());
		initialValues.put(KEY_MEDICAL_HISTORY_DESCRIPTION, m.getDescription());

		return mDb.insert(DATABASE_TABLE_MEDICAL_HISTORY, null, initialValues);

	}

	public long addProfile(User u) {
		ContentValues initialValues = new ContentValues();

		initialValues.put(KEY_PROFILE_PROFILE, u.getUser());
		initialValues.put(KEY_PROFILE_GENDER, u.getGender());
		initialValues.put(KEY_PROFILE_DOB, u.getDob());
		initialValues.put(KEY_PROFILE_WEIGHT, u.getWeight());
		initialValues.put(KEY_PROFILE_HEIGHT, u.getHeight());
		initialValues.put(KEY_PROFILE_DESCRIPTION, u.getDesc());

		return mDb.insert(DATABASE_TABLE_PROFILE, null, initialValues);

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// ++++++++++++Delete Methods+++++++++++++++++++++++++++++++++++

	public boolean deleteStudent(long rowId) {
		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	public boolean deleteAllergy(Allergy a) {

		return mDb.delete(DATABASE_TABLE,
				KEY_ALLERGY_ALLERGY + "=" + a.getAllergy() + "AND"
						+ KEY_ALLERGY_PROFILE + "=" + a.getUser(), null) > 0;
	}

	public boolean deleteMedicalHistoryRecord(MedicalRecord m) {

		return mDb
				.delete(DATABASE_TABLE_MEDICAL_HISTORY,
						KEY_MEDICAL_HISTORY_DATE + "=" + m.getDate() + "AND"
								+ KEY_MEDICAL_HISTORY_PROFILE + "="
								+ m.getUser(), null) > 0;

	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	/**
	 * This method will return Cursor holding all the Student records.
	 * 
	 * @return Cursor
	 */

	// ++++++++++++++fetchAll Methods+++++++++++++++++++++++++++++++++++

	public Cursor fetchAllStudents() {
		return mDb.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_NAME,
				KEY_GRADE }, null, null, null, null, null);
	}

	public Cursor fetchAllAllergy() {
		return mDb.query(DATABASE_TABLE, new String[] { KEY_ALLERGY_PROFILE,
				KEY_ALLERGY_ALLERGY, KEY_ALLERGY_SYMPTOMS,
				KEY_ALLERGY_TREATMENT }, null, null, null, null, null);
	}

	public Cursor fetchAllMedicalHistoryRecord() {
		return mDb
				.query(DATABASE_TABLE_MEDICAL_HISTORY, new String[] {
						KEY_MEDICAL_HISTORY_PROFILE, KEY_MEDICAL_HISTORY_DATE,
						KEY_MEDICAL_HISTORY_DESCRIPTION }, null, null, null,
						null, null);
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	// +++++++++++++++fetch specific profile records+++++++++++++++++++++++
	public Cursor fetchProfileAllergy(String profile) throws SQLException {
		Cursor mCursor = mDb.query(true, DATABASE_TABLE, new String[] {
				KEY_ALLERGY_PROFILE, KEY_ALLERGY_ALLERGY, KEY_ALLERGY_SYMPTOMS,
				KEY_ALLERGY_TREATMENT }, KEY_ALLERGY_PROFILE + "=" + profile,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor fetchProfileMedicalHistory(String profile)
			throws SQLException {
		Cursor mCursor = mDb.query(true, DATABASE_TABLE_MEDICAL_HISTORY,
				new String[] { KEY_MEDICAL_HISTORY_PROFILE,
						KEY_MEDICAL_HISTORY_DATE,
						KEY_MEDICAL_HISTORY_DESCRIPTION },
				KEY_MEDICAL_HISTORY_PROFILE + "=" + profile, null, null, null,
				null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor fetchProfile(String profile) throws SQLException {
		Cursor mCursor = mDb.query(true, DATABASE_TABLE_PROFILE,
				new String[] { KEY_PROFILE_PROFILE, KEY_PROFILE_GENDER,
						KEY_PROFILE_DOB, KEY_PROFILE_WEIGHT,
						KEY_PROFILE_HEIGHT, KEY_PROFILE_DESCRIPTION },
				KEY_PROFILE_PROFILE + "=?", new String[] { profile }, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	/**
	 * This method will update Student record.
	 * 
	 * @param id
	 * @param name
	 * @param standard
	 * @return boolean
	 */

	// +++++++++++++++update methods++++++++++++++++++++++++++++++++++++++++
	public boolean updateAllergy(Allergy a) {
		ContentValues args = new ContentValues();
		args.put(KEY_ALLERGY_PROFILE, a.getUser());
		args.put(KEY_ALLERGY_ALLERGY, a.getAllergy());
		args.put(KEY_ALLERGY_SYMPTOMS, a.getSymptoms());
		args.put(KEY_ALLERGY_TREATMENT, a.getTreatment());
		return mDb.update(DATABASE_TABLE, args,
				KEY_ALLERGY_ALLERGY + "=" + a.getAllergy(), null) > 0;
	}

	public void updateMedicalHistoryRecord(MedicalRecord m, MedicalRecord old) {

		deleteMedicalHistoryRecord(old);
		addMedicalHistoryRecord(m);

	}

	public boolean updateProfile(User u) {
		ContentValues args = new ContentValues();

		args.put(KEY_PROFILE_GENDER, u.getGender());
		args.put(KEY_PROFILE_DOB, u.getDob());
		args.put(KEY_PROFILE_WEIGHT, u.getWeight());
		args.put(KEY_PROFILE_HEIGHT, u.getHeight());
		args.put(KEY_PROFILE_DESCRIPTION, u.getDesc());
		return mDb.update(DATABASE_TABLE_PROFILE, args, KEY_PROFILE_PROFILE
				+ "= ?", new String[] { u.getUser() }) > 0;
	}

	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

}