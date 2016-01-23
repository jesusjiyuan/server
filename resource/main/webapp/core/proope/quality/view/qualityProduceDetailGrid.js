/**
 * 产品入库待检明细
 * */
Ext.define("ERP.proope.quality.view.qualityProduceDetailGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopequalityqualityproducedetailgrid",
    store:"ERP.proope.quality.store.qualityProduceDetailStore",
    selModel:{
        selType:"checkboxmodel"
    },
    border:0,
    multiSelect:true,
    /**
     * 顶部工具栏
     */
    tbar:[{
        xtype:'button',
        id:'proopeQualityProducePutWharehouseTbarButton',
        text:'入库',
        tooltip:'选中的记录入库操作'
    },"->",
        "产品名称:",
        {
            xtype: 'triggerfield',
            id:'proopeQualityProducePutWharehouseApplyDetailCpmcConditionTriggerfield',
            triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
            /**
             * 输入查询条件后点击触发,查询条件可部分匹配
             */
            onTriggerClick: function() {
            	this.ownerCt.ownerCt.getStore().load();
            }
        }
    ],
    /**
     * 分页工具条
     */
    bbar:{
        xtype:'pagingtoolbar',
        firstText:'首页',
        lastText:'末页',
        prevText:'前一页',
        nextText:'下一页',
        refreshText:'刷新',
        beforePageText:'当前页:',
        afterPageText:'总页数: {0}',
        displayMsg:"当前展示纪录 {0} - {1} 总共纪录: {2}",
        store:'ERP.proope.quality.store.qualityProduceDetailStore',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"入库申请明细代码",dataIndex:"dm",hidden:true},
        {text:"产品入库申请代码",dataIndex:"cprksqdm",hidden:true},
        {text:"产品代码",dataIndex:"cpdm",hidden:true},
        {text:"产品名称",dataIndex:"cpmc",width:300},
        {text:"申请数量",dataIndex:"sqsl",width:80},
        {text:"已入库数量",dataIndex:"yrksl",width:80}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var qualityProduceDetailStore = this.getStore();
        qualityProduceDetailStore.on("beforeload",function(){
        	Ext.apply(qualityProduceDetailStore.proxy.extraParams,{cpmc:Ext.getCmp('proopeQualityProducePutWharehouseApplyDetailCpmcConditionTriggerfield').value}); 
        });
    }
});