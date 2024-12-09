package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateCharacterRequest;
import moveslikejagger.controller.requests.UpdateCharacterRequest;
import moveslikejagger.models.Character;

import java.util.List;

public interface CharacterRepository {

    void create(CreateCharacterRequest createCharacterRequest);
    void update(UpdateCharacterRequest updateCharacterRequest);
    List<Character> all();
    List<Character> all(int locationId);
    Character find(int characterId);
    void destroy(int characterId);
}
