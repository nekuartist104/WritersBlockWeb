import{X as p}from"./index.08ad63da.js";import{httpClient as a}from"./axios.b75ae6bc.js";const y=p("areaType",{state:()=>({areaType:{},areaTypes:[]}),actions:{async loadAreaTypeById(e){const t=await a.get("/getAreaTypeById?areaTypeId="+e);this.areaType=t.data},async createAreaType(){await a.post("/createAreaType",this.areaType)},async updateAreaType(){await a.post("/updateAreaType",this.areaType)},async deleteAreaType(){await a.delete("/deleteAreaType?areaTypeId="+this.areaType.areaTypeId),this.areaTypes.splice(this.areaTypes.findIndex(e=>e.areaTypeId===this.areaType.areaTypeId),1),this.areaTypes.length>0?this.loadAreaTypeById(this.areaTypes[this.areaTypes.length-1].areaTypeId):this.areaType={}},async getAllAreaTypes(){const e=await a.get("/getAreaTypes");this.areaTypes=e.data,this.areaType=this.areaTypes[0],this.loadAreaTypeById(this.areaType.areaTypeId)}}});export{y as u};
