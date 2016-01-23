/**
 * 用料申请
 * */
Ext.define("ERP.proope.purchase.view.purchaseApplyGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopepurchasepurchaseapplygrid",
    store:"ERP.proope.purchase.store.purchaseApplyStore",
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
        id:'addPurchaseApplyButton',
        text:'新建',
        tooltip:'新建采购申请'
    }, '-', {
        xtype:'button',
        id: 'updatePurchaseApplyButton',
        text:'修改',
        tooltip:'修改采购申请'
    }, '-', {
        xtype:'button',
        id: 'deletePurchaseApplyButton',
        text:'删除',
        tooltip:'删除采购申请'
    }, '-', {
        xtype:'button',
        id: 'submitPurchaseApplyButton',
        text:'提交',
        tooltip:'提交采购申请'
    },"->",
        "申请部门:",
        {
            xtype: 'triggerfield',
            id: 'searchPurchaseApplyBmmcConditionTriggerfield',
            triggerCls: Ext.baseCSSPrefix + 'form-search-trigger',
            //  输入查询条件后点击触发
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
        store:'ERP.proope.purchase.store.purchaseApplyStore',
        displayInfo:true
    },
    enableKeyNav:true,  //可以使用键盘控制上下
    columnLines:true,
    autoScroll:true,//展示竖线
    //列模式
    columns:[
        {xtype: 'rownumberer'},
        {text:"代码",dataIndex:"dm",hidden:true},
        {text:"申请部门",dataIndex:"bmmc",width:350},
        {text:"创建日期",dataIndex:"cjrqYMDHMS",width:120}
    ],
    initComponent:function(){
        this.callParent(arguments);
        //  初始化时，设置store.load时设置的查询条件
        var purchaseApplyStore = this.getStore();
        purchaseApplyStore.on("beforeload",function(){
        	Ext.apply(purchaseApplyStore.proxy.extraParams,{bmmc:Ext.getCmp('searchPurchaseApplyBmmcConditionTriggerfield').value}); 
        });
    }
});