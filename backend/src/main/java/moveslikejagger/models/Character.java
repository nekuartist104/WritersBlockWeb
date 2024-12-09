package moveslikejagger.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer characterId;
    private Integer locationId;
    private String name;
    private Integer age;

    @Column(name = "Characteristic_Trait")
    private String characteristicTrait;
    private String appearance;
    private String personality;
    @Column(insertable = false, updatable = false)
    private Date createdAt;

    public Integer getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCharacteristicTrait() {
        return characteristicTrait;
    }

    public void setCharacteristicTrait(String characteristicTrait) {
        this.characteristicTrait = characteristicTrait;
    }

    public String getAppearance() {
        return appearance;
    }

    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
