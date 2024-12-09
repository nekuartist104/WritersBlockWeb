package moveslikejagger.repository;

import moveslikejagger.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Integer> {

    List<Location> findAllByWorldId(int id);
}
