package model.relation.building;

import java.util.Date;

/**
 * Created by your dad on 2019/1/3.
 */
public class Complaint {
    private Integer feedback_id;
    private Integer house_id;
    private Integer type;
    private String description;
    private String process;
    private Date date;

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

    public String getProcess() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
