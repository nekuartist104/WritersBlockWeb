package moveslikejagger.repository;

import moveslikejagger.models.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaJpaRepository extends JpaRepository<Area, Integer> {

    List<Area> findAllByLocationId(int id);
    List<Area> findAllByAreaTypeId(int id);

}
