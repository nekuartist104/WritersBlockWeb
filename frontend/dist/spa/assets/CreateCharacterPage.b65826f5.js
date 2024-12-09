import{D as V,Y as b,Z as C,F as f,_ as g,a0 as s,I as t,$ as u,a2 as o,aq as r,a3 as h}from"./index.08ad63da.js";import{Q as k}from"./QSelect.2cb75358.js";import{u as w}from"./character-store.907ea829.js";import"./QItemSection.8eace9fd.js";import"./rtl.b51694b1.js";import"./axios.b75ae6bc.js";const y={class:"row no-wrap"},U={style:{"min-width":"424px"}},P=V({__name:"CreateCharacterPage",setup(T){const n=w(),c=b(),{character:e,locations:d}=C(n);n.getAllLocations();function m(){c.push("/character")}function p(){e.value.locationId=e.value.location.locationId,n.createCharacter()}return(v,l)=>(f(),g(h,null,[s("div",y,[s("div",U,[t(u,{style:{color:"white","background-color":"hotpink"},icon:"arrow_back",onClick:m})])]),t(r,{modelValue:o(e).name,"onUpdate:modelValue":l[0]||(l[0]=a=>o(e).name=a),label:"Name"},null,8,["modelValue"]),t(r,{modelValue:o(e).age,"onUpdate:modelValue":l[1]||(l[1]=a=>o(e).age=a),label:"Age"},null,8,["modelValue"]),t(k,{modelValue:o(e).location,"onUpdate:modelValue":l[2]||(l[2]=a=>o(e).location=a),label:"Residence",options:o(d),"option-label":a=>{var i;return a.name+" ("+((i=a.world)==null?void 0:i.name)+")"}},null,8,["modelValue","options","option-label"]),t(r,{modelValue:o(e).characteristicTrait,"onUpdate:modelValue":l[3]||(l[3]=a=>o(e).characteristicTrait=a),label:"Characteristic Trait"},null,8,["modelValue"]),t(r,{modelValue:o(e).appearance,"onUpdate:modelValue":l[4]||(l[4]=a=>o(e).appearance=a),label:"Appearance"},null,8,["modelValue"]),t(r,{modelValue:o(e).personality,"onUpdate:modelValue":l[5]||(l[5]=a=>o(e).personality=a),label:"Personality"},null,8,["modelValue"]),t(u,{style:{color:"white","background-color":"hotpink"},label:"Create character",onClick:p})],64))}});export{P as default};