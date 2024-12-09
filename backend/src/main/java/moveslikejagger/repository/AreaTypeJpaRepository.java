package moveslikejagger.repository;

import moveslikejagger.models.AreaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaTypeJpaRepository extends JpaRepository<AreaType, Integer> {
}
