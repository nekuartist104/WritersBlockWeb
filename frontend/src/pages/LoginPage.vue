<template>
    <q-layout view="hHh LpR fFf">
        <q-page-container style="padding-left: 500px; padding-right: 500px; padding-top: 100px">
            <div class="column">
                <h1 style="text-align: center;"> LOGIN!! </h1>
                <q-input v-model="username" dense filled label="Username" />
                <!-- <q-input v-model="password" label="Password" /> -->
                <q-input v-model="password" class="q-pt-sm" dense filled :type="isPwd ? 'password' : 'text'" label="Password">
                    <template v-slot:append>
                    <q-icon
                        :name="isPwd ? 'visibility_off' : 'visibility'"
                        class="cursor-pointer"
                        @click="isPwd = !isPwd"
                    />
                    </template>
                </q-input>
                <q-btn color="primary" style="padding-left: 50px; padding-right: 50px; margin-top: 8px;" label="Login" @click='checkLogin'/>
                <q-btn color="indigo" style="padding-left: 50px; padding-right: 50px; margin-top: 8px" label="Create account ?" @click='createAccount'/>

            </div>
        </q-page-container>
    </q-layout>
</template>




<script setup lang="ts">
import { ref } from 'vue';
import { useUserStore } from '../stores/user-store';
import { useRouter } from 'vue-router';
import { useQuasar } from 'quasar';


const username = ref("")
const password = ref("")
const isPwd = ref(true)
const userStore = useUserStore()
const router = useRouter()
const $q = useQuasar()
const response = ref(null as any)

async function checkLogin() {
    if (username.value === '' || password.value === '') {
        showEmptyFieldsNotif()
    }
    else {
        response.value = await userStore.accountResponse(username.value, password.value)
        if (response.value.accountResponse === 'SUCCESSFUL_LOGIN') {
            sessionStorage.setItem('loogedInID', response.value.id)
            goToHomePage()
        }
        else if (response.value.accountResponse === 'NOT_FOUND' || response.value.accountResponse === 'USERNAME_TAKEN'){
            sessionStorage.removeItem('loogedInID')
            showNoAccountNotif()
        }
    }
}

async function createAccount() {
    if (username.value === '' || password.value === '') {
        showEmptyFieldsNotif()
    }
    else {
        response.value = await userStore.accountResponse(username.value, password.value)
        if (response.value.accountResponse === 'USERNAME_TAKEN' || response.value.accountResponse === 'SUCCESSFUL_LOGIN') {
            sessionStorage.removeItem('loogedInID')
            showAccountExistsNotif()
        }   
        else {
            sessionStorage.setItem('loogedInID', await userStore.createAccount(username.value, password.value))
            goToHomePage()
        } 
    }    
}

function showAccountExistsNotif() {
    $q.notify({
        message: 'This account already exists.',
        color: 'indigo'
    })
}

function showNoAccountNotif() {
    $q.notify({
        message: 'The username or password is incorrect. Try again.',
        color: 'indigo'
    })
}

function showEmptyFieldsNotif() {
    $q.notify({
        message: 'Please fill in both fields.',
        color: 'indigo'
    })
}

function goToHomePage() {
    router.push("/home")
}




</script>

