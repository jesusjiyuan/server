/**
 * 产品－BOM表管理控制器
 */
Ext.define("ERP.proope.business.controller.businessProductForBomController", {
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
     
     Ext.define('proopeBusinessBomDetailModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'wlmc', type: 'string'},
            {name: 'sl', type: 'float'}
        ]
     });
     
     //  查询取物料
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
	 
	 var proopeBusinessBomDetailEditStore;  //  修改Store，全局变量
	 var proopeBusinessProductForBomEditRecords;
     //控制响应
     self.control({
     	"proopebusinessproductforbomgrid button[id=proopeBusinessProductForBomEditTbarButton]" : {
            click : function(btn) {
            	//  修改选中产品的BOM表
            	proopeBusinessProductForBomEditRecords = btn.up("proopebusinessproductforbomgrid").getSelectionModel().getSelection();
            	if(proopeBusinessProductForBomEditRecords.length == 0){
               		Ext.Msg.alert("提示", "未选中产品记录。");
               		return;
               	}
            	//var productId = records[0].get('dm');
               	//alert(productId);
               	//  出现弹窗的响应，先判断弹窗对象是否存在，如存在，显示即可
               	if(Ext.getCmp('proopeBusinessBomEditWindow')){
            		Ext.getCmp('proopeBusinessBomEditWindow').show();
               		findBomDetail(proopeBusinessProductForBomEditRecords[0]);  //  显示弹窗后，查询出数据显示在弹窗中
            		return;
            	}
            	
            	function findBomDetail(record){
            		Ext.getCmp('proopeBusinessBomProductLabel').setText("产品："+record.get('cpmc'), true);
       				//  查询出BOM表明细
       				proopeBusinessBomDetailEditStore.removeAll();
       				ajaxQueryAll("/proopeBusiness/findBomListByProductId.do", {"productId":record.get('dm')}, 
            			function(bomDetailList){
       						for (var i = 0; i < bomDetailList.length; i++) {
            			        var editR = Ext.create('proopeBusinessBomDetailModel', {
				                    wlmc: bomDetailList[i].wlmc+'_'+bomDetailList[i].wldm,
				                    sl: bomDetailList[i].sl
				                });
				                proopeBusinessBomDetailEditStore.insert(0, editR);
				            }
            			});
            	}
            	
            	// 全局变量store
			    proopeBusinessBomDetailEditStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeBusinessBomDetailModel'
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
			        store: proopeBusinessBomDetailEditStore,
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
				            header: '数量',
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
			        selModel: selEditModel,
			        dockedItems: [{
			            xtype: 'toolbar',
			            items: [{
				            text: '添加',
				            itemId: 'addEditButton',
				            handler : function(){
				            	var editR = Ext.create('proopeBusinessBomDetailModel', {
				                    wlmc: '',
				                    sl: 1
				                });
				                proopeBusinessBomDetailEditStore.insert(0, editR);
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
			                    		proopeBusinessBomDetailEditStore.remove(gridSel);
			                		}
			                	});
			                }
			            }]
			        }],
			        width: 450,
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
	                   forId: "proopeBusinessBomProductLabel",
	                   id: "proopeBusinessBomProductLabel",
	                   text: "产品："
			        },
			        gridEditForm
                    ],

                    buttons: [{
                        text: '保存',
                        handler: function(){
                        	if(validateProopeBusinessBomDetailEditStore(proopeBusinessBomDetailEditStore)){return;}
                        	
                        	var param = strToJson('{"productId":"'+proopeBusinessProductForBomEditRecords[0].get('dm')+'"}');
							var wlmcStr;
							for(var i = 0; i < proopeBusinessBomDetailEditStore.getCount(); i ++){
								wlmcStr = proopeBusinessBomDetailEditStore.getAt(i).get("wlmc");
								param["bomEntityList["+i+"].wldm"]=wlmcStr.split('_')[1];
								param["bomEntityList["+i+"].sl"]=proopeBusinessBomDetailEditStore.getAt(i).get("sl");
							}
							ajaxOpera("/proopeBusiness/editBomListByProductId.do", param, function(){
								Ext.Msg.alert("提示", "修改BOM表成功。", function(){
									proopeBusinessBomDetailEditStore.removeAll();
									Ext.getCmp('proopeBusinessBomEditWindow').close();
								});
							});
                        }
                    },' ',{
                        text:'关闭',
                        handler: function(){
                            Ext.getCmp('proopeBusinessBomEditWindow').close();
                        }
                    }]
                });
                showPupWindow(editWindow, "Bom明细",550,380,'proopeBusinessBomEditWindow');
                findBomDetail(proopeBusinessProductForBomEditRecords[0]);  //  弹窗创建结束，查询出数据显示在弹窗中
            }
         }
     });//  end control
     
     function validateProopeBusinessBomDetailEditStore(pbbdeStore){
    	 if(pbbdeStore.getCount() == 0){
    		 Ext.Msg.alert("提示", "未选择物料。");
    		 return true;
    	 }
    	 
    	 var wlmcStr;
    	 if(pbbdeStore.getCount() == 1){
    		 wlmcStr = pbbdeStore.getAt(0).get("wlmc");
    		 if(checkBlankChar(wlmcStr)){
    			 Ext.Msg.alert("提示", "请选择物料。");
    			 return true;
    		 }
    		 if(pbbdeStore.getAt(0).get("sl") <= 0){
    			 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"数量不可为0。");
    			 return true;
    		 }
    		 return false;
    	 }
    	 
    	 for(var i = 0; i < pbbdeStore.getCount() -1; i ++){
    		 wlmcStr = pbbdeStore.getAt(i).get("wlmc");
    		 if(checkBlankChar(wlmcStr)){
    			 Ext.Msg.alert("提示", "请选择物料。");
    			 return true;
    		 }
    		 //  验证申请数量
    		 if(pbbdeStore.getAt(i).get("sl") <= 0){
    			 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"数量不可为0。");
    			 return true;
    		 }
    		 for(var j = i + 1; j < pbbdeStore.getCount(); j ++){
    			 if(wlmcStr.split('_')[1] == pbbdeStore.getAt(j).get("wlmc").split('_')[1]){
    				 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"重复选择。");
        			 return true;
    			 }
    		 }
		 }
    	 
    	 wlmcStr = pbbdeStore.getAt(pbbdeStore.getCount()-1).get("wlmc");
    	 if(checkBlankChar(wlmcStr)){
			 Ext.Msg.alert("提示", "请选择物料。");
			 return true;
		 }
    	 if(pbbdeStore.getAt(pbbdeStore.getCount()-1).get("sl") <= 0){
			 Ext.Msg.alert("提示", wlmcStr.split('_')[0]+"数量不可为0。");
			 return true;
		 }
    	 return false;
     }
    },//  end init
    views : [
        "ERP.proope.business.view.businessProductForBomLayout",
        "ERP.proope.business.view.businessProductForBomGrid"
    ],
    stores : ["ERP.proope.business.store.businessProductForBomStore"],
    models : ["ERP.proope.business.model.businessProductForBomModel"]
});