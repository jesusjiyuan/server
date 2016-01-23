/**
 * 到货历史记录控制器
 */
Ext.define("ERP.proope.purchase.controller.purchaseArrivalsGoodsHistoryController", {
    extend : "Ext.app.Controller",
    init : function() {
     var self = this;
     
     /**
      * 弹窗公用方法
      * @param gridForm 弹窗中显示的主体
      * @param title 弹窗的标题
      * @param width 弹窗的宽度
      * @param height 弹窗的高度
      * @param id     弹窗的ID
      * @return
      */
     function showPupWindow(gridForm, title,width,height,id){
         var win = Ext.create('Ext.window.Window', {
	       title: title,
	       width: width,
	       height: height,
	       modal: true,
	       padding:'5 5 5 5',
	       id:id,
	       minWidth: 300,
	       minHeight: 200,
	       border:false,
	       layout: 'fit',
	       plain:true,
	       closeAction: 'hide',//  设置窗体关闭响应为 隐藏
	       items: gridForm
        });
        win.show();
     }
     
     Ext.define('proopePurchaseArrivalGoodHistoryDetailWindowModel', {
         extend: 'Ext.data.Model',
         fields: [
             {name: 'dm', type: 'string'},
             {name: 'dhlsdm', type: 'string'},
             {name: 'cgsqmxlsdm', type: 'string'},
             {name: 'cgddmxlsdm', type: 'string'},
         	 {name: 'wldm', type: 'string'},
             {name: 'wlmc', type: 'string'},
             {name: 'sqsl', type: 'float'},
             {name: 'dgsl', type: 'float'},
             {name: 'dhsl', type: 'float'},
             {name: 'rksl', type: 'float'},
             {name: 'cjrqYMDHMS', type: 'string'},
             {name: 'cjr', type: 'string'}
         ]
      });
      var proopePurchaseArrivalGoodHistoryDetailWindowStore;  //  设置为全局变量，在每次打开弹窗时，要清空该store中的数据
     
     //控制响应
     self.control({
     	"proopepurchasearrivalsgoodshistorygrid button[id=prropePurchaseShowArrivalsGoodsDetailTbarButton]" : {
           click : function(btn) {
               var records = btn.up("proopepurchasearrivalsgoodshistorygrid").getSelectionModel().getSelection();
               if(records.length == 0){
             		Ext.Msg.alert("提示", "未选中到货历史记录。");
             		return;
               }
               
               if(Ext.getCmp('proopePurchaseArrivalGoodHistoryDetailWindow')){
	           		Ext.getCmp('proopePurchaseArrivalGoodHistoryDetailWindow').show();
	           		findProopePurchaseArrivalGoodHistoryDetail(records[0]);
	           		return;
	           	}
              	
              	function findProopePurchaseArrivalGoodHistoryDetail(record){
              		proopePurchaseArrivalGoodHistoryDetailWindowStore.removeAll();
         			ajaxQueryAll("/proopePurchase/findPurchaseArrivalsGoodsDetailHistoryListByPaghId.do", {"arrivalsGoodsHistoryId":record.get('dm')}, 
	           			function(purchaseArrivalGoodDetailHistoryList){
	           				for (var i = 0; i < purchaseArrivalGoodDetailHistoryList.length; i++) {
	           			        var r = Ext.create('proopePurchasePurchaseOrderDetailHistoryModel', {
	           			        	dm: purchaseArrivalGoodDetailHistoryList[i].dm,
	           			        	dhlsdm: purchaseArrivalGoodDetailHistoryList[i].dhlsdm,
	           			        	cgsqmxlsdm: purchaseArrivalGoodDetailHistoryList[i].cgsqmxlsdm,
	           			        	cgddmxlsdm: purchaseArrivalGoodDetailHistoryList[i].cgddmxlsdm,
           			        		wldm: purchaseArrivalGoodDetailHistoryList[i].wldm,
				                    wlmc: purchaseArrivalGoodDetailHistoryList[i].wlmc,
				                    sqsl: purchaseArrivalGoodDetailHistoryList[i].sqsl,
				                    dgsl: purchaseArrivalGoodDetailHistoryList[i].dgsl,
				                    dhsl: purchaseArrivalGoodDetailHistoryList[i].dhsl,
				                    rksl: purchaseArrivalGoodDetailHistoryList[i].rksl,
				                    cjrqYMDHMS: purchaseArrivalGoodDetailHistoryList[i].cjrqYMDHMS,
				                    cjr: purchaseArrivalGoodDetailHistoryList[i].cjr
					             });
	           			     proopePurchaseArrivalGoodHistoryDetailWindowStore.insert(0, r);
					       }
	           			});
              	}
              	proopePurchaseArrivalGoodHistoryDetailWindowStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopePurchaseArrivalGoodHistoryDetailWindowModel'
			    });
			    
			    var historyGridForm = Ext.create('Ext.grid.Panel', {
			        store: proopePurchaseArrivalGoodHistoryDetailWindowStore,
			        columns: [
			            {header: '代码',dataIndex: 'dm', hidden:true},
			            {header: '到货历史代码',dataIndex: 'dhlsdm', hidden:true},
			            {header: '采购申请明细历史代码',dataIndex: 'cgsqmxlsdm', hidden:true},
			            {header: '采购订单明细历史代码',dataIndex: 'cgddmxlsdm', hidden:true},
			        	{header: '物料代码',dataIndex: 'wldm', hidden:true}, 
				        {header: '名称',dataIndex: 'wlmc',width:150},
				        {header: '申请数量',dataIndex: 'sqsl',width:70}, 
				        {header: '采购数量',dataIndex: 'dgsl',width:70},
				        {header: '到货数量',dataIndex: 'dhsl',width:70},
				        {header: '入库数量',dataIndex: 'rksl',width:70},
				        {header: '创建日期',dataIndex: 'cjrqYMDHMS',width:120},
				        {header: '创建人',dataIndex: 'cjr',width:80}
			        ],
			        columnLines: true,
			        width: 650,
			        height: 245,
			        frame: true,
			        iconCls: 'icon-grid'
			    });
             	
                 var historyWindow = Ext.create('Ext.form.Panel', {
                     frame:true,
                     autoHeight: true,
                     bodyStyle:'padding:5px 5px 0',
                     margin:1,
                     fieldDefaults: {
                         msgTarget: 'side',
                         labelWidth: 90
                     },
                     defaultType: 'textfield',
                     defaults: {
                         anchor: '100%'
                     },
                     items: [
                     	{
				            xtype: "label",
		                    id: "proopePurchaseArrivalGoodDetailHistoryLabel",
		                    text: "到货记录明细"
				        },
				        historyGridForm
                     ],

                     buttons: [{
                         text:'关闭',
                         handler: function(){
                             Ext.getCmp('proopePurchaseArrivalGoodHistoryDetailWindow').close();
                         }
                     }]
                 });
                 showPupWindow(historyWindow, "到货历史明细",700,380,'proopePurchaseArrivalGoodHistoryDetailWindow');
                 findProopePurchaseArrivalGoodHistoryDetail(records[0]);
            }//  end create order button click
         }
     });  //  end control
    },//  end init
    views : [
        "ERP.proope.purchase.view.purchaseArrivalsGoodsHistoryLayout",
        "ERP.proope.purchase.view.purchaseArrivalsGoodsHistoryGrid"
    ],
    stores : ["ERP.proope.purchase.store.purchaseArrivalsGoodsHistoryStore"],
    models : ["ERP.proope.purchase.model.purchaseArrivalsGoodsHistoryModel"]
});