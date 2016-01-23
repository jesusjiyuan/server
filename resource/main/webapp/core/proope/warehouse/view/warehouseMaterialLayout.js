/**
 * 物料库存量视图布局类
 * */
var proopeWarehouseWarehouseMaterialNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.warehouse.view.warehouseMaterialLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopewarehousewarehousemateriallayout',
    title : "物料库存量",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopewarehousewarehousematerialgrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeWarehouseWarehouseMaterialNeedLoadFlag){
      		pan.down('proopewarehousewarehousematerialgrid').getStore().load();
      		proopeWarehouseWarehouseMaterialNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopeWarehouseWarehouseMaterialNeedLoadFlag = true;
      }
    }
});