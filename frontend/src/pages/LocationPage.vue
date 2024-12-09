<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Create" @click="goToCreateLocationPage" />
        </div>
    </div>
    <q-select v-model="location" label="Location" :options="locations" :option-label="l => l.locationId ? l.name + ' (' + l.world?.name + ')' : ''" @update:model-value="loadLocation"/>
    <q-input disable v-model="location.locationId" label="Location ID" />
    <q-input v-model="location.name" label="Location Name" :disable="!locations[0]" />
    <q-select v-model="location.world" label="World" :options="worlds" option-label="name" :disable="!locations[0]" />
    <q-input v-model="location.nationality" label="Nationality" :disable="!locations[0]" />
    <q-input v-model="location.population" label="Population" :disable="!locations[0]" />
    <q-input v-model="location.climate" label="Climate" :disable="!locations[0]" />
    <q-input v-model="location.terrain" label="Terrain" :disable="!locations[0]" />
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Update location" :disable="!locations[0]" @click="updateLocation" />
    <q-btn style="color: white; background-color: hotpink;" class="q-ml-sm q-mt-sm" label="Delete location" :disable="!locations[0]" @click="deleteLocation" />
    <!-- <q-btn style="font-family: 'MyFont Final'; color: hotpink;" label="Update location" @click="updateLocation" /> -->
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useLocationStore } from '../stores/location-store';
import { storeToRefs } from 'pinia';

const locationStore = useLocationStore()
const router = useRouter()
const { location, worlds, locations } = storeToRefs(locationStore)
locationStore.getAllWorlds()
locationStore.getAllLocations()

async function loadLocation() {
    await locationStore.loadLocationById(location.value.locationId)
}

function goToCreateLocationPage() {
    router.push("/createLocation")
    location.value = {}
}

function updateLocation() {
    locationStore.updateLocation()
}

function deleteLocation() {
    var retVal = confirm("Are you sure you want to delete this location? \nThis action cannot be undone.");
    if( retVal) {
        locationStore.deleteLocation()
    }
}

</script>