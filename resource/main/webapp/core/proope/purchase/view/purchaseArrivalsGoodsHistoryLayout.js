/**
 * 到货历史记录Layout
 * */
var proopePurchaseArrivalsGoodsHistoryNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.purchase.view.purchaseArrivalsGoodsHistoryLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopepurchasearrivalsgoodshistorylayout',
    title : "到货历史记录",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopepurchasearrivalsgoodshistorygrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopePurchaseArrivalsGoodsHistoryNeedLoadFlag){
      		pan.down('proopepurchasearrivalsgoodshistorygrid').getStore().load();
      		proopePurchaseArrivalsGoodsHistoryNeedLoadFlag = false;
      	}
      },
      close:function(pan){
    	  proopePurchaseArrivalsGoodsHistoryNeedLoadFlag = true;
      }
    }
});