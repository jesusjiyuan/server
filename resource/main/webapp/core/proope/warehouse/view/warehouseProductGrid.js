/**
 * 产品库存量
 * */
Ext.define("ERP.proope.warehouse.view.warehouseProductGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopewarehousewarehouseproductgrid",
    store:"ERP.proope.warehouse.store.warehouseProductStore",
    selModel:{
        selType:"checkboxmodel"
    },
    border:0,
    multiSelect:true,
    /**
     * 顶部工具栏
     */
    tbar:[{
    },"->",
        "产品名称:",
        {
            xtype: 'triggerfield',
            id: 'proopeWarehouseSearchWarehouseProductCpmcConditionTriggerfield',
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
        store:'ERP.proope.warehouse.store.warehouseProductStore',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"产品名称",dataIndex:"cpmc",width:300},
        {text:"数量",dataIndex:"sl",width:80},
        {text:"最后修改日期",dataIndex:"xgrqYMDHMS",flex:1}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var warehouseProductStore = this.getStore();
        warehouseProductStore.on("beforeload",function(){
        	Ext.apply(warehouseProductStore.proxy.extraParams,{cpmc:Ext.getCmp('proopeWarehouseSearchWarehouseProductCpmcConditionTriggerfield').value}); 
        });
    }
});