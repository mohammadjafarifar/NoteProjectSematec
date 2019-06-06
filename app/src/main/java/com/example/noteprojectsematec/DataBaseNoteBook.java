package com.example.noteprojectsematec;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.noteprojectsematec.Models.NoteBookModels;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DataBaseNoteBook extends SQLiteOpenHelper {

    String TABLE_NAME = "NoteBook";
    String CREATE_TABLE_QRY = "" +
            "CREATE TABLE " +
            TABLE_NAME +
            "(" +
            "_Id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NoteTitle TEXT," +
            "CreateDate TEXT," +
            "NoteTxt TEXT" +
            ")" +
            "";




    public DataBaseNoteBook(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QRY);
    }

    public void InsertNote(String title, String text) {
        SimpleDateFormat FormatDate= new SimpleDateFormat("yyyy-MM-dd");
        Date DateTimeNow = new Date(System.currentTimeMillis());
        String insertDataIntoDB = "" +
                "INSERT INTO " +
                TABLE_NAME +
                "(" +
                "NoteTitle," +
                "CreateDate," +
                "NoteTxt" +
                ")" +
                "VALUES" +
                "(" +
                "'" + title + "'" + "," +
                "'" + FormatDate.format(DateTimeNow) + "'" + "," +
                "'" + text + "'" +
                ")" +
                "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertDataIntoDB);
        db.close();
    }
    public  NoteBookModels getNoteById(String nid) {
        ArrayList<NoteBookModels> notes = new ArrayList<NoteBookModels>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _Id, NoteTitle, CreateDate, NoteTxt FROM " + TABLE_NAME + " WHERE _Id = " + nid, null);
        NoteBookModels tempNote = null;
        while (cursor.moveToNext()) {
            tempNote = new NoteBookModels(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }

        db.close();
        return tempNote;
    }

    public  ArrayList<NoteBookModels> AllNotes() {
        ArrayList<NoteBookModels> notes = new ArrayList<NoteBookModels>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT _Id, NoteTitle, CreateDate, NoteTxt FROM " + TABLE_NAME, null);
        NoteBookModels tempNote = null;
        while (cursor.moveToNext()) {
            tempNote = new NoteBookModels(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            notes.add(tempNote);
        }

        db.close();
        return notes;
    }

    public void updateNote(String nid, String tit, String text) {
        String Updatedata = "" +
                "UPDATE " +
                TABLE_NAME +
                " SET NoteTitle = "+
                "'"+ tit +"'"+
                ", NoteTxt ="+
                "'"+ text +"'"+
                " WHERE _Id = " +
                nid +
                "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(Updatedata);
        db.close();
    }


    public void deleteAll() {
        String deleteData = "" +
                "DELETE FROM " +
                TABLE_NAME +
                "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteData);
        db.close();
    }
    public void deleteById(String nid) {
        String deleteData = "" +
                "DELETE FROM " +
                TABLE_NAME +
                " WHERE _id = " +
                nid +
                "";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteData);
        db.close();
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
