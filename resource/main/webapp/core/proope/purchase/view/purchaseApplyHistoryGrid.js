/**
 * 用料申请
 * */
Ext.define("ERP.proope.purchase.view.purchaseApplyHistoryGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopepurchasepurchaseapplyhistorygrid",
    store:"ERP.proope.purchase.store.purchaseApplyHistoryStore",
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
        id:'showPurchaseApplyDetailHistoryTbarButton',
        text:'明细',
        tooltip:'查看采购申请历史明细'
    },{
        xtype:'button',
        id:'showPurchaseApplyReportExportTbarButton',
        text:'报表导出',
        tooltip:'导出采购申请历史明细报表'
    },"->",
        "部门名称:",
        {
            xtype: 'triggerfield',
            id: 'searchPurchaseApplyHistoryBmmcConditionTriggerfield',
            triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
            /**
             * 输入查询条件后点击触发,使用输入的查询条件重新load
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
        store:'ERP.proope.purchase.store.purchaseApplyHistoryStore',
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
        {text:"部门名称",dataIndex:"bmmc",width:400},
        {text:"申请提出时间",dataIndex:"sqtcsjYMDHMS",width:120}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var purchaseApplyHistoryStore = this.getStore();
        purchaseApplyHistoryStore.on("beforeload",function(){
        	Ext.apply(purchaseApplyHistoryStore.proxy.extraParams,{bmmc:Ext.getCmp('searchPurchaseApplyHistoryBmmcConditionTriggerfield').value}); 
        });
    }
});