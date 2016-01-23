/**
 * 采购申请明细视图布局类
 * */
var proopePurchasePurchaseApplyDetailNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.purchase.view.purchaseApplyDetailLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopepurchasepurchaseapplydetaillayout',
    title : "采购申请明细",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopepurchasepurchaseapplydetailgrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopePurchasePurchaseApplyDetailNeedLoadFlag){
      		pan.down('proopepurchasepurchaseapplydetailgrid').getStore().load();
      		proopePurchasePurchaseApplyDetailNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopePurchasePurchaseApplyDetailNeedLoadFlag = true;
      }
    }
});