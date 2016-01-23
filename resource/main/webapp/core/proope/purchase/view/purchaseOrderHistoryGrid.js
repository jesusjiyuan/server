/**
 * 采购订单历史记录
 * */
Ext.define("ERP.proope.purchase.view.purchaseOrderHistoryGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopepurchasepurchaseorderhistorygrid",
    store:"ERP.proope.purchase.store.purchaseOrderHistoryStore",
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
        id:'proopePurchasePurchaseOrderHistoryShowDetailTbarButton',
        text:'明细',
        tooltip:'查看采购订单明细'
    },
    {
        xtype:'button',
        id:'proopePurchasePurchaseOrderHistoryExportTbarButton',
        text:'报表导出',
        tooltip:'导出采购订单历史记录报表'
    },"->",
        "供应商:",
        {
            xtype: 'triggerfield',
            id: 'proopePurchasePurchaseOrderHistoryGysmcConditionTriggerfield',
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
        store:'ERP.proope.purchase.store.purchaseOrderHistoryStore',
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
        {text:"供应商代码",dataIndex:"gysdm",hidden:true},
        {text:"供应商名称",dataIndex:"gysmc",width:200},
        {text:"供应商电话",dataIndex:"gysdh",width:100,hidden:true},
        {text:"供应商传真",dataIndex:"gyscz",width:100,hidden:true},
        {text:"收货方",dataIndex:"shf",width:100},
        {text:"收货方联系人",dataIndex:"shflxr",width:100},
        {text:"收货方电话",dataIndex:"shfdh",width:100},
        {text:"收货方传真",dataIndex:"shfcz",width:80,hidden:true},
        {text:"创建日期",dataIndex:"cjrqYMDHMS",flex:1}
    ],

    initComponent:function(){
        this.callParent(arguments);
        var purchaseOrderHistoryStore = this.getStore();
        purchaseOrderHistoryStore.on("beforeload",function(){
        	Ext.apply(purchaseOrderHistoryStore.proxy.extraParams,{gysmc:Ext.getCmp('proopePurchasePurchaseOrderHistoryGysmcConditionTriggerfield').value}); 
        });
    }
});