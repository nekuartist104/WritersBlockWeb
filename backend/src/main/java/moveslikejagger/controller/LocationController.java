package moveslikejagger.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import moveslikejagger.controller.requests.CreateLocationRequest;
import moveslikejagger.controller.requests.UpdateLocationRequest;
import moveslikejagger.models.Location;
import moveslikejagger.repository.LocationJpaRepository;
import moveslikejagger.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationJpaRepository locationJpaRepository;

    @GetMapping("/getLocationsByWorldId")
    public ResponseEntity getLocationsByWorldId(@RequestParam int worldId) {
        //Hibernate
        List<Location> locations = locationJpaRepository.findAllByWorldId(worldId);
        //SQL
//        List<Location> locations = locationRepository.all(worldId);
        return new ResponseEntity(locations, HttpStatus.OK);
    }

    @GetMapping("/getAllLocations")
    public ResponseEntity getAllLocations() {
        //Hibernate
        List<Location> locations = locationJpaRepository.findAll();
        //SQL
//        List<Location> locations = locationRepository.all();
        return new ResponseEntity(locations, HttpStatus.OK);
    }

    @GetMapping("/getLocationById")
    public ResponseEntity getLocationById(@RequestParam int locationId) {
        //Hibernate
        Location myLocation = locationJpaRepository.findById(locationId).orElse(null);
        //SQL
//        Location myLocation = locationRepository.find(locationId);
        if (myLocation == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(myLocation, HttpStatus.OK);
        }
    }

    @PostMapping("/createLocation")
    public ResponseEntity createLocation(@RequestBody CreateLocationRequest createLocationRequest) {
        Location location = new Location();
        location.setName(createLocationRequest.getName());
        location.setWorldId(createLocationRequest.getWorldId());
        location.setNationality(createLocationRequest.getNationality());
        location.setPopulation(createLocationRequest.getPopulation());
        location.setClimate(createLocationRequest.getClimate());
        location.setTerrain(createLocationRequest.getTerrain());

        //Hibernate
        if (location.getPopulation() >= 0) {
            locationJpaRepository.save(location);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //SQL
//        if (location.getPopulation() >= 0) {
//            try {
//                locationRepository.create(createLocationRequest);
//                return new ResponseEntity(HttpStatus.CREATED);
//            } catch (UncategorizedSQLException e) {
//                if (e.getSQLException().getErrorCode() == 2628) {
//                    return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
//                }
//            } catch (DataIntegrityViolationException e) {
//                if (((SQLServerException) e.getCause()).getErrorCode() == 547) {
//                    return new ResponseEntity(HttpStatus.NOT_FOUND);
//                }
//                if (((SQLServerException) e.getCause()).getErrorCode() == 2627) {
//                    return new ResponseEntity(HttpStatus.CONFLICT);
//                }
//            }
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
    }

    @PostMapping("/updateLocation")
    public ResponseEntity updateLocation(@RequestBody UpdateLocationRequest updateLocationRequest) {
        Location location = new Location();
        location.setLocationId(updateLocationRequest.getLocationId());
        location.setName(updateLocationRequest.getName());
        location.setWorldId(updateLocationRequest.getWorldId());
        location.setNationality(updateLocationRequest.getNationality());
        location.setPopulation(updateLocationRequest.getPopulation());
        location.setClimate(updateLocationRequest.getClimate());
        location.setTerrain(updateLocationRequest.getTerrain());

        //Hibernate
        if (location.getPopulation() >= 0) {
            locationJpaRepository.save(location);
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //SQL
//        if (location.getPopulation() >= 0) {
//            try {
//                locationRepository.update(updateLocationRequest);
//                return new ResponseEntity(HttpStatus.OK);
//
//            } catch (UncategorizedSQLException e) {
//                if (e.getSQLException().getErrorCode() == 2628) {
//                    return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
//                }
//            } catch (DataIntegrityViolationException e) {
//                if (((SQLServerException) e.getCause()).getErrorCode() == 547) {
//                    return new ResponseEntity(HttpStatus.NOT_FOUND);
//                }
//                if (((SQLServerException) e.getCause()).getErrorCode() == 2627) {
//                    return new ResponseEntity(HttpStatus.CONFLICT);
//                }
//            }
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
    }

    @DeleteMapping("/deleteLocation")
    public ResponseEntity deleteLocation(@RequestParam int locationId) {
        //Hibernate
        locationJpaRepository.deleteById(locationId);
        //SQL
//        locationRepository.destroy(locationId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
