/**
 * 采购订单历史视图布局类
 * */
var proopePurchasePurchaseOrderHistoryNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.purchase.view.purchaseOrderHistoryLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopepurchasepurchaseorderhistorylayout',
    title : "采购订单历史",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopepurchasepurchaseorderhistorygrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopePurchasePurchaseOrderHistoryNeedLoadFlag){
      		pan.down('proopepurchasepurchaseorderhistorygrid').getStore().load();
      		proopePurchasePurchaseOrderHistoryNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopePurchasePurchaseOrderHistoryNeedLoadFlag = true;
      }
    }
});