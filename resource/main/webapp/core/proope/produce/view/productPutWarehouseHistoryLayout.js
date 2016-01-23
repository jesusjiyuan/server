/**
 * 	产品入库历史记录视图布局类
 * */
var proopeProduceProducePutWarehouseHistoryNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.produce.view.productPutWarehouseHistoryLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopeproduceproductputwarehousehistorylayout',
    title : "入库历史记录",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopeproduceproductputwarehousehistorygrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeProduceProducePutWarehouseHistoryNeedLoadFlag){
      		pan.down('proopeproduceproductputwarehousehistorygrid').getStore().load();
      		proopeProduceProducePutWarehouseHistoryNeedLoadFlag = false;
      	}
      },
      close:function(pan){
    	  proopeProduceProducePutWarehouseHistoryNeedLoadFlag = true;
      }
    }
});