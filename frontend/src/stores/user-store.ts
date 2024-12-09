import { defineStore } from 'pinia'
import { httpClient } from 'src/boot/axios'

export const useUserStore = defineStore('user', {
    state: () => ({
        name: '' as string,
    }),
    actions: {
        async createAccount(name: string, pass: string) {
            const loginDetails = {
                username: name,
                password: pass
            }
            const response = await httpClient.post('/createAccount', loginDetails)
            return response.data
        },

        async accountResponse(name: string, pass: string): Promise<String> {
            const loginDetails = {
                username: name,
                password: pass
            }
            const response = await httpClient.post('/accountResponse', loginDetails)
            return response.data
        },

        async saveAccount(first: string, second: string, nomber: number) {
            const accountDetails = {
                firstName: first,
                secondName: second,
                age: nomber,
                id: sessionStorage.getItem('loogedInID')
            }
            await httpClient.post('/saveAccount', accountDetails)
        },

        async loadAccount() {
            const userId = sessionStorage.getItem('loogedInID')
            const response = await httpClient.get('/loadAccount?id=' + userId)
            return response.data
        },
    },
})