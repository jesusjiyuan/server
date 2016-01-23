/**
 * 未处理的销售订单
 * */
Ext.define("ERP.proope.business.view.businessNotProcessSalesOrderGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopebusinessnotprocesssalesordergrid",
    store:"ERP.proope.business.store.businessNotProcessSalesOrderStore",
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
        id:'proopeBusinessNotProcessSaleOrderManageTbarButton',
        text:'管理',
        tooltip:'管理选中的销售订单'
    },{
        xtype:'button',
        id:'proopeBusinessNotProcessSaleOrderEndTbarButton',
        text:'结束',
        tooltip:'强制结束选中的销售订单'
    },"->",
        "客户名称:",
        {
            xtype: 'triggerfield',
            id: 'proopeBusinessNotProcessSalesOrderKhmcConditionTriggerfield',
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
        store:'ERP.proope.business.store.businessNotProcessSalesOrderStore',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"代码",dataIndex:"dm",hidden:true},
        {text:"客户名称",dataIndex:"khmc",flex:1}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var businessNotProcessSalesOrderStore = this.getStore();
        businessNotProcessSalesOrderStore.on("beforeload",function(){
        	Ext.apply(businessNotProcessSalesOrderStore.proxy.extraParams,{khmc:Ext.getCmp('proopeBusinessNotProcessSalesOrderKhmcConditionTriggerfield').value}); 
        });
    }
});