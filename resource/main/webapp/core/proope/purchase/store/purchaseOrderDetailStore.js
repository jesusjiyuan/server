/**
 * 查询订单明细
 */
Ext.define("ERP.proope.purchase.store.purchaseOrderDetailStore",{
    extend:'Ext.data.Store',
    model:'ERP.proope.purchase.model.purchaseOrderDetailModel',
    pageSize:25,
    proxy:{
        type:"ajax",
        url:root+"/proopePurchase/findPurchaseOrderDetailStorePageResult.do",
        pageParam:"pageNumber",
        reader:{
            type:"json",
            root:"purchaseOrderDetailList",
            totalProperty:"itemTotal"
        }
    },
    autoLoad:true
});