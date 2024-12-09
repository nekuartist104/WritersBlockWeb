package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateAreaRequest;
import moveslikejagger.controller.requests.UpdateAreaRequest;
import moveslikejagger.models.Area;
import moveslikejagger.models.AreaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AreaRepositoryImpl implements AreaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(CreateAreaRequest createAreaRequest) {
        if (createAreaRequest.getSize() > 0) {
            jdbcTemplate.execute("INSERT INTO Area(LocationId, AreaTypeId, Name, Size) VALUES(" + createAreaRequest.getLocationId()
                    + "," + createAreaRequest.getAreaTypeId()
                    + ", '" + createAreaRequest.getName()
                    + "'," + createAreaRequest.getSize() +  ")");
        }
        else {
            Exception ex = new Exception("Size must be greater than 0.");
            try {
                throw ex;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(UpdateAreaRequest updateAreaRequest) {
        if (updateAreaRequest.getSize() > 0) {
            jdbcTemplate.execute("UPDATE Area SET LocationId=" + updateAreaRequest.getLocationId()
                    + ", AreaTypeId=" + updateAreaRequest.getAreaTypeId()
                    + ", Name=" + "'" + updateAreaRequest.getName()
                    + "', Size=" + updateAreaRequest.getSize() + " WHERE AreaId=" + updateAreaRequest.getAreaId());
        }
        else {
            Exception ex = new Exception("Size must be greater than 0.");
            try {
                throw ex;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Area> all() {
        return jdbcTemplate.query("SELECT * FROM Area;", new BeanPropertyRowMapper<>(Area.class));
    }

    @Override
    public List<Area> allByLocation(int locationId) {
        return jdbcTemplate.query("SELECT * FROM Area WHERE LocationId = " + locationId, new BeanPropertyRowMapper<>(Area.class));
    }

    @Override
    public List<Area> allByAreaType(int areaTypeId) {
        return jdbcTemplate.query("SELECT * FROM Area WHERE AreaTypeId = " + areaTypeId, new BeanPropertyRowMapper<>(Area.class));
    }

    @Override
    public Area find(int areaId) {
        try {
            Area area = jdbcTemplate.queryForObject("SELECT TOP 1 * FROM Area WHERE AreaId = " + areaId, new BeanPropertyRowMapper<>(Area.class));
            area.setAreaType(jdbcTemplate.queryForObject("SELECT TOP 1 * FROM AreaType WHERE AreaTypeId = " + area.getAreaTypeId(), new BeanPropertyRowMapper<>(AreaType.class)));

            return area;
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Area findByName(String name) {
        try {
            Area area = jdbcTemplate.queryForObject("SELECT TOP 1 * FROM Area WHERE Name = '" + name + "'", new BeanPropertyRowMapper<>(Area.class));
            area.setAreaType(jdbcTemplate.queryForObject("SELECT TOP 1 * FROM AreaType WHERE AreaTypeId = " + area.getAreaTypeId(), new BeanPropertyRowMapper<>(AreaType.class)));

            return area;
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void destroy(int areaId) {
        jdbcTemplate.execute("DELETE FROM Area WHERE AreaId = " + areaId);
    }

    @Override
    public void destroyByName(String name) {
        jdbcTemplate.execute("DELETE FROM Area WHERE Name = '" + name + "'");
    }
}
