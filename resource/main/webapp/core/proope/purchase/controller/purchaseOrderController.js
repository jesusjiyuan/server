/**
 * 采购订单控制器
 */
Ext.define("ERP.proope.purchase.controller.purchaseOrderController", {
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
     
     Ext.define('proopePurchasePurchaseOrderDetailModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'dm', type: 'string'},
        	{name: 'wldm', type: 'string'},
            {name: 'wlmc', type: 'string'},
            {name: 'dj', type: 'float'},
            {name: 'dgsl', type: 'float'},
            {name: 'ydhsl', type: 'float'}
        ]
     });
     
     var proopePurchasePurchaseOrderDetailStore;  //  采购订单store,设置为全局变量，在每次打开采购订单明细弹窗时，要清空该store中的数据
     //控制响应
     self.control({
     	"proopepurchasepurchaseordergrid button[id=proopePurchaseOrderShowDetailButton]" : {
           click : function(btn) {
               var records = btn.up("proopepurchasepurchaseordergrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中订单记录。");
               		return;
               	}
               	var poId = records[0].get('dm');
               	
               	if(Ext.getCmp('proopePurchaseOrderDetailWindow')){
            		Ext.getCmp('proopePurchaseOrderDetailWindow').show();
            		findPurchaseOrder(poId);
            		return;
            	}
               	
               	function findPurchaseOrder(purchaseOrderId){
               		ajaxQueryEntity("/proopePurchase/findPurchaseOrderResultByPoId.do", {"purchaseOrderId":purchaseOrderId}, 
            			function(entityObj){
            				Ext.getCmp('proopePurchaseOrderSupplierLabel').setText("供应商："+entityObj.gysmc, true);
            				//  查询出采购订单明细
            				proopePurchasePurchaseOrderDetailStore.removeAll();
            				ajaxQueryAll("/proopePurchase/findPurchaseOrderDetailListByPoId.do", {"purchaseOrderId":purchaseOrderId}, 
		            			function(purchaseOrderDetailList){
		            				for (var i = 0; i < purchaseOrderDetailList.length; i++) {
		            			        var r = Ext.create('proopePurchasePurchaseOrderDetailModel', {
		            			        	dm: purchaseOrderDetailList[i].dm,
		            			        	wldm: purchaseOrderDetailList[i].wldm,
						                    wlmc: purchaseOrderDetailList[i].wlmc,
						                    dj: purchaseOrderDetailList[i].dj,
						                    dgsl: purchaseOrderDetailList[i].dgsl,
						                    ydhsl: purchaseOrderDetailList[i].ydhsl
						                });
						                proopePurchasePurchaseOrderDetailStore.insert(0, r);
						            }
		            			});
            			});
               	}
               	
               	// 全局变量订单明细store
			    proopePurchasePurchaseOrderDetailStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopePurchasePurchaseOrderDetailModel'
			    });
			    
			     var proopePurchaseOrderGridForm = Ext.create('Ext.grid.Panel', {
				        store: proopePurchasePurchaseOrderDetailStore,
				        columns: [
					        {header: '代码',dataIndex: 'dm',hidden:true},
					        {header: '物料代码',dataIndex: 'wldm',hidden:true},
					        {header: '物料名称',dataIndex: 'wlmc',width: 300},
					        {header: '单价',dataIndex: 'dj',width: 70,align: 'right'}, 
					        {header: '订购数量',dataIndex: 'dgsl',width: 70,align: 'right'}, 
					        {header: '已到货数量',dataIndex: 'ydhsl',width: 70,align: 'right'}
				        ],
				        columnLines: true,
				        width: 500,
				        height: 250,
				        frame: true,
				        iconCls: 'icon-grid'
				    });
                	
                    var proopePurchaseOrderWindow = Ext.create('Ext.form.Panel', {
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
			                    id: "proopePurchaseOrderSupplierLabel",
			                    text: "供应商："
					        },
					        proopePurchaseOrderGridForm
                        ],

                        buttons: [{
                            text:'关闭',
                            handler: function(){
                                Ext.getCmp('proopePurchaseOrderDetailWindow').close();
                            }
                        }]
                    });
                    showPupWindow(proopePurchaseOrderWindow, "采购订单明细",600,380,'proopePurchaseOrderDetailWindow');
                    findPurchaseOrder(poId);  //  弹窗创建结束，查询出数据显示在弹窗中
           }
         }//  end show detail button
     });  //  end control
    },  //  end init
    views : [
        "ERP.proope.purchase.view.purchaseOrderLayout",
        "ERP.proope.purchase.view.purchaseOrderGrid"
    ],
    stores : ["ERP.proope.purchase.store.purchaseOrderStore"],
    models : ["ERP.proope.purchase.model.purchaseOrderModel"]
});