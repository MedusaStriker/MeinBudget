package com.example.meinbudget.application.datenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import com.example.meinbudget.application.modell.Ausgabe;
import com.example.meinbudget.application.modell.Budget;
import com.example.meinbudget.application.modell.Kategorie;

public class Datenbank extends SQLiteOpenHelper{
    private static final int datenbankVersion = 9:
    private static final String datenbankName = "MeinBudgetDB";
    static final String ausgabenTabelle = "Ausgaben";
    static final String budgetTabelle = "Budget";
    static final String kategorieTabelle = "Kategorie";

    static sqlDB db;

    Datenbank(@NonNull Context context){
        super(context, datenbankName, null, datenbankVersion );
        SqlBrite sqlBrite = new SqlBrite.Builder().build();
        db = sqlBrite.wrapDatabaseHelper(this, Schedulers.io());
    }

    @Override public void onCreate(SQLiteDatabase db){
        String create_AusgabeTabelle = "CREATE TABLE"
                + ausgabenTabelle
                + " ( "
                + Ausgabe.ID
                +" INTERGER PRIMARY KEY, "
                + Ausgabe.DAY
                + " INTEGER, "
                + Ausgabe.MONTH
                + " INTEGER, "
                + Ausgabe.YEAR
                + " INTEGER, "
                + Ausgabe.VALUE
                + " INTEGER, "
                + Ausgabe.ID_CATEGORY
                + " INTEGER, "
                + Ausgabe.ID_BUDGET
                + " INTEGER, "
                + Ausgabe.DESCRIPTION
                + " TEXT ) ";


        String create_KategorieTabelle = "CREATE TABLE "
                + kategorieTabelle
                + " ( "
                + Kategorie.ID
                + " INTEGER PRIMARY KEY, "
                + Kategorie.TITLE
                + " TEXT, "
                + Kategorie.COLOR
                + " INTEGER, "
                + Kategorie.ICON
                + " INTEGER, "
                + Kategorie.ID_BUDGET
                + " INTEGER )";

        String create_budgetTabelle = "CREATE TABLE "
                + budgetTabelle
                + " ( "
                + Budget.ID
                + " INTEGER PRIMARY KEY, "
                + Budget.TITLE
                + " TEXT, "
                + Budget.VALUE
                + " INTEGER ) ";

        db.execSQL(create_AusgabeTabelle);
        db.execSQL(create_KategorieTabelle);
        db.execSQL(create_budgetTabelle);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ausgabenTabelle);
        db.execSQL("DROP TABLE IF EXISTS " + kategorieTabelle);
        db.execSQL("DROP TABLE IF EXISTS " + budgetTabelle);
        onCreate(db);
    }

    Cursor getCursor(@NonNull SqlBrite.Query query) {
        return query.run();
    }

    ContentValues getContentValues(@NonNull Kategorie kategorie) {
        ContentValues values = new ContentValues();
        values.put(Kategorie.TITLE, kategorie.getTitle());
        values.put(Kategorie.COLOR, kategorie.getColor());
        values.put(Kategorie.ICON, kategorie.getIcon());
        values.put(Kategorie.ID_BUDGET, kategorie.getIdBudget());

        return values;
    }

    ContentValues getContentValues(@NonNull Budget budget) {
        ContentValues values = new ContentValues();
        values.put(Budget.TITLE, budget.getTitle());
        values.put(Budget.VALUE, budget.getValue());
        return values;
    }
}
