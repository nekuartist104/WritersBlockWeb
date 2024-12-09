import { defineStore } from 'pinia'
import { httpClient } from 'src/boot/axios'
import Character from 'src/domain/Character'
import Location from 'src/domain/Location'


export const useCharacterStore = defineStore('character', {
    state: () => ({
        character: {} as Character,
        characters: [] as Character[],
        locations: [] as Location[],
    }),
    actions: {
        async loadCharacterById(id: number) {
            const response = await httpClient.get('/getCharacterById?characterId=' + id)
            const locationResponse = await httpClient.get('getLocationById?locationId=' + response.data.locationId)
            const worldResponse = await httpClient.get('getWorldById?worldId=' + locationResponse.data.worldId)
            this.character = response.data
            this.character.location = locationResponse.data
            this.character.location.world = worldResponse.data
        },
        async createCharacter() {
            await httpClient.post('/createCharacter', this.character)
        },
        async updateCharacter() {
            this.character.locationId = this.character.location.locationId
            await httpClient.post('/updateCharacter', this.character)
        },
        async deleteCharacter() {
            this.character.locationId = this.character.location.locationId
            await httpClient.delete('/deleteCharacter?characterId=' + this.character.characterId)
            this.characters.splice(this.characters.findIndex(c => c.characterId === this.character.characterId), 1)

            if (this.characters.length > 0) {
                this.character = this.characters[this.characters.length-1]
                this.loadCharacterById(this.characters[this.characters.length-1].characterId) 
            }
            else {
                this.character = {} as Character
            }
        },
        async getAllCharacters() {
            const response = await httpClient.get('/getAllCharacters')
            this.characters = response.data

            for (const c of this.characters) {
                for (const l of this.locations) {
                    if (c.locationId === l.locationId) {
                        c.location = l
                    }
                }
            }
            this.character = this.characters[0]
            this.loadCharacterById(this.character.characterId)
        },
        async getAllLocations() {
            // const l = {} as Location
            // var ls = [] as Location[]
            // l.name = 'None'
            const response = await httpClient.get('/getAllLocations')
            // ls = response.data
            // this.locations.push(l)
            // for (const location of ls) {
            //     this.locations.push(location)
            // }
            this.locations = response.data

            for (const l of this.locations) {
                const worldResponse = await httpClient.get('/getWorldById?worldId=' + l.worldId)
                l.world = worldResponse.data
            }
        },
    },
})