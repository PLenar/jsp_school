package pl.coderslab.model;

import org.jetbrains.annotations.NotNull;

import java.sql.Date;

public class Solution implements Comparable<Solution>{
    private int id;
    private Date created;
    private Date updated;
    private String description;
    private int excerciseId;
    private int userId;

    public Solution(){}

    public Solution(int excerciseId, int userId) {
        this.excerciseId = excerciseId;
        this.userId = userId;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        this.created = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(java.sql.Date created) {

        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(java.sql.Date updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExcerciseId() {
        return excerciseId;
    }

    public void setExcerciseId(int excerciseId) {
        this.excerciseId = excerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        String s = String.format("ID rozwiązania: %d, Opis: %s, excerciseId: %d, userId: %d utworzone: %s", getId(), getDescription(), getExcerciseId(), getUserId(), getCreated().toString());
        return s;
    }
    @Override
    public int compareTo(@NotNull Solution o) {
        return this.getCreated().compareTo(o.getCreated());
    }
}