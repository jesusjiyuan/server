/**
 * 查询订单明细
 */
Ext.define("ERP.proope.purchase.store.purchaseArrivalsGoodsHistoryStore",{
    extend:'Ext.data.Store',
    model:'ERP.proope.purchase.model.purchaseArrivalsGoodsHistoryModel',
    pageSize:25,
    proxy:{
        type:"ajax",
        url:root+"/proopePurchase/findPurchaseArrivalsGoodsHistoryStorePageResult.do",
        pageParam:"pageNumber",
        reader:{
            type:"json",
            root:"purchaseArrivalsGoodsHistoryList",
            totalProperty:"itemTotal"
        }
    },
    autoLoad:true
});
