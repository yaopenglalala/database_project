package model.entity.parkingspace;

/**
 * Created by MoonBird on 2019/1/3.
 */
public class LeasedParkingSpace {
    private  int parking_space_id;
    private int community_id;
    private String description;
    private int parking_state;

    public int getParking_space_id() {
        return parking_space_id;
    }

    public void setParking_space_id(int parking_space_id) {
        this.parking_space_id = parking_space_id;
    }

    public int getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(int community_id) {
        this.community_id = community_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParking_state() {
        return parking_state;
    }

    public void setParking_state(int parking_state) {
        this.parking_state = parking_state;
    }
}
