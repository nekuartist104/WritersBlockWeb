package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateCharacterRequest;
import moveslikejagger.controller.requests.UpdateAreaRequest;
import moveslikejagger.controller.requests.UpdateCharacterRequest;
import moveslikejagger.controller.requests.UpdateLocationRequest;
import moveslikejagger.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(CreateCharacterRequest createCharacterRequest) {
        jdbcTemplate.execute("INSERT INTO Character(LocationId, Name, Age, Characteristic_Trait, Appearance, Personality) VALUES("
                            + createCharacterRequest.getLocationId()
                            + ", '" + createCharacterRequest.getName() + "'"
                            + "," + createCharacterRequest.getAge()
                            + ", '" + createCharacterRequest.getCharacteristicTrait() + "'"
                            + ", '" + createCharacterRequest.getAppearance() + "'"
                            + ", '" + createCharacterRequest.getPersonality() + "');");
    }

    @Override
    public void update(UpdateCharacterRequest updateCharacterRequest) {
        jdbcTemplate.execute("UPDATE Character SET LocationId=" + updateCharacterRequest.getLocationId()
                + ", Name=" + "'" + updateCharacterRequest.getName()
                + "', Age=" + updateCharacterRequest.getAge()
                + ", Characteristic_Trait=" + "'" + updateCharacterRequest.getCharacteristicTrait()
                + "', Appearance=" + "'" + updateCharacterRequest.getAppearance()
                + "', Personality=" + "'" + updateCharacterRequest.getPersonality() + "'"
                + " WHERE CharacterId=" + updateCharacterRequest.getCharacterId());
    }


    @Override
    public List<Character> all() {
        return jdbcTemplate.query("SELECT * FROM Character;", new BeanPropertyRowMapper<>(Character.class));
    }

    @Override
    public List<Character> all(int locationId) {
        return jdbcTemplate.query("SELECT * FROM Character WHERE LocationId = " + locationId, new BeanPropertyRowMapper<>(Character.class));
    }

    @Override
    public Character find(int characterId) {
        try {
            return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM Character WHERE CharacterId = " + characterId, new BeanPropertyRowMapper<>(Character.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void destroy(int characterId) {
        jdbcTemplate.execute("DELETE FROM Character WHERE CharacterId = " + characterId);
    }
}
