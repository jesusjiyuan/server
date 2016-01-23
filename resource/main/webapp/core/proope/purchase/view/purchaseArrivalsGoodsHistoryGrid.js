/**
 * 物料采购订单明细  用于创建到货表记录
 * */
Ext.define("ERP.proope.purchase.view.purchaseArrivalsGoodsHistoryGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopepurchasearrivalsgoodshistorygrid",
    store:"ERP.proope.purchase.store.purchaseArrivalsGoodsHistoryStore",
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
        id:'prropePurchaseShowArrivalsGoodsDetailTbarButton',
        text:'明细',
        tooltip:'显示明细'
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
        store:'ERP.proope.purchase.store.purchaseArrivalsGoodsHistoryStore',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"代码",dataIndex:"dm",hidden:true},
        {text:"创建日期",dataIndex:"cjrqYMDHMS",width:120},
        {text:"创建人",dataIndex:"cjr",width:150}
    ],
    initComponent:function(){
        this.callParent(arguments);
    }
});