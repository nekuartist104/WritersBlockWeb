package moveslikejagger.controller;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import moveslikejagger.controller.requests.CreateCharacterRequest;
import moveslikejagger.controller.requests.UpdateCharacterRequest;
import moveslikejagger.repository.CharacterJpaRepository;
import moveslikejagger.repository.CharacterRepository;
import moveslikejagger.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private CharacterJpaRepository characterJpaRepository;

    @GetMapping("/getAllCharacters")
    public ResponseEntity getAllCharacters() {
        //Hibernate
        List<Character> characters = characterJpaRepository.findAll();
        //SQL
//        List<Character> characters = characterRepository.all();
        return new ResponseEntity(characters, HttpStatus.OK);
    }

    @GetMapping("/getAllCharactersByLocation")
    public ResponseEntity getAllCharactersByLocation(@RequestParam int locationId) {
        List<Character> characters = characterRepository.all(locationId);
        return new ResponseEntity(characters, HttpStatus.OK);
    }

    @GetMapping("/getCharacterById")
    public ResponseEntity getCharacterById(@RequestParam int characterId) {
        //Hibernate
        Character myCharacter = characterJpaRepository.findById(characterId).orElse(null);
        //SQL
//        Character myCharacter = characterRepository.find(characterId);
        if (myCharacter == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(myCharacter, HttpStatus.OK);
        }
//        return new ResponseEntity(myCharacter, HttpStatus.OK);
    }

    @PostMapping("/createCharacter")
    public ResponseEntity createCharacter(@RequestBody CreateCharacterRequest createCharacterRequest) {
        Character character = new Character();
        character.setName(createCharacterRequest.getName());
        character.setAge(createCharacterRequest.getAge());
        character.setLocationId(createCharacterRequest.getLocationId());
        character.setCharacteristicTrait(createCharacterRequest.getCharacteristicTrait());
        character.setAppearance(createCharacterRequest.getAppearance());
        character.setPersonality(createCharacterRequest.getPersonality());

        //Hibernate
        if (character.getAge() >= 0) {
            characterJpaRepository.save(character);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //SQL
//        if (character.getAge() >= 0) {
//            try {
//                characterRepository.create(createCharacterRequest);
//                return new ResponseEntity(HttpStatus.CREATED);
//            } catch (UncategorizedSQLException e) {
//                if (e.getSQLException().getErrorCode() == 2628) {
//                    return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
//                }
//            } catch (DataIntegrityViolationException e) {
//                if (((SQLServerException) e.getCause()).getErrorCode() == 2627) {
//                    return new ResponseEntity(HttpStatus.CONFLICT);
//                }
//                if (((SQLServerException) e.getCause()).getErrorCode() == 547) {
//                    return new ResponseEntity(HttpStatus.NOT_FOUND);
//                }
//            }
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
    }

    @PostMapping("/updateCharacter")
    public ResponseEntity updateCharacter(@RequestBody UpdateCharacterRequest updateCharacterRequest) {
        Character character = new Character();
        character.setCharacterId(updateCharacterRequest.getCharacterId());
        character.setName(updateCharacterRequest.getName());
        character.setAge(updateCharacterRequest.getAge());
        character.setLocationId(updateCharacterRequest.getLocationId());
        character.setCharacteristicTrait(updateCharacterRequest.getCharacteristicTrait());
        character.setAppearance(updateCharacterRequest.getAppearance());
        character.setPersonality(updateCharacterRequest.getPersonality());

        //Hibernate
        if (character.getAge() >= 0) {
            characterJpaRepository.save(character);
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        //SQL
//        if (character.getAge() >= 0) {
//            try {
//                characterRepository.update(updateCharacterRequest);
//                return new ResponseEntity(HttpStatus.OK);
//            } catch (UncategorizedSQLException e) {
//                if (e.getSQLException().getErrorCode() == 2628) {
//                    return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
//                }
//            } catch (DataIntegrityViolationException e) {
//                if (((SQLServerException) e.getCause()).getErrorCode() == 2627) {
//                    return new ResponseEntity(HttpStatus.CONFLICT);
//                }
//                if (((SQLServerException) e.getCause()).getErrorCode() == 547) {
//                    return new ResponseEntity(HttpStatus.NOT_FOUND);
//                }
//            }
//            return new ResponseEntity(HttpStatus.OK);
//        }
//        else {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
    }

    @DeleteMapping("/deleteCharacter")
    public ResponseEntity deleteCharacter(@RequestParam int characterId) {
        //Hibernate
        characterJpaRepository.deleteById(characterId);

        //SQL
//        characterRepository.destroy(characterId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
