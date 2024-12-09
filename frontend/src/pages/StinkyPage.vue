<template>
    <div class="row no-wrap">
        <div style="min-width: 424px;">
            <h3 style="color: rosybrown;">THIS IS LOCATION</h3>
            <h2 style="color: rebeccapurple;">SMELLY FISHY</h2>
            <h4 style="color: tomato;"> {{text}} {{ name }} </h4>
            <q-select v-model="selectedName" :options="names"/>
            <q-btn color="primary" label="oh nana, whats my name?" @click="nameLabel" />
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';

const selectedName = ref("")
const names = ref(["bob", "jonny", "steven universe"])
var text = ref("")
var name = ref("")

function nameLabel() {
    text.value = "Hi, my name is"
    name.value = selectedName.value

}

</script>

