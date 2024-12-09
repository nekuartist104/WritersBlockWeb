package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateLocationRequest;
import moveslikejagger.controller.requests.UpdateLocationRequest;
import moveslikejagger.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(CreateLocationRequest createLocationRequest) {
        if (createLocationRequest.getPopulation() > 0) {
            jdbcTemplate.execute("INSERT INTO Location (WorldId, Name, Nationality, Population, Climate, Terrain) VALUES (" + createLocationRequest.getWorldId()
                    + "," + "'" + createLocationRequest.getName() + "'"
                    + "," + "'" + createLocationRequest.getNationality() + "'"
                    + "," + createLocationRequest.getPopulation()
                    + "," + "'" + createLocationRequest.getClimate() + "'"
                    + "," + "'" + createLocationRequest.getTerrain() + "')");
        }
        else {
            Exception ex = new Exception("Population must be greater than 0.");
            try {
                throw ex;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void update(UpdateLocationRequest updateLocationRequest) {
        if (updateLocationRequest.getPopulation() > 0) {
            jdbcTemplate.execute("UPDATE Location SET WorldId=" + updateLocationRequest.getWorldId()
                    + ", Name=" + "'" + updateLocationRequest.getName()
                    + "', Nationality=" + "'" + updateLocationRequest.getNationality()
                    + "', Population=" + updateLocationRequest.getPopulation()
                    + ", Climate=" + "'" + updateLocationRequest.getClimate()
                    + "', Terrain=" + "'" + updateLocationRequest.getTerrain() + "'"
                    + " WHERE LocationId=" + updateLocationRequest.getLocationId());
        }
        else {
            Exception ex = new Exception("Population must be greater than 0.");
            try {
                throw ex;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Location> all() {
        return jdbcTemplate.query("SELECT * FROM Location;", new BeanPropertyRowMapper<>(Location.class));
    }

    @Override
    public List<Location> all(int worldId) {
        return jdbcTemplate.query("SELECT * FROM Location WHERE WorldId = " + worldId, new BeanPropertyRowMapper<>(Location.class));
    }



    @Override
    public Location find(int locationId) {
        try {
            return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM Location WHERE LocationId = " + locationId, new BeanPropertyRowMapper<>(Location.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Location findByName(String name) {
        try {
            return jdbcTemplate.queryForObject("SELECT TOP 1 * FROM Location WHERE Name = '" + name + "';", new BeanPropertyRowMapper<>(Location.class));
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void destroy(int locationId) {
        jdbcTemplate.execute("DELETE FROM Location WHERE LocationId = " + locationId);
    }

    @Override
    public void destroyByName(String name) {
        jdbcTemplate.execute("DELETE FROM Location WHERE Name = '" + name + "';");

    }
}
