package com.example.databaselab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import kotlin.contracts.Returns;


public class DataBaseHelper extends SQLiteOpenHelper {
public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String COLUMN_ID = "ID";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "CustomerData", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //First time a database get accssesed
        String createTableStatment = " Create Table "+CUSTOMER_TABLE+" ( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_CUSTOMER_NAME +" TEXT, "+ COLUMN_CUSTOMER_AGE+" INT , "+ COLUMN_ACTIVE_CUSTOMER +" BOOL ) " ;
        db.execSQL(createTableStatment);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
// ver. change
    }

    public boolean addOne (CostumerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_AGE, customerModel.getAge());
        cv.put(COLUMN_ACTIVE_CUSTOMER, customerModel.isActive());

        long insert = db.insert(CUSTOMER_TABLE, null,cv);
        if (insert== -1){
            return false;
        }else {
            return true;
        }
    }

    public boolean deleteOne (CostumerModel costumerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+ CUSTOMER_TABLE+" WHERE "+COLUMN_ID+" = "+ costumerModel.getId();
       Cursor cursor = db.rawQuery(queryString,null);

       if (cursor.moveToFirst()){
           return true;
       }else {
           return false;
       }

    }

    public List<CostumerModel> getEveryone(){
        List<CostumerModel> returnList = new ArrayList<>();
        String queryString = " SELECT * FROM "+CUSTOMER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()){
          do {
             int customerID = cursor.getInt(0);
             String customerName  = cursor.getString(1);
             int customerAge = cursor.getInt(2);
             boolean customerActive = cursor.getInt(3)== 1 ? true:false;

             CostumerModel newCustomer = new CostumerModel(customerID,customerName,customerAge,customerActive);
             returnList.add(newCustomer);

          }while (cursor.moveToNext());
        }else {
          //fail
        }
        cursor.close();
        db.close();

        return returnList;
    }



}