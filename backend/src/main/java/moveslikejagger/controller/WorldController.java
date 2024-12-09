package moveslikejagger.controller;

import moveslikejagger.controller.requests.CreateWorldRequest;
import moveslikejagger.controller.requests.UpdateWorldRequest;
import moveslikejagger.models.World;
import moveslikejagger.repository.WorldJpaRepository;
import moveslikejagger.repository.WorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class WorldController {

    @Autowired
    private WorldRepository worldRepository;

    @Autowired
    private WorldJpaRepository worldJpaRepository;

    @GetMapping("/getWorldById")
    public ResponseEntity getWorld(@RequestParam int worldId) {
        //Hibernate
        World myWorld = worldJpaRepository.findById(worldId).orElse(null);
        //SQL
//        World myWorld = worldRepository.find(worldId);
        if (myWorld == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(myWorld, HttpStatus.OK);
        }
    }

    @GetMapping("/getWorldByName")
    public ResponseEntity getWorld(@RequestParam String name) {
        //Hibernate
        World myWorld = worldJpaRepository.findByName(name);
        //SQL
//        World myWorld = worldRepository.findByName(name);

        if (myWorld == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(myWorld, HttpStatus.OK);
        }
    }

    @GetMapping("/getWorlds")
    public ResponseEntity getWorlds() {
        //Hibernate
        List<World> worlds = worldJpaRepository.findAll();
        //SQL
//        List<World> worlds = worldRepository.all();
        return new ResponseEntity(worlds, HttpStatus.OK);
    }

    @DeleteMapping("/deleteWorld")
    public ResponseEntity deleteWorld(@RequestParam int worldId) {
        //Hibernate
        worldJpaRepository.deleteById(worldId);
        //SQL
//        worldRepository.destroy(worldId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/createWorld")
    public ResponseEntity createWorld(@RequestBody CreateWorldRequest createWorldRequest) {
        //Hibernate
        World world = new World();
        world.setName(createWorldRequest.getName());
        worldJpaRepository.save(world);

        //SQL
//        try {
//            worldRepository.create(createWorldRequest);
//        }
//        catch (DataIntegrityViolationException e) {
//            if (((SQLException) e.getCause()).getErrorCode() == 2627) {
//                return new ResponseEntity(HttpStatus.CONFLICT);
//            }
//        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/updateWorld")
    public ResponseEntity updateWorld(@RequestBody UpdateWorldRequest updateWorldRequest) {
        //Hibernate
        World world = new World();
        world.setWorldId(updateWorldRequest.getWorldId());
        world.setName(updateWorldRequest.getName());
        worldJpaRepository.save(world);

        //SQL
//        try {
//            worldRepository.update(updateWorldRequest);
//        }
//        catch (DataIntegrityViolationException e) {
//            if (((SQLException) e.getCause()).getErrorCode() == 2627) {
//                return new ResponseEntity(HttpStatus.CONFLICT);
//            }
//        }

        return new ResponseEntity(HttpStatus.OK);
    }
}
