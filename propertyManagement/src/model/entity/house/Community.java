package model.entity.house;

/**
 * Created by your dad on 2019/1/3.
 */
public class Community {
    private Integer communityId;
    private String name;
    private float longitude;
    private float latitude;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer community_id) {
        this.communityId = community_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
