package moveslikejagger.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import moveslikejagger.controller.requests.CreateAreaRequest;
import moveslikejagger.controller.requests.UpdateAreaRequest;
import moveslikejagger.models.Area;
import moveslikejagger.repository.AreaJpaRepository;
import moveslikejagger.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AreaController {

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaJpaRepository areaJpaRepository;

    @GetMapping("/getAllAreas")
    public ResponseEntity getAllAreas() {
        //Hibernate
        List<Area> areas = areaJpaRepository.findAll();
        //SQL
//        List<Area> areas = areaRepository.all();
        return new ResponseEntity(areas, HttpStatus.OK);
    }

    @GetMapping("/getAreasByLocationId")
    public ResponseEntity getAreasByLocationId(@RequestParam int locationId) {
        //Hibernate
        List<Area> areas = areaJpaRepository.findAllByLocationId(locationId);
        //SQL
//        List<Area> areas = areaRepository.allByLocation(locationId);
        return new ResponseEntity(areas, HttpStatus.OK);
    }

    @GetMapping("/getAreasByAreaTypeId")
    public ResponseEntity getAreasByAreaTypeId(@RequestParam int areaTypeId) {
        //Hibernate
        List<Area> areas = areaJpaRepository.findAllByAreaTypeId(areaTypeId);
        //SQL
//        List<Area> areas = areaRepository.allByAreaType(areaTypeId);
        return new ResponseEntity(areas, HttpStatus.OK);
    }

    @GetMapping("/getAreaById")
    public ResponseEntity getAreaById(@RequestParam int areaId) {
        //Hibernate
        Area myArea = areaJpaRepository.findById(areaId).orElse(null);
        //SQL
//        Area myArea = areaRepository.Find(areaId);
        if (myArea == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(myArea, HttpStatus.OK);
        }
//        return new ResponseEntity(myArea, HttpStatus.OK);
    }

    @PostMapping("/createArea")
    public ResponseEntity createArea(@RequestBody CreateAreaRequest createAreaRequest) {
        Area area = new Area();
        area.setName(createAreaRequest.getName());
        area.setLocationId(createAreaRequest.getLocationId());
        area.setAreaTypeId(createAreaRequest.getAreaTypeId());
        area.setSize(createAreaRequest.getSize());

        //Hibernate
        if (area.getSize() >= 0) {
            areaJpaRepository.save(area);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //SQL
//        if (area.getSize() >= 0) {
//            try {
//                areaRepository.create(createAreaRequest);
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

    @PostMapping("/updateArea")
    public ResponseEntity updateArea(@RequestBody UpdateAreaRequest updateAreaRequest) {
        Area area = new Area();
        area.setName(updateAreaRequest.getName());
        area.setAreaId(updateAreaRequest.getAreaId());
        area.setLocationId(updateAreaRequest.getLocationId());
        area.setAreaTypeId(updateAreaRequest.getAreaTypeId());
        area.setSize(updateAreaRequest.getSize());

        //Hibernate
        if (area.getSize() >= 0) {
            areaJpaRepository.save(area);
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //SQL
//        if (area.getSize() >= 0) {
//            try {
//                areaRepository.update(updateAreaRequest);
//                return new ResponseEntity(HttpStatus.OK);
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

    @DeleteMapping("/deleteArea")
    public ResponseEntity deleteArea(@RequestParam int areaId) {
        //Hibernate
        areaJpaRepository.deleteById(areaId);
        //SQL
//        areaRepository.destroy(areaId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
