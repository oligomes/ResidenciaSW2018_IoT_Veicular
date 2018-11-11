package com.github.pires.obd.reader;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jose on 6/18/2018.
 */

public class Drive implements Serializable {
    private Integer id;

    private Date date;
    private double mileage_start;
    private double mileage_end;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMileage_start() {
        return mileage_start;
    }

    public void setMileage_start(double mileage_start) {
        this.mileage_start = mileage_start;
    }

    public double getMileage_end() {
        return mileage_end;
    }

    public void setMileage_end(double mileage_end) {
        this.mileage_end = mileage_end;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
