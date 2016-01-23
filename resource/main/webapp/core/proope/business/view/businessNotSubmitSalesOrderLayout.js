/**
 * 未提交销售订单视图布局类
 * */
var proopeBusinessNotSubmitSalesOrderNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.business.view.businessNotSubmitSalesOrderLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopebusinessnotsubmitsalesorderlayout',
    title : "未提交订单",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopebusinessnotsubmitsalesordergrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeBusinessNotSubmitSalesOrderNeedLoadFlag){
      		pan.down('proopebusinessnotsubmitsalesordergrid').getStore().load();
      		proopeBusinessNotSubmitSalesOrderNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopeBusinessNotSubmitSalesOrderNeedLoadFlag = true;
      }
    }
});