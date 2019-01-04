package model.relation.equipment;

import java.util.Date;

/**
 * Created by your dad on 2019/1/3.
 */
public class OutdoorEquipRepair {
    private  int repair_id;
    private  int equipment_id;
    private int state;
    private float cost;
    private Date time;

    public int getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(int repair_id) {
        this.repair_id = repair_id;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
