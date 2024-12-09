package moveslikejagger.repository;

import moveslikejagger.models.World;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldJpaRepository extends JpaRepository<World, Integer> {

    World findByName(String name);
}
