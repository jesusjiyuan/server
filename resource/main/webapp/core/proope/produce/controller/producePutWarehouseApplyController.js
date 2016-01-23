/**
 * 产品入库申请
 */
Ext.define("ERP.proope.produce.controller.producePutWarehouseApplyController", {
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
     
     Ext.define('proopeProducePutWarehouseApplyCreateWindowModel', {
         extend: 'Ext.data.Model',
         fields: [
             {name: 'cpmc', type: 'string'},
             {name: 'sl', type: 'float'}
         ]
      });
     
     //  查询取得产品，用于产品入库申请
	 function findProductStore(){
	 	var productArray = new Array();
	 	var itemData;
	    ajaxQueryAll("/proopeBasedata/findAllProduct.do", {},
	        function (productList) {
	            for (var i = 0; i < productList.length; i++) {
	                itemData = new Array();
	                itemData.push(productList[i].cpmc+'_'+productList[i].dm);
	                itemData.push(productList[i].cpmc);
	                productArray.push(itemData);
	            }
	        });
	 	return productArray;
	 }
     
	 Ext.define('proopeProducePutWarehouseApplyDetailWindowModel', {
         extend: 'Ext.data.Model',
         fields: [
			 {name: 'dm', type: 'string'},
			 {name: 'cprksqdm', type: 'string'},
             {name: 'cpdm', type: 'string'},
             {name: 'cpmc', type: 'string'},
             {name: 'sqsl', type: 'float'},
             {name: 'yrksl', type: 'float'},
             {name: 'cjrqYMDHMS', type: 'float'}
         ]
      });
	 
	 var proopeProducePutWarehouseApplyDetailWindowStore;

     //控制响应
     self.control({
    	 "proopeproduceproduceputwarehouseapplygrid button[id=proopeProductProducePutWarehouseApplyAddTbarButton]" : {
             click : function(btn) {
            	 if(Ext.getCmp('proopeProduceProducePutWharehouseApplyCreateWindow')){
             		Ext.getCmp('proopeProduceProducePutWharehouseApplyCreateWindow').show();
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
 			    var proopeProduceProducePutWharehouseApplyCreateWindowStore = Ext.create('Ext.data.Store', {
 			        autoDestroy: true,
 			        model: 'proopeProducePutWarehouseApplyCreateWindowModel'
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
 			        store: proopeProduceProducePutWharehouseApplyCreateWindowStore,
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
 				            header: '入库申请数量',
 				            dataIndex: 'sl',
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
 				                var addR = Ext.create('proopeProducePutWarehouseApplyCreateWindowModel', {
 				                    cpmc: '',
 				                    sl: 1
 				                });
 				               proopeProduceProducePutWharehouseApplyCreateWindowStore.insert(0, addR);
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
 			                			proopeProduceProducePutWharehouseApplyCreateWindowStore.remove(gridSel);
 			                		}
 			                	});
 			                }
 			            }]
 			        }],
 			        width: 450,
 			        height: 245,
 			        frame: true,
 			        plugins: [cellAddEditing],
 			        iconCls: 'icon-grid'
 			    });
 				 
 			   var producePutWharehouseApplyCreateDepartmentStore = departmentComboboxFieldCreate();
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
			            name: 'proopeProduceProducePutWharehouseApplyCreateDepartmentCombobox',
			            id: 'proopeProduceProducePutWharehouseApplyCreateDepartmentCombobox',
			            fieldLabel: '申请部门',
			            labelWidth: 90,
			            width: 350,
			            store: producePutWharehouseApplyCreateDepartmentStore,
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
                       	var bmdm = Ext.getCmp("proopeProduceProducePutWharehouseApplyCreateDepartmentCombobox").getValue();
                       	
                       	if(checkBlankChar(bmdm)){
                    		Ext.Msg.alert("提示", "未选择申请部门。");
                    		return;
                    	}
                       	if(validateProopeProduceProducePutWharehouseApplyStore(proopeProduceProducePutWharehouseApplyCreateWindowStore)){return;}
                       	
                       	var bmmc = '';
                       	producePutWharehouseApplyCreateDepartmentStore.each(function(r){
                           if(r.data['dm'] == bmdm){
                           	bmmc = r.data['bmmc'];
                           }
                       });
                       	//alert(bmdm + "|" + bmmc);
                           var param = strToJson('{"productPutWarehouseApplyHistoryEntity.bmdm":"'+bmdm+'"}');
                           param["productPutWarehouseApplyHistoryEntity.bmmc"]=bmmc;
                           
							var cp;
							for(var i = 0; i < proopeProduceProducePutWharehouseApplyCreateWindowStore.getCount(); i ++){
								cp = proopeProduceProducePutWharehouseApplyCreateWindowStore.getAt(i).get("cpmc").split('_');
								param["productPutWarehouseApplyDetailHistoryEntityList["+i+"].cpdm"]=cp[1];
								param["productPutWarehouseApplyDetailHistoryEntityList["+i+"].cpmc"]=cp[0];
								param["productPutWarehouseApplyDetailHistoryEntityList["+i+"].sqsl"]=proopeProduceProducePutWharehouseApplyCreateWindowStore.getAt(i).get("sl");
							}
							
							ajaxOpera("/proopeProduce/createProductPutWharehouseApply.do", param, function(){
								Ext.Msg.alert("提示", "产品入库申请创建成功。", function(){
									Ext.getCmp('proopeProduceProducePutWharehouseApplyCreateWindow').close();
									btn.up("proopeproduceproduceputwarehouseapplygrid").getStore().load();
								});
							});
                       }
                   },' ',{
                       text:'关闭',
                       handler: function(){
                           Ext.getCmp('proopeProduceProducePutWharehouseApplyCreateWindow').close();
                       }
                   }]
               });
               showPupWindow(addWindow, "创建入库申请",550,380,'proopeProduceProducePutWharehouseApplyCreateWindow');
 				 
             }
    	 }, "proopeproduceproduceputwarehouseapplygrid button[id=proopeProductProducePutWarehouseApplyDetailTbarButton]" : {
             click : function(btn) {
            	var records = btn.up("proopeproduceproduceputwarehouseapplygrid").getSelectionModel().getSelection();
             	if(records.length == 0){
             		Ext.Msg.alert("提示", "未选中记录。");
             		return;
             	}
            	
            	if(Ext.getCmp('proopeProduceProducePutWarehouseApplyDetailWindow')){
            		Ext.getCmp('proopeProduceProducePutWarehouseApplyDetailWindow').show();
            		findProducePutWarehouseApplyDetail(records[0]);
            		return;
            	}
            	
            	proopeProducePutWarehouseApplyDetailWindowStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeProducePutWarehouseApplyDetailWindowModel'
			    });
            	
            	function findProducePutWarehouseApplyDetail(record){
            		Ext.getCmp('proopeProduceProducePutWarehouseApplyDetailBmmcLabel').setText("部门名称："+
                   			record.get('bmmc') + "    创建日期：" + record.get('cjrqYMDHMS'), true);
            		proopeProducePutWarehouseApplyDetailWindowStore.removeAll();
              			ajaxQueryAll("/proopeProduce/findProductPutWarehouseApplyDetailResultListByPpwaId.do", {"productPutWarehouseApplyId":record.get('dm')}, 
                			function(productPutWarehouseApplyDetailResultList){
                				for (var i = 0; i < productPutWarehouseApplyDetailResultList.length; i++) {
                			        var r = Ext.create('proopeProducePutWarehouseApplyDetailWindowModel', {
                			        	dm: productPutWarehouseApplyDetailResultList[i].dm,
                			        	cprksqdm: productPutWarehouseApplyDetailResultList[i].cprksqdm,
                			        	cpdm: productPutWarehouseApplyDetailResultList[i].cpdm,
                			        	cpmc: productPutWarehouseApplyDetailResultList[i].cpmc,
                			        	sqsl: productPutWarehouseApplyDetailResultList[i].sqsl,
                			        	yrksl: productPutWarehouseApplyDetailResultList[i].yrksl,
                			        	cjrqYMDHMS: productPutWarehouseApplyDetailResultList[i].cjrqYMDHMS
    				                });
                			        proopeProducePutWarehouseApplyDetailWindowStore.insert(0, r);
    				            }
                			});
              			
            	}
            	
            	var detailGridForm = Ext.create('Ext.grid.Panel', {
			        store: proopeProducePutWarehouseApplyDetailWindowStore,
			        columns: [
				        {header: '代码',dataIndex: 'dm',hidden:true},
				        {header: '产品入库申请代码',dataIndex: 'cprksqdm',hidden:true},
			        	{header: '产品代码',dataIndex: 'cpdm',hidden:true}, 
				        {header: '产品名称',dataIndex: 'cpmc',width:280},
				        {header: '申请数量',dataIndex: 'sqsl',width:70},
				        {header: '已入库数量',dataIndex: 'yrksl',width:70},
				        {header: '创建日期',dataIndex: 'cjrqYMDHMS',width:120}
			        ],
			        columnLines: true,
			        width: 550,
			        height: 245,
			        frame: true,
			        iconCls: 'icon-grid'
			    });
            	
            	var detailWindow = Ext.create('Ext.form.Panel', {
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
		                    id: "proopeProduceProducePutWarehouseApplyDetailBmmcLabel",
		                    text: "部门名称："
				        },
				        detailGridForm
                    ],

                    buttons: [{
                        text:'关闭',
                        handler: function(){
                            Ext.getCmp('proopeProduceProducePutWarehouseApplyDetailWindow').close();
                        }
                    }]
                });
                showPupWindow(detailWindow, "入库申请明细",600,380,'proopeProduceProducePutWarehouseApplyDetailWindow');
                findProducePutWarehouseApplyDetail(records[0]);
            	
             }
    	 }
     });//  end control
		
     function validateProopeProduceProducePutWharehouseApplyStore(ppppwaStore){
    	 if(ppppwaStore.getCount() == 0){
    		 Ext.Msg.alert("提示", "未选择入库产品。");
    		 return true;
    	 }
    	 
    	 var cpmcStr;
    	 if(ppppwaStore.getCount() == 1){
    		 cpmcStr = ppppwaStore.getAt(0).get("cpmc");
    		 if(checkBlankChar(cpmcStr)){
    			 Ext.Msg.alert("提示", "请选择物料。");
    			 return true;
    		 }
    		 if(ppppwaStore.getAt(0).get("sl") <= 0){
    			 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"数量不可为0。");
    			 return true;
    		 }
    		 return false;
    	 }
    	 
    	 for(var i = 0; i < ppppwaStore.getCount() -1; i ++){
    		 cpmcStr = ppppwaStore.getAt(i).get("cpmc");
    		 if(checkBlankChar(cpmcStr)){
    			 Ext.Msg.alert("提示", "请选择产品。");
    			 return true;
    		 }
    		 if(ppppwaStore.getAt(i).get("sl") <= 0){
    			 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"数量不可为0。");
    			 return true;
    		 }
    		 for(var j = i + 1; j < ppppwaStore.getCount(); j ++){
    			 if(cpmcStr.split('_')[1] == ppppwaStore.getAt(j).get("cpmc").split('_')[1]){
    				 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"重复选择。");
        			 return true;
    			 }
    		 }
		 }
    	 
    	 cpmcStr = ppppwaStore.getAt(ppppwaStore.getCount()-1).get("cpmc");
    	 if(checkBlankChar(cpmcStr)){
			 Ext.Msg.alert("提示", "请选择产品。");
			 return true;
		 }
    	 if(ppppwaStore.getAt(ppppwaStore.getCount()-1).get("sl") <= 0){
			 Ext.Msg.alert("提示", cpmcStr.split('_')[0]+"数量不可为0。");
			 return true;
		 }
    	 return false;
     }
    },//   end init
    views : [
        "ERP.proope.produce.view.producePutWarehouseApplyLayout",
        "ERP.proope.produce.view.producePutWarehouseApplyGrid"
    ],
    stores : ["ERP.proope.produce.store.producePutWarehouseApplyStore"],
    models : ["ERP.proope.produce.model.producePutWarehouseApplyModel"]
});