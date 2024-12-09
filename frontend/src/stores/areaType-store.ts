import { defineStore } from 'pinia'
import { httpClient } from 'src/boot/axios'
import AreaType from 'src/domain/AreaType'

export const useAreaTypeStore = defineStore('areaType', {
    state: () => ({
        areaType: {} as AreaType,
        areaTypes: [] as AreaType[]
    }),
    actions: {
        async loadAreaTypeById(id: number) {
            const response = await httpClient.get('/getAreaTypeById?areaTypeId=' + id)
            this.areaType = response.data
        },
        async createAreaType() {
            await httpClient.post('/createAreaType', this.areaType)
        },
        async updateAreaType() {
            await httpClient.post('/updateAreaType', this.areaType)
        },
        async deleteAreaType() {
            await httpClient.delete('/deleteAreaType?areaTypeId=' + this.areaType.areaTypeId)
            this.areaTypes.splice(this.areaTypes.findIndex(a => a.areaTypeId === this.areaType.areaTypeId), 1)

            if (this.areaTypes.length > 0) {
                this.loadAreaTypeById(this.areaTypes[this.areaTypes.length-1].areaTypeId) 
            }
            else {
                this.areaType = {} as AreaType
            }
        },
        async getAllAreaTypes() {
            const response = await httpClient.get('/getAreaTypes')
            this.areaTypes = response.data
            this.areaType = this.areaTypes[0]
            this.loadAreaTypeById(this.areaType.areaTypeId)
        }
    },
})