package model.relation.equipment;

import java.util.Date;

/**
 * Created by your dad on 2019/1/3.
 */
public class EquipmentIssue {
    private Integer feedback_id;
    private Integer house_id;
    private Integer equipment_id;
    private Integer repair_id;
    private Integer type;
    private String description;
    private Date time;

    public Integer getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(Integer feedback_id) {
        this.feedback_id = feedback_id;
    }

    public Integer getHouse_id() {
        return house_id;
    }

    public void setHouse_id(Integer house_id) {
        this.house_id = house_id;
    }

    public Integer getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Integer equipment_id) {
        this.equipment_id = equipment_id;
    }

    public Integer getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(Integer repair_id) {
        this.repair_id = repair_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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
