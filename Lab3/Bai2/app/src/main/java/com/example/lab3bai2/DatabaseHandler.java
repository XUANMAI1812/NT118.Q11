package com.example.lab3bai2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "students.db";
    private static final int DB_VERSION = 1;


    public static final String TABLE = "students";
    public static final String COL_MSSV = "mssv";
    public static final String COL_NAME = "name";
    public static final String COL_GENDER = "gender";
    public static final String COL_DOB = "dob";
    public static final String COL_COURSE = "course";
    public static final String COL_MAJOR = "major";
    public static final String COL_GPA = "gpa";


    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + TABLE + " ("
                + COL_MSSV + " TEXT PRIMARY KEY,"
                + COL_NAME + " TEXT,"
                + COL_GENDER + " TEXT,"
                + COL_DOB + " TEXT,"
                + COL_COURSE + " TEXT,"
                + COL_MAJOR + " TEXT,"
                + COL_GPA + " REAL"
                + ");";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public boolean addStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_MSSV, s.getMssv());
        cv.put(COL_NAME, s.getName());
        cv.put(COL_GENDER, s.getGender());
        cv.put(COL_DOB, s.getDob());
        cv.put(COL_COURSE, s.getCourse());
        cv.put(COL_MAJOR, s.getMajor());
        cv.put(COL_GPA, s.getGpa());


        long res = -1;
        try {
            res = db.insertOrThrow(TABLE, null, cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        db.close();
        return res != -1;
    }

    public boolean updateStudent(Student s) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, s.getName());
        cv.put(COL_GENDER, s.getGender());
        cv.put(COL_DOB, s.getDob());
        cv.put(COL_COURSE, s.getCourse());
        cv.put(COL_MAJOR, s.getMajor());
        cv.put(COL_GPA, s.getGpa());


        int rows = db.update(TABLE, cv, COL_MSSV + "=?", new String[]{s.getMssv()});
        db.close();
        return rows > 0;
    }


    public boolean deleteStudent(String mssv) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE, COL_MSSV + "=?", new String[]{mssv});
        db.close();
        return rows > 0;
    }

    public Student getStudent(String mssv) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE, null, COL_MSSV + "=?", new String[]{mssv}, null, null, null);
        if (c != null && c.moveToFirst()) {
            Student s = new Student(
                    c.getString(c.getColumnIndexOrThrow(COL_MSSV)),
                    c.getString(c.getColumnIndexOrThrow(COL_NAME)),
                    c.getString(c.getColumnIndexOrThrow(COL_GENDER)),
                    c.getString(c.getColumnIndexOrThrow(COL_DOB)),
                    c.getString(c.getColumnIndexOrThrow(COL_COURSE)),
                    c.getString(c.getColumnIndexOrThrow(COL_MAJOR)),
                    c.getDouble(c.getColumnIndexOrThrow(COL_GPA))
            );
            c.close();
            db.close();
            return s;
        }
        if (c != null) c.close();
        db.close();
        return null;
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String q = "SELECT * FROM " + TABLE + " ORDER BY " + COL_NAME + " COLLATE NOCASE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(q, null);
        if (c.moveToFirst()) {
            do {
                Student s = new Student(
                        c.getString(c.getColumnIndexOrThrow(COL_MSSV)),
                        c.getString(c.getColumnIndexOrThrow(COL_NAME)),
                        c.getString(c.getColumnIndexOrThrow(COL_GENDER)),
                        c.getString(c.getColumnIndexOrThrow(COL_DOB)),
                        c.getString(c.getColumnIndexOrThrow(COL_COURSE)),
                        c.getString(c.getColumnIndexOrThrow(COL_MAJOR)),
                        c.getDouble(c.getColumnIndexOrThrow(COL_GPA))
                );
                list.add(s);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return list;
    }
}
