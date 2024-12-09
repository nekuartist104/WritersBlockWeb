package moveslikejagger.controller.requests;

public class CreateAreaRequest {
    private int locationId;
    private int areaTypeId;
    private String name;
    private int size;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getAreaTypeId() {
        return areaTypeId;
    }

    public void setAreaTypeId(int areaTypeId) {
        this.areaTypeId = areaTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}