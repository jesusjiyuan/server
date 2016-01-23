/**
 * 未提交销售订单控制器
 */
Ext.define("ERP.proope.business.controller.businessNotSubmitSalesOrderController", {
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
     
     Ext.define('proopeBusinessSaleOrderDetailModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'cpmc', type: 'string'},
            {name: 'sl', type: 'float'},
            {name: 'dj', type: 'float'}
        ]
     });
     
     //  查询产品
	 function findProductStore(){
	 	var productArray = new Array();
	 	var itemData;
	    ajaxQueryAll("/proopeBasedata/findAllProduct.do", {},
	        function (productList) {
	            for (var i = 0; i < productList.length; i++) {
	                itemData = new Array();
	                itemData.push(productList[i].cpmc+'('+productList[i].xh+')_'+productList[i].dm);
	                itemData.push(productList[i].cpmc+'('+productList[i].xh+')');
	                productArray.push(itemData);
	            }
	        });
	 	return productArray;
	 }
	 
	 var proopeBusinessSaleOrderDetailEditStore;  //  修改Store，全局变量
     
     //控制响应
     self.control({
     	"proopebusinessnotsubmitsalesordergrid button[id=proopeBusinessNotSubmitSalesOrderAddTbarButton]" : {
            click : function(btn) {
            	//alert("add");
            	if(Ext.getCmp('proopeBusinessAddSaleOrderWindow')){
            		Ext.getCmp('proopeBusinessAddSaleOrderWindow').show();
            		return;
            	}
            	
			    var clientComboboxFields = [{name: 'dm'}, {name: 'khmc'}];  //  客户combobox对应的字段
			    
			    //  查询出客户
				function findClientFieldList(){
				 	var clientArray = new Array();
				    var itemData;
				    ajaxQueryAll("/proopeBasedata/findAllClient.do", {},
				        function (clientResultList) {
				            for (var i = 0; i < clientResultList.length; i++) {
				                itemData = new Array();
				                itemData.push(clientResultList[i].dm);
				                itemData.push(clientResultList[i].khmc);
				                clientArray.push(itemData);
				            }
				        });
				    return clientArray;
				 }
				 //  生成客户下拉框中的数据
				 function clientComboboxFieldCreate() {
				    var clientComboboxStore = Ext.create('Ext.data.ArrayStore', {
				        fields: clientComboboxFields,
				        data: findClientFieldList()
				    });
				    return clientComboboxStore;
				 }

            	// 创建新增store
			    var proopeBusinessSaleOrderDetailAddStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeBusinessSaleOrderDetailModel'
			    });
			    
			    var selAddModel = Ext.create('Ext.selection.CheckboxModel', {
			        listeners: {
			            selectionchange: function(sm, selections) {
			                gridAddForm.down('#deleteAddButton').setDisabled(selections.length == 0);
			            }
			        }
			    });
			    
			    var cellAddEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			        clicksToEdit: 1
			    });
			    
			    var gridAddForm = Ext.create('Ext.grid.Panel', {
			        store: proopeBusinessSaleOrderDetailAddStore,
			        columns: [
				        {
				            header: '产品名称',
				            dataIndex: 'cpmc',
				            width: 300,
				            editor: {
				                xtype: 'combobox',
				                typeAhead: true,
				                triggerAction: 'all',
				                selectOnTab: true,
				                store: findProductStore(),
				                lazyRender: true,
				                listClass: 'x-combo-list-small'
				            }
				        }, 
				        {
				            header: '数量',
				            dataIndex: 'sl',
				            width: 80,
				            align: 'right',
				            editor: {
				                xtype: 'numberfield',
				                allowBlank: false,
				                minValue: 0
				            }
				        }, 
				        {
				            header: '单价',
				            dataIndex: 'dj',
				            width: 80,
				            align: 'right',
				            editor: {
				                xtype: 'numberfield',
				                allowBlank: false,
				                minValue: 0
				            }
				        }
			        ],
			        columnLines: true,
			        selModel: selAddModel,
			        dockedItems: [{
			            xtype: 'toolbar',
			            items: [{
			            	itemId: 'addAddButton',
				            text: '添加',
				            handler : function(){
				                var addR = Ext.create('proopeBusinessSaleOrderDetailModel', {
				                    cpmc: '',
				                    sl: 1,
				                    dj: 1
				                });
				                proopeBusinessSaleOrderDetailAddStore.insert(0, addR);
				            }
				        },{
			                itemId: 'deleteAddButton',
			                text:'删除',
			                tooltip:'移除已选的产品',
			                iconCls:'remove',
			                disabled: true,
			                handler: function() {
			                	Ext.Msg.confirm("确认", "是否确定删除选中的产品？", function(retStr){
			                		if("yes" == retStr){
			                			var gridSel = gridAddForm.getSelectionModel().getSelection();
			                    		proopeBusinessSaleOrderDetailAddStore.remove(gridSel);
			                		}
			                	});
			                }
			            }]
			        }],
			        width: 500,
			        height: 240,
			        frame: true,
			        plugins: [cellAddEditing],
			        iconCls: 'icon-grid'
			    });
	              	
	              	
              	var proopeBusinessSaleOrderClientStore = clientComboboxFieldCreate();
                  var addWindow = Ext.create('Ext.form.Panel', {
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
				            xtype: 'combobox',
				            name: 'proopeBusinessSaleOrderClientCombobox',
				            id: 'proopeBusinessSaleOrderClientCombobox',
				            fieldLabel: '客户',
				            labelWidth: 90,
				            width: 350,
				            store: proopeBusinessSaleOrderClientStore,
				            valueField: 'dm',
				            displayField: 'khmc',
				            typeAhead: true,
				            allowBlank: true,
				            forceSelection: true
				        },
				        gridAddForm
                      ],

                      buttons: [{
                          text: '保存',
                          handler: function(){
                                var khdm = Ext.getCmp("proopeBusinessSaleOrderClientCombobox").getValue();
                                
                                if(checkBlankChar(khdm)){
                                	Ext.Msg.alert("提示", "未选择客户。");
                                	return;
                                }
                                if(validateProopeBusinessSaleOrderDetailEditStore(proopeBusinessSaleOrderDetailAddStore)){return;}
                                
                                var param = strToJson('{"salesOrderEntity.khdm":"'+khdm+'"}');
								var cpmcStr;
								for(var i = 0; i < proopeBusinessSaleOrderDetailAddStore.getCount(); i ++){
									cpmcStr = proopeBusinessSaleOrderDetailAddStore.getAt(i).get("cpmc");
									param["salesOrderDetailEntityList["+i+"].cpdm"]=cpmcStr.split('_')[1];
									param["salesOrderDetailEntityList["+i+"].sl"]=proopeBusinessSaleOrderDetailAddStore.getAt(i).get("sl");
									param["salesOrderDetailEntityList["+i+"].dj"]=proopeBusinessSaleOrderDetailAddStore.getAt(i).get("dj");
								}
								
								ajaxOpera("/proopeBusiness/createSaleOrder.do", param, function(){
									Ext.Msg.alert("提示", "销售订单创建成功。", function(){
										proopeBusinessSaleOrderDetailAddStore.removeAll();
										Ext.getCmp('proopeBusinessAddSaleOrderWindow').close();
										btn.up("proopebusinessnotsubmitsalesordergrid").getStore().load();
									});
								});
                          }
                      },' ',{
                          text:'关闭',
                          handler: function(){
                              Ext.getCmp('proopeBusinessAddSaleOrderWindow').close();
                          }
                      }]
                  });
                  showPupWindow(addWindow, "新增采购申请",600,380,'proopeBusinessAddSaleOrderWindow');
            }
         },"proopebusinessnotsubmitsalesordergrid button[id=proopeBusinessNotSubmitSalesOrderEditTbarButton]" : {
            click : function(btn) {
            	var records = btn.up("proopebusinessnotsubmitsalesordergrid").getSelectionModel().getSelection();
               	var salesOrderId = records[0].get('dm');
               	
               	//  出现弹窗的响应，先判断弹窗对象是否存在，如存在，显示即可
               	if(Ext.getCmp('proopeBusinessNotSubmitSalesOrderEditWindow')){
            		Ext.getCmp('proopeBusinessNotSubmitSalesOrderEditWindow').show();
               		findEditSalesOrder(salesOrderId);  //  显示弹窗后，查询出数据显示在弹窗中
            		return;
            	}
            	
            	function findEditSalesOrder(soId){
            		ajaxQueryEntity("/proopeBusiness/findSalesOrderResultBySalesOrderId.do", {"salesOrderId":soId}, 
            			function(entityObj){
            				Ext.getCmp('proopeBusinessEditSaleOrderClientLabel').setText("客户："+entityObj.khmc, true);
            				//  查询出销售订单明细
            				proopeBusinessSaleOrderDetailEditStore.removeAll();
            				ajaxQueryAll("/proopeBusiness/findSalesOrderDetailResultListBySalesOrderId.do", {"salesOrderId":soId}, 
		            			function(saleOrderDetailList){
		            				for (var i = 0; i < saleOrderDetailList.length; i++) {
		            			        var editR = Ext.create('proopeBusinessSaleOrderDetailModel', {
						                    cpmc: saleOrderDetailList[i].cpmc+'_'+saleOrderDetailList[i].cpdm,
						                    sl: saleOrderDetailList[i].sl,
						                    dj:saleOrderDetailList[i].dj
						                });
						                proopeBusinessSaleOrderDetailEditStore.insert(0, editR);
						            }
		            			});
            			});
            	}

            	// 全局变量修改store
			    proopeBusinessSaleOrderDetailEditStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeBusinessSaleOrderDetailModel'
			    });
			    
			    var selEditModel = Ext.create('Ext.selection.CheckboxModel', {
			        listeners: {
			            selectionchange: function(sm, selections) {
			                gridEditForm.down('#deleteEditButton').setDisabled(selections.length == 0);
			            }
			        }
			    });
			    
			    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			        clicksToEdit: 1
			    });
			    
              	var gridEditForm = Ext.create('Ext.grid.Panel', {
			        store: proopeBusinessSaleOrderDetailEditStore,
			        columns: [
				        {
				            header: '产品名称',
				            dataIndex: 'cpmc',
				            width: 300,
				            editor: {
				                xtype: 'combobox',
				                typeAhead: true,
				                triggerAction: 'all',
				                selectOnTab: true,
				                store: findProductStore(),
				                lazyRender: true,
				                listClass: 'x-combo-list-small'
				            }
				        }, 
				        {
				            header: '数量',
				            dataIndex: 'sl',
				            width: 80,
				            align: 'right',
				            editor: {
				                xtype: 'numberfield',
				                allowBlank: false,
				                minValue: 0.01
				            }
				        }, 
				        {
				            header: '单价',
				            dataIndex: 'dj',
				            width: 80,
				            align: 'right',
				            editor: {
				                xtype: 'numberfield',
				                allowBlank: false,
				                minValue: 0,
				                maxValue:10
				            }
				        }
			        ],
			        columnLines: true,
			        selModel: selEditModel,
			        dockedItems: [{
			            xtype: 'toolbar',
			            items: [{
				            text: '添加',
				            itemId: 'addEditButton',
				            handler : function(){
				            	var editR = Ext.create('proopeBusinessSaleOrderDetailModel', {
				                    cpmc: '',
				                    sl: 1,
				                    dj: 1
				                });
				                proopeBusinessSaleOrderDetailEditStore.insert(0, editR);
				            }
				        },{
			                itemId: 'deleteEditButton',
			                text:'删除',
			                tooltip:'移除已选的物料',
			                iconCls:'remove',
			                disabled: true,
			                handler: function() {
			                	Ext.Msg.confirm("确认", "是否确定删除选中的物料？", function(retStr){
			                		if("yes" == retStr){
			                			var gridSel = gridEditForm.getSelectionModel().getSelection();
			                    		proopeBusinessSaleOrderDetailEditStore.remove(gridSel);
			                		}
			                	});
			                }
			            }]
			        }],
			        width: 500,
			        height: 240,
			        frame: true,
			        plugins: [cellEditing],
			        iconCls: 'icon-grid'
			    });
              	
              	
               var editWindow = Ext.create('Ext.form.Panel', {
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
	                   forId: "proopeBusinessEditSaleOrderClientLabel",
	                   id: "proopeBusinessEditSaleOrderClientLabel",
	                   text: "客户："
			        },
			        gridEditForm
                    ],

                    buttons: [{
                        text: '保存',
                        handler: function(){
                        	if(validateProopeBusinessSaleOrderDetailEditStore(proopeBusinessSaleOrderDetailEditStore)){return;}
                        	
                            var param = strToJson('{"salesOrderEntity.dm":"'+salesOrderId+'"}');
							var cpmcStr;
							for(var i = 0; i < proopeBusinessSaleOrderDetailEditStore.getCount(); i ++){
								cpmcStr = proopeBusinessSaleOrderDetailEditStore.getAt(i).get("cpmc");
								param["salesOrderDetailEntityList["+i+"].cpdm"]=cpmcStr.split('_')[1];
								param["salesOrderDetailEntityList["+i+"].sl"]=proopeBusinessSaleOrderDetailEditStore.getAt(i).get("sl");
								param["salesOrderDetailEntityList["+i+"].dj"]=proopeBusinessSaleOrderDetailEditStore.getAt(i).get("dj");
							}
							ajaxOpera("/proopeBusiness/updateSaleOrder.do", param, function(){
								Ext.Msg.alert("提示", "修改销售订单成功。", function(){
									proopeBusinessSaleOrderDetailEditStore.removeAll();
									Ext.getCmp('proopeBusinessNotSubmitSalesOrderEditWindow').close();
								});
							});
                        }
                    },' ',{
                        text:'关闭',
                        handler: function(){
                            Ext.getCmp('proopeBusinessNotSubmitSalesOrderEditWindow').close();
                        }
                    }]
                });
                showPupWindow(editWindow, "修改销售订单",600,380,'proopeBusinessNotSubmitSalesOrderEditWindow');
                findEditSalesOrder(salesOrderId);  //  弹窗创建结束，查询出数据显示在弹窗中
            }
         },"proopebusinessnotsubmitsalesordergrid button[id=proopeBusinessNotSubmitSalesOrderDeleTbarButton]" : {
            click : function(btn) {
            	//  删除未提交的销售订单
            	var records = btn.up("proopebusinessnotsubmitsalesordergrid").getSelectionModel().getSelection();
                	if(records.length == 0){
                		Ext.Msg.alert("提示", "未选中要删除的记录。");
                		return;
                	}
                	Ext.Msg.confirm("确认", "是否确定删除选中的销售订单？", function(retStr){
                		if("yes" == retStr){
                			var param = {};
		                	for(var i = 0; i < records.length; i ++){
		                		param["salesOrderIdList["+i+"]"]=records[i].get('dm');
		                	}
		                	ajaxOpera("/proopeBusiness/deleSaleOrder.do", param, function(){
								Ext.Msg.alert("提示", "删除销售订单成功。", function(){
									btn.up("proopebusinessnotsubmitsalesordergrid").getStore().load();
								});
							});
                		}
                	});
            }
         },"proopebusinessnotsubmitsalesordergrid button[id=proopeBusinessNotSubmitSalesOrderSubmitTbarButton]" : {
            click : function(btn) {
            	//  提交销售订单
            	var records = btn.up("proopebusinessnotsubmitsalesordergrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中要提交的记录。");
               		return;
               	}
               	Ext.Msg.confirm("确认", "是否确定提交选中的销售订单？提交后，该订单不可再作修改。", function(retStr){
               		if("yes" == retStr){
               			var param = {};
	                	for(var i = 0; i < records.length; i ++){
	                		param["salesOrderIdList["+i+"]"]=records[i].get('dm');
	                	}
	                	ajaxOpera("/proopeBusiness/submitSaleOrder.do", param, function(){
							Ext.Msg.alert("提示", "提交销售订单成功。", function(){
								btn.up("proopebusinessnotsubmitsalesordergrid").getStore().load();
							});
						});
               		}
               	});
            }
         }
     });//  end control
     
     function validateProopeBusinessSaleOrderDetailEditStore(pbsodeStore){
    	 if(pbsodeStore.getCount() == 0){
    		 Ext.Msg.alert("提示", "未选择产品。");
    		 return true;
    	 }
    	 
    	 var cpmcStr;
    	 if(pbsodeStore.getCount() == 1){
    		 cpmcStr = pbsodeStore.getAt(0).get("cpmc");
    		 if(checkBlankChar(cpmcStr)){
    			 Ext.Msg.alert("提示", "请选择产品。");
    			 return true;
    		 }
    		 if(pbsodeStore.getAt(0).get("sl") <= 0){
    			 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"数量不可为0。");
    			 return true;
    		 }
    		 if(pbsodeStore.getAt(0).get("dj") <= 0){
    			 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"单价不可为0。");
    			 return true;
    		 }
    		 return false;
    	 }
    	 
    	 for(var i = 0; i < pbsodeStore.getCount() -1; i ++){
    		 cpmcStr = pbsodeStore.getAt(i).get("cpmc");
    		 if(checkBlankChar(cpmcStr)){
    			 Ext.Msg.alert("提示", "请选择产品。");
    			 return true;
    		 }
    		 //  验证数量，单价
    		 if(pbsodeStore.getAt(i).get("sl") <= 0){
    			 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"数量不可为0。");
    			 return true;
    		 }
    		 if(pbsodeStore.getAt(i).get("dj") <= 0){
    			 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"单价不可为0。");
    			 return true;
    		 }
    		 for(var j = i + 1; j < pbsodeStore.getCount(); j ++){
    			 if(cpmcStr.split('_')[1] == pbsodeStore.getAt(j).get("cpmc").split('_')[1]){
    				 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"重复选择。");
        			 return true;
    			 }
    		 }
		 }
    	 
    	 cpmcStr = pbsodeStore.getAt(pbsodeStore.getCount()-1).get("cpmc");
    	 if(checkBlankChar(cpmcStr)){
			 Ext.Msg.alert("提示", "请选择产品。");
			 return true;
		 }
    	 if(pbsodeStore.getAt(pbsodeStore.getCount()-1).get("sl") <= 0){
			 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"数量不可为0。");
			 return true;
		 }
    	 if(pbsodeStore.getAt(pbsodeStore.getCount()-1).get("dj") <= 0){
			 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"单价不可为0。");
			 return true;
		 }
    	 return false;
     }
    },//  end init
    views : [
        "ERP.proope.business.view.businessNotSubmitSalesOrderLayout",
        "ERP.proope.business.view.businessNotSubmitSalesOrderGrid"
    ],
    stores : ["ERP.proope.business.store.businessNotSubmitSalesOrderStore"],
    models : ["ERP.proope.business.model.businessNotSubmitSalesOrderModel"]
});