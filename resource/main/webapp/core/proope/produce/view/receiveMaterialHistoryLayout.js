/**
 * 	领料历史记录视图布局类
 * */
var proopeProduceReceiveMaterialHistoryNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.produce.view.receiveMaterialHistoryLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopeproducereceivematerialhistorylayout',
    title : "领料记录",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopeproducereceivematerialhistorygrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeProduceReceiveMaterialHistoryNeedLoadFlag){
      		pan.down('proopeproducereceivematerialhistorygrid').getStore().load();
      		proopeProduceReceiveMaterialHistoryNeedLoadFlag = false;
      	}
      },
      close:function(pan){
    	  proopeProduceReceiveMaterialHistoryNeedLoadFlag = true;
      }
    }
});