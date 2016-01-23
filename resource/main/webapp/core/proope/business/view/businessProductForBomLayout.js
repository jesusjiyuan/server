/**
 * 	BOM表管理视图布局类
 * */
var proopeBusinessProductForBomNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.business.view.businessProductForBomLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopebusinessproductforbomlayout',
    title : "BOM表管理",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopebusinessproductforbomgrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeBusinessProductForBomNeedLoadFlag){
      		pan.down('proopebusinessproductforbomgrid').getStore().load();
      		proopeBusinessProductForBomNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopeBusinessProductForBomNeedLoadFlag = true;
      }
    }
});