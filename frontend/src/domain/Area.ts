import AreaType from "./AreaType"
import Location from './Location'

interface Area {
    areaId: number,
    name: string,
    size: number
    locationId: number,
    areaTypeId: number,
    areaType: AreaType,
    location: Location
}

export default Area