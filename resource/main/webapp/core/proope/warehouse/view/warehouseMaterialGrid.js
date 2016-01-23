/**
 * 物料库存量
 * */
Ext.define("ERP.proope.warehouse.view.warehouseMaterialGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopewarehousewarehousematerialgrid",
    store:"ERP.proope.warehouse.store.warehouseMaterialStore",
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
        "物料名称:",
        {
            xtype: 'triggerfield',
            id: 'proopeWarehouseSearchWarehouseMaterialWlmcConditionTriggerfield',
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
        store:'ERP.proope.warehouse.store.warehouseMaterialStore',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"物料名称",dataIndex:"wlmc",width:300},
        {text:"数量",dataIndex:"sl",width:80},
        {text:"最后修改日期",dataIndex:"xgrqYMDHMS",flex:1}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var warehouseMaterialStore = this.getStore();
        warehouseMaterialStore.on("beforeload",function(){
        	Ext.apply(warehouseMaterialStore.proxy.extraParams,{wlmc:Ext.getCmp('proopeWarehouseSearchWarehouseMaterialWlmcConditionTriggerfield').value}); 
        });
    }
});