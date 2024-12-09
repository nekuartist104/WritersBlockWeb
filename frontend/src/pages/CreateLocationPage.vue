<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" icon="arrow_back" @click="goToLocationPage" />
        </div>
    </div>
    <q-input v-model="location.name" label="Location Name" />
    <q-select v-model="location.world" label="World" :options="worlds" option-label="name"/>
    <q-input v-model="location.nationality" label="Nationality" />
    <q-input v-model="location.population" label="Population" />
    <q-input v-model="location.climate" label="Climate" />
    <q-input v-model="location.terrain" label="Terrain" />
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Create new location" @click="createLocation" />
    <!-- <q-btn style="font-family: 'MyFont Final'; color: white; background-color: hotpink;" label="Create new location" @click="createLocation" /> -->
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useLocationStore } from '../stores/location-store';
import { storeToRefs } from 'pinia';

const locationStore = useLocationStore()
const router = useRouter()
const { location, worlds } = storeToRefs(locationStore)
locationStore.getAllWorlds()

function goToLocationPage() {
    router.push("/location")
}

function createLocation() {
    location.value.worldId = location.value.world.worldId
    locationStore.createLocation()
}

</script>