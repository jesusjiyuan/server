/**
 * 采购到货明细视图布局类
 * */
var proopeQualityPurchaseOrderArrivalGoodDetailNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.quality.view.qualityPurchaseOrderArrivalGoodDetailLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopequalitypurchaseorderarrivalgoodlayout',
    title : "到货明细",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopequalitypurchaseorderarrvalgooddetailgrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeQualityPurchaseOrderArrivalGoodDetailNeedLoadFlag){
      		pan.down('proopequalitypurchaseorderarrvalgooddetailgrid').getStore().load();
      		proopeQualityPurchaseOrderArrivalGoodDetailNeedLoadFlag = false;
      	}
      },
      close:function(pan){
    	  proopeQualityPurchaseOrderArrivalGoodDetailNeedLoadFlag = true;
      }
    }
});