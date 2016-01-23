/**
 * 物料采购订单明细  用于创建到货表记录
 * */
Ext.define("ERP.proope.purchase.view.purchaseOrderDetailGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopepurchasearrivalgoodsorderdetailgrid",
    store:"ERP.proope.purchase.store.purchaseOrderDetailStore",
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
        id:'prropePurchaseOrderDetailCreateArrivalGoodTbarButton',
        text:'确认到货',
        tooltip:'确认选中的记录已到货'
    },"->",
        "供应商名称:",
        {
            xtype: 'triggerfield',
            id: 'proopePurchaseOrderDetailArrivalGoodGysmcConditionTriggerfield',
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
        store:'ERP.proope.purchase.store.purchaseOrderDetailStore',
        //dock:'bottom',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"采购订单明细代码",dataIndex:"dm",hidden:true},
        {text:"采购申请明细代码",dataIndex:"cgsqmxdm",hidden:true},
        {text:"物料代码",dataIndex:"wldm",hidden:true},
        {text:"物料名称",dataIndex:"wlmc",width:250},
        {text:"申请数量",dataIndex:"sqsl",width:70},
        {text:"订购数量",dataIndex:"dgsl",width:70},
        {text:"已到货数量",dataIndex:"ydhsl",width:70},
        {text:"供应商名称",dataIndex:"gysmc",width:300}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var purchaseOrderDetailStore = this.getStore();
        purchaseOrderDetailStore.on("beforeload",function(){
        	Ext.apply(purchaseOrderDetailStore.proxy.extraParams,{gysmc:Ext.getCmp('proopePurchaseOrderDetailArrivalGoodGysmcConditionTriggerfield').value}); 
        });
    }
});