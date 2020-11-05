package com.example.meinbudget.application.datenbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class KategorieDB extends Datenbank {

    public kategorieTabelle(@NonNull Context context) {
        super(context);
    }

    public Observable<ArrayList<Kategorie>> getAll() {
        return db.createQuery(kategorieTabelle, "SELECT "
                + Kategorie.ID
                + ", "
                + Kategorie.TITLE
                + ", "
                + Kategorie.COLOR
                + ","
                + Kategorie.ICON
                + ", "
                + Kategorie.ID_BUDGET
                + ", "
                + Budget.TITLE
                + " FROM "
                + kategorieTabelle
                + " LEFT JOIN "
                + budgetTabelle
                + " ON "
                + Kategorie.ID_BUDGET
                + " = "
                + Budget.ID
                + " ORDER BY "
                + Kategorie.TITLE).map(super::getCursor).map(cursor -> {
            try {
                ArrayList<Kategorie> categories = new ArrayList<>(cursor.getCount());
                while (cursor.moveToNext()) {
                    categories.add(getCategory(cursor));
                }
                return categories;
            } finally {
                cursor.close();
            }
        });
    }

    public long add(@NonNull Kategorie kategorie) {
        return db.insert(kategorieTabelle, getContentValues(kategorie));
    }

    public int delete(@NonNull Kategorie category) {
        return db.delete(kategorieTabelle, Kategorie.ID + " = ?", String.valueOf(category.getId()));
    }

    public int update(@NonNull Kategorie category) {
        return db.update(kategorieTabelle, getContentValues(category), Kategorie.ID + " = ?",
                String.valueOf(category.getId()));
    }

    public int removeIdBudget(long idBudget) {
        ContentValues values = new ContentValues();
        values.put(Kategorie.ID_BUDGET, Budget.NOT_DEFINED);
        return db.update(kategorieTabelle, values, Kategorie.ID_BUDGET + " = ?",
                String.valueOf(idBudget));
    }

    private Kategorie getCategory(@NonNull Cursor cursor) {
        Kategorie kategorie =  new Kategorie(DbUtil.getLong(cursor, Kategorie.ID),
                DbUtil.getLong(cursor, Kategorie.ID_BUDGET), DbUtil.getString(cursor, Kategorie.TITLE),
                DbUtil.getInt(cursor, Kategorie.COLOR), DbUtil.getInt(cursor, Kategorie.ICON));
        kategorie.setTitleBudget(DbUtil.getString(cursor, Budget.TITLE));
        return kategorie;
    }

    public Observable<Boolean> isEmpty() {
        return db.createQuery(kategorieTabelle, "SELECT "
                + Kategorie.ID
                + " FROM "
                + kategorieTabelle)
                .map(super::getCursor).map(cursor -> {
                    try {
                        return cursor.getCount() == 0;
                    } finally {
                        cursor.close();
                    }
                });
    }
}

