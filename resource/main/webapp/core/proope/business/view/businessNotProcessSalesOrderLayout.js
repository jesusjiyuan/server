/**
 * 	未处理销售订单管理视图布局类
 * */
var proopeBusinessNotProcessSalesOrderNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.business.view.businessNotProcessSalesOrderLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopebusinessnotprocesssalesorderlayout',
    title : "未处理销售订单管理",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopebusinessnotprocesssalesordergrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeBusinessNotProcessSalesOrderNeedLoadFlag){
      		pan.down('proopebusinessnotprocesssalesordergrid').getStore().load();
      		proopeBusinessNotProcessSalesOrderNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopeBusinessNotProcessSalesOrderNeedLoadFlag = true;
      }
    }
});