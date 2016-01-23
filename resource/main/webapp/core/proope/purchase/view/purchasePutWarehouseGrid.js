/**
 * 入库单
 * */
Ext.define("ERP.proope.purchase.view.purchasePutWarehouseGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopepurchasepurchaseputwarehousegrid",
    store:"ERP.proope.purchase.store.purchasePutWarehouseStore",
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
        id:'proopePurchasePutWarehouseHistoryShowDetailTbarButton',
        text:'明细',
        tooltip:'查看入库单明细'
    },
    {
        xtype:'button',
        id:'proopePurchasePutWarehouseHistoryExportTbarButton',
        text:'报表导出',
        tooltip:'导出入库单报表'
    },"->",
        "入库部门:",
        {
            xtype: 'triggerfield',
            id: 'proopePurchasePutWarehouseRkbmmcConditionTriggerfield',
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
        store:'ERP.proope.purchase.store.purchasePutWarehouseStore',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"代码",dataIndex:"dm",flex:1, hidden: true},
        {text:"入库部门代码",dataIndex:"rkbmdm",hidden:true},
        {text:"入库部门名称",dataIndex:"rkbmmc",width:180},
        {text:"入库类别",dataIndex:"rklb",width:80},
        {text:"生产车间",dataIndex:"sccj",width:150},
        {text:"备注",dataIndex:"bz",width:250},
        {text:"创建日期",dataIndex:"cjrqYMDHMS",width:120}
    ],
    
    initComponent:function(){
        this.callParent(arguments);
        var purchasePutWarehouseStore = this.getStore();
        purchasePutWarehouseStore.on("beforeload",function(){
        	Ext.apply(purchasePutWarehouseStore.proxy.extraParams,{rkbmmc:Ext.getCmp('proopePurchasePutWarehouseRkbmmcConditionTriggerfield').value}); 
        });
    }
});