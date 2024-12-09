import { defineStore } from 'pinia'
import { httpClient } from 'src/boot/axios'

export const useWriteStore = defineStore('write', {
    state: () => ({
        text: '' as string,
        title: '' as string,
        filepaths: [] as string[],
        filenames: [] as string[],
        selectedFile: '' as string,
        illegal: false as boolean,
        spaceCount: 0 as number
    }),
    actions: {
        async save(text: string, title: string, name: string) {
            const request = {
                content: text,
                title: title,
                filename: name
            }
            const response = await httpClient.post('/save', request)
            this.selectedFile = response.data
            this.loadAll()
        },
        async getSelectedFile(name: string) {
            const searchParams = new URLSearchParams({ filename: name })
            const response = await httpClient.get('/getSelectedFile', { params: searchParams })
            this.selectedFile = response.data
        },
        async load(f: string) {
            const searchParams = new URLSearchParams({ filepath: f })
            const response = await httpClient.get('/loadText', { params: searchParams })
            const nameResponse = await httpClient.get('/loadFilename', { params: searchParams })
            this.text = response.data
            this.title = nameResponse.data
        },
        async loadAll() {
            const response = await httpClient.get('/loadFiles')
            const nameResponse = await httpClient.get('/loadFilenames')
            this.filepaths = response.data
            this.filenames = nameResponse.data
        },
        async firstLoadAll() {
            await this.loadAll()
            this.selectedFile = this.filepaths[0]
            this.load(this.selectedFile)
        },
        async delete(f: string) {
            const searchParams = new URLSearchParams({ filepath: f })
            await httpClient.delete('/delete', { params: searchParams })
            this.loadAll()
        },
        async checkChars(name: string) {
            const illegalSearchParams = new URLSearchParams({ fileTitle: name })
            const spaceSearchParams = new URLSearchParams({ fileTitle: name })
            const illegalResponse = await httpClient.get('/checkChars', { params: illegalSearchParams })
            const spaceCountResponse = await httpClient.get('/checkSpaceChars', { params: spaceSearchParams })
            this.illegal = illegalResponse.data
            this.spaceCount = spaceCountResponse.data
        }
    },
})