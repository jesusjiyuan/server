/**
 * 产品入库待检布局类
 * */
var proopeQualityQualityProduceDetailNeedLoadFlag = false;  //  标记是否需要查询列表中的store作load操作
Ext.define("ERP.proope.quality.view.qualityProduceDetailLayout", {
    extend : 'Ext.panel.Panel',
    alias : 'widget.proopequalityqualityproducedetaillayout',
    title : "产品入库待检明细",
    closable:true,
    layout : 'fit',
    items:[{
        xtype:"proopequalityqualityproducedetailgrid"
    }],
    listeners:{// 添加监听器
      activate:function(pan){
      	if(proopeQualityQualityProduceDetailNeedLoadFlag){
      		pan.down('proopequalityqualityproducedetailgrid').getStore().load();
      		proopeQualityQualityProduceDetailNeedLoadFlag = false;
      	}
      },
      close:function(pan){
      	proopeQualityQualityProduceDetailNeedLoadFlag = true;
      }
    }
});