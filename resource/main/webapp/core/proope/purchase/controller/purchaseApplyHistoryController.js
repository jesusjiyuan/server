/**
 * 采购申请控制器
 */
Ext.define("ERP.proope.purchase.controller.purchaseApplyHistoryController", {
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
     
     Ext.define('purchaseApplyDetailHistoryModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'wlmc', type: 'string'},
            {name: 'sqsl', type: 'float'},
            {name: 'sjcgsl', type: 'float'}
        ]
     });
     
     var purchaseApplyDetailHistoryStore;  //  修改store,设置为全局变量，在每次打开历史明细弹窗时，要清空该store中的数据
     
     //控制响应
     self.control({
     	"proopepurchasepurchaseapplyhistorygrid button[id=showPurchaseApplyDetailHistoryTbarButton]" : {
             click : function(btn) {
             	var records = btn.up("proopepurchasepurchaseapplyhistorygrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中记录。");
               		return;
               	}
               	var pahId = records[0].get('dm');
               	
               	if(Ext.getCmp('proopePurchaseApplyPurchaseApplyHistoryWindow')){
            		Ext.getCmp('proopePurchaseApplyPurchaseApplyHistoryWindow').show();
            		findHistoryPurchaseApply(pahId);
            		return;
            	}
               	
               	//  查询出历史明细
               	function findHistoryPurchaseApply(purchaseApplyHistoryIdParam){
            		ajaxQueryEntity("/proopePurchase/findPurchaseApplyHistoryEntityById.do", {"purchaseApplyHistoryId":purchaseApplyHistoryIdParam}, 
            			function(entityObj){
            				Ext.getCmp('purchaseApplyHistoryDepartmentLabel').setText("申请部门："+entityObj.bmmc + "    申请时间：" + entityObj.sqtcsj, true);
            				//  查询出采购申请历史明细
            				purchaseApplyDetailHistoryStore.removeAll();
            				ajaxQueryAll("/proopePurchase/findPurchaseApplyDetailHistoryByPadhId.do", {"purchaseApplyHistoryId":purchaseApplyHistoryIdParam}, 
		            			function(purchaseApplyDetailHistoryList){
		            				for (var i = 0; i < purchaseApplyDetailHistoryList.length; i++) {
		            			        var r = Ext.create('purchaseApplyDetailHistoryModel', {
						                    wlmc: purchaseApplyDetailHistoryList[i].wlmc+'_'+purchaseApplyDetailHistoryList[i].wldm,
						                    sqsl: purchaseApplyDetailHistoryList[i].sqsl,
						                    sjcgsl: purchaseApplyDetailHistoryList[i].sjcgsl
						                });
						                purchaseApplyDetailHistoryStore.insert(0, r);
						            }
		            			});
            			});
            	}
	            	
               	// 全局变量历史store
			    purchaseApplyDetailHistoryStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'purchaseApplyDetailHistoryModel'
			    });
			    
			     var historyGridForm = Ext.create('Ext.grid.Panel', {
				        store: purchaseApplyDetailHistoryStore,
				        columns: [
					        {header: '物料名称', dataIndex: 'wlmc', width: 350}, 
					        {header: '申请数量', dataIndex: 'sqsl',width: 80, align: 'right'}, 
					        {header: '采购数量',dataIndex: 'sjcgsl',width: 80,align: 'right'}
				        ],
				        columnLines: true,
				        width: 550,
				        height: 260,
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
			                    id: "purchaseApplyHistoryDepartmentLabel",
			                    text: "申请部门："
					        },
					        historyGridForm
                        ],

                        buttons: [{
                            text:'关闭',
                            handler: function(){
                                Ext.getCmp('proopePurchaseApplyPurchaseApplyHistoryWindow').close();
                            }
                        }]
                    });
                    showPupWindow(historyWindow, "采购申请历史明细",600,380,'proopePurchaseApplyPurchaseApplyHistoryWindow');
                    findHistoryPurchaseApply(pahId);  //  弹窗创建结束，查询出数据显示在弹窗中
			     
             }
         },
         "proopepurchasepurchaseapplyhistorygrid button[id=showPurchaseApplyReportExportTbarButton]" : {
             click : function(btn) {
            	 alert("11");
             	var records = btn.up("proopepurchasepurchaseapplyhistorygrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中记录。");
               		return;
               	}
               	var pahId = records[0].get('dm');
               	window.open(root+"/proopePurchase/purchaseHistoryReportExport.do?purchaseApplyHistoryId="+pahId);
               //	window.location.href=root+"/proopePurchase/purchaseHistoryReportExport.do";
               	//alert("report | "+pahId);
               	//ajaxOpera("/proopePurchase/purchaseHistoryReportExport.do", {}, function(){});
             }
         }
     });
    },//  end init
    views : [
        "ERP.proope.purchase.view.purchaseApplyHistoryLayout",
        "ERP.proope.purchase.view.purchaseApplyHistoryGrid"
    ],
    stores : ["ERP.proope.purchase.store.purchaseApplyHistoryStore"],
    models : ["ERP.proope.purchase.model.purchaseApplyHistoryModel"]
});