/**
 * 入库单视图布局类
 * */
var proopePurchasePurchasePutWarehouseNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.purchase.view.purchasePutWarehouseLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopepurchasepurchaseputwarehouselayout',
    title : "入库单",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopepurchasepurchaseputwarehousegrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopePurchasePurchasePutWarehouseNeedLoadFlag){
      		pan.down('proopepurchasepurchaseputwarehousegrid').getStore().load();
      		proopePurchasePurchasePutWarehouseNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopePurchasePurchasePutWarehouseNeedLoadFlag = true;
      }
    }
});