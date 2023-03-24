package com.example.prm_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;

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
                + COLUMN_FILM_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL, "
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

//        values.put(COLUMN_FILM_ID, id);
        values.put(COLUMN_FILM_NAME, name.trim());
        values.put(COLUMN_FILM_CATEGORY, category.trim());
        values.put(COLUMN_FILM_CONTENT, content.trim());
        values.put(COLUMN_FILM_SCORE, score.trim());
        values.put(COLUMN_FILM_DATE, date.trim());
        values.put(COLUMN_FILM_VIEWS, views);
        SQLiteDatabase db1 = getWritableDatabase();
        long result = db1.insert(TABLE_FILM_NAME, null, values);
        return result;
    }




//    pass null for the parameters don't want to use
//     find films by category: (null, "Action", null)
//    find films by name:
//    find films by year:
//    no parameters are passed or all parameters are null, the method will return all films

    public ArrayList<Film> findFilms(String filmName, ArrayList<String> categories, String year) {
        ArrayList<Film> list = new ArrayList<Film>();
        SQLiteDatabase db = getReadableDatabase();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM " + TABLE_FILM_NAME + " WHERE 1 = 1");

        if (filmName != null && !filmName.isEmpty()) {
            queryBuilder.append(" AND " + COLUMN_FILM_NAME + " LIKE ?");
        }

        if (categories != null && !categories.isEmpty()) {
            for (String category : categories) {
                queryBuilder.append(" AND " + COLUMN_FILM_CATEGORY + " LIKE ?");
            }
        }

        if (year != null && !year.isEmpty()) {
            queryBuilder.append(" AND substr(" + COLUMN_FILM_DATE + ", -5) LIKE ?");
        }

        String[] selectionArgs = new String[0];
        if (filmName != null && !filmName.isEmpty()) {
            selectionArgs = addStringToArray(selectionArgs, "%" + filmName + "%");
        }

        if (categories != null && !categories.isEmpty()) {
            for (String category : categories) {
                selectionArgs = addStringToArray(selectionArgs, "%" + category + "%");
            }
        }

        if (year != null && !year.isEmpty()) {
            selectionArgs = addStringToArray(selectionArgs, "%" + year + "%");
        }

        Cursor cursor = db.rawQuery(queryBuilder.toString(), selectionArgs);
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
        }
        cursor.close();
        db.close();
        return list;
    }



//    public ArrayList<Film> findFilms(String filmName, ArrayList<String> categories, String year) {
//        ArrayList<Film> list = new ArrayList<Film>();
//        SQLiteDatabase db = getReadableDatabase();
//        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM " + TABLE_FILM_NAME + " WHERE 1 = 1");
//
//        if (filmName != null && !filmName.isEmpty()) {
//            queryBuilder.append(" AND " + COLUMN_FILM_NAME + " LIKE ?");
//        }
//
//        if (categories != null && !categories.isEmpty()) {
//            queryBuilder.append(" AND " + COLUMN_FILM_CATEGORY + " IN (");
//            StringBuilder placeholders = new StringBuilder();
//            for (int i = 0; i < categories.size(); i++) {
//                placeholders.append("?");
//                if (i < categories.size() - 1) {
//                    placeholders.append(",");
//                }
//            }
//            queryBuilder.append(placeholders.toString() + ")");
//        }
//
//        if (year != null && !year.isEmpty()) {
//            queryBuilder.append(" AND  substr(" + COLUMN_FILM_DATE + ", -5) = ?");
//        }
//
//        String[] selectionArgs = new String[0];
//        if (filmName != null && !filmName.isEmpty()) {
//            selectionArgs = addStringToArray(selectionArgs, "%" + filmName + "%");
//        }
//
//        if (categories != null && !categories.isEmpty()) {
//            selectionArgs = addStringToArray(selectionArgs, categories.toArray(new String[0]));
//        }
//
//        if (year != null && !year.isEmpty()) {
//            selectionArgs = addStringToArray(selectionArgs,"%" + year+ "%");
//        }
//
//        Cursor cursor = db.rawQuery(queryBuilder.toString(), selectionArgs);
//        if (cursor.moveToFirst()) {
//            while (!cursor.isAfterLast()) {
//                Film film = new Film();
//                film.setId(cursor.getInt(0));
//                film.setName(cursor.getString(1));
//                film.setCategory(cursor.getString(2));
//                film.setImage(cursor.getString(3));
//                film.setVideo(cursor.getString(4));
//                film.setContent(cursor.getString(5));
//                film.setScore(cursor.getString(6));
//                film.setDate(cursor.getString(7));
//                film.setViews(cursor.getInt(8));
//                list.add(film);
//                cursor.moveToNext();
//            }
//        }
//        cursor.close();
//        db.close();
//        return list;
//    }




    public ArrayList<Film> findFilms(String filmName, String category, String year) {
        ArrayList<Film> list = new ArrayList<Film>();
        String selection = "";
        ArrayList<String> selectionArgsList = new ArrayList<String>();

        if (filmName != null) {
            selection += COLUMN_FILM_NAME + " LIKE ? ";
            selectionArgsList.add("%" + filmName + "%");
        }

        if (category != null) {
            if (!selection.isEmpty()) {
                selection += " AND ";
            }
            selection += COLUMN_FILM_CATEGORY + " = ? ";
            selectionArgsList.add(category);
        }

        if (year != null) {
            if (!selection.isEmpty()) {
                selection += " AND ";
            }
            selection += "strftime('%Y', " + COLUMN_FILM_DATE + ") = ?";
            selectionArgsList.add(year);
        }

        String[] selectionArgs = selectionArgsList.toArray(new String[selectionArgsList.size()]);
        String query = "SELECT * FROM " + TABLE_FILM_NAME;
        if (!selection.isEmpty()) {
            query += " WHERE " + selection;
        }

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
            cursor.close();
        }
        db.close();
        return list;
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
        String query = "SELECT * FROM " + TABLE_FILM_NAME + " WHERE " + COLUMN_FILM_NAME + " LIKE ?";
        String[] selectionArgs = {"%"+filmName +"%"};
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

    public ArrayList<Film> findFilmByCategories(ArrayList<String> categories) {
        ArrayList<Film> list = new ArrayList<Film>();
        String query = "SELECT * FROM " + TABLE_FILM_NAME + " WHERE " + COLUMN_FILM_CATEGORY + " IN (";
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < categories.size(); i++) {
            placeholders.append("?");
            if (i < categories.size() - 1) {
                placeholders.append(",");
            }
        }
        query += placeholders.toString() + ")";
        String[] selectionArgs = categories.toArray(new String[0]);
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

    public ArrayList<Film> findFilmByYear(String year) {
        ArrayList<Film> list = new ArrayList<Film>();
        String query = "SELECT * FROM " + TABLE_FILM_NAME + " WHERE SUBSTR(" + COLUMN_FILM_DATE + ", -4) = ?";
        String[] selectionArgs = {year};
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

//    public String formatDate2(String dateString) {
//        try {
//            return new String(new SimpleDateFormat("yyyyMMdd")
//                    .format(new SimpleDateFormat("MMMM d, yyyy").parse(dateString)));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


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

    private String[] addStringToArray(String[] array, String str) {
        String[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[newArray.length - 1] = str;
        return newArray;
    }

    private String[] addStringToArray(String[] array, String[] strings) {
        String[] newArray = Arrays.copyOf(array, array.length + strings.length);
        System.arraycopy(strings, 0, newArray, array.length, strings.length);
        return newArray;
    }

//    private String formatDate(String dateString) {
//        try {
//            Log.d("date1", dateString);
//            SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d, yyyy");
//
//            Date date = inputFormat.parse(dateString.trim());
//            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//            String formattedDate = outputFormat.format(date);
//            Log.d("date", formattedDate);
//
//            return formattedDate;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
