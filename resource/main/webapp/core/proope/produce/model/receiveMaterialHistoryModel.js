/**
 * 生产领料历史model
 */
Ext.define("ERP.proope.produce.model.receiveMaterialHistoryModel",{
    extend:"Ext.data.Model",
    fields:[
        {name:"dm",type:"string",srotable:false},
        {name:"bmdm",type:"string",srotable:false},
        {name:"bmmc",type:"string",srotable:true},
        {name:"scyl",type:"int",srotable:true},
        {name:"ypyl",type:"int",srotable:true},
        {name:"bgyl",type:"int",srotable:true},
        {name:"qtyl",type:"int",srotable:true},
        {name:"cjrqYMDHMS",type:"string",srotable:true}
    ]
});
