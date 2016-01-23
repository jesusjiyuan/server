/**
 * 采购订单明细列表model
 */
Ext.define("ERP.proope.purchase.model.purchaseOrderDetailModel",{
    extend:"Ext.data.Model",
    fields:[
        {name:"dm",type:"string",srotable:true},
        {name:"cgsqmxdm",type:"string",srotable:true},
        {name:"wldm",type:"string",srotable:true},
        {name:"wlmc",type:"string",srotable:true},
        {name:"sqsl",type:"string",srotable:true},
        {name:"dgsl",type:"string",srotable:true},
        {name:"ydhsl",type:"string",srotable:true},
        {name:"gysmc",type:"string",srotable:true}
    ]
});
