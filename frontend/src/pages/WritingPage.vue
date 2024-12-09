<template>
    <div class="row no-wrap q-mt-sm q-mb-sm">
        <q-btn style="color: white; background-color: hotpink;" icon="save" :disable="!title" @click="save()" />
        <q-btn style="color: white; background-color: hotpink;" class="q-ml-sm" label="Create" @click="create()" />
        <q-btn style="color: white; background-color: hotpink;" class="q-ml-sm" label="Delete" :disable="!selectedFile" @click="deleteChapter()" />
    </div>

    <div style="min-width: 424px;">
            <q-btn v-for="(item, i) in filepaths" :key="i" :class="i !== 0 ? 'q-ml-sm' : ''" v-model="filepaths[i]" :label=filenames[i] @click="load(filepaths[i])"/>
    </div>
    <q-input v-model="title" label="Title" />
    <q-editor v-model="text" min-height="25rem" max-height="25rem" />

</template>

<script setup lang="ts">
import { useWriteStore } from '../stores/write-store';
import { storeToRefs } from 'pinia';

const writeStore = useWriteStore()
const { text, title, filepaths, filenames, selectedFile, illegal, spaceCount } = storeToRefs(writeStore)
writeStore.firstLoadAll()

async function save() {
    await writeStore.checkChars(title.value)
    if (illegal.value) {
        alert("Sorry! A file name can't contain any of the following characters: \n \\ / : * ? \" < > |")
    }
    else if (spaceCount.value === title.value.length) {
        alert("Sorry! A blank space is not a title!")
    }
    else {
        writeStore.save(text.value, title.value, selectedFile.value)
    }
}

async function load(s: string) {
    await writeStore.getSelectedFile(s)
    await writeStore.load(selectedFile.value)
}

function create() {
    text.value = ""
    title.value = "Untitled"
    selectedFile.value = ""
}

function deleteChapter() {
    var retVal = confirm("Are you sure you want to delete this chapter? \nThis action cannot be undone.")
    if (retVal) {
        writeStore.delete(selectedFile.value)
        title.value = ""
        text.value = ""
        selectedFile.value = ""
    }
}

</script>