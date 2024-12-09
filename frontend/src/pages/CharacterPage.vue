<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Create" @click="goToCreateCharacterPage" />
            <q-select v-model="character" label="Character" :options="characters" :option-label="c => c.characterId ? c.name : ''" @update:model-value="loadCharacter"/>
        </div>
    </div>
    <q-input disable v-model="character.characterId" label="Character ID" />
    <q-input v-model="character.name" label="Name" :disable="!characters[0]" />
    <q-input v-model="character.age" label="Age" :disable="!characters[0]"/>
    <q-select v-model="character.location" label="Residence" :options="locations" :option-label="l => l.name + ' (' + l.world?.name + ')'" :disable="!characters[0]"/>
    <q-input v-model="character.characteristicTrait" label="Characteristic Trait" :disable="!characters[0]" />
    <q-input v-model="character.appearance" label="Appearance" :disable="!characters[0]" />
    <q-input v-model="character.personality" label="Personality" :disable="!characters[0]" />
    <q-btn style="color: white; background-color: hotpink;" class="q-mt-sm" label="Update character" :disable="!characters[0]" @click="updateCharacter" />
    <q-btn style="color: white; background-color: hotpink;" class="q-ml-sm q-mt-sm" label="Delete character" :disable="!characters[0]" @click="deleteCharacter" />
</template>

<script setup lang="ts">
import { useRouter } from 'vue-router';
import { useCharacterStore } from '../stores/character-store';
import { storeToRefs } from 'pinia';

const characterStore = useCharacterStore()
const router = useRouter()
const { character, locations, characters } = storeToRefs(characterStore)
characterStore.getAllLocations().then(characterStore.getAllCharacters)

async function loadCharacter() {
    await characterStore.loadCharacterById(character.value.characterId)
}

function goToCreateCharacterPage() {
    router.push('/createCharacter')
    character.value = {}
}

function updateCharacter() {
    characterStore.updateCharacter()
}

function deleteCharacter() {
    var retVal = confirm("Are you sure you want to delete this character? \nThis action cannot be undone.");
    if( retVal) {
        characterStore.deleteCharacter()
    }
}

</script>