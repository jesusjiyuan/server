/**
 * 领料历史控制器
 */
Ext.define("ERP.proope.produce.controller.receiveMaterialHistoryController", {
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
     
     Ext.define('proopeProduceReceiveMaterialCreateModel', {
         extend: 'Ext.data.Model',
         fields: [
             {name: 'wlmc', type: 'string'},
             {name: 'llsl', type: 'float'}
         ]
      });
     
     //  查询取得物料，用料申请
	 function findMaterialStore(){
	 	var materialArray = new Array();
	 	var itemData;
	    ajaxQueryAll("/proopeBasedata/findAllMaterial.do", {},
	        function (materialResultList) {
	            for (var i = 0; i < materialResultList.length; i++) {
	                itemData = new Array();
	                itemData.push(materialResultList[i].mc+'_'+materialResultList[i].dm);
	                itemData.push(materialResultList[i].mc+'_'+materialResultList[i].dw+')');
	                materialArray.push(itemData);
	            }
	        });
	 	return materialArray;
	 }
	 
	 Ext.define('proopeProduceReceiveMaterialDetailHistoryModel', {
         extend: 'Ext.data.Model',
         fields: [
             {name: 'dm', type: 'string'},
             {name: 'lllsdm', type: 'string'},
             {name: 'wldm', type: 'string'},
             {name: 'wlmc', type: 'string'},
             {name: 'sl', type: 'float'}
         ]
      });

	 var proopeProduceReceiveMaterialDetailHistoryStore;  //  全局变量
	 
     //控制响应
     self.control({
    	 "proopeproducereceivematerialhistorygrid button[id=proopeProductReceiveMaterialHistoryAddTbarButton]" : {
             click : function(btn) {
            	 if(Ext.getCmp('proopeProduceReceiveMaterialCreateWindow')){
            		Ext.getCmp('proopeProduceReceiveMaterialCreateWindow').show();
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
			    var proopeProduceReceiveMaterialCreateStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeProduceReceiveMaterialCreateModel'
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
			        store: proopeProduceReceiveMaterialCreateStore,
			        columns: [
				        {
				            header: '物料名称',
				            dataIndex: 'wlmc',
				            width: 300,
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
				            header: '领料数量',
				            dataIndex: 'llsl',
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
				                var addR = Ext.create('proopeProduceReceiveMaterialCreateModel', {
				                    wlmc: '',
				                    llsl: 1
				                });
				                proopeProduceReceiveMaterialCreateStore.insert(0, addR);
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
			                			proopeProduceReceiveMaterialCreateStore.remove(gridSel);
			                		}
			                	});
			                }
			            }]
			        }],
			        width: 500,
			        height: 245,
			        frame: true,
			        plugins: [cellAddEditing],
			        iconCls: 'icon-grid'
			    });
			    
			    var receiveMaterialCreateDepartmentStore = departmentComboboxFieldCreate();
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
				            name: 'proopeProduceReceiveMaterialCreateDepartmentCombobox',
				            id: 'proopeProduceReceiveMaterialCreateDepartmentCombobox',
				            fieldLabel: '领料部门',
				            labelWidth: 90,
				            width: 350,
				            store: receiveMaterialCreateDepartmentStore,
				            valueField: 'dm',
				            displayField: 'bmmc',
				            typeAhead: true,
				            allowBlank: true,
				            forceSelection: true
				        },{ xtype:'checkboxgroup',
				        	fieldLabel: '用途',
				        	id:'proopeProduceReceiveMaterialYtCheckboxgroup',
				        	columns: 4,
				        	vertical:true,
				        	items:[{boxLabel: '生产用料',name:'proopeProduceReceiveMaterialYtCheckbox',inputValue:'1'},
				        	       {boxLabel: '样品用料',name:'proopeProduceReceiveMaterialYtCheckbox',inputValue:'2'},
				        	       {boxLabel: '办公用品',name:'proopeProduceReceiveMaterialYtCheckbox',inputValue:'3'},
				        	       {boxLabel: '其他用料',name:'proopeProduceReceiveMaterialYtCheckbox',inputValue:'4'}]
				        
				        },
				        gridAddForm
                    ],

                    buttons: [{
                        text: '保存',
                        handler: function(){
                        	var bmdm = Ext.getCmp("proopeProduceReceiveMaterialCreateDepartmentCombobox").getValue();
                        	if(checkBlankChar(bmdm)){
                        		Ext.Msg.alert("提示", "未选择领料部门。");
                        		return;
                        	}
                        	
                        	
                        	if(validateProopeProduceReceiveMaterialCreateStore(proopeProduceReceiveMaterialCreateStore)){return;}
                        	
                        	var bmmc = '';
                        	receiveMaterialCreateDepartmentStore.each(function(r){
                                if(r.data['dm'] == bmdm){
                                	bmmc = r.data['bmmc'];
                                }
                            });
                        	//用途
                        	var ytValue = Ext.getCmp("proopeProduceReceiveMaterialYtCheckboxgroup").getChecked();
                        	
                        	alert(bmdm + "|" + bmmc);
                            var param = strToJson('{"receiveMaterialHistoryEntity.bmdm":"'+bmdm+'"}');
                            param["receiveMaterialHistoryEntity.bmmc"]=bmmc;
                            Ext.Array.each(ytValue,function(item){
                            	var i = parseInt(item.inputValue+" ");
                            	alert(i);
                            	if('生产用料' == item.boxLabel ){param["receiveMaterialHistoryEntity.scyl"]=i;}
                            	if('样品用料' == item.boxLabel ){param["receiveMaterialHistoryEntity.ypyl"]=i;}
                            	if('办公用品' == item.boxLabel ){param["receiveMaterialHistoryEntity.bgyl"]=i;}
                            	if('其他用料' == item.boxLabel ){param["receiveMaterialHistoryEntity.qtyl"]=i;}
                            	 
                            });
                            
							var wl;
							for(var i = 0; i < proopeProduceReceiveMaterialCreateStore.getCount(); i ++){
								wl = proopeProduceReceiveMaterialCreateStore.getAt(i).get("wlmc").split('_');
								param["receiveMaterialDetailHistoryEntityList["+i+"].wldm"]=wl[1];
								param["receiveMaterialDetailHistoryEntityList["+i+"].wlmc"]=wl[0];
								param["receiveMaterialDetailHistoryEntityList["+i+"].sl"]=proopeProduceReceiveMaterialCreateStore.getAt(i).get("llsl");
							}
							
							ajaxOpera("/proopeProduce/createReceiveMaterialHistory.do", param, function(){
								Ext.Msg.alert("提示", "领料成功。", function(){
									Ext.getCmp('proopeProduceReceiveMaterialCreateWindow').close();
									btn.up("proopeproducereceivematerialhistorygrid").getStore().load();
								});
							});
                        }
                    },' ',{
                        text:'关闭',
                        handler: function(){
                            Ext.getCmp('proopeProduceReceiveMaterialCreateWindow').close();
                        }
                    }]
                });
                showPupWindow(addWindow, "创建领料记录",600,380,'proopeProduceReceiveMaterialCreateWindow');
			    
             }
    	 }, "proopeproducereceivematerialhistorygrid button[id=proopeProductReceiveMaterialHistoryDetailTbarButton]" : {
             click : function(btn) {
            	var records = btn.up("proopeproducereceivematerialhistorygrid").getSelectionModel().getSelection();
            	if(records.length == 0){
            		Ext.Msg.alert("提示", "未选中领料记录。");
            		return;
            	}
            	
            	if(Ext.getCmp('proopeProduceReceiveMaterialHistoryDetailWindow')){
            		Ext.getCmp('proopeProduceReceiveMaterialHistoryDetailWindow').show();
            		findProduceReceiveMaterialHistoryDetail(records[0]);
            		return;
            	}
            	
            	proopeProduceReceiveMaterialDetailHistoryStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeProduceReceiveMaterialDetailHistoryModel'
			    });
            	
            	
            	function findProduceReceiveMaterialHistoryDetail(record){
            		Ext.getCmp('proopeProduceReceiveMaterialDetailHistoryBmmcLabel').setText("部门名称："+
                   			record.get('bmmc') + "    创建日期：" + record.get('cjrqYMDHMS'), true);
            			proopeProduceReceiveMaterialDetailHistoryStore.removeAll();
              			ajaxQueryAll("/proopeProduce/findReceiveMaterialDetailHistoryListByRmhId.do", {"receiveMaterialHistoryId":record.get('dm')}, 
                			function(receiveMaterialDetailHistoryList){
                				for (var i = 0; i < receiveMaterialDetailHistoryList.length; i++) {
                			        var r = Ext.create('proopeProduceReceiveMaterialDetailHistoryModel', {
                			        	dm: receiveMaterialDetailHistoryList[i].dm,
                			        	lllsdm: receiveMaterialDetailHistoryList[i].lllsdm,
                			        	wldm: receiveMaterialDetailHistoryList[i].wldm,
    				                    wlmc: receiveMaterialDetailHistoryList[i].wlmc,
    				                    sl: receiveMaterialDetailHistoryList[i].sl
    				                });
                			        proopeProduceReceiveMaterialDetailHistoryStore.insert(0, r);
    				            }
                			});
              			
            	}
            	
            	var historyGridForm = Ext.create('Ext.grid.Panel', {
			        store: proopeProduceReceiveMaterialDetailHistoryStore,
			        columns: [
				        {header: '代码',dataIndex: 'dm', hidden:true},
				        {header: '领料历史代码',dataIndex: 'lllsdm',hidden:true},
			        	{header: '物料代码',dataIndex: 'wldm',hidden:true}, 
				        {header: '物料名称',dataIndex: 'wlmc',width:300},
				        {header: '数量',dataIndex: 'sl',width:80}
			        ],
			        columnLines: true,
			        width: 400,
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
		                    id: "proopeProduceReceiveMaterialDetailHistoryBmmcLabel",
		                    text: "供应商："
				        },
				        historyGridForm
                    ],

                    buttons: [{
                        text:'关闭',
                        handler: function(){
                            Ext.getCmp('proopeProduceReceiveMaterialHistoryDetailWindow').close();
                        }
                    }]
                });
                showPupWindow(historyWindow, "领料历史明细",500,380,'proopeProduceReceiveMaterialHistoryDetailWindow');
                findProduceReceiveMaterialHistoryDetail(records[0]);
            	
             }//  end detail button click
    	 }
     });//  end control
		
     function validateProopeProduceReceiveMaterialCreateStore(pprmcStore){
    	 if(pprmcStore.getCount() == 0){
    		 Ext.Msg.alert("提示", "未选择物料。");
    		 return true;
    	 }
    	 
    	 var wlmcStr;
    	 if(pprmcStore.getCount() == 1){
    		 wlmcStr = pprmcStore.getAt(0).get("wlmc");
    		 if(checkBlankChar(wlmcStr)){
    			 Ext.Msg.alert("提示", "请选择物料。");
    			 return true;
    		 }
    		 if(pprmcStore.getAt(0).get("llsl") <= 0){
    			 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"领料数量不可为0。");
    			 return true;
    		 }
    		 return false;
    	 }
    	 
    	 for(var i = 0; i < pprmcStore.getCount() -1; i ++){
    		 wlmcStr = pprmcStore.getAt(i).get("wlmc");
    		 if(checkBlankChar(wlmcStr)){
    			 Ext.Msg.alert("提示", "请选择物料。");
    			 return true;
    		 }
    		 if(pprmcStore.getAt(i).get("llsl") <= 0){
    			 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"领料数量不可为0。");
    			 return true;
    		 }
    		 for(var j = i + 1; j < pprmcStore.getCount(); j ++){
    			 if(wlmcStr.split('_')[1] == pprmcStore.getAt(j).get("wlmc").split('_')[1]){
    				 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"重复选择。");
        			 return true;
    			 }
    		 }
		 }
    	 
    	 wlmcStr = pprmcStore.getAt(pprmcStore.getCount()-1).get("wlmc");
    	 if(checkBlankChar(wlmcStr)){
			 Ext.Msg.alert("提示", "请选择物料。");
			 return true;
		 }
    	 if(pprmcStore.getAt(pprmcStore.getCount()-1).get("llsl") <= 0){
			 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"领料数量不可为0。");
			 return true;
		 }
    	 return false;
     }
    },//   end init
    views : [
        "ERP.proope.produce.view.receiveMaterialHistoryLayout",
        "ERP.proope.produce.view.receiveMaterialHistoryGrid"
    ],
    stores : ["ERP.proope.produce.store.receiveMaterialHistoryStore"],
    models : ["ERP.proope.produce.model.receiveMaterialHistoryModel"]
});