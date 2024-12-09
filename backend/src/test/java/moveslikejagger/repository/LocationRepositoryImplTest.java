package moveslikejagger.repository;

import moveslikejagger.controller.requests.CreateLocationRequest;
import moveslikejagger.controller.requests.CreateWorldRequest;
import moveslikejagger.controller.requests.UpdateLocationRequest;
import moveslikejagger.models.Location;
import moveslikejagger.models.World;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LocationRepositoryImplTest {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private WorldRepository worldRepository;

    @Test
    public void createLocationTest() {
        String testLocationName = "Test Location-123456789";
        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);

        //Create valid world with unique name
        String testWorldName = "Test World-123456789Location";
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location using test name and check it returns the correct location
        Location myLocation = locationRepository.findByName(testLocationName);
        assertEquals(testLocationName, myLocation.getName());
        assertEquals(myWorld.getWorldId(), myLocation.getWorldId());
        assertEquals("nationality", myLocation.getNationality());
        assertEquals(1, myLocation.getPopulation());
        assertEquals("climate", myLocation.getClimate());
        assertEquals("terrain", myLocation.getTerrain());
    }

    @Test
    public void deleteLocationTest() {
        String testLocationName = "Test Location-123456789";
        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);

        //Create valid world with unique name
        String testWorldName = "Test World-123456789Location";
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Try to delete location and check location cannot be found afterwards
        locationRepository.destroyByName(testLocationName);
        Location location = locationRepository.findByName(testLocationName);
        assertNull(location);
    }

    @Test
    public void updateLocationNameTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        String updatedTestLocationName = "Test Location-123456789V2";
        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName(updatedTestLocationName);
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its name
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(updatedTestLocationName);
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(myLocation.getTerrain());
        locationRepository.update(updateLocationRequest);

        //Get location again and check its name is not equal to test name
        Location myUpdatedLocation = locationRepository.findByName(updatedTestLocationName);
        assertNotEquals(testLocationName, myUpdatedLocation.getName());
        assertEquals(myWorld.getWorldId(), myLocation.getWorldId());
        assertEquals(myLocation.getLocationId(), myUpdatedLocation.getLocationId());
        assertEquals("nationality", myLocation.getNationality());
        assertEquals(1, myLocation.getPopulation());
        assertEquals("climate", myLocation.getClimate());
        assertEquals("terrain", myLocation.getTerrain());
    }

    @Test
    public void updateLocationWorldIdTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        String secondTestWorldName = "Test World2-123456789Location";

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);
        worldRepository.destroyByName(secondTestWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create second valid world with unique name
        World mySecondWorld = worldRepository.findByName(secondTestWorldName);
        if (mySecondWorld == null) {
            CreateWorldRequest createSecondWorldRequest = new CreateWorldRequest();
            createSecondWorldRequest.setName(secondTestWorldName);
            worldRepository.create(createSecondWorldRequest);
            mySecondWorld = worldRepository.findByName(secondTestWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its nationality
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(mySecondWorld.getWorldId());
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(myLocation.getTerrain());
        locationRepository.update(updateLocationRequest);

        //Get location again and check its world ID is not equal to test world ID
        Location myUpdatedLocation = locationRepository.findByName(testLocationName);
        assertNotEquals(myLocation.getWorldId(), myUpdatedLocation.getWorldId());
        assertEquals(testLocationName, myLocation.getName());
        assertEquals(myLocation.getLocationId(), myUpdatedLocation.getLocationId());
        assertEquals("nationality", myLocation.getNationality());
        assertEquals(1, myLocation.getPopulation());
        assertEquals("climate", myLocation.getClimate());
        assertEquals("terrain", myLocation.getTerrain());
    }

    @Test
    public void updateLocationNationalityTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        String updatedTestLocationNationality = "updated nationality";
        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testWorldName);
        worldRepository.create(createWorldRequest);
        World myWorld = worldRepository.findByName(testWorldName);

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its nationality
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(updatedTestLocationNationality);
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(myLocation.getTerrain());
        locationRepository.update(updateLocationRequest);

        //Get location again and check its nationality is not equal to test nationality
        Location myUpdatedLocation = locationRepository.findByName(testLocationName);
        assertNotEquals(myLocation.getNationality(), myUpdatedLocation.getNationality());
        assertEquals(testLocationName, myLocation.getName());
        assertEquals(myLocation.getLocationId(), myUpdatedLocation.getLocationId());
        assertEquals(myWorld.getWorldId(), myLocation.getWorldId());
        assertEquals(1, myLocation.getPopulation());
        assertEquals("climate", myLocation.getClimate());
        assertEquals("terrain", myLocation.getTerrain());
    }

    @Test
    public void updateLocationPopulationTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        int updatedTestLocationPopulation = 2;

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testWorldName);
        worldRepository.create(createWorldRequest);
        World myWorld = worldRepository.findByName(testWorldName);

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its nationality
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(updatedTestLocationPopulation);
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(myLocation.getTerrain());
        locationRepository.update(updateLocationRequest);

        //Get location again and check its population is not equal to test population
        Location myUpdatedLocation = locationRepository.findByName(testLocationName);
        assertNotEquals(myLocation.getPopulation(), myUpdatedLocation.getPopulation());
        assertEquals(testLocationName, myLocation.getName());
        assertEquals(myLocation.getLocationId(), myUpdatedLocation.getLocationId());
        assertEquals(myWorld.getWorldId(), myLocation.getWorldId());
        assertEquals("nationality", myLocation.getNationality());
        assertEquals("climate", myLocation.getClimate());
        assertEquals("terrain", myLocation.getTerrain());
    }

    @Test
    public void updateLocationClimateTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        String updatedTestLocationClimate = "updated climate";

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testWorldName);
        worldRepository.create(createWorldRequest);
        World myWorld = worldRepository.findByName(testWorldName);

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its nationality
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(updatedTestLocationClimate);
        updateLocationRequest.setTerrain(myLocation.getTerrain());
        locationRepository.update(updateLocationRequest);

        //Get location again and check its climate is not equal to test climate
        Location myUpdatedLocation = locationRepository.findByName(testLocationName);
        assertNotEquals(myLocation.getClimate(), myUpdatedLocation.getClimate());
        assertEquals(testLocationName, myLocation.getName());
        assertEquals(myLocation.getLocationId(), myUpdatedLocation.getLocationId());
        assertEquals(myWorld.getWorldId(), myLocation.getWorldId());
        assertEquals("nationality", myLocation.getNationality());
        assertEquals(1, myLocation.getPopulation());
        assertEquals("terrain", myLocation.getTerrain());
    }

    @Test
    public void updateLocationTerrainTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        String updatedTestLocationTerrain = "updated terrain";

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testWorldName);
        worldRepository.create(createWorldRequest);
        World myWorld = worldRepository.findByName(testWorldName);

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its nationality
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(updatedTestLocationTerrain);
        locationRepository.update(updateLocationRequest);

        //Get location again and check its terrain is not equal to test terrain
        Location myUpdatedLocation = locationRepository.findByName(testLocationName);
        assertNotEquals(myLocation.getTerrain(), myUpdatedLocation.getTerrain());
        assertEquals(testLocationName, myLocation.getName());
        assertEquals(myLocation.getLocationId(), myUpdatedLocation.getLocationId());
        assertEquals(myWorld.getWorldId(), myLocation.getWorldId());
        assertEquals("nationality", myLocation.getNationality());
        assertEquals(1, myLocation.getPopulation());
        assertEquals("climate", myLocation.getClimate());
    }

    @Test
    public void locationNotFoundTest() {
        //Find location with invalid ID and check does not exist
        int invalidId = -1;
        Location location = locationRepository.find(invalidId);
        assertNull(location);
    }

    @Test
    public void createLocationWithInvalidNameTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testWorldName);
        worldRepository.create(createWorldRequest);
        World myWorld = worldRepository.findByName(testWorldName);

        //Create location with invalid name
        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(text);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");

        try {
            locationRepository.create(createLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void createLocationWithInvalidWorldIdTest() {
        //Create location with invalid world ID
        String testLocationName = "Test Location-123456789";
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(-1);
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");

        try {
            locationRepository.create(createLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void createLocationWithInvalidNationalityTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testWorldName);
        worldRepository.create(createWorldRequest);
        World myWorld = worldRepository.findByName(testWorldName);

        //Create location with invalid nationality
        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality(text);
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");

        try {
            locationRepository.create(createLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void createLocationWithInvalidPopulationTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testWorldName);
        worldRepository.create(createWorldRequest);
        World myWorld = worldRepository.findByName(testWorldName);

        //Create location with invalid population
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(-1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");

        try {
            locationRepository.create(createLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void createLocationWithInvalidClimateTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testWorldName);
        worldRepository.create(createWorldRequest);
        World myWorld = worldRepository.findByName(testWorldName);

        //Create location with invalid climate
        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate(text);
        createLocationRequest.setTerrain("terrain");

        try {
            locationRepository.create(createLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void createLocationWithInvalidTerrainTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";
        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        CreateWorldRequest createWorldRequest = new CreateWorldRequest();
        createWorldRequest.setName(testWorldName);
        worldRepository.create(createWorldRequest);
        World myWorld = worldRepository.findByName(testWorldName);

        //Create location with invalid terrain
        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain(text);

        try {
            locationRepository.create(createLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidLocationNameTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";

        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its name
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(text);
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(myLocation.getTerrain());

        try {
            locationRepository.update(updateLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidLocationWorldIdTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its name
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(-1);
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(myLocation.getTerrain());

        try {
            locationRepository.update(updateLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidLocationNationalityTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";

        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its name
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(text);
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(myLocation.getTerrain());

        try {
            locationRepository.update(updateLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidLocationPopulationTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its name
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(-1);
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(myLocation.getTerrain());

        try {
            locationRepository.update(updateLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidLocationClimateTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";

        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its name
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(text);
        updateLocationRequest.setTerrain(myLocation.getTerrain());

        try {
            locationRepository.update(updateLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }

    @Test
    public void updateInvalidLocationTerrainTest() {
        String testWorldName = "Test World-123456789Location";
        String testLocationName = "Test Location-123456789";

        char[] chars = new char[256];
        Arrays.fill(chars, 'a');
        String text = new String(chars);

        //Delete pre-existing test location
        locationRepository.destroyByName(testLocationName);
        locationRepository.destroyByName("Test Location-123456789V2");
        worldRepository.destroyByName(testWorldName);

        //Create valid world with unique name
        World myWorld = worldRepository.findByName(testWorldName);
        if (myWorld == null) {
            CreateWorldRequest createWorldRequest = new CreateWorldRequest();
            createWorldRequest.setName(testWorldName);
            worldRepository.create(createWorldRequest);
            myWorld = worldRepository.findByName(testWorldName);
        }

        //Create location with unique name
        CreateLocationRequest createLocationRequest = new CreateLocationRequest();
        createLocationRequest.setWorldId(myWorld.getWorldId());
        createLocationRequest.setName(testLocationName);
        createLocationRequest.setNationality("nationality");
        createLocationRequest.setPopulation(1);
        createLocationRequest.setClimate("climate");
        createLocationRequest.setTerrain("terrain");
        locationRepository.create(createLocationRequest);

        //Get location and update its name
        Location myLocation = locationRepository.findByName(testLocationName);
        UpdateLocationRequest updateLocationRequest = new UpdateLocationRequest();
        updateLocationRequest.setName(myLocation.getName());
        updateLocationRequest.setLocationId(myLocation.getLocationId());
        updateLocationRequest.setWorldId(myLocation.getWorldId());
        updateLocationRequest.setNationality(myLocation.getNationality());
        updateLocationRequest.setPopulation(myLocation.getPopulation());
        updateLocationRequest.setClimate(myLocation.getClimate());
        updateLocationRequest.setTerrain(text);

        try {
            locationRepository.update(updateLocationRequest);
            fail("Exception should be thrown");
        }
        catch (Exception ex) {

        }
    }
}