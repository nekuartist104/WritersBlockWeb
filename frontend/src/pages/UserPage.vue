
<template>
    <div class="column">
        <h1> user betty </h1>
        <h1 style="text-align: center;"> u smell so frooty </h1>
        <q-input v-model="firstName" style="padding-left: 50px; padding-right: 50px; margin-top: 8px;" dense filled label="First name" />
        <q-input v-model="secondName" style="padding-left: 50px; padding-right: 50px; margin-top: 8px;" dense filled label="Second name" />
        <q-input v-model="age" style="padding-left: 50px; padding-right: 50px; margin-top: 8px;" dense filled label="Age" />
        <q-btn flat round stretch dense style="width: 24px" icon="save" class="q-mr-sm" @click="save"/>


    </div>
</template>

<script setup lang="ts">
import { useUserStore } from 'src/stores/user-store';
import { ref } from 'vue';

const firstName = ref("")
const secondName = ref("")
const age = ref("")
const userStore = useUserStore()
userStore.loadAccount().then(accountDetails => {
    firstName.value = accountDetails.firstName
    secondName.value = accountDetails.secondName
    age.value = accountDetails.age
})




function save() {
    console.log('haha sme!!y')
    userStore.saveAccount(firstName.value, secondName.value, parseInt(age.value))
}



</script>

