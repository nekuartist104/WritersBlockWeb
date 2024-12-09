import Location from "./Location"

interface Character {
    characterId: number,
    name: string,
    age: number,
    locationId: number,
    location: Location,
    characteristicTrait: string
    appearance: string,
    personality: string
}

export default Character