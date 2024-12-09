import { defineStore } from 'pinia'
import { httpClient } from 'src/boot/axios'
import Location from 'src/domain/Location'
import World from 'src/domain/World'


export const useLocationStore = defineStore('location', {
    state: () => ({
        location: {} as Location,
        worlds: [] as World[],
        locations: [] as Location[]
    }),
    actions: {
        async loadLocationById(id: number) {
            const response = await httpClient.get('/getLocationById?locationId=' + id)
            const worldResponse = await httpClient.get('/getWorldById?worldId=' + response.data.worldId)
            this.location = response.data
            this.location.world = worldResponse.data
        },
        async createLocation() {
            await httpClient.post('/createLocation', this.location)
        },
        async updateLocation() {
            this.location.worldId = this.location.world.worldId
            await httpClient.post('/updateLocation', this.location)
        },
        async deleteLocation() {
            this.location.worldId = this.location.world.worldId
            await httpClient.delete('/deleteLocation?locationId=' + this.location.locationId)
            this.locations.splice(this.locations.findIndex(l => l.locationId === this.location.locationId), 1)

            if (this.locations.length > 0) {
                this.loadLocationById(this.locations[this.locations.length-1].locationId)
            }
            else {
                this.location = {} as Location
            }
        },
        async getAllWorlds() {
            const response = await httpClient.get('/getWorlds')
            this.worlds = response.data
        },
        async getAllLocations() {
            const response = await httpClient.get('/getAllLocations')
            this.locations = response.data

            for (const l of this.locations) {
                for (const w of this.worlds) {
                    if (w.worldId === l.worldId) {
                        l.world = w
                        break
                    }
                }
            }

            this.location = this.locations[0]
            this.loadLocationById(this.location.locationId)
        }
    },
})
