package moveslikejagger.controller.requests;

public class UpdateCharacterRequest {
    private int characterId;
    private String name;
    private int age;
    private int locationId;
    private String characteristicTrait;
    private String appearance;
    private String personality;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
}
