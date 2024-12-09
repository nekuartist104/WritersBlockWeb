import World from "./World"

interface Location {
    locationId: number,
    worldId: number,
    world: World,
    name: string,
    nationality: string,
    population: number
    climate: string,
    terrain: string
}

export default Location