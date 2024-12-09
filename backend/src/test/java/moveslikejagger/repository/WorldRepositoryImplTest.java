package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateWorldRequest;
import moveslikejagger.controller.requests.UpdateWorldRequest;
import moveslikejagger.models.World;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorldRepositoryImplTest {

    @Autowired
    private WorldRepository repository;

    @Test
    public void createWorldTest() {
        String testName = "Test World-123456789";
        //Delete pre-existing test world
        repository.destroyByName(testName);

        //Create world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testName);
        repository.create(createWorldRequest);

        //Get world using test name and check it returns the correct world
        World myWorld = repository.findByName(testName);
        assertEquals(testName, myWorld.getName());

    }

    @Test
    public void deleteWorldTest() {
        String testName = "Test World-123456789";
        //Delete pre-existing test world
        repository.destroyByName(testName);

        //Create world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testName);
        repository.create(createWorldRequest);

        //Try to delete world and check world cannot be found afterwards
        repository.destroyByName(testName);
        World world = repository.findByName(testName);
        assertNull(world);
    }

    @Test
    public void updateWorldTest() {
        String testName = "Test World-123456789";
        String updatedTestName = "Test World-123456789V2";
        //Delete pre-existing test world
        repository.destroyByName(testName);
        repository.destroyByName(updatedTestName);

        //Create world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testName);
        repository.create(createWorldRequest);

        //Get world and update its name
        World myWorld = repository.findByName(testName);
        UpdateWorldRequest updateWorldRequest = new UpdateWorldRequest();
        updateWorldRequest.setWorldId(myWorld.getWorldId());
        updateWorldRequest.setName(updatedTestName);
        repository.update(updateWorldRequest);

        //Get world again and check its name is not equal to test name
        World myUpdatedWorld = repository.findByName(updatedTestName);
        assertNotEquals(testName, myUpdatedWorld.getName());
    }

    @Test
    public void worldNotFoundTest() {
        //Find world with invalid ID and check does not exist
        int invalidId = -1;
        World world = repository.find(invalidId);
        assertNull(world);
    }

    @Test
    public void worldNameTooLongTest() {
        //Create world with invalid name
        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(text);
        try {
            repository.create(createWorldRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }
}