package com.example.meinbudget.application.modell;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

public class Kategorie{


    public static final String CATEGORY = "category";
    public static final String CATEGORIES = "categories";
    public static final String ID = "c_id";
    public static final String TITLE = "c_title";
    public static final String COLOR = "c_color";
    public static final String ICON = "c_icon";
    public static final String ID_BUDGET = "c_id_budget";

    private long id;
    private long idBudget;
    private String titleBudget;
    private String title;
    @ColorInt private int color;
    @DrawableRes private int icon;

    public Kategorie(Parcel in) {
        id = in.readLong();
        title = in.readString();
        color = in.readInt();
        icon = in.readInt();
        idBudget = in.readLong();
        titleBudget = in.readString();
    }

    public Kategorie(@ColorInt int color) {
        this(-1, -1, null, color, R.mipmap.ic_category);
    }

    public Kategorie(long id, long idBudget, String title, @ColorInt int color, @DrawableRes int icon) {
        this.id = id;
        this.idBudget = idBudget;
        this.title = title;
        this.color = color;
        this.icon = icon;
    }

    public Kategorie(long idBudget, String title, @ColorInt int color, @DrawableRes int icon) {
        this(-1, idBudget, title, color, icon);
    }

    public Kategorie(String title, @ColorInt int color, @DrawableRes int icon) {
        this(-1, -1, title, color, icon);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ColorInt public int getColor() {
        return color;
    }

    public void setColor(@ColorInt int color) {
        this.color = color;
    }

    @DrawableRes public int getIcon() {
        return icon;
    }

    public void setIcon(@DrawableRes int icon) {
        this.icon = icon;
    }

    public long getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(long idBudget) {
        this.idBudget = idBudget;
    }

    public String getTitleBudget() {
        return titleBudget;
    }

    public void setTitleBudget(String titleBudget) {
        this.titleBudget = titleBudget;
    }

    public boolean isBudgetIdDefined() {
        return idBudget != Budget.NOT_DEFINED;
    }

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeInt(color);
        dest.writeInt(icon);
        dest.writeLong(idBudget);
        dest.writeString(titleBudget);
    }

    public static final Parcelable.Creator<Kategorie> CREATOR = new Parcelable.Creator<Kategorie>() {
        @Override public Kategorie createFromParcel(Parcel source) {
            return new Kategorie(source);
        }

        @Override public Kategorie[] newArray(int size) {
            return new Kategorie[size];
        }
    };

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kategorie category = (Kategorie) o;

        if (id != category.id) return false;
        if (idBudget != category.idBudget) return false;
        if (color != category.color) return false;
        if (icon != category.icon) return false;
        return title != null ? title.equals(category.title) : category.title == null;
    }

    @Override public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (idBudget ^ (idBudget >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + color;
        result = 31 * result + icon;
        return result;
    }
}

