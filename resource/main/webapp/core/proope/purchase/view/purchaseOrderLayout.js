/**
 * 采购订单视图布局类
 * */
var proopePurchasePurchaseOrderNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.purchase.view.purchaseOrderLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopepurchasepurchaseorderlayout',
    title : "采购订单",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopepurchasepurchaseordergrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopePurchasePurchaseOrderNeedLoadFlag){
      		pan.down('proopepurchasepurchaseordergrid').getStore().load();
      		proopePurchasePurchaseOrderNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopePurchasePurchaseOrderNeedLoadFlag = true;
      }
    }
});