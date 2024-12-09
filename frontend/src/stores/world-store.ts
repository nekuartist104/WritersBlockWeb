import { defineStore } from 'pinia'
import { httpClient } from 'src/boot/axios'
import World from 'src/domain/World'

export const useWorldStore = defineStore('world', {
    state: () => ({
        world: {} as World,
        worlds: [] as World[]
    }),
    actions: {
        async loadWorldById(id: number) {
            const response = await httpClient.get('/getWorldById?worldId=' + id)
            this.world = response.data
        },
        async createWorld() {
            await httpClient.post('/createWorld', this.world)
        },
        async updateWorld() {
            await httpClient.post('/updateWorld', this.world)
        },
        async deleteWorld() {
            await httpClient.delete('/deleteWorld?worldId=' + this.world.worldId)
            this.worlds.splice(this.worlds.findIndex(w => w.worldId === this.world.worldId), 1)

            if (this.worlds.length > 0) {
                this.loadWorldById(this.worlds[this.worlds.length-1].worldId) 
            }
            else {
                this.world = {} as World
            }
        },
        async getAllWorlds() {
            const response = await httpClient.get('/getWorlds')
            this.worlds = response.data
            this.world = this.worlds[0]
            this.loadWorldById(this.world.worldId)
        }
    },
})