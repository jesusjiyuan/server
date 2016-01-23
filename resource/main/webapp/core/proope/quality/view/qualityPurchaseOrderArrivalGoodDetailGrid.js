/**
 * 物料入库待检明细
 * */
Ext.define("ERP.proope.quality.view.qualityPurchaseOrderArrivalGoodDetailGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopequalitypurchaseorderarrvalgooddetailgrid",
    store:"ERP.proope.quality.store.qualityPurchaseOrderArrivalGoodDetailStore",
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
        id:'prropeQualityPurchaseOrderArrivalGoodDetailCpwTbarButton',
        text:'检验入库',
        tooltip:'对选中的物料作质检并创建入库单'
    },"->",
        "供应商名称:",
        {
            xtype: 'triggerfield',
            id: 'proopeQualityPurchaseOrderArrivalGoodDetailGysmcConditionTriggerfield',
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
        store:'ERP.proope.quality.store.qualityPurchaseOrderArrivalGoodDetailStore',
        //dock:'bottom',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"代码",dataIndex:"dm",hidden:true},
        {text:"到货代码",dataIndex:"dhdm",hidden:true},
        {text:"采购申请明细代码",dataIndex:"cgsqmxdm",hidden:true},
        {text:"采购订单明细代码",dataIndex:"cgddmxdm",hidden:true},
        {text:"物料代码",dataIndex:"wldm",hidden:true},
        {text:"物料名称",dataIndex:"wlmc",width:250},
        {text:"申请数量",dataIndex:"sqsl",width:70},
        {text:"订购数量",dataIndex:"dgsl",width:70},
        {text:"到货数量",dataIndex:"dhsl",width:70},
        {text:"已入库数量",dataIndex:"yrksl",width:70},
        {text:"创建日期",dataIndex:"cjrqYMDHMS",width:120},
        {text:"创建人",dataIndex:"cjr",width:70},
        {text:"供应商名称",dataIndex:"gysmc",flex:1}
    ],

    initComponent:function(){
        this.callParent(arguments);
        var qualityArrivalGoodDetailStore = this.getStore();
        qualityArrivalGoodDetailStore.on("beforeload",function(){
        	Ext.apply(qualityArrivalGoodDetailStore.proxy.extraParams,{gysmc:Ext.getCmp('proopeQualityPurchaseOrderArrivalGoodDetailGysmcConditionTriggerfield').value}); 
        });
    }
});