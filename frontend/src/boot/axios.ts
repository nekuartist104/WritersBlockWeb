import { boot } from 'quasar/wrappers'
import axios, { AxiosError, AxiosInstance } from 'axios'
import { Cookies, Notify } from 'quasar'

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $axios: AxiosInstance
    }
}

let cookies
if (Cookies.hasOwnProperty('getAll')) {
    cookies = Cookies.getAll()
}
const httpClient = axios.create({
    baseURL: '/api',
    timeout: 5000000,
    headers: { 'X-XSRF-TOKEN': cookies?['XSRF-TOKEN'] : undefined }
})

function isForbidden(error: AxiosError) {
    return error.response!.status === 403
}

function isValidationError(error: AxiosError) {
    return (error.response!.data!.hasOwnProperty('pageErrors') || error.response!.data!.hasOwnProperty('fieldErrors'))
}

function isUnprocessable(error: AxiosError) {
    return error.response!.status === 422
}

function isExpected(error: AxiosError) {
    return error.response!.data!.hasOwnProperty('expected')
}

httpClient.interceptors.response.use(
    (response) => response,
    (error) => {
        if (isExpected(error)) {
            throw error
        }
        if (isForbidden(error) || isValidationError(error) || isUnprocessable(error)) {
            return Promise.reject(error)
        }
        else {
            Notify.create({
                message: 'Web Project error: ' + error.response.status + ' - ' + error.response.statusText,
                position: 'bottom',
                timeout: 3000,
                type: 'negative'
            })
        }
    }
)

export default boot(({app}) => {
    app.config.globalProperties.$axios = axios
    app.config.globalProperties.$api = httpClient
})

export { httpClient }