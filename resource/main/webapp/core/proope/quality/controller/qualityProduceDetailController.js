/**
 * 产品入库待检明细控制器
 */
Ext.define("ERP.proope.quality.controller.qualityProduceDetailController", {
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
     
     Ext.define('proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowModel', {
         extend: 'Ext.data.Model',
         fields: [
             {name:'cprksqdm', type: 'string'},
             {name:'rksqmxdm', type: 'string'},
             {name:"cpdm",type:"string"},
             {name:"cpmc",type:"string"},
             {name:"sqsl",type:"float"},
             {name:"yrksl",type:"float"},
             {name:"rksl",type:"string"},
             {name:"bz",type:"string"}
         ]
      });
     
     var proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore;  //全局store
     
     //控制响应
     self.control({
    	 "proopequalityqualityproducedetailgrid button[id=proopeQualityProducePutWharehouseTbarButton]" : {
             click : function(btn) {
            	var records = btn.up("proopequalityqualityproducedetailgrid").getSelectionModel().getSelection();
            	if(records.length == 0){
            		Ext.Msg.alert("提示", "未选中入库待检记录。");
            		return;
            	}
            	
            	if(Ext.getCmp('proopeQualityProductPutWharehouseApplyCreatePutWarehouseWindow')){
            		Ext.getCmp('proopeQualityProductPutWharehouseApplyCreatePutWarehouseWindow').show();
            		showPorducePutWharehouseApplyDetailCreatePutWarehouse(records);
            		return;
            	}
            	
            	function showPorducePutWharehouseApplyDetailCreatePutWarehouse(recordList){
            		proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.removeAll();
               		for(var i = 0; i < recordList.length; i ++){
               			var r = Ext.create('proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowModel', {
               				cprksqdm: recordList[i].get('cprksqdm'),
               				rksqmxdm: recordList[i].get('dm'),
               				cpdm: recordList[i].get('cpdm'),
               				cpmc: recordList[i].get('cpmc'),
               				sqsl: recordList[i].get('sqsl'),
               				yrksl: recordList[i].get('yrksl'),
               				rksl: 1,
		                    bz: ''
		                });
               			proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.insert(0, r);
               		}
            	}
                
            	// 创建订单质检入库store
            	proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowModel'
			    });
            	
            	var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			        clicksToEdit: 1
			    });
			    
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
				 
				 var gridForm = Ext.create('Ext.grid.Panel', {
				        store: proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore,
				        columns: [
				            {header: '入库申请代码',dataIndex: 'cprksqdm', hidden:true},
				        	{header: '入库申请明细代码',dataIndex: 'rksqmxdm', hidden:true},
					        {header: '产品代码',dataIndex: 'cpdm', hidden:true},
					        {header: '产品名称',dataIndex: 'cpmc',width:220},
					        {header: '申请数量',dataIndex: 'sqsl',width:80},
					        {header: '已入库数量',dataIndex: 'yrksl',width:80},
					        {
					            header: '入库数量',
					            dataIndex: 'rksl',
					            width: 70,
					            align: 'right',
					            editor: {
					                xtype: 'numberfield',
					                allowBlank: false,
					                minValue: 0
					            }
					        },
					        {
					            header: '备注',
					            dataIndex: 'bz',
					            width: 80,
					            align: 'right',
					            editor: {
					                allowBlank: false
					            }
					        }
				        ],
				        columnLines: true,
				        width: 550,
				        height: 240,
				        frame: true,
				        plugins: [cellEditing],
				        iconCls: 'icon-grid'
				    });
				 
				 var proopeQualityProducePutWarehouseApplylPutWarehouseDepartmentComboboxStore = departmentComboboxFieldCreate();
                 var createProducePutWarehouseApplyDetailPutWarehouseWindow = Ext.create('Ext.form.Panel', {
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
					            name: 'proopeQualityProducePutWarehouseApplylPutWarehouseDepartmentCombobox',
					            id: 'proopeQualityProducePutWarehouseApplylPutWarehouseDepartmentCombobox',
					            fieldLabel: '入库部门',
					            labelWidth: 90,
					            width: 350,
					            store: proopeQualityProducePutWarehouseApplylPutWarehouseDepartmentComboboxStore,
					            valueField: 'dm',
					            displayField: 'bmmc',
					            typeAhead: true,
					            allowBlank: true,
					            forceSelection: true
					        },
					        gridForm
                     ],
                     buttons: [{
                         text: '入库',
                         handler: function(){
                             var rkbmdm = Ext.getCmp("proopeQualityProducePutWarehouseApplylPutWarehouseDepartmentCombobox").getValue();
                            
                             if(checkBlankChar(rkbmdm)){
                            	 Ext.Msg.alert("提示", "未选择入库部门。");
                            	 return;
                             }
                             if(validateProopeQualityProductPutWarehouseAyylyDetailPutWarehouseStore(proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore)){return;}
                             
                             var rkbmmc = '';
                             proopeQualityProducePutWarehouseApplylPutWarehouseDepartmentComboboxStore.each(function(r){
                                if(r.data['dm'] == rkbmdm){
                                	rkbmmc = r.data['bmmc'];
                                }
                            });
                             
                             var param = strToJson('{"productPutWarehouseHistoryEntity.bmdm":"'+rkbmdm+'"}');
                             param["productPutWarehouseHistoryEntity.bmmc"]=rkbmmc;
							for(var i = 0; i < proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.getCount(); i ++){
								param["productPutWarehouseDetailHistoryResultList["+i+"].rksqlldm"]=proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.getAt(i).get("cprksqdm");
								param["productPutWarehouseDetailHistoryResultList["+i+"].rksqmxlldm"]=proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.getAt(i).get("rksqmxdm");
								param["productPutWarehouseDetailHistoryResultList["+i+"].cpdm"]=proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.getAt(i).get("cpdm");
								param["productPutWarehouseDetailHistoryResultList["+i+"].cpmc"]=proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.getAt(i).get("cpmc");
								param["productPutWarehouseDetailHistoryResultList["+i+"].sqsl"]=proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.getAt(i).get("sqsl");
								param["productPutWarehouseDetailHistoryResultList["+i+"].yrksl"]=proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.getAt(i).get("yrksl");
								param["productPutWarehouseDetailHistoryResultList["+i+"].sl"]=proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.getAt(i).get("rksl");
								param["productPutWarehouseDetailHistoryResultList["+i+"].bz"]=proopeQualityProductPutWarehouseAyylyDetailPutWarehouseWindowStore.getAt(i).get("bz");
							}
							
							ajaxOpera("/proopeQuality/createProductPutWarehouseHistory.do", param, function(){
								Ext.Msg.alert("提示", "产品入库成功。", function(){
									Ext.getCmp('proopeQualityProductPutWharehouseApplyCreatePutWarehouseWindow').close();
									btn.up("proopequalityqualityproducedetailgrid").getStore().load();
								});
							});
                         }
                     },' ',{
                         text:'关闭',
                         handler: function(){
                             Ext.getCmp('proopeQualityProductPutWharehouseApplyCreatePutWarehouseWindow').close();
                         }
                     }]
                 });
                 showPupWindow(createProducePutWarehouseApplyDetailPutWarehouseWindow, "产品入库质检",600,380,'proopeQualityProductPutWharehouseApplyCreatePutWarehouseWindow');
                 showPorducePutWharehouseApplyDetailCreatePutWarehouse(records);
             }
    	 }
     });//  end control
     
     function validateProopeQualityProductPutWarehouseAyylyDetailPutWarehouseStore(pqppwadpwStore){
    	 for(var i = 0; i < pqppwadpwStore.getCount(); i ++){
				if(pqppwadpwStore.getAt(i).get("rksl") <= 0){
					Ext.Msg.alert("提示", pqppwadpwStore.getAt(i).get("cpmc")+"入库数量不可为0。");
					return true;
				}
				
				if(!checkBlankChar(pqppwadpwStore.getAt(i).get("bz"))){
					if(checkStrLength(pqppwadpwStore.getAt(i).get("bz"), 0, 64)){
						Ext.Msg.alert("提示", pqppwadpwStore.getAt(i).get("cpmc")+"备注长度不可大于64位。");
						return true;
					}
					if(checkIllegaeChar(pqppwadpwStore.getAt(i).get("bz"))){
						Ext.Msg.alert("提示", pqppwadpwStore.getAt(i).get("cpmc")+"备注中不可带有字符"+validateIllegagChar+"。");
						return true;
					}
				}
			}
    	 return false;
     }
    },//  end init
    views : [
        "ERP.proope.quality.view.qualityProduceDetailLayout",
        "ERP.proope.quality.view.qualityProduceDetailGrid"
    ],
    stores : ["ERP.proope.quality.store.qualityProduceDetailStore"],
    models : ["ERP.proope.quality.model.qualityProduceDetailModel"]
});