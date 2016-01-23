/**
 * 产品入库待检明细列表model
 */
Ext.define("ERP.proope.quality.model.qualityProduceDetailModel",{
    extend:"Ext.data.Model",
    fields:[
        {name:"dm",type:"string",srotable:false},
        {name:"cprksqdm",type:"string",srotable:false},
        {name:"cpdm",type:"string",srotable:true},
        {name:"cpmc",type:"string",srotable:true},
        {name:"sqsl",type:"string",srotable:true},
        {name:"yrksl",type:"string",srotable:true}
    ]
});
