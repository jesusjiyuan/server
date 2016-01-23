/**
 * 根据订单查询对应的物料使用
 * */
Ext.define("ERP.proope.business.view.businessOrderMaterialGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopebusinessordermaterialgrid",
    store:"ERP.proope.business.store.businessOrderMaterialStore",
    selModel:{
        selType:"checkboxmodel"
    },
    border:0,
    multiSelect:true,
    /**
     * 顶部工具栏
     */
    tbar:["->",
        "客户名称:",
        {
            xtype: 'triggerfield',
            id: 'proopeBusinessSearchOrderMaterialKhmcConditionTriggerfield',
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
        store:'ERP.proope.business.store.businessOrderMaterialStore',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"销售订单代码",dataIndex:"xsdddm",hidden:true},
        {text:"订单明细代码",dataIndex:"ddmxdm",hidden:true},
        {text:"物料代码",dataIndex:"wldm",hidden:true},
        {text:"物料名称",dataIndex:"wlmc",width:200},
        {text:"物料数量",dataIndex:"wlsl",width:80},
        {text:"产品代码",dataIndex:"cpdm",hidden:true},
        {text:"产品名称",dataIndex:"cpmc",width:200},
        {text:"产品数量",dataIndex:"cpsl",width:80},
        {text:"产品物料数量",dataIndex:"cpwlsl",width:80},
        {text:"客户名称",dataIndex:"khmc",flex:1}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var businessOrderMaterialStore = this.getStore();
        businessOrderMaterialStore.on("beforeload",function(){
        	Ext.apply(businessOrderMaterialStore.proxy.extraParams,{khmc:Ext.getCmp('proopeBusinessSearchOrderMaterialKhmcConditionTriggerfield').value}); 
        });
    }
});