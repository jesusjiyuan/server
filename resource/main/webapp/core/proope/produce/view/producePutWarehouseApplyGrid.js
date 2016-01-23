/**
 * 产品入库申请Grid
 * */
Ext.define("ERP.proope.produce.view.producePutWarehouseApplyGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopeproduceproduceputwarehouseapplygrid",
    store:"ERP.proope.produce.store.producePutWarehouseApplyStore",
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
        id: 'proopeProductProducePutWarehouseApplyAddTbarButton',
        text:'新增申请',
        tooltip:'创建产品入库申请'
      },{
          xtype:'button',
          id: 'proopeProductProducePutWarehouseApplyDetailTbarButton',
          text:'明细',
          tooltip:'显示产品入库申请明细'
        },"->",
        "部门名称:",
        {
            xtype: 'triggerfield',
            id: 'proopeProduceProducePutWarehouseApplyBmmcConditionTriggerfield',
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
        store:'ERP.proope.produce.store.producePutWarehouseApplyStore',
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
        {text:"部门名称",dataIndex:"bmmc",width:300},
        {text:"创建日期",dataIndex:"cjrqYMDHMS",flex:1}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var producePutWarehouseApplyStore = this.getStore();
        producePutWarehouseApplyStore.on("beforeload",function(){
        	Ext.apply(producePutWarehouseApplyStore.proxy.extraParams,{bmmc:Ext.getCmp('proopeProduceProducePutWarehouseApplyBmmcConditionTriggerfield').value}); 
        });
    }
});