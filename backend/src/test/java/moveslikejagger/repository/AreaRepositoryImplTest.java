package moveslikejagger.repository;

import moveslikejagger.controller.requests.*;
import moveslikejagger.models.Area;
import moveslikejagger.models.AreaType;
import moveslikejagger.models.Location;
import moveslikejagger.models.World;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AreaRepositoryImplTest {

    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private WorldRepository worldRepository;
    @Autowired
    private AreaTypeRepository areaTypeRepository;

    @Test
    public void createAreaTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";
        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        locationRepository.destroyByName(testLocationName);
        areaTypeRepository.destroyByName(testAreaTypeName);
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Get area using test name and check it returns the correct area
        Area myArea = areaRepository.findByName(testAreaName);
        assertEquals(testAreaName, myArea.getName());
        assertEquals(myLocation.getLocationId(), myArea.getLocationId());
        assertEquals(myAreaType.getAreaTypeId(), myArea.getAreaTypeId());
        assertEquals(1, myArea.getSize());
    }

    @Test
    public void deleteAreaTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";
        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        locationRepository.destroyByName(testLocationName);
        areaTypeRepository.destroyByName(testAreaTypeName);
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Delete area and then try to find it
        areaRepository.destroyByName(testAreaName);
        Area myArea = areaRepository.findByName(testAreaName);
        assertNull(myArea);
    }

    @Test
    public void updateAreaNameTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";
        String secondTestAreaName = "Test Area-123456789V2";
        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName(secondTestAreaName);
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Get area using test name and update its name
        Area myArea = areaRepository.findByName(testAreaName);
        UpdateAreaRequest updateAreaRequest = new UpdateAreaRequest();
        updateAreaRequest.setName(secondTestAreaName);
        updateAreaRequest.setAreaId(myArea.getAreaId());
        updateAreaRequest.setAreaTypeId(myArea.getAreaTypeId());
        updateAreaRequest.setLocationId(myArea.getLocationId());
        updateAreaRequest.setSize(myArea.getSize());
        areaRepository.update(updateAreaRequest);

        //Get area again and check its name is not equal to test name
        Area myUpdatedArea = areaRepository.findByName(secondTestAreaName);
        assertNotEquals(testAreaName, myUpdatedArea.getName());
        assertEquals(myArea.getAreaId(), myUpdatedArea.getAreaId());
        assertEquals(myArea.getAreaTypeId(), myUpdatedArea.getAreaTypeId());
        assertEquals(myArea.getLocationId(), myUpdatedArea.getLocationId());
        assertEquals(1, myUpdatedArea.getSize());
    }

    @Test
    public void updateAreaTypeIdTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";
        String secondTestAreaTypeName = "Test AreaType2-123456789Area";
        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        locationRepository.destroyByName(testLocationName);
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName(secondTestAreaTypeName);
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create second valid area type with unique name
        AreaType mySecondAreaType = areaTypeRepository.findByName(secondTestAreaTypeName);
        if (mySecondAreaType == null) {
            CreateAreaTypeRequest secondCreateAreaTypeRequest = new CreateAreaTypeRequest();
            secondCreateAreaTypeRequest.setName(secondTestAreaTypeName);
            areaTypeRepository.create(secondCreateAreaTypeRequest);
            mySecondAreaType = areaTypeRepository.findByName(secondTestAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Get area using test name and update its area type ID
        Area myArea = areaRepository.findByName(testAreaName);
        UpdateAreaRequest updateAreaRequest = new UpdateAreaRequest();
        updateAreaRequest.setName(myArea.getName());
        updateAreaRequest.setAreaId(myArea.getAreaId());
        updateAreaRequest.setAreaTypeId(mySecondAreaType.getAreaTypeId());
        updateAreaRequest.setLocationId(myArea.getLocationId());
        updateAreaRequest.setSize(myArea.getSize());
        areaRepository.update(updateAreaRequest);

        //Get area again and check its area type ID is not equal to test area type ID
        Area myUpdatedArea = areaRepository.findByName(myArea.getName());
        assertNotEquals(myArea.getAreaTypeId(), myUpdatedArea.getAreaTypeId());
        assertEquals(testAreaName, myUpdatedArea.getName());
        assertEquals(myArea.getAreaId(), myUpdatedArea.getAreaId());
        assertEquals(myArea.getLocationId(), myUpdatedArea.getLocationId());
        assertEquals(1, myUpdatedArea.getSize());
    }

    @Test
    public void updateLocationIdTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";
        String secondTestLocationName = "Test Location2-123456789Area";
        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        locationRepository.destroyByName(testLocationName);
        areaTypeRepository.destroyByName(testAreaTypeName);
        locationRepository.destroyByName(secondTestLocationName);
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create second valid location with unique name
        Location mySecondLocation = locationRepository.findByName(secondTestLocationName);
        if (mySecondLocation == null) {
            CreateLocationRequest createSecondLocationRequest = new CreateLocationRequest();
            createSecondLocationRequest.setWorldId(myWorld.getWorldId());
            createSecondLocationRequest.setName(secondTestLocationName);
            createSecondLocationRequest.setNationality("nationality");
            createSecondLocationRequest.setPopulation(1);
            createSecondLocationRequest.setClimate("climate");
            createSecondLocationRequest.setTerrain("terrain");
            locationRepository.create(createSecondLocationRequest);
            mySecondLocation = locationRepository.findByName(secondTestLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Get area using test name and update its location ID
        Area myArea = areaRepository.findByName(testAreaName);
        UpdateAreaRequest updateAreaRequest = new UpdateAreaRequest();
        updateAreaRequest.setName(myArea.getName());
        updateAreaRequest.setAreaId(myArea.getAreaId());
        updateAreaRequest.setAreaTypeId(myArea.getAreaTypeId());
        updateAreaRequest.setLocationId(mySecondLocation.getLocationId());
        updateAreaRequest.setSize(myArea.getSize());
        areaRepository.update(updateAreaRequest);

        //Get area again and check its location ID is not equal to test location ID
        Area myUpdatedArea = areaRepository.findByName(myArea.getName());
        assertNotEquals(myArea.getLocationId(), myUpdatedArea.getLocationId());
        assertEquals(testAreaName, myUpdatedArea.getName());
        assertEquals(myArea.getAreaId(), myUpdatedArea.getAreaId());
        assertEquals(myArea.getAreaTypeId(), myUpdatedArea.getAreaTypeId());
        assertEquals(1, myUpdatedArea.getSize());
    }

    @Test
    public void updateSizeTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";

        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName("Test Area-123456789V2");
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Get area using test name and update its size
        Area myArea = areaRepository.findByName(testAreaName);
        UpdateAreaRequest updateAreaRequest = new UpdateAreaRequest();
        updateAreaRequest.setName(testAreaName);
        updateAreaRequest.setAreaId(myArea.getAreaId());
        updateAreaRequest.setAreaTypeId(myArea.getAreaTypeId());
        updateAreaRequest.setLocationId(myArea.getLocationId());
        updateAreaRequest.setSize(2);
        areaRepository.update(updateAreaRequest);

        //Get area again and check its name is not equal to test name
        Area myUpdatedArea = areaRepository.findByName(testAreaName);
        assertNotEquals(myArea.getSize(), myUpdatedArea.getSize());
        assertEquals(testAreaName, myUpdatedArea.getName());
        assertEquals(myArea.getAreaId(), myUpdatedArea.getAreaId());
        assertEquals(myArea.getAreaTypeId(), myUpdatedArea.getAreaTypeId());
        assertEquals(myArea.getLocationId(), myUpdatedArea.getLocationId());
    }

    @Test
    public void areaNotFoundTest() {
        //Find area with invalid ID and check does not exist
        int invalidId = -1;
        Area area = areaRepository.find(invalidId);
        assertNull(area);
    }

    @Test
    public void createAreaWithInvalidNameTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";

        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName("Test Area-123456789V2");
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with invalid name
        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(text);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);

        try {
            areaRepository.create(createAreaRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void createAreaWithInvalidAreaTypeIdTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";

        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName("Test Area-123456789V2");
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with invalid area type ID
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(-1);
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);

        try {
            areaRepository.create(createAreaRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void createAreaWithInvalidLocationIdTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";

        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName("Test Area-123456789V2");
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create area with invalid area type ID
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(-1);
        createAreaRequest.setSize(1);

        try {
            areaRepository.create(createAreaRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void createAreaWithInvalidSizeTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";

        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName("Test Area-123456789V2");
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with invalid size
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(-1);

        try {
            areaRepository.create(createAreaRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidNameTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";

        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName("Test Area-123456789V2");
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Get area using test name and update its size
        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        Area myArea = areaRepository.findByName(testAreaName);
        UpdateAreaRequest updateAreaRequest = new UpdateAreaRequest();
        updateAreaRequest.setName(text);
        updateAreaRequest.setAreaId(myArea.getAreaId());
        updateAreaRequest.setAreaTypeId(myArea.getAreaTypeId());
        updateAreaRequest.setLocationId(myArea.getLocationId());
        updateAreaRequest.setSize(myArea.getSize());

        try {
            areaRepository.update(updateAreaRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidAreaTypeIdTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";

        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName("Test Area-123456789V2");
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Get area using test name and update its size
        Area myArea = areaRepository.findByName(testAreaName);
        UpdateAreaRequest updateAreaRequest = new UpdateAreaRequest();
        updateAreaRequest.setName(testAreaName);
        updateAreaRequest.setAreaId(myArea.getAreaId());
        updateAreaRequest.setAreaTypeId(-1);
        updateAreaRequest.setLocationId(myArea.getLocationId());
        updateAreaRequest.setSize(myArea.getSize());

        try {
            areaRepository.update(updateAreaRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidLocationIdTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";

        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName("Test Area-123456789V2");
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Get area using test name and update its size
        Area myArea = areaRepository.findByName(testAreaName);
        UpdateAreaRequest updateAreaRequest = new UpdateAreaRequest();
        updateAreaRequest.setName(testAreaName);
        updateAreaRequest.setAreaId(myArea.getAreaId());
        updateAreaRequest.setAreaTypeId(myArea.getAreaTypeId());
        updateAreaRequest.setLocationId(-1);
        updateAreaRequest.setSize(myArea.getSize());

        try {
            areaRepository.update(updateAreaRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidSizeTest() {
        String testAreaName = "Test Area-123456789";
        String testWorldName = "Test World-123456789Area";
        String testAreaTypeName = "Test AreaType-123456789Area";
        String testLocationName = "Test Location-123456789Area";

        //Delete pre-existing test area, location, area type and world
        areaRepository.destroyByName(testAreaName);
        areaRepository.destroyByName("Test Area-123456789V2");
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location2-123456789Area");
        areaTypeRepository.destroyByName(testAreaTypeName);
        areaTypeRepository.destroyByName("Test AreaType2-123456789Area");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create valid area type with unique name
        AreaType myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        if (myAreaType == null) {
            CreateAreaTypeRequest createAreaTypeRequest = new CreateAreaTypeRequest();
            createAreaTypeRequest.setName(testAreaTypeName);
            areaTypeRepository.create(createAreaTypeRequest);
            myAreaType = areaTypeRepository.findByName(testAreaTypeName);
        }

        //Create valid location with unique name
        Location myLocation = locationRepository.findByName(testLocationName);
        if (myLocation == null) {
            CreateLocationRequest createLocationRequest = new CreateLocationRequest();
            createLocationRequest.setWorldId(myWorld.getWorldId());
            createLocationRequest.setName(testLocationName);
            createLocationRequest.setNationality("nationality");
            createLocationRequest.setPopulation(1);
            createLocationRequest.setClimate("climate");
            createLocationRequest.setTerrain("terrain");
            locationRepository.create(createLocationRequest);
            myLocation = locationRepository.findByName(testLocationName);
        }

        //Create area with unique name
        CreateAreaRequest createAreaRequest = new CreateAreaRequest();
        createAreaRequest.setName(testAreaName);
        createAreaRequest.setAreaTypeId(myAreaType.getAreaTypeId());
        createAreaRequest.setLocationId(myLocation.getLocationId());
        createAreaRequest.setSize(1);
        areaRepository.create(createAreaRequest);

        //Get area using test name and update its size
        Area myArea = areaRepository.findByName(testAreaName);
        UpdateAreaRequest updateAreaRequest = new UpdateAreaRequest();
        updateAreaRequest.setName(testAreaName);
        updateAreaRequest.setAreaId(myArea.getAreaId());
        updateAreaRequest.setAreaTypeId(myArea.getAreaTypeId());
        updateAreaRequest.setLocationId(myArea.getLocationId());
        updateAreaRequest.setSize(-1);

        try {
            areaRepository.update(updateAreaRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }
}