/**
 * 根据订单和fom表，查询订单的用料情况model
 */
Ext.define("ERP.proope.business.model.businessOrderMaterialModel",{
    extend:"Ext.data.Model",
    fields:[
        {name:"xsdddm",type:"string",srotable:true},
        {name:"ddmxdm",type:"string",srotable:true},
        {name:"wldm",type:"string",srotable:true},
        {name:"wlmc",type:"string",srotable:true},
        {name:"wlsl",type:"string",srotable:true},
        {name:"cpdm",type:"string",srotable:true},
        {name:"cpmc",type:"string",srotable:true},
        {name:"cpsl",type:"string",srotable:true},
        {name:"cpwlsl",type:"string",srotable:true},
        {name:"khmc",type:"string",srotable:true}
    ]
});
