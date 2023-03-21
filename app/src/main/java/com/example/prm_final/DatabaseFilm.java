package com.example.prm_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseFilm extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "film_category.db";
    private final String TABLE_FILM_NAME = "film";
    private final String COLUMN_FILM_ID = "id";
    private final String COLUMN_FILM_NAME = "name";
    private final String COLUMN_FILM_CATEGORY = "category";
    private final String COLUMN_FILM_IMAGE = "image";
    private final String COLUMN_FILM_VIDEO = "video";
    private final String COLUMN_FILM_CONTENT = "content";
    private final String COLUMN_FILM_SCORE = "score";
    private final String COLUMN_FILM_DATE = "date";
    private final String COLUMN_FILM_VIEWS = "views";
    private final String TABLE_USER_NAME = "user";
    private final String COLUMN_USER_ID = "id";
    private final String COLUMN_USER_USERNAME = "username";
    private final String COLUMN_USER_EMAIL = "email";
    private final String COLUMN_USER_PASSWORD = "password";

    public DatabaseFilm(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createFilm = " CREATE TABLE " + TABLE_FILM_NAME + " ( "
                + COLUMN_FILM_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_FILM_NAME + " TEXT, "
                + COLUMN_FILM_CATEGORY + " TEXT, "
                + COLUMN_FILM_IMAGE + " TEXT, "
                + COLUMN_FILM_VIDEO + " TEXT, "
                + COLUMN_FILM_CONTENT + " TEXT, "
                + COLUMN_FILM_SCORE + " TEXT, "
                + COLUMN_FILM_DATE + " TEXT, "
                + COLUMN_FILM_VIEWS + " INTEGER) ";
        String createUser = " CREATE TABLE " + TABLE_USER_NAME + " ( "
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COLUMN_USER_USERNAME + " TEXT, "
                + COLUMN_USER_EMAIL + " TEXT, "
                + COLUMN_USER_PASSWORD + " TEXT) ";
        db.execSQL(createUser);
        db.execSQL(createFilm);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_FILM_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER_NAME);
        onCreate(db);
    }
    public long insertData(int id, String name, String category, String content, String score, String date, int views, String image, String video) {
        ContentValues values = new ContentValues(); //lưu trữ dữ liệu vào db

        values.put(COLUMN_FILM_IMAGE, image);
        values.put(COLUMN_FILM_VIDEO, video);

        values.put(COLUMN_FILM_ID, id);
        values.put(COLUMN_FILM_NAME, name);
        values.put(COLUMN_FILM_CATEGORY, category);
        values.put(COLUMN_FILM_CONTENT, content);
        values.put(COLUMN_FILM_SCORE, score);
        values.put(COLUMN_FILM_DATE, date);
        values.put(COLUMN_FILM_VIEWS, views);
        SQLiteDatabase db1 = getWritableDatabase();
        long result = db1.insert(TABLE_FILM_NAME, null, values);
        return result;
    }

    public ArrayList<Film> getAllListFilm() {
        ArrayList<Film> list = new ArrayList<Film>();
        String query = "SELECT * FROM " + TABLE_FILM_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Film film = new Film();
                film.setId(cursor.getInt(0));
                film.setName(cursor.getString(1));
                film.setCategory(cursor.getString(2));
                film.setImage(cursor.getString(3));
                film.setVideo(cursor.getString(4));
                film.setContent(cursor.getString(5));
                film.setScore(cursor.getString(6));
                film.setDate(cursor.getString(7));
                film.setViews(cursor.getInt(8));
                list.add(film);
                cursor.moveToNext();
            }
            return list;
        }
        cursor.close();
        db.close();
        return null;
    }
    public ArrayList<Film> getListTreding() {
        ArrayList<Film> list = new ArrayList<Film>();
        String query = "select * from "   + TABLE_FILM_NAME + " order by views desc limit 5 ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Film film = new Film();
                film.setId(cursor.getInt(0));
                film.setName(cursor.getString(1));
                film.setCategory(cursor.getString(2));
                film.setImage(cursor.getString(3));
                film.setVideo(cursor.getString(4));
                film.setContent(cursor.getString(5));
                film.setScore(cursor.getString(6));
                film.setDate(cursor.getString(7));
                film.setViews(cursor.getInt(8));
                list.add(film);
                cursor.moveToNext();
            }
            return list;
        }
        cursor.close();
        db.close();
        return null;
    }
    public ArrayList<Film> findFilmByName(String filmName) {
        ArrayList<Film> list = new ArrayList<Film>();
        String query = "SELECT * FROM " + TABLE_FILM_NAME + " WHERE " + COLUMN_FILM_NAME + " Like ?";
        String[] selectionArgs = {filmName};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Film film = new Film();
                film.setId(cursor.getInt(0));
                film.setName(cursor.getString(1));
                film.setCategory(cursor.getString(2));
                film.setImage(cursor.getString(3));
                film.setVideo(cursor.getString(4));
                film.setContent(cursor.getString(5));
                film.setScore(cursor.getString(6));
                film.setDate(cursor.getString(7));
                film.setViews(cursor.getInt(8));
                list.add(film);
                cursor.moveToNext();
            }
            return list;
        }
        cursor.close();
        db.close();
        return null;
    }
    public ArrayList<Film> findFilmByCategory(String category) {
        ArrayList<Film> list = new ArrayList<Film>();
        String query = "SELECT * FROM " + TABLE_FILM_NAME + " WHERE " + COLUMN_FILM_CATEGORY + " =? ";
        String[] selectionArgs = {category};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Film film = new Film();
                film.setId(cursor.getInt(0));
                film.setName(cursor.getString(1));
                film.setCategory(cursor.getString(2));
                film.setImage(cursor.getString(3));
                film.setVideo(cursor.getString(4));
                film.setContent(cursor.getString(5));
                film.setScore(cursor.getString(6));
                film.setDate(cursor.getString(7));
                film.setViews(cursor.getInt(8));
                list.add(film);
                cursor.moveToNext();
            }
            return list;
        }
        cursor.close();
        db.close();
        return null;
    }
    public int deleteFilm(String name) {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TABLE_FILM_NAME, COLUMN_FILM_NAME + "=?", new String[]{name});
        db.close();
        return result;
    }
    public long updateView (String filmId, int views) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FILM_VIEWS, views);
        SQLiteDatabase db1 = getWritableDatabase();
        long result = db1.update(TABLE_FILM_NAME, values,COLUMN_FILM_ID + "=?", new String[]{filmId});
        return result;
    }
    public Film filmBest() {
        String query = " SELECT * FROM " + TABLE_FILM_NAME + " ORDER BY VIEWS DESC LIMIT 1 ";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            Film film = new Film();
            film.setId(cursor.getInt(0));
            film.setName(cursor.getString(1));
            film.setCategory(cursor.getString(2));
            film.setImage(cursor.getString(3));
            film.setVideo(cursor.getString(4));
            film.setContent(cursor.getString(5));
            film.setScore(cursor.getString(6));
            film.setDate(cursor.getString(7));
            film.setViews(cursor.getInt(8));
            return film;
        }
        cursor.close();
        db.close();
        return null;
    }

    public long signUp(String name, String email, String password) {
        ContentValues values = new ContentValues(); //lưu trữ dữ liệu vào db
        values.put(COLUMN_USER_USERNAME, name);
        values.put(COLUMN_USER_EMAIL, email);
        values.put(COLUMN_USER_PASSWORD, password);
        SQLiteDatabase db1 = getWritableDatabase();
        long result = db1.insert(TABLE_USER_NAME, null, values);
        return result;
    }

    public User signIn(String email, String password) {
        String query = " SELECT * FROM " + TABLE_USER_NAME + " WHERE " + COLUMN_USER_EMAIL + " =? AND " + COLUMN_USER_PASSWORD + " =? ";
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = {email, password};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            return user;
        }
        cursor.close();
        db.close();
        return null;
    }
    public User checkEmailExist(String email) {
        String query = " SELECT * FROM " + TABLE_USER_NAME + " WHERE " + COLUMN_USER_EMAIL + " =? " ;
        SQLiteDatabase db = getReadableDatabase();
        String[] selectionArgs = {email};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            User user = new User();
            user.setId(cursor.getInt(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            return user;
        }
        cursor.close();
        db.close();
        return null;
    }
}
