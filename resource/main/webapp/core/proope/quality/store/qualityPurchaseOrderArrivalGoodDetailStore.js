/**
 * 
 */
Ext.define("ERP.proope.quality.store.qualityPurchaseOrderArrivalGoodDetailStore",{
    extend:'Ext.data.Store',
    model:'ERP.proope.quality.model.qualityPurchaseOrderArrivalGoodDetailModel',
    pageSize:25,
    proxy:{
        type:"ajax",
        url:root+"/proopeQuality/findPurchaseArrivalsGoodsDetailStorePageResult.do",
        pageParam:"pageNumber",
        reader:{
            type:"json",
            root:"purchaseArrivalsGoodsDetailList",
            totalProperty:"itemTotal"
        }
    },
    autoLoad:true
});