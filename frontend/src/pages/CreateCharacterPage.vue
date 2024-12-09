<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" icon="arrow_back" @click="goToCharacterPage" />
        </div>
    </div>
    <q-input v-model="character.name" label="Name" />
    <q-input v-model="character.age" label="Age" />
    <q-select v-model="character.location" label="Residence" :options="locations" :option-label="l => l.name + ' (' + l.world?.name + ')'"/>
    <q-input v-model="character.characteristicTrait" label="Characteristic Trait" />
    <q-input v-model="character.appearance" label="Appearance" />
    <q-input v-model="character.personality" label="Personality" />
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Create character" @click="createCharacter" />
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useCharacterStore } from '../stores/character-store';
import { storeToRefs } from 'pinia';

const characterStore = useCharacterStore()
const router = useRouter()
const { character, locations } = storeToRefs(characterStore)
characterStore.getAllLocations()

function goToCharacterPage() {
    router.push('/character')
}

function createCharacter() {
    character.value.locationId = character.value.location.locationId
    characterStore.createCharacter()
}

</script>