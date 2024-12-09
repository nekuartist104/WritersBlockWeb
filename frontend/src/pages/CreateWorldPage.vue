<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" icon="arrow_back" @click="goToWorldPage" />
        </div>
    </div>
    <q-input v-model="world.name" label="World Name" />   
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Create world" @click="createWorld" /> 
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useWorldStore } from '../stores/world-store';
import { storeToRefs } from 'pinia';

const worldStore = useWorldStore()
const router = useRouter()
const { world } = storeToRefs(worldStore)

function goToWorldPage() {
    router.push('/world')
}

function createWorld() {
    worldStore.createWorld()
}

</script>