import { defineStore } from 'pinia'
import { httpClient } from 'src/boot/axios'
import Area from 'src/domain/Area'
import AreaType from 'src/domain/AreaType'
import Location from 'src/domain/Location'

export const useAreaStore = defineStore('area', {
    state: () => ({
        area: {} as Area,
        locations: [] as Location[],
        areaTypes: [] as AreaType[],
        areas: [] as Area[]
    }),
    actions: {
        async loadAreaById(id: number) {
            const response = await httpClient.get('/getAreaById?areaId=' + id)
            const areaTypeResponse = await httpClient.get('/getAreaTypeById?areaTypeId=' + response.data.areaTypeId)
            const locationResponse = await httpClient.get('getLocationById?locationId=' + response.data.locationId)
            const worldResponse = await httpClient.get('getWorldById?worldId=' + locationResponse.data.worldId)

            const loadedArea = response.data
            loadedArea.areaType = areaTypeResponse.data
            loadedArea.location = locationResponse.data
            loadedArea.location.world = worldResponse.data

            this.area = loadedArea
        },
        async createArea() {
            await httpClient.post('/createArea', this.area)
        },
        async updateArea() {
            this.area.locationId = this.area.location.locationId
            this.area.areaTypeId = this.area.areaType.areaTypeId
            await httpClient.post('/updateArea', this.area)
        },
        async deleteArea() {
            this.area.locationId = this.area.location.locationId
            this.area.areaTypeId = this.area.areaType.areaTypeId
            await httpClient.delete('/deleteArea?areaId=' + this.area.areaId)
            this.areas.splice(this.areas.findIndex(a => a.areaId === this.area.areaId), 1)

            if (this.areas.length > 0) {
                await this.loadAreaById(this.areas[this.areas.length-1].areaId) 
            }
            else {
                this.area = {} as Area
            }
        },
        async getAllAreaTypes() {
            const response = await httpClient.get('/getAreaTypes')
            this.areaTypes = response.data
        },
        async getAllLocations() {
            const response = await httpClient.get('/getAllLocations')
            this.locations = response.data

            for (const l of this.locations) {
                const worldResponse = await httpClient.get('getWorldById?worldId=' + l.worldId)
                l.world = worldResponse.data
            }
        },
        async getAllAreas() {
            const response = await httpClient.get('/getAllAreas')
            this.areas = response.data

            //SQL
            for (const a of this.areas) {
                for (const l of this.locations) {
                    if (l.locationId === a.locationId) {
                        a.location = l
                        break
                    }
                }
                for (const at of this.areaTypes) {
                    if (at.areaTypeId === a.areaTypeId) {
                        a.areaType = at
                        break
                    }
                }
            }

            await this.loadAreaById(this.areas[0].areaId)
        }
    },
})