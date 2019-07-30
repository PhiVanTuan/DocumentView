//package com.example.admin.etest.db;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.support.annotation.Nullable;
//
//import com.example.admin.etest.di.scope.DataBaseInfo;
//
//import javax.inject.Inject;
//
//public class DataBaseManager extends SQLiteOpenHelper {
//    private static final String SQL_TABLE_RECENT ="CREATE TABLE \"recent\" (\n" +
//            "\t\"id\"\tINTEGER NOT NULL,\n" +
//            "\t\"path\"\tTEXT NOT NULL,\n" +
//            "\t\"time\"\tREAL NOT NULL,\n" +
//            "\tPRIMARY KEY(\"id\")\n" +
//            ");";
//
//    @Inject
//    public DataBaseManager(@DataBaseInfo Context context, @DataBaseInfo String name, @Nullable SQLiteDatabase.CursorFactory factory, @DataBaseInfo int version) {
//        super(context, name, null, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//          sqLiteDatabase.execSQL(SQL_TABLE_RECENT);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//
//    public int getCount() {
//        SQLiteDatabase db=getReadableDatabase();
//        int id = 0;
//        try {
//            Cursor cursor2 = getReadableDatabase().rawQuery("SELECT Count(*) FROM recent", null);
//            while (cursor2.moveToNext()) {
//                id = cursor2.getInt(0);
//            }
//            int i = id + 1;
//            if (cursor2 != null) {
//                cursor2.close();
//            }
//            return i;
//        } catch (Throwable th) {
//            if (cursor != null) {
//                cursor.close();
//            }
//            throw th;
//        }
//    }
//
//}
