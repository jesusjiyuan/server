/**
 * 采购订单历史控制器
 */
Ext.define("ERP.proope.purchase.controller.purchaseOrderHistoryController", {
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
     
     Ext.define('proopePurchasePurchaseOrderDetailHistoryModel', {
        extend: 'Ext.data.Model',
        fields: [
        	{name: 'wldm', type: 'string'},
            {name: 'wlmc', type: 'string'},
            {name: 'wlgg', type: 'string'},
            {name: 'wldw', type: 'string'},
            {name: 'dj', type: 'float'},
            {name: 'sqsl', type: 'float'},
            {name: 'dgsl', type: 'float'},
            {name: 'rksl', type: 'float'},
            {name: 'bz', type: 'string'}
        ]
     });
     
     var proopePurchasePurchaseOrderDetailHistoryStore;  //  设置为全局变量，在每次打开弹窗时，要清空该store中的数据
     
     //控制响应
     self.control({
     	"proopepurchasepurchaseorderhistorygrid button[id=proopePurchasePurchaseOrderHistoryShowDetailTbarButton]" : {
             click : function(btn) {
             	var records = btn.up("proopepurchasepurchaseorderhistorygrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中记录。");
               		return;
               	}
               	
               	if(Ext.getCmp('proopePurchasePurchaseOrderHistoryDetailWindow')){
            		Ext.getCmp('proopePurchasePurchaseOrderHistoryDetailWindow').show();
            		findProopePurchasePurchaseOrderDetail(records[0]);
            		return;
            	}
               	
               	function findProopePurchasePurchaseOrderDetail(record){
               		Ext.getCmp('proopePurchasePurchaseOrderDetailHistoryGysmcLabel').setText("供应商名称："+
               			record.get('gysmc') +"收货方；"+record.get('shf')+ "收货方联系人："+record.get('shflxr')+"收货方电话："+record.get('shfdh')
               			+"    创建日期：" + record.get('cjrqYMDHMS'),true);
               		proopePurchasePurchaseOrderDetailHistoryStore.removeAll();
          			ajaxQueryAll("/proopePurchase/findPurchaseOrderDetailHistoryListByPohId.do", {"purchaseOrderHistoryId":record.get('dm')}, 
            			function(purchaseOrderDetailHistoryList){
            				for (var i = 0; i < purchaseOrderDetailHistoryList.length; i++) {
            			        var r = Ext.create('proopePurchasePurchaseOrderDetailHistoryModel', {
            			        	wldm: purchaseOrderDetailHistoryList[i].wldm,
				                    wlmc: purchaseOrderDetailHistoryList[i].wlmc,
				                    wlgg: purchaseOrderDetailHistoryList[i].wlgg,
				                    wldw: purchaseOrderDetailHistoryList[i].wldw,
				                    dj: purchaseOrderDetailHistoryList[i].dj,
				                    sqsl: purchaseOrderDetailHistoryList[i].sqsl,
				                    dgsl: purchaseOrderDetailHistoryList[i].dgsl,
				                    rksl: purchaseOrderDetailHistoryList[i].rksl,
				                    bz: purchaseOrderDetailHistoryList[i].bz
				                });
				                proopePurchasePurchaseOrderDetailHistoryStore.insert(0, r);
				            }
            			});
               	}
               	proopePurchasePurchaseOrderDetailHistoryStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopePurchasePurchaseOrderDetailHistoryModel'
			    });
			    
			    var historyGridForm = Ext.create('Ext.grid.Panel', {
			        store: proopePurchasePurchaseOrderDetailHistoryStore,
			        columns: [
			        	{header: '物料代码',dataIndex: 'wldm',hidden:true}, 
				        {header: '名称',dataIndex: 'wlmc',width:120},
				        {header: '规格',dataIndex: 'wlgg',hidden:true},
				        {header: '单位',dataIndex: 'wldw',hidden:true},
				        {header: '单价',dataIndex: 'dj',width:60},
				        {header: '申请数量',dataIndex: 'sqsl',width:60}, 
				        {header: '采购数量',dataIndex: 'dgsl',width:60},
				        {header: '入库数量',dataIndex: 'rksl',width:60},
				        {header: '备注',dataIndex: 'bz',flex:1}
			        ],
			        columnLines: true,
			        width: 600,
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
		                    id: "proopePurchasePurchaseOrderDetailHistoryGysmcLabel",
		                    text: "供应商："
				        },
				        historyGridForm
                      ],

                      buttons: [{
                          text:'关闭',
                          handler: function(){
                              Ext.getCmp('proopePurchasePurchaseOrderHistoryDetailWindow').close();
                          }
                      }]
                  });
                  showPupWindow(historyWindow, "采购订单历史明细",650,380,'proopePurchasePurchaseOrderHistoryDetailWindow');
                  findProopePurchasePurchaseOrderDetail(records[0]);
             }
         },
         "proopepurchasepurchaseorderhistorygrid button[id=proopePurchasePurchaseOrderHistoryExportTbarButton]" : {
             click : function(btn) {
             	var records = btn.up("proopepurchasepurchaseorderhistorygrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中记录。");
               		return;
               	}
               	var pahId = records[0].get('dm');
               	window.open(root+"/proopePurchase/exportPDFPutWarehouseHistory.do?purchaseOrderId="+pahId);
             }
         }
     });  //  end control
    },//  end init
    views : [
        "ERP.proope.purchase.view.purchaseOrderHistoryLayout",
        "ERP.proope.purchase.view.purchaseOrderHistoryGrid"
    ],
    stores : ["ERP.proope.purchase.store.purchaseOrderHistoryStore"],
    models : ["ERP.proope.purchase.model.purchaseOrderHistoryModel"]
});