package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateAreaRequest;
import moveslikejagger.controller.requests.UpdateAreaRequest;
import moveslikejagger.models.Area;

import java.util.List;

public interface AreaRepository {

    void create(CreateAreaRequest createAreaRequest);
    void update(UpdateAreaRequest updateAreaRequest);
    List<Area> all();
    List<Area> allByLocation(int locationId);
    List<Area> allByAreaType(int areaTypeId);
    Area find(int areaId);
    Area findByName(String name);
    void destroy(int areaId);
    void destroyByName(String name);
}
