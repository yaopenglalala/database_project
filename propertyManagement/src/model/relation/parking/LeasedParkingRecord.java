package model.relation.parking;

import java.util.Date;

/**
 * Created by your dad on 2019/1/3.
 */
public class LeasedParkingRecord {
    private int record_id;
    private int parking_space_id;
    private int resident_id;
    private Date start_time;
    private Date end_time;

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

    public int getResident_id() {
        return resident_id;
    }

    public void setResident_id(int resident_id) {
        this.resident_id = resident_id;
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
}
