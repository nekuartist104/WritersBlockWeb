package moveslikejagger.repository;

import moveslikejagger.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterJpaRepository extends JpaRepository<Character, Integer> {
}
