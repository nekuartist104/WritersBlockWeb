package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateWorldRequest;
import moveslikejagger.controller.requests.UpdateWorldRequest;
import moveslikejagger.models.World;

import java.util.List;

public interface WorldRepository {

    void create(CreateWorldRequest createWorldRequest);
    void update(UpdateWorldRequest updateWorldRequest);
    List<World> all();
    World find(int worldId);
    World findByName(String name);
    void destroy(int worldId);
    void destroyByName(String name);
}
