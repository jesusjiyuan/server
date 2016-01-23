/**
 * 用料申请明细
 * */
Ext.define("ERP.proope.purchase.view.purchaseApplyDetailGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopepurchasepurchaseapplydetailgrid",
    store:"ERP.proope.purchase.store.purchaseApplyDetailStore",
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
        id:'purchaseApplyDetailCreateOrderButton',
        text:'创建订单',
        tooltip:'根据选中的记录创建采购订单'
    },"->",
        "申请部门名称:",
        {
            xtype: 'triggerfield',
            id: 'searchPurchaseApplyDetailBmmcConditionTriggerfield',
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
        store:'ERP.proope.purchase.store.purchaseApplyDetailStore',
        //dock:'bottom',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"采购申请明细代码",dataIndex:"dm",hidden:true},
        {text:"物料代码",dataIndex:"wldm",hidden:true},
        {text:"物料名称",dataIndex:"wlmc",width:300},
        {text:"申请数量",dataIndex:"sqsl"},
        {text:"已采购数量",dataIndex:"ycgsl"},
        {text:"申请部门",dataIndex:"bmmc",width:400}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var purchaseApplyDetailStore = this.getStore();
        purchaseApplyDetailStore.on("beforeload",function(){
        	Ext.apply(purchaseApplyDetailStore.proxy.extraParams,{bmmc:Ext.getCmp('searchPurchaseApplyDetailBmmcConditionTriggerfield').value}); 
        });
    }
});