/**
 * 产品入库申请Store
 */
Ext.define("ERP.proope.produce.store.producePutWarehouseApplyStore",{
    extend:'Ext.data.Store',
    model:'ERP.proope.produce.model.producePutWarehouseApplyModel',
    pageSize:25,
    proxy:{
        type:"ajax",
        url:root+"/proopeProduce/findProductPutWarehouseApplyStorePageResult.do",
        pageParam:"pageNumber",
        reader:{
            type:"json",
            root:"productPutWarehouseApplyList",
            totalProperty:"itemTotal"
        }
    },
    autoLoad:true
});