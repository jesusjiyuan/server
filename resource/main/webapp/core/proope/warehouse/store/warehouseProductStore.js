/**
 * 查询产品库存量
 */
Ext.define("ERP.proope.warehouse.store.warehouseProductStore",{
    extend:'Ext.data.Store',
    model:'ERP.proope.warehouse.model.warehouseProductModel',
    pageSize:25,
    proxy:{
        type:"ajax",
        url:root+"/proopeWarehouse/findProductInventoryStorePageResult.do",
        pageParam:"pageNumber",
        reader:{
            type:"json",
            root:"productInventoryList",
            totalProperty:"itemTotal"
        }
    },
    autoLoad:true
});
