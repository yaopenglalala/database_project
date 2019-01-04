package model;

import java.util.Date;

/**
 * Created by your dad on 2019/1/3.
 */
public class Temporary_parking_record {
    private int record_id;
    private int parking_space_id;
    private String car_id;
    private Date start_time;
    private Date end_time;
    private float cost;

    public int getRecord_id() {
        return record_id;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public int getParking_space_id() {
        return parking_space_id;
    }

    public void setParking_space_id(int parking_space_id) {
        this.parking_space_id = parking_space_id;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
