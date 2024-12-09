package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateAreaTypeRequest;
import moveslikejagger.controller.requests.UpdateAreaTypeRequest;
import moveslikejagger.models.AreaType;

import java.util.List;

public interface AreaTypeRepository {

    void create(CreateAreaTypeRequest createAreaTypeRequest);
    void update(UpdateAreaTypeRequest updateAreaTypeRequest);
    List<AreaType> all();
    AreaType findById(int areaTypeId);
    AreaType findByName(String name);
    void destroy(int areaTypeId);
    void destroyByName(String name);
}
