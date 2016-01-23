/**
 * 采购申请控制器
 */
Ext.define("ERP.proope.purchase.controller.purchaseApplyController", {
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
     
     Ext.define('purchaseApplyDetailModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'wlmc', type: 'string'},
            {name: 'sqsl', type: 'float'}
        ]
     });
	
	 
	 //  查询取得物料，采购申请
	 function findMaterialStore(){
	 	var materialArray = new Array();
	 	var itemData;
	    ajaxQueryAll("/proopeBasedata/findAllMaterial.do", {},
	        function (materialResultList) {
	            for (var i = 0; i < materialResultList.length; i++) {
	                itemData = new Array();
	                itemData.push(materialResultList[i].mc+'('+materialResultList[i].gg+')_'+materialResultList[i].dm);
	                itemData.push(materialResultList[i].mc+'('+materialResultList[i].gg+'_'+materialResultList[i].dw+')');
	                materialArray.push(itemData);
	            }
	        });
	 	return materialArray;
	 }
	 
	 var purchaseApplyDetailEditStore;  //  修改store,设置为全局变量，在每次打开修改弹窗时，要清空该store中的数据
	 var purchaseApplyId;  //  修改时的申请代码
     //控制响应
     self.control({
     		"proopepurchasepurchaseapplygrid button[id=addPurchaseApplyButton]" : {
                click : function(btn) {
                	//  出现弹窗的响应，先判断弹窗对象是否存在，如存在，显示即可
                	if(Ext.getCmp('proopePurchaseApplyAddPurchaseApplyWindow')){
	            		Ext.getCmp('proopePurchaseApplyAddPurchaseApplyWindow').show();
	            		return;
	            	}
	            	
				    var departmentComboboxFields = [{name: 'dm'}, {name: 'bmmc'}];  //  部门combobox对应的字段
				     //  查询出部门
					 function findDepartmentFieldList(){
					 	var departmentArray = new Array();
					    var itemData;
					    ajaxQueryAll("/proopeBasedata/findNotSubmitPurchaseApplyDepartment.do", {},
					        function (departmentResultList) {
					            for (var i = 0; i < departmentResultList.length; i++) {
					                itemData = new Array();
					                itemData.push(departmentResultList[i].dm);
					                itemData.push(departmentResultList[i].bmmc);
					                departmentArray.push(itemData);
					            }
					        });
					    return departmentArray;
					 }
					 //  生成部门下拉框中的数据
					 function departmentComboboxFieldCreate() {
					    var departmentComboboxStore = Ext.create('Ext.data.ArrayStore', {
					        fields: departmentComboboxFields,
					        data: findDepartmentFieldList()
					    });
					    return departmentComboboxStore;
					 }

	            	// 创建新增store
				    var purchaseApplyDetailAddStore = Ext.create('Ext.data.Store', {
				        autoDestroy: true,
				        model: 'purchaseApplyDetailModel'
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
				        store: purchaseApplyDetailAddStore,
				        columns: [
					        {
					            header: '物料名称',
					            dataIndex: 'wlmc',
					            width: 400,
					            editor: {
					                xtype: 'combobox',
					                typeAhead: true,
					                triggerAction: 'all',
					                selectOnTab: true,
					                store: findMaterialStore(),
					                lazyRender: true,
					                listClass: 'x-combo-list-small'
					            }
					        }, 
					        {
					            header: '申请数量',
					            dataIndex: 'sqsl',
					            width: 100,
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
					                var addR = Ext.create('purchaseApplyDetailModel', {
					                    wlmc: '',
					                    sqsl: 1
					                });
					                purchaseApplyDetailAddStore.insert(0, addR);
					            }
					        },{
				                itemId: 'deleteAddButton',
				                text:'删除',
				                tooltip:'移除已选的物料',
				                iconCls:'remove',
				                disabled: true,
				                handler: function() {
				                	Ext.Msg.confirm("确认", "是否确定删除选中的物料？", function(retStr){
				                		if("yes" == retStr){
				                			var gridSel = gridAddForm.getSelectionModel().getSelection();
				                    		purchaseApplyDetailAddStore.remove(gridSel);
				                		}
				                	});
				                }
				            }]
				        }],
				        width: 200,
				        height: 245,
				        frame: true,
				        plugins: [cellAddEditing],
				        iconCls: 'icon-grid'
				    });
                	
                	
                	var purchaseApplyDepartmentStore = departmentComboboxFieldCreate();
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
					            name: 'purchaseApplyAddDepartmentCombobox',
					            id: 'purchaseApplyAddDepartmentCombobox',
					            fieldLabel: '采购申请部门',
					            labelWidth: 90,
					            width: 350,
					            store: purchaseApplyDepartmentStore,
					            valueField: 'dm',
					            displayField: 'bmmc',
					            typeAhead: true,
					            allowBlank: true,
					            forceSelection: true
					        },
					        gridAddForm
                        ],

                        buttons: [{
                            text: '保存',
                            handler: function(){
                                var bmdm = Ext.getCmp("purchaseApplyAddDepartmentCombobox").getValue();
                                if(checkBlankChar(bmdm)){
                                	Ext.Msg.alert("提示", "未选择申请部门。");
                                	return;
                                }
                                if(validatePurchaseApplyDetailStore(purchaseApplyDetailAddStore)){return;}
                                
                                var param = strToJson('{"purchaseApplyEntity.bmdm":"'+bmdm+'"}');
								var wlmcStr;
								for(var i = 0; i < purchaseApplyDetailAddStore.getCount(); i ++){
									wlmcStr = purchaseApplyDetailAddStore.getAt(i).get("wlmc");
									param["purchaseApplyDetailList["+i+"].wldm"]=wlmcStr.split('_')[1];
									param["purchaseApplyDetailList["+i+"].sqsl"]=purchaseApplyDetailAddStore.getAt(i).get("sqsl");
								}
								ajaxOpera("/proopePurchase/createPurchaseApply.do", param, function(){
									Ext.Msg.alert("提示", "采购申请创建成功。", function(){
										purchaseApplyDetailAddStore.removeAll();
										Ext.getCmp('proopePurchaseApplyAddPurchaseApplyWindow').close();
										btn.up("proopepurchasepurchaseapplygrid").getStore().load();
									});
								});
                            }
                        },' ',{
                            text:'关闭',
                            handler: function(){
                                Ext.getCmp('proopePurchaseApplyAddPurchaseApplyWindow').close();
                            }
                        }]
                    });
                    showPupWindow(addWindow, "新增采购申请",600,380,'proopePurchaseApplyAddPurchaseApplyWindow');
                }
            }, "proopepurchasepurchaseapplygrid button[id=updatePurchaseApplyButton]" : {
                click : function(btn) {
                	var records = btn.up("proopepurchasepurchaseapplygrid").getSelectionModel().getSelection();
                	purchaseApplyId = records[0].get('dm');
                	//  出现弹窗的响应，先判断弹窗对象是否存在，如存在，显示即可
                	if(Ext.getCmp('proopePurchaseApplyEditPurchaseApplyWindow')){
	            		Ext.getCmp('proopePurchaseApplyEditPurchaseApplyWindow').show();
                		findEditPurchaseApply(records[0]);  //  显示弹窗后，查询出数据显示在弹窗中
	            		return;
	            	}

	            	function findEditPurchaseApply(record){
           				Ext.getCmp('purchaseApplyEditDepartmentLabel').setText("申请部门："+record.get('bmmc'), true);
           				//  查询出采购申请明细
           				purchaseApplyDetailEditStore.removeAll();
           				ajaxQueryAll("/proopePurchase/findPurchaseApplyDetailResultListByPaId.do", {"purchaseApplyId":purchaseApplyId}, 
	            			function(purchaseApplyDetailList){
	            				for (var i = 0; i < purchaseApplyDetailList.length; i++) {
	            			        var editR = Ext.create('purchaseApplyDetailModel', {
					                    wlmc: purchaseApplyDetailList[i].wlmc+'('+purchaseApplyDetailList[i].gg+')_'+purchaseApplyDetailList[i].wldm,
					                    sqsl: purchaseApplyDetailList[i].sqsl
					                });
					                purchaseApplyDetailEditStore.insert(0, editR);
					            }
	            			});
	            	}
	            	
	            	// 全局变量修改store
				    purchaseApplyDetailEditStore = Ext.create('Ext.data.Store', {
				        autoDestroy: true,
				        model: 'purchaseApplyDetailModel'
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
				        store: purchaseApplyDetailEditStore,
				        columns: [
					        {
					            header: '物料名称',
					            dataIndex: 'wlmc',
					            width: 400,
					            editor: {
					                xtype: 'combobox',
					                typeAhead: true,
					                triggerAction: 'all',
					                selectOnTab: true,
					                store: findMaterialStore(),
					                lazyRender: true,
					                listClass: 'x-combo-list-small'
					            }
					        }, 
					        {
					            header: '申请数量',
					            dataIndex: 'sqsl',
					            width: 100,
					            align: 'right',
					            editor: {
					                xtype: 'numberfield',
					                allowBlank: false,
					                minValue: 0
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
					            	var editR = Ext.create('purchaseApplyDetailModel', {
					                    wlmc: '',
					                    sqsl: 1
					                });
					                purchaseApplyDetailEditStore.insert(0, editR);
					                //cellEditing.startEditByPosition({row: 0, column: 0});
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
				                    		purchaseApplyDetailEditStore.remove(gridSel);
				                		}
				                	});
				                }
				            }]
				        }],
				        width: 200,
				        height: 245,
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
			                    forId: "purchaseApplyEditDepartmentLabel",
			                    id: "purchaseApplyEditDepartmentLabel",
			                    text: "申请部门："
					        },
					        gridEditForm
                        ],

                        buttons: [{
                            text: '保存',
                            handler: function(){
                            	if(validatePurchaseApplyDetailStore(purchaseApplyDetailEditStore)){return;}
                            	
                                var param = strToJson('{"purchaseApplyEntity.dm":"'+purchaseApplyId+'"}');
								var wlmcStr;
								for(var i = 0; i < purchaseApplyDetailEditStore.getCount(); i ++){
									wlmcStr = purchaseApplyDetailEditStore.getAt(i).get("wlmc");
									param["purchaseApplyDetailList["+i+"].wldm"]=wlmcStr.split('_')[1];
									param["purchaseApplyDetailList["+i+"].sqsl"]=purchaseApplyDetailEditStore.getAt(i).get("sqsl");
								}
								ajaxOpera("/proopePurchase/updatePurchaseApply.do", param, function(){
									Ext.Msg.alert("提示", "修改申请创建成功。", function(){
										purchaseApplyDetailEditStore.removeAll();
										Ext.getCmp('proopePurchaseApplyEditPurchaseApplyWindow').close();
									});
								});
                            }
                        },' ',{
                            text:'关闭',
                            handler: function(){
                                Ext.getCmp('proopePurchaseApplyEditPurchaseApplyWindow').close();
                            }
                        }]
                    });
                    showPupWindow(editWindow, "修改采购申请",600,380,'proopePurchaseApplyEditPurchaseApplyWindow');
                    findEditPurchaseApply(records[0]);  //  弹窗创建结束，查询出数据显示在弹窗中
                }
            }, "proopepurchasepurchaseapplygrid button[id=deletePurchaseApplyButton]" : {
                click : function(btn) {
                	var records = btn.up("proopepurchasepurchaseapplygrid").getSelectionModel().getSelection();
                	if(records.length == 0){
                		Ext.Msg.alert("提示", "未选中要删除的记录。");
                		return;
                	}
                	Ext.Msg.confirm("确认", "是否确定删除选中的采购申请？", function(retStr){
                		if("yes" == retStr){
                			var param = {};
		                	for(var i = 0; i < records.length; i ++){
		                		param["purchaseApplyIdList["+i+"]"]=records[i].get('dm');
		                	}
		                	ajaxOpera("/proopePurchase/deletePurchaseApply.do", param, function(){
								Ext.Msg.alert("提示", "删除采购申请成功。", function(){
									btn.up("proopepurchasepurchaseapplygrid").getStore().load();
								});
							});
                		}
                	});
                	
                }
            }, "proopepurchasepurchaseapplygrid button[id=submitPurchaseApplyButton]" : {
                click : function(btn) {
                	var records = btn.up("proopepurchasepurchaseapplygrid").getSelectionModel().getSelection();
                	if(records.length == 0){
                		Ext.Msg.alert("提示", "未选中要提交的记录。");
                		return;
                	}
                	Ext.Msg.confirm("确认", "是否确定提交选中的采购申请？提交后，该申请不可再作修改。", function(retStr){
                		if("yes" == retStr){
                			var param = {};
		                	for(var i = 0; i < records.length; i ++){
		                		param["purchaseApplyIdList["+i+"]"]=records[i].get('dm');
		                	}
		                	ajaxOpera("/proopePurchase/submitPurchaseApply.do", param, function(){
								Ext.Msg.alert("提示", "提交采购申请成功。", function(){
									btn.up("proopepurchasepurchaseapplygrid").getStore().load();
								});
							});
                		}
                	});
                }
            }
     });//  end control
     
     function validatePurchaseApplyDetailStore(purchaseApplyDetailStore){
    	 if(purchaseApplyDetailStore.getCount() == 0){
    		 Ext.Msg.alert("提示", "未选择物料。");
    		 return true;
    	 }
    	 
    	 var wlmcStr;
    	 if(purchaseApplyDetailStore.getCount() == 1){
    		 wlmcStr = purchaseApplyDetailStore.getAt(0).get("wlmc");
    		 if(checkBlankChar(wlmcStr)){
    			 Ext.Msg.alert("提示", "请选择物料。");
    			 return true;
    		 }
    		 if(purchaseApplyDetailStore.getAt(0).get("sqsl") <= 0){
    			 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"申请数量不可为0。");
    			 return true;
    		 }
    		 return false;
    	 }
    	 
    	 for(var i = 0; i < purchaseApplyDetailStore.getCount() -1; i ++){
    		 wlmcStr = purchaseApplyDetailStore.getAt(i).get("wlmc");
    		 if(checkBlankChar(wlmcStr)){
    			 Ext.Msg.alert("提示", "请选择物料。");
    			 return true;
    		 }
    		 //  验证申请数量
    		 if(purchaseApplyDetailStore.getAt(i).get("sqsl") <= 0){
    			 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"申请数量不可为0。");
    			 return true;
    		 }
    		 for(var j = i + 1; j < purchaseApplyDetailStore.getCount(); j ++){
    			 if(wlmcStr.split('_')[1] == purchaseApplyDetailStore.getAt(j).get("wlmc").split('_')[1]){
    				 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"重复选择。");
        			 return true;
    			 }
    		 }
		 }
    	 
    	 wlmcStr = purchaseApplyDetailStore.getAt(purchaseApplyDetailStore.getCount()-1).get("wlmc");
    	 if(checkBlankChar(wlmcStr)){
			 Ext.Msg.alert("提示", "请选择物料。");
			 return true;
		 }
    	 if(purchaseApplyDetailStore.getAt(purchaseApplyDetailStore.getCount()-1).get("sqsl") <= 0){
			 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"申请数量不可为0。");
			 return true;
		 }
    	 return false;
     }
    },//  end init
    views : [
        "ERP.proope.purchase.view.purchaseApplyLayout",
        "ERP.proope.purchase.view.purchaseApplyGrid"
    ],
    stores : ["ERP.proope.purchase.store.purchaseApplyStore"],
    models : ["ERP.proope.purchase.model.purchaseApplyModel"]
});
