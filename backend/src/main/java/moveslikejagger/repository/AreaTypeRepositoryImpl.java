package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateAreaTypeRequest;
import moveslikejagger.controller.requests.UpdateAreaTypeRequest;
import moveslikejagger.models.AreaType;
import moveslikejagger.models.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaTypeRepositoryImpl implements AreaTypeRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(CreateAreaTypeRequest createAreaTypeRequest) {
        jdbcTemplate.execute("INSERT INTO AreaType (Name) VALUES ('" + createAreaTypeRequest.getName() + "')");
    }

    @Override
    public void update(UpdateAreaTypeRequest updateAreaTypeRequest) {
        jdbcTemplate.execute("UPDATE AreaType SET Name='" + updateAreaTypeRequest.getName() + "' WHERE AreaTypeId=" + updateAreaTypeRequest.getAreaTypeId());
    }

    @Override
    public List<AreaType> all() {
        return jdbcTemplate.query("SELECT * FROM AreaType;", new BeanPropertyRowMapper<>(AreaType.class));
    }

    @Override
    public AreaType findById(int areaTypeId) {
        try {
            return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM AreaType WHERE AreaTypeId = " + areaTypeId, new BeanPropertyRowMapper<>(AreaType.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public AreaType findByName(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM AreaType WHERE Name = '" + name + "';", new BeanPropertyRowMapper<>(AreaType.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void destroy(int areaTypeId) {
        jdbcTemplate.execute("DELETE FROM AreaType WHERE AreaTypeId = " + areaTypeId);
    }

    @Override
    public void destroyByName(String name) {
        jdbcTemplate.execute("DELETE FROM AreaType WHERE Name = '" + name + "';");
    }
}
