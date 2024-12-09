package moveslikejagger.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class AreaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer areaTypeId;
    private String name;
    @Column(insertable = false, updatable = false)
    private Date createdAt;

    public Integer getAreaTypeId() {
        return areaTypeId;
    }

    public void setAreaTypeId(Integer areaTypeId) {
        this.areaTypeId = areaTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
