/**
 * 根据产品管理BOM表
 * */
Ext.define("ERP.proope.business.view.businessProductForBomGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopebusinessproductforbomgrid",
    store:"ERP.proope.business.store.businessProductForBomStore",
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
        id: 'proopeBusinessProductForBomEditTbarButton',
        text:'修改',
        tooltip:'修改选中产品的BOM表'
    },"->",
        "产品名称:",
        {
            xtype: 'triggerfield',
            id: 'proopeBusinessProductForBomCpmcConditionTriggerfield',
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
        store:'ERP.proope.business.store.businessProductForBomStore',
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
        {text:"产品名称",dataIndex:"cpmc",flex:1}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var businessProductForBomStore = this.getStore();
        businessProductForBomStore.on("beforeload",function(){
        	Ext.apply(businessProductForBomStore.proxy.extraParams,{cpmc:Ext.getCmp('proopeBusinessProductForBomCpmcConditionTriggerfield').value}); 
        });
    }
});