package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateAreaTypeRequest;
import moveslikejagger.controller.requests.UpdateAreaTypeRequest;
import moveslikejagger.models.AreaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AreaTypeRepositoryImplTest {

    @Autowired
    private AreaTypeRepository repository;

    @Test
    public void createAreaTypeTest() {
        String testName = "Test AreaType-123456789";
        //Delete pre-existing test area type
        repository.destroyByName(testName);

        //Create area type with unique name
        CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
        createAreaTypeRequest.setName(testName);
        repository.create(createAreaTypeRequest);

        //Get area type using test name and check it returns the correct area type
        AreaType myAreaType = repository.findByName(testName);
        assertEquals(testName, myAreaType.getName());
    }

    @Test
    public void deleteAreaTypeTest() {
        String testName = "Test AreaType-123456789";
        //Delete pre-existing test area type
        repository.destroyByName(testName);

        //Create area type with unique name
        CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
        createAreaTypeRequest.setName(testName);
        repository.create(createAreaTypeRequest);

        //Try to delete area type and check area type cannot be found afterwards
        repository.destroyByName(testName);
        AreaType areaType = repository.findByName(testName);
        assertNull(areaType);
    }

    @Test
    public void updateAreaTypeTest() {
        String testName = "Test AreaType-123456789";
        String updatedTestName = "Test AreaType-123456789V2";
        //Delete pre-existing test area type
        repository.destroyByName(testName);
        repository.destroyByName(updatedTestName);

        //Create area type with unique name
        CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
        createAreaTypeRequest.setName(testName);
        repository.create(createAreaTypeRequest);

        //Get area type and update its name
        AreaType myAreaType = repository.findByName(testName);
        UpdateAreaTypeRequest updateAreaTypeRequest = new UpdateAreaTypeRequest();
        updateAreaTypeRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        updateAreaTypeRequest.setName(updatedTestName);
        repository.update(updateAreaTypeRequest);

        //Get area type again and check its name is not equal to test name
        AreaType myUpdatedAreaType = repository.findByName(updatedTestName);
        assertNotEquals(testName, myUpdatedAreaType.getName());
    }

    @Test
    public void areaTypeNotFoundTest() {
        //Find area type with invalid ID and check does not exist
        int invalidId = -1;
        AreaType areaType = repository.findById(invalidId);
        assertNull(areaType);
    }

    @Test
    public void areaTypeNameTooLongTest() {
        //Create area type with invalid name
        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);
        CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
        createAreaTypeRequest.setName(text);
        try {
            repository.create(createAreaTypeRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }
}