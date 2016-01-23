/**
 * 	产品入库申请视图布局类
 * */
var proopeProduceProducePutWarehouseApplyNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.produce.view.producePutWarehouseApplyLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopeproduceproduceputwarehouseapplylayout',
    title : "入库申请记录",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopeproduceproduceputwarehouseapplygrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeProduceProducePutWarehouseApplyNeedLoadFlag){
      		pan.down('proopeproduceproduceputwarehouseapplygrid').getStore().load();
      		proopeProduceProducePutWarehouseApplyNeedLoadFlag = false;
      	}
      },
      close:function(pan){
    	  proopeProduceProducePutWarehouseApplyNeedLoadFlag = true;
      }
    }
});