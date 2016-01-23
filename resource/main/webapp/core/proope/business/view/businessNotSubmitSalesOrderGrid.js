/**
 * 未提交销售订单
 * */
Ext.define("ERP.proope.business.view.businessNotSubmitSalesOrderGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopebusinessnotsubmitsalesordergrid",
    store:"ERP.proope.business.store.businessNotSubmitSalesOrderStore",
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
        id:'proopeBusinessNotSubmitSalesOrderAddTbarButton',
        text:'新增',
        tooltip:'创建销售订单'
    }, '-', {
        xtype:'button',
        id:'proopeBusinessNotSubmitSalesOrderEditTbarButton',
        text:'修改',
        tooltip:'修改销售订单'
    }, '-',{
        xtype:'button',
        id:'proopeBusinessNotSubmitSalesOrderDeleTbarButton',
        text:'删除',
        tooltip:'删除销售订单'
    }, '-',{
        xtype:'button',
        id: 'proopeBusinessNotSubmitSalesOrderSubmitTbarButton',
        text:'提交',
        tooltip:'提交选中的销售订单，提交后，该订单不可再作修改。'
    },"->",
        "客户名称:",
        {
            xtype: 'triggerfield',
            id:'proopeBusinessNotSubmitSalesOrderKhmcConditionTriggerfield',
            triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
            /**
             * 输入查询条件后点击触发,重新查询数据
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
        store:'ERP.proope.business.store.businessNotSubmitSalesOrderStore',
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
        var businessNotSubmitSalesOrderStore = this.getStore();
        businessNotSubmitSalesOrderStore.on("beforeload",function(){
        	Ext.apply(businessNotSubmitSalesOrderStore.proxy.extraParams,{khmc:Ext.getCmp('proopeBusinessNotSubmitSalesOrderKhmcConditionTriggerfield').value}); 
        });
    }
});