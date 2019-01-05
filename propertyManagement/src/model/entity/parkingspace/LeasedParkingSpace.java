package model.entity.parkingspace;

/**
 * Created by MoonBird on 2019/1/3.
 */
public class LeasedParkingSpace {
    private  Integer parking_space_id;
    private Integer community_id;
    private String description;
    private Integer parking_state;

    public Integer getParking_space_id() {
        return parking_space_id;
    }

    public void setParking_space_id(Integer parking_space_id) {
        this.parking_space_id = parking_space_id;
    }

    public Integer getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(Integer community_id) {
        this.community_id = community_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParking_state() {
        return parking_state;
    }

    public void setParking_state(Integer parking_state) {
        this.parking_state = parking_state;
    }
}
