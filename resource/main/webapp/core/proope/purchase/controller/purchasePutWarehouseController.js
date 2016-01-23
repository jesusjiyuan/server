/**
 * 入库控制器
 */
Ext.define("ERP.proope.purchase.controller.purchasePutWarehouseController", {
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
     
     Ext.define('proopePurchasePutWarehouseDetailHistoryModel', {
        extend: 'Ext.data.Model',
        fields: [
        	{name: 'dm', type: 'string'},
            {name: 'rklsdm', type: 'string'},
            {name: 'cgsqmxlsdm', type: 'string'},
            {name: 'wldm', type: 'string'},
            {name: 'wlmc', type: 'string'},
            {name: 'rksl', type: 'float'},
            {name: 'jyjg', type: 'string'},
            {name: 'bz', type: 'string'}
        ]
     });

     var proopePurchasePutWarehouseDetailHistoryStore;  //  设置为全局变量，在每次打开弹窗时，要清空该store中的数据
     
     //控制响应
     self.control({
     	"proopepurchasepurchaseputwarehousegrid button[id=proopePurchasePutWarehouseHistoryShowDetailTbarButton]" : {
             click : function(btn) {
             	var records = btn.up("proopepurchasepurchaseputwarehousegrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中记录。");
               		return;
               	}
               	
               	if(Ext.getCmp('proopePurchasePutWarehouseHistoryDetailWindow')){
            		Ext.getCmp('proopePurchasePutWarehouseHistoryDetailWindow').show();
            		findProopePurchasePutWarehouseDetailHistore(records[0]);
            		return;
            	}
               	
               	function findProopePurchasePutWarehouseDetailHistore(record){
               		Ext.getCmp('proopePurchasePutWarehouseDetailHistoryRkbmmcLabel').setText("入库部门名称："+
               			record.get('rkbmmc') + "入库类别："+ record.get('rklb') + "生产车间："+ record.get('sccj') +
               			"备注："+ record.get('bz')+"入库日期：" + record.get('cjrqYMDHMS'), true);
               		proopePurchasePutWarehouseDetailHistoryStore.removeAll();
          			ajaxQueryAll("/proopePurchase/findPutWarehouseDetailHistoryListByPwhId.do", {"putWarehouseHistoryId":record.get('dm')}, 
            			function(putWorehouseDetailHistoryList){
            				for (var i = 0; i < putWorehouseDetailHistoryList.length; i++) {
            			        var r = Ext.create('proopePurchasePutWarehouseDetailHistoryModel', {
            			        	dm: putWorehouseDetailHistoryList[i].dm,
            			        	rklsdm: putWorehouseDetailHistoryList[i].rklsdm,
            			        	cgsqmxlsdm: putWorehouseDetailHistoryList[i].cgsqmxlsdm,
            			        	wldm: putWorehouseDetailHistoryList[i].wldm,
            			        	wlmc: putWorehouseDetailHistoryList[i].wlmc,
            			        	rksl: putWorehouseDetailHistoryList[i].rksl,
            			        	jyjg: putWorehouseDetailHistoryList[i].jyjg,
				                    bz: putWorehouseDetailHistoryList[i].bz
				                });
				                proopePurchasePutWarehouseDetailHistoryStore.insert(0, r);
				            }
            			});
               	}
               	
               	// 全局变量历史store
               	proopePurchasePutWarehouseDetailHistoryStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopePurchasePutWarehouseDetailHistoryModel'
			    });
			    
			    var historyGridForm = Ext.create('Ext.grid.Panel', {
			        store: proopePurchasePutWarehouseDetailHistoryStore,
			        columns: [
			        	{header: '代码',dataIndex: 'dm', hidden: true},
			        	{header: '入库历史代码',dataIndex: 'rklsdm', hidden: true},
			        	{header: '采购申请明细历史代码',dataIndex: 'cgsqmxlsdm', hidden: true},
			        	{header: '物料代码',dataIndex: 'wldm', hidden:true}, 
				        {header: '物料名称',dataIndex: 'wlmc', width:180},
				        {header: '入库数量',dataIndex: 'rksl',width:70},
				        {header: '检验结果',dataIndex: 'jyjg',width:80},
				        {header: '备注',dataIndex: 'bz',width:200}
			        ],
			        columnLines: true,
			        width: 550,
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
		                    id: "proopePurchasePutWarehouseDetailHistoryRkbmmcLabel",
		                    text: "入库部门："
				        },
				        historyGridForm
                      ],

                      buttons: [{
                          text:'关闭',
                          handler: function(){
                              Ext.getCmp('proopePurchasePutWarehouseHistoryDetailWindow').close();
                          }
                      }]
                  });
                  showPupWindow(historyWindow, "入库单历史明细",600,380,'proopePurchasePutWarehouseHistoryDetailWindow');
                  findProopePurchasePutWarehouseDetailHistore(records[0]);
             }
         },
         "proopepurchasepurchaseputwarehousegrid button[id=proopePurchasePutWarehouseHistoryExportTbarButton]" : {
             click : function(btn) {
            	 alert("11");
             	var records = btn.up("proopepurchasepurchaseputwarehousegrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中记录。");
               		return;
               	}
               	var pahId = records[0].get('dm');
               	window.open(root+"/proopePurchase/purchaseHistoryReportExport.do?purchaseApplyHistoryId="+pahId);
             }
         }
     });//  end control
    },//  end init
    views : [
        "ERP.proope.purchase.view.purchasePutWarehouseLayout",
        "ERP.proope.purchase.view.purchasePutWarehouseGrid"
    ],
    stores : ["ERP.proope.purchase.store.purchasePutWarehouseStore"],
    models : ["ERP.proope.purchase.model.purchasePutWarehouseModel"]
});