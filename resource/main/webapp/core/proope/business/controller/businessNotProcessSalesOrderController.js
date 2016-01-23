/**
 * 未处理销售订单管理控制器
 */
Ext.define("ERP.proope.business.controller.businessNotProcessSalesOrderController", {
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
     
     Ext.define('proopeBusinessProcessSaleOrderModel', {
         extend: 'Ext.data.Model',
         fields: [
             {name: 'dm', type: 'string'},
         	 {name: 'cpdm', type: 'string'},
             {name: 'cpmc', type: 'string'},
             {name: 'sl', type: 'float'},
             {name: 'yjfsl', type: 'float'},
             {name: 'jfsl', type: 'float'},
             {name: 'bz', type: 'string'}
         ]
      });

     var proopeBusinessProcessSaleOrderStore;
     
     var xsdddm = '';
     
     //控制响应
     self.control({
    	 "proopebusinessnotprocesssalesordergrid button[id=proopeBusinessNotProcessSaleOrderManageTbarButton]" : {
             click : function(btn) {
             	var records = btn.up("proopebusinessnotprocesssalesordergrid").getSelectionModel().getSelection();
                if(records.length == 0){
              		Ext.Msg.alert("提示", "未选中任何订单记录。");
              		return;
                }
                
                xsdddm = records[0].get('dm');
                
                if(Ext.getCmp('proopeBusinessProcessSaleOrderWindow')){
            		Ext.getCmp('proopeBusinessProcessSaleOrderWindow').show();
            		findSaleOrderDetail(records[0]);
            		return;
            	}
                
                function findSaleOrderDetail(record){
                	//alert(record.get('dm'));
                	Ext.getCmp('purchaseBusinessSalesOrderManageKhLabel').setText("客户名称："+record.get('khmc'), true);
                	proopeBusinessProcessSaleOrderStore.removeAll();
                	
       				ajaxQueryAll("/proopeBusiness/findSalesOrderDetailResultListBySalesOrderId.do", {"salesOrderId":record.get('dm')}, 
            			function(salesOrderDetailResultList){
            				for (var i = 0; i < salesOrderDetailResultList.length; i++) {
            			        var r = Ext.create('proopeBusinessProcessSaleOrderModel', {
				                    dm: salesOrderDetailResultList[i].dm,
				                    cpdm: salesOrderDetailResultList[i].cpdm,
				                    cpmc: salesOrderDetailResultList[i].cpmc,
				                    sl: salesOrderDetailResultList[i].sl,
				                    yjfsl: salesOrderDetailResultList[i].yjfsl,
				                    jfsl: 1,
				                    bz: ''
				                });
            			        proopeBusinessProcessSaleOrderStore.insert(0, r);
				            }
            			});
                }
                
                proopeBusinessProcessSaleOrderStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeBusinessProcessSaleOrderModel'
			    });
                
                var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			        clicksToEdit: 1
			    });
			    
                var processSaleOrderForm = Ext.create('Ext.grid.Panel', {
			        store: proopeBusinessProcessSaleOrderStore,
			        columns: [
				        {header: '订单明细代码', dataIndex: 'dm', hidden:true}, 
				        {header: '产品代码', dataIndex: 'cpdm', hidden:true},
				        {header: '产品名称', dataIndex: 'cpmc',width:200},
				        {header: '采购数量', dataIndex: 'sl',width:80},
				        {header: '已交付数量', dataIndex: 'yjfsl', width:80},
				        {
				            header: '交付',
				            dataIndex: 'jfsl',
				            width: 80,
				            align: 'right',
				            editor: {
				                xtype: 'numberfield',
				                allowBlank: false,
				                minValue: 0
				            }
				        },{
				            header: '备注',
				            dataIndex: 'bz',
				            width: 150,
				            align: 'right',
				            editor: {
				                allowBlank: true
				            }
				        }
			        ],
			        columnLines: true,
			        width: 600,
			        height: 245,
			        frame: true,
			        plugins: [cellEditing],
			        iconCls: 'icon-grid'
			    });
            	
                var saleOrderManageWindow = Ext.create('Ext.form.Panel', {
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
		                    forId: "purchaseBusinessSalesOrderManageKhLabel",
		                    id: "purchaseBusinessSalesOrderManageKhLabel",
		                    text: "客户名称："
				        },
				        processSaleOrderForm
                    ],

                    buttons: [{
                        text: '出货',
                        handler: function(){
                        	if(validateProopeBusinessProcessSaleOrderStore(proopeBusinessProcessSaleOrderStore)){return;}
                            var param = strToJson("{}");
							for(var i = 0; i < proopeBusinessProcessSaleOrderStore.getCount(); i ++){
								param["salesOrderOutGoodsDetailHistoryResultList["+i+"].xsddlsdm"]=xsdddm;
								param["salesOrderOutGoodsDetailHistoryResultList["+i+"].xsddmxlsdm"]=proopeBusinessProcessSaleOrderStore.getAt(i).get("dm");
								param["salesOrderOutGoodsDetailHistoryResultList["+i+"].cpdm"]=proopeBusinessProcessSaleOrderStore.getAt(i).get("cpdm");
								param["salesOrderOutGoodsDetailHistoryResultList["+i+"].cpmc"]=proopeBusinessProcessSaleOrderStore.getAt(i).get("cpmc");
								param["salesOrderOutGoodsDetailHistoryResultList["+i+"].yjfsl"]=proopeBusinessProcessSaleOrderStore.getAt(i).get("yjfsl");
								param["salesOrderOutGoodsDetailHistoryResultList["+i+"].sl"]=proopeBusinessProcessSaleOrderStore.getAt(i).get("sl");
								param["salesOrderOutGoodsDetailHistoryResultList["+i+"].chsl"]=proopeBusinessProcessSaleOrderStore.getAt(i).get("jfsl");
								param["salesOrderOutGoodsDetailHistoryResultList["+i+"].bz"]=proopeBusinessProcessSaleOrderStore.getAt(i).get("bz");
							}
							ajaxOpera("/proopeBusiness/sealOrderOutGoods.do", param, function(){
								Ext.Msg.alert("提示", "出货成功。", function(){
									Ext.getCmp('proopeBusinessProcessSaleOrderWindow').close();
									btn.up("proopebusinessnotprocesssalesordergrid").getStore().load();
								});
							});
                        }
                    },' ',{
                        text:'关闭',
                        handler: function(){
                            Ext.getCmp('proopeBusinessProcessSaleOrderWindow').close();
                        }
                    }]
                });
                showPupWindow(saleOrderManageWindow, "销售订单管理",650,380,'proopeBusinessProcessSaleOrderWindow');
                findSaleOrderDetail(records[0]);
             }
          },"proopebusinessnotprocesssalesordergrid button[id=proopeBusinessNotProcessSaleOrderEndTbarButton]" : {
              click : function(btn) {
            	  var records = btn.up("proopebusinessnotprocesssalesordergrid").getSelectionModel().getSelection();
                  if(records.length == 0){
                		Ext.Msg.alert("提示", "未选中任何订单记录。");
                		return;
                  }
               }
            }
     });  //  end control
     
     function validateProopeBusinessProcessSaleOrderStore(pbpsoStore){
    	 for(var i = 0; i < pbpsoStore.getCount(); i ++){
				if(pbpsoStore.getAt(i).get("jfsl") <= 0){
					Ext.Msg.alert("提示", pbpsoStore.getAt(i).get("cpmc")+"交付数量不可为0。");
					return true;
				}
				if(!checkBlankChar(pbpsoStore.getAt(i).get("bz"))){
					if(checkStrLength(pbpsoStore.getAt(i).get("bz"), 0, 128)){
						Ext.Msg.alert("提示", pbpsoStore.getAt(i).get("cpmc")+"备注长度不可大于128个字符。");
						return true;
					}
					if(checkIllegaeChar(pbpsoStore.getAt(i).get("bz"))){
						Ext.Msg.alert("提示", pbpsoStore.getAt(i).get("cpmc")+"备注中不可带有字符"+validateIllegagChar+"。");
						return true;
					}
				}
				
			}
    	 return false;
     }
    },  //  end init
    views : [
        "ERP.proope.business.view.businessNotProcessSalesOrderLayout",
        "ERP.proope.business.view.businessNotProcessSalesOrderGrid"
    ],
    stores : ["ERP.proope.business.store.businessNotProcessSalesOrderStore"],
    models : ["ERP.proope.business.model.businessNotProcessSalesOrderModel"]
});