/**
 * 	根据销售订单查看物料使用量视图布局类
 * */
var proopeBusinessOrderMaterialNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.business.view.businessOrderMaterialLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopebusinessordermateriallayout',
    title : "销售订单用料",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopebusinessordermaterialgrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeBusinessOrderMaterialNeedLoadFlag){
      		pan.down('proopebusinessordermaterialgrid').getStore().load();
      		proopeBusinessOrderMaterialNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopeBusinessOrderMaterialNeedLoadFlag = true;
      }
    }
});