<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Create" @click="goToCreateAreaTypePage" />
            <q-select v-model="areaType" label="Area Type" :options="areaTypes" :option-label="a => a ? a.name : ''" @update:model-value="loadAreaType"/>
        </div>
    </div>
    <q-input disable v-model="areaType.areaTypeId" label="Area Type ID" />
    <q-input v-model="areaType.name" label="Area Type" :disable="!areaTypes[0]"/>
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Update area type" :disable="!areaTypes[0]" @click="updateAreaType" />
    <q-btn style="color: white; background-color: hotpink;" class="q-ml-sm q-mt-sm" label="Delete area type" :disable="!areaTypes[0]" @click="deleteAreaType" />
</template>

<script setup lang="ts">
import { useAreaTypeStore } from '../stores/areaType-store';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';

const areaTypeStore = useAreaTypeStore()
const router = useRouter()
const { areaType, areaTypes } = storeToRefs(areaTypeStore)
areaTypeStore.getAllAreaTypes()


function loadAreaType() {
    areaTypeStore.loadAreaTypeById(areaType.value.areaTypeId)
}

function goToCreateAreaTypePage() {
    router.push('/createAreaType')
    areaType.value = {}
}

function updateAreaType() {
    areaTypeStore.updateAreaType()
}

function deleteAreaType() {
    var retVal = confirm("Are you sure you want to delete this area type? \nThis action cannot be undone.");
    if( retVal) {
        areaTypeStore.deleteAreaType()
    }
}

</script>