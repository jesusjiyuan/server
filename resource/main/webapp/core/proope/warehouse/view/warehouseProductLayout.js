/**
 * 产品库存量视图布局类
 * */
var proopeWarehouseWarehouseProductNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.warehouse.view.warehouseProductLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopewarehousewarehouseproductlayout',
    title : "产品库存量",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopewarehousewarehouseproductgrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeWarehouseWarehouseProductNeedLoadFlag){
      		pan.down('proopewarehousewarehouseproductgrid').getStore().load();
      		proopeWarehouseWarehouseProductNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopeWarehouseWarehouseProductNeedLoadFlag = true;
      }
    }
});