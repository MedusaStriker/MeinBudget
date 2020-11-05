package com.example.meinbudget.application.datenbank;

import android.content.Context;
import android.database.Cursor;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BudgetDB extends Datenbank{


    public budgetTabelle(@NonNull Context context) {
        super(context);
    }

    public Observable<ArrayList<Budget>> getAll() {
        return db.createQuery(budgetTabelle, "SELECT "
                + Budget.ID
                + ", "
                + Budget.TITLE
                + ", "
                + Budget.VALUE
                + " FROM "
                + budgetTabelle
                + " ORDER BY "
                + Budget.TITLE).map(super::getCursor).map(cursor -> {
            try {
                ArrayList<Budget> budgets = new ArrayList<>(cursor.getCount());
                while (cursor.moveToNext()) {
                    budgets.add(getBudget(cursor));
                }
                return budgets;
            } finally {
                cursor.close();
            }
        });
    }

    public long add(@NonNull Budget budget) {
        return db.insert(budgetTabelle, getContentValues(budget));
    }

    public int delete(@NonNull Budget budget) {
        return db.delete(budgetTabelle, Budget.ID + " = ?", String.valueOf(budget.getId()));
    }

    public int update(@NonNull Budget budget) {
        return db.update(budgetTabelle, getContentValues(budget), Budget.ID + " = ?",
                String.valueOf(budget.getId()));
    }

    private Budget getBudget(@NonNull Cursor cursor) {
        return new Budget(DbUtil.getLong(cursor, Budget.ID),
                DbUtil.getString(cursor, Budget.TITLE), DbUtil.getDouble(cursor, Budget.VALUE));
    }
}
