package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Professional extends User{

    private long id;
    private String profession;

    public Professional() {
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public String getProfession() {
        return profession;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
