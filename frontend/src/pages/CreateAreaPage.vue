<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" icon="arrow_back" @click="goToAreaPage" />
        </div>
    </div>
    <q-input v-model="area.name" label="Area Name" />
    <q-input v-model="area.size" label="Area Size" />
    <q-select v-model="area.location" label="Location" :options="locations" :option-label="l => l.name + ' (' + l.world?.name + ')'"/>
    <q-select v-model="area.areaType" label="Area Type" :options="areaTypes" option-label="name"/>
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Create area" @click="createArea" />
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useAreaStore } from '../stores/area-store';
import { storeToRefs } from 'pinia';

const areaStore = useAreaStore()
const router = useRouter()
areaStore.getAllAreaTypes()
areaStore.getAllLocations()

const { area, locations, areaTypes } = storeToRefs(areaStore)

function createArea() {
    area.value.locationId = area.value.location.locationId
    area.value.areaTypeId = area.value.areaType.areaTypeId
    areaStore.createArea()
}

function goToAreaPage() {
    router.push("/area")
}


</script>