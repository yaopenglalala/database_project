package model.relation.equipment;

import java.util.Date;

/**
 * Created by your dad on 2019/1/3.
 */
public class EquipmentIssue {
    private  int feedback_id;
    private int house_id;
    private int equipment_id;
    private  int repair_id;
    private int type;
    private String description;
    private Date time;

    public int getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public int getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(int repair_id) {
        this.repair_id = repair_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
