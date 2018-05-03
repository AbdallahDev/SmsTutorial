package com.jhr.abdallahsarayrah.smstutorial

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by abdallah.sarayrah on 12/18/2017.
 */
class StudentsDB(context: Context) : SQLiteOpenHelper(context, "Students.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table student(mobile string, mark integer)")
        db?.execSQL("insert into student values('1',90)")
        db?.execSQL("insert into student values('2',80)")
        db?.execSQL("insert into student values('3',70)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}