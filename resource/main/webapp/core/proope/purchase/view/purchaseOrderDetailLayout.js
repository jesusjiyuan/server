/**
 * 采购订单明细视图布局类
 * */
var proopePurchaseArrivalGoodOrderDetailNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.purchase.view.purchaseOrderDetailLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopepurchasearrivalgoodorderdetaillayout',
    title : "订单明细",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopepurchasearrivalgoodsorderdetailgrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopePurchaseArrivalGoodOrderDetailNeedLoadFlag){
      		pan.down('proopepurchasearrivalgoodsorderdetailgrid').getStore().load();
      		proopePurchaseArrivalGoodOrderDetailNeedLoadFlag = false;
      	}
      },
      close:function(pan){
    	  proopePurchaseArrivalGoodOrderDetailNeedLoadFlag = true;
      }
    }
});