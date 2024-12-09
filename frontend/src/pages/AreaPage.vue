<template>
    <div>
        <!-- <q-select v-model="area" label="Area" :options="areas" :option-label="a => a.name + ' (' + a.location?.name + ')'" @update:model-value="loadArea"/> -->
    </div>
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Create" @click="goToCreateAreaPage" />
    <q-select v-model="area" label="Area" :options="areas" :option-label="a => a.areaId ? a.name + ' (' + a.location?.name + ')' : ''" @update:model-value="loadArea"/>
    <q-input disable v-model="area.areaId" label="Area ID" />
    <q-input v-model="area.name" label="Area Name" :disable="!areas[0]" />
    <q-input v-model="area.size" label="Area Size" :disable="!areas[0]" />
    <q-select v-model="area.location" label="Location" :options="locations" :option-label="l => l.name + ' (' + l.world?.name + ')'" :disable="!areas[0]" />
    <q-select v-model="area.areaType" label="Area Type" :options="areaTypes" option-label="name" :disable="!areas[0]"/>
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Update area" :disable="!areas[0]" @click="updateArea" />
    <q-btn style="color: white; background-color: hotpink;" class="q-ml-sm q-mt-sm" label="Delete area" :disable="!areas[0]" @click="deleteArea" />
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useAreaStore } from '../stores/area-store';
import { storeToRefs } from 'pinia';

const areaStore = useAreaStore()
const router = useRouter()
areaStore.getAllAreaTypes()
areaStore.getAllLocations()
areaStore.getAllAreas()

const { area, locations, areaTypes, areas } = storeToRefs(areaStore)

async function loadArea() {
    await areaStore.loadAreaById(area.value.areaId)
}

function goToCreateAreaPage() {
    router.push("/createArea")
    area.value = {}
}

function updateArea() {
    areaStore.updateArea()
}

function deleteArea() {
    var retVal = confirm("Are you sure you want to delete this area? \nThis action cannot be undone.")
    if(retVal) {
        areaStore.deleteArea()
    }
}


</script>