/**
 * 采购订单
 * */
Ext.define("ERP.proope.purchase.view.purchaseOrderGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopepurchasepurchaseordergrid",
    store:"ERP.proope.purchase.store.purchaseOrderStore",
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
        id:'proopePurchaseOrderShowDetailButton',
        text:'明细',
        tooltip:'查看采购订单明细'
    },"->",
        "供应商:",
        {
            xtype: 'triggerfield',
            id: 'proopeSearchPurchaseOrderGysmcConditionTriggerfield',
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
        store:'ERP.proope.purchase.store.purchaseOrderStore',
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
        {text:"供应商名称",dataIndex:"gysmc",flex:1},
        {text:"收货方",dataIndex:"shf",hidden:true}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var purchaseOrderStore = this.getStore();
        purchaseOrderStore.on("beforeload",function(){
        	Ext.apply(purchaseOrderStore.proxy.extraParams,{gysmc:Ext.getCmp('proopeSearchPurchaseOrderGysmcConditionTriggerfield').value}); 
        });
    }
});