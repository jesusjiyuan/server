/**
 * 采购申请视图布局类
 * */
var proopePurchasePurchaseApplyNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.purchase.view.purchaseApplyLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopepurchasepurchaseapplylayout',
    title : "采购申请",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopepurchasepurchaseapplygrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopePurchasePurchaseApplyNeedLoadFlag){
      		pan.down('proopepurchasepurchaseapplygrid').getStore().load();
      		proopePurchasePurchaseApplyNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopePurchasePurchaseApplyNeedLoadFlag = true;
      }
    }
});