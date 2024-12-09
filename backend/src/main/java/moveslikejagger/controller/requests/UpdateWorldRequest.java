package moveslikejagger.controller.requests;

public class UpdateWorldRequest {

    private int worldId;
    private String name;

    public int getWorldId() {
        return worldId;
    }

    public void setWorldId(int worldId) {
        this.worldId = worldId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
