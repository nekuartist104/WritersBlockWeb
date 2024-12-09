package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateWorldRequest;
import moveslikejagger.controller.requests.UpdateWorldRequest;
import moveslikejagger.models.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorldRepositoryImpl implements WorldRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(CreateWorldRequest createWorldRequest) {
        jdbcTemplate.execute("INSERT INTO World(Name) VALUES ('" + createWorldRequest.getName() + "');");
    }

    @Override
    public void update(UpdateWorldRequest updateWorldRequest) {
        jdbcTemplate.execute("UPDATE World SET Name='" + updateWorldRequest.getName() + "' WHERE WorldId=" + updateWorldRequest.getWorldId());
    }


    @Override
    public List<World> all() {
        return jdbcTemplate.query("SELECT * FROM World;", new BeanPropertyRowMapper<>(World.class));
    }

    @Override
    public World find(int worldId) {
        try {
            return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM World WHERE WorldId = " + worldId, new BeanPropertyRowMapper<>(World.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public World findByName(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM World WHERE Name = '" + name + "';", new BeanPropertyRowMapper<>(World.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void destroy(int worldId) {
        jdbcTemplate.execute("DELETE FROM World WHERE WorldId = " + worldId);
    }

    @Override
    public void destroyByName(String name) {
        jdbcTemplate.execute("DELETE FROM World WHERE Name = '" + name + "';");
    }
}
