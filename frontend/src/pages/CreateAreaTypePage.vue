<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" icon="arrow_back" @click="goToAreaTypePage" />
        </div>
    </div>
    <q-input v-model="areaType.name" label="Area Type" />
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="create area type" @click="createAreaType" />
</template>

<script setup lang="ts">
import { useAreaTypeStore } from '../stores/areaType-store';
import { storeToRefs } from 'pinia';
import { useRouter } from 'vue-router';

const areaTypeStore = useAreaTypeStore()
const { areaType } = storeToRefs(areaTypeStore)
const router = useRouter()

function goToAreaTypePage() {
    router.push('/areaType')
}

function createAreaType() {
    areaTypeStore.createAreaType()
}

</script>