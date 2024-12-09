package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateLocationRequest;
import moveslikejagger.controller.requests.UpdateLocationRequest;
import moveslikejagger.models.Location;

import java.util.List;

public interface LocationRepository {
    void create(CreateLocationRequest createLocationRequest);
    void update(UpdateLocationRequest updateLocationRequest);
    List<Location> all();
    List<Location> all(int worldId);
    Location find(int locationId);
    Location findByName(String name);
    void destroy(int locationId);
    void destroyByName(String name);
}
