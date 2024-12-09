package moveslikejagger.controller.requests;

public class UpdateAreaTypeRequest {

    private int areaTypeId;
    private String name;

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
}
