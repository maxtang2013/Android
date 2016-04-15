package com.example.maxtang.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by maxtang on 16/4/12.
 */
public class Crime {
    public UUID getId() {
        return id;
    }

    private UUID id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Date date;

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    private boolean solved;

    public Crime() {
        id = UUID.randomUUID();
        date = new Date();
    }

    public String toString() {
        return title;
    }
}
