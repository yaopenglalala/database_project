package model.entity;

/**
 * Created by your dad on 2019/1/3.
 */
public class Resident {
    private Integer resident_id ;
    private String identity_card;
    private String name;
    private long tel;
    public Integer getResident_id() {
        return resident_id;
    }

    public void setResident_id(Integer resident_id) {
        this.resident_id = resident_id;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }


}
