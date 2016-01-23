/**
 * 采购申请历史视图布局类
 * */
var proopePurchasePurchaseApplyHistoryNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.purchase.view.purchaseApplyHistoryLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopepurchasepurchaseapplyhistorylayout',
    title : "采购申请历史",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopepurchasepurchaseapplyhistorygrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopePurchasePurchaseApplyHistoryNeedLoadFlag){
      		pan.down('proopepurchasepurchaseapplyhistorygrid').getStore().load();
      		proopePurchasePurchaseApplyHistoryNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopePurchasePurchaseApplyHistoryNeedLoadFlag = true;
      }
    }
});