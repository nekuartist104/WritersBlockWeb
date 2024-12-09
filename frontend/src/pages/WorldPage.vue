<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Create" @click="goToCreateWorldPage" />
            <q-select v-model="world" label="World" :options="worlds" :option-label="w => w ? w.name : ''"/>
        </div>
    </div>
    <q-input disable v-model="world.worldId" label="World ID" />    
    <q-input v-model="world.name" label="World Name" :disable="!worlds[0]"/>   
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Update world" :disable="!worlds[0]" @click="updateWorld" /> 
    <q-btn style="color: white; background-color: hotpink;" class="q-ml-sm q-mt-sm" label="Delete world" :disable="!worlds[0]" @click="deleteWorld" /> 
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useWorldStore } from '../stores/world-store';
import { storeToRefs } from 'pinia';

const worldStore = useWorldStore()
const router = useRouter()
const { world, worlds } = storeToRefs(worldStore)
worldStore.getAllWorlds()


function goToCreateWorldPage() {
    router.push('/createWorld')
    world.value = {}
}

function updateWorld() {
    worldStore.updateWorld()
}

function deleteWorld() {
    var retVal = confirm("Are you sure you want to delete this world? \nThis action cannot be undone.");
    if( retVal) {
        worldStore.deleteWorld()
    }
}

</script>