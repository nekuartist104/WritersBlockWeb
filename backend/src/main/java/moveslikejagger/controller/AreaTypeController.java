package moveslikejagger.controller;

import moveslikejagger.controller.requests.CreateAreaTypeRequest;
import moveslikejagger.controller.requests.UpdateAreaTypeRequest;
import moveslikejagger.models.AreaType;
import moveslikejagger.repository.AreaTypeJpaRepository;
import moveslikejagger.repository.AreaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AreaTypeController {

    @Autowired
    private AreaTypeRepository areaTypeRepository;

    @Autowired
    private AreaTypeJpaRepository areaTypeJpaRepository;

    @GetMapping("/getAreaTypeById")
    public ResponseEntity getAreaTypeById(@RequestParam int areaTypeId) {
        //Hibernate
        AreaType myAreaType = areaTypeJpaRepository.findById(areaTypeId).orElse(null);
        //SQL
//        AreaType myAreaType = areaTypeRepository.findById(areaTypeId);
        if (myAreaType == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(myAreaType, HttpStatus.OK);
        }
    }

    @GetMapping("/getAreaTypes")
    public ResponseEntity getAreaTypes() {
        //Hibernate
        List<AreaType> areaTypes = areaTypeJpaRepository.findAll();
        //SQL
//        List<AreaType> areaTypes = areaTypeRepository.all();
        return new ResponseEntity(areaTypes, HttpStatus.OK);
    }

    @DeleteMapping("/deleteAreaType")
    public ResponseEntity deleteAreaType(@RequestParam int areaTypeId) {
        //Hibernate
        areaTypeJpaRepository.deleteById(areaTypeId);
        //SQL
//        areaTypeRepository.destroy(areaTypeId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/createAreaType")
    public ResponseEntity createAreaType(@RequestBody CreateAreaTypeRequest createAreaTypeRequest) {
        //Hibernate
        AreaType areaType = new AreaType();
        areaType.setName(createAreaTypeRequest.getName());
        areaTypeJpaRepository.save(areaType);

        //SQL
//        try {
//            areaTypeRepository.create(createAreaTypeRequest);
//        }
//        catch (DataIntegrityViolationException e) {
//            if (((SQLException) e.getCause()).getErrorCode() == 2627) {
//                return new ResponseEntity(HttpStatus.CONFLICT);
//            }
//        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/updateAreaType")
    public ResponseEntity updateAreaType(@RequestBody UpdateAreaTypeRequest updateAreaTypeRequest) {
        //Hibernate
        AreaType areaType = new AreaType();
        areaType.setAreaTypeId(updateAreaTypeRequest.getAreaTypeId());
        areaType.setName(updateAreaTypeRequest.getName());
        areaTypeJpaRepository.save(areaType);

        //SQL
//        try {
//            areaTypeRepository.update(updateAreaTypeRequest);
//        }
//        catch (DataIntegrityViolationException e) {
//            if (((SQLException) e.getCause()).getErrorCode() == 2627) {
//                return new ResponseEntity(HttpStatus.CONFLICT);
//            }
//        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
