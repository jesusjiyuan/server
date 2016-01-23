/**
 * 领料历史记录Grid
 * */
Ext.define("ERP.proope.produce.view.receiveMaterialHistoryGrid",{
    extend:"Ext.grid.Panel",
    alias:"widget.proopeproducereceivematerialhistorygrid",
    store:"ERP.proope.produce.store.receiveMaterialHistoryStore",
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
        id: 'proopeProductReceiveMaterialHistoryAddTbarButton',
        text:'新增领料',
        tooltip:'创建领料'
      },{
          xtype:'button',
          id: 'proopeProductReceiveMaterialHistoryDetailTbarButton',
          text:'明细',
          tooltip:'显示领料历史明细'
        },"->",
        "部门名称:",
        {
            xtype: 'triggerfield',
            id: 'proopeProduceReceiveMaterialHistoryBmmcConditionTriggerfield',
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
        store:'ERP.proope.produce.store.receiveMaterialHistoryStore',
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
        {text:"部门代码",dataIndex:"bmdm",hidden:true},
        {text:"部门名称",dataIndex:"bmmc",width:300},
        {text:"用途",dataIndex:"yt",width:300},
        {text:"创建日期",dataIndex:"cjrqYMDHMS",flex:1}
    ],
    initComponent:function(){
        this.callParent(arguments);
        var receiveMaterialHistoryStore = this.getStore();
        receiveMaterialHistoryStore.on("beforeload",function(){
        	Ext.apply(receiveMaterialHistoryStore.proxy.extraParams,function(){
        		var receiveMaterialHistoryStoreTemp = Ext.getCmp('proopeProduceReceiveMaterialHistoryBmmcConditionTriggerfield').value;
        		
        		bmmc : receiveMaterialHistoryStoreTemp.bmmc;
        		yt : yt;
        		function yt(){
        			var yt = "";
        			if(receiveMaterialHistoryStoreTemp.scyl == parseInt("1") ){ yt += yt + "生产用料"; }
        			if(receiveMaterialHistoryStoreTemp.ypyl == parseInt("2") ){ yt += yt + "样品用料"; }
        			if(receiveMaterialHistoryStoreTemp.bgyl == parseInt("3") ){ yt += yt + "办公用料"; }
        			if(receiveMaterialHistoryStoreTemp.qtyl == parseInt("4") ){ yt += yt + "其它用料"; }
        		}
        		}); 
        });
        
    }
});