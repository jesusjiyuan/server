/**
 * 采购到货入库待检明细控制器
 */
Ext.define("ERP.proope.quality.controller.qualityPurchaseOrderArrivalGoodDetailController", {
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
     
     Ext.define('proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name:'dhmxdm', type: 'string'},
            {name:"dhdm",type:"string"},
            {name:"cgsqmxdm",type:"string"},
            {name:"cgddmxdm",type:"string"},
            {name:"wldm",type:"string"},
            {name:"wlmc",type:"string"},
            {name:"sqsl",type:"string"},
            {name:"dgsl",type:"string"},
            {name:"dhsl",type:"string"},
            {name:"yrksl",type:"string"},
            {name:"rksl",type:"float"},
            {name:"jyjg",type:"string"},
            {name:"bz",type:"string"},
            {name:"cjrqYMDHMS",type:"string"},
            {name:"cjr",type:"string"},
            {name:"gysmc",type:"string"}
        ]
     });
     
     var proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore;  //全局store
     
     //控制响应
     self.control({
     	"proopequalitypurchaseorderarrvalgooddetailgrid button[id=prropeQualityPurchaseOrderArrivalGoodDetailCpwTbarButton]" : {
           click : function(btn) {
               var records = btn.up("proopequalitypurchaseorderarrvalgooddetailgrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中待检记录。");
               		return;
               	}
               	
               	if(Ext.getCmp('proopeQualityArrivalGoodCreatePutWarehouseWindow')){
            		Ext.getCmp('proopeQualityArrivalGoodCreatePutWarehouseWindow').show();
            		showArrivalGoodDetailCreatePutWarehouse(records);
            		return;
            	}
            	
            	function showArrivalGoodDetailCreatePutWarehouse(recordList){
            		proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.removeAll();
               		for(var i = 0; i < recordList.length; i ++){
               			var r = Ext.create('proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowModel', {
               				dhmxdm: recordList[i].get('dm'),
               				dhdm: recordList[i].get('dhdm'),
               				cgsqmxdm: recordList[i].get('cgsqmxdm'),
               				cgddmxdm: recordList[i].get('cgddmxdm'),
               				wldm: recordList[i].get('wldm'),
		                    wlmc: recordList[i].get('wlmc'),
		                    sqsl: recordList[i].get('sqsl'),
		                    dgsl: recordList[i].get('dgsl'),
		                    dhsl: recordList[i].get('dhsl'),
		                    yrksl: recordList[i].get('yrksl'),
		                    rksl: recordList[i].get('dhsl'),
		                    jyjg: '合格',
		                    bz: '',
		                    cjrqYMDHMS: recordList[i].get('cjrqYMDHMS'),
		                    cjr: recordList[i].get('cjr'),
		                    gysmc: recordList[i].get('gysmc')
		                });
               			proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.insert(0, r);
               		}
            	}
            	
            	// 创建订单质检入库store
            	proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowModel'
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
				        store: proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore,
				        columns: [
				        	{header: '到货明细代码',dataIndex: 'dhmxdm',hidden:true},
				        	{header: '到货代码',dataIndex: 'dhdm',hidden:true},
				        	{header: '采购申请明细代码',dataIndex: 'cgsqmxdm',hidden:true},
				        	{header: '采购订单明细代码',dataIndex: 'cgddmxdm',hidden:true},
					        {header: '物料代码',dataIndex: 'wldm',hidden:true},
					        {header: '物料名称',dataIndex: 'wlmc',width:200},
					        {header: '申请数量',dataIndex: 'sqsl',width:60},
					        {header: '订购数量',dataIndex: 'dgsl',width:60},
					        {header: '到货数量',dataIndex: 'dhsl',width:60},
					        {header: '已入库数量',dataIndex: 'yrksl',width:70},
					        {
					            header: '入库数量',
					            dataIndex: 'rksl',
					            width: 60,
					            align: 'right',
					            editor: {
					                xtype: 'numberfield',
					                allowBlank: false,
					                minValue: 0
					            }
					        },
					        {
					            header: '检验结果',
					            dataIndex: 'jyjg',
					            width: 60,
					            align: 'right',
					            editor: {
					                xtype: 'combobox',
					                typeAhead: true,
					                triggerAction: 'all',
					                selectOnTab: true,
					                store: [
					                    ['合格','合格'],
					                    ['不合格','不合格']
					                ],
					                lazyRender: true,
					                listClass: 'x-combo-list-small'
					            }
					        },
					        {
					            header: '备注',
					            dataIndex: 'bz',
					            width: 70,
					            align: 'right',
					            editor: {
					                allowBlank: false
					            }
					        },
					        {header: '创建日期',dataIndex: 'cjrqYMDHMS',hidden:true},
					        {header: '创建人',dataIndex: 'cjr',hidden:true},
					        {header: '供应商名称',dataIndex: 'gysmc',width:140}
				        ],
				        columnLines: true,
				        width: 800,
				        height: 245,
				        frame: true,
				        plugins: [cellEditing],
				        iconCls: 'icon-grid'
				    });
                	
                	var proopeQualityOrderDetailPutWarehouseDepartmentComboboxStore = departmentComboboxFieldCreate();
                    var createArrivalGoodDetailPutWarehouseWindow = Ext.create('Ext.form.Panel', {
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
					            name: 'proopeQualityArrivalGoodDetailPutWarehouseDepartmentCombobox',
					            id: 'proopeQualityArrivalGoodDetailPutWarehouseDepartmentCombobox',
					            fieldLabel: '入库部门',
					            labelWidth: 90,
					            width: 350,
					            store: proopeQualityOrderDetailPutWarehouseDepartmentComboboxStore,
					            valueField: 'dm',
					            displayField: 'bmmc',
					            typeAhead: true,
					            allowBlank: true,
					            forceSelection: true
					        },
					        { fieldLabel: "入库类别",id: "proopeQualityProducePutWarehouseApplyRklbFiled"},
					        { fieldLabel: "生产车间",id: "proopeQualityProducePutWarehouseApplySccjFiled"},
					        {
	                            xtype: "textarea", fieldLabel: "备注",
	                            id: "proopeQualityProducePutWarehouseApplyBzTextarea", width: 230 },
					        gridForm
                        ],
                        buttons: [{
                            text: '入库',
                            handler: function(){
                                var rkbmdm = Ext.getCmp("proopeQualityArrivalGoodDetailPutWarehouseDepartmentCombobox").getValue();
                                var rklb = Ext.getCmp("proopeQualityProducePutWarehouseApplyRklbFiled").getValue();
                                var sccj = Ext.getCmp("proopeQualityProducePutWarehouseApplySccjFiled").getValue();
                                var bz = Ext.getCmp("proopeQualityProducePutWarehouseApplyBzTextarea").getValue();
                                
                                var rkbmmc = '';
                                proopeQualityOrderDetailPutWarehouseDepartmentComboboxStore.each(function(r){
                                    if(r.data['dm'] == rkbmdm){
                                    	rkbmmc = r.data['bmmc'];
                                    }
                                });
                                
                                if(checkBlankChar(rkbmdm)){
                               	 Ext.Msg.alert("提示", "未选择入库部门。");
                               	 return;
                                }
                                if(validateProopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseStore(proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore)){return;}
                                
                                var param = strToJson('{"putWarehouseHistoryEntity.rkbmdm":"'+rkbmdm+'"}');
                                param["putWarehouseHistoryEntity.rkbmmc"]=rkbmmc;
                                param["putWarehouseHistoryEntity.rklb"]=rklb;
                                param["putWarehouseHistoryEntity.sccj"]=sccj;
                                param["putWarehouseHistoryEntity.bz"]=bz;
								for(var i = 0; i < proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getCount(); i ++){
									param["putWarehouseDetailHistoryResultList["+i+"].cgsqmxlsdm"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("cgsqmxdm");
									param["putWarehouseDetailHistoryResultList["+i+"].cgddmxlsdm"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("cgddmxdm");
									param["putWarehouseDetailHistoryResultList["+i+"].dhmxlsdm"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("dhmxdm");
									param["putWarehouseDetailHistoryResultList["+i+"].wldm"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("wldm");
									param["putWarehouseDetailHistoryResultList["+i+"].wlmc"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("wlmc");
									param["putWarehouseDetailHistoryResultList["+i+"].sqsl"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("sqsl");
									param["putWarehouseDetailHistoryResultList["+i+"].dgsl"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("dgsl");
									param["putWarehouseDetailHistoryResultList["+i+"].dhsl"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("dhsl");
									param["putWarehouseDetailHistoryResultList["+i+"].yrksl"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("yrksl");
									param["putWarehouseDetailHistoryResultList["+i+"].rksl"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("rksl");
									param["putWarehouseDetailHistoryResultList["+i+"].jyjg"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("jyjg");
									param["putWarehouseDetailHistoryResultList["+i+"].bz"]=proopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseWindowStore.getAt(i).get("bz");
								}
								
								ajaxOpera("/proopeQuality/createMaterialPutWarehouse.do", param, function(){
									Ext.Msg.alert("提示", "物料入库成功。", function(){
										Ext.getCmp('proopeQualityArrivalGoodCreatePutWarehouseWindow').close();
										btn.up("proopequalitypurchaseorderarrvalgooddetailgrid").getStore().load();
									});
								});
                            }
                        },' ',{
                            text:'关闭',
                            handler: function(){
                                Ext.getCmp('proopeQualityArrivalGoodCreatePutWarehouseWindow').close();
                            }
                        }]
                    });
                    showPupWindow(createArrivalGoodDetailPutWarehouseWindow, "采购订单入库质检",850,380,'proopeQualityArrivalGoodCreatePutWarehouseWindow');
                    showArrivalGoodDetailCreatePutWarehouse(records);
            }//  end create order button click
         }
     });  //  end control
     
     function validateProopeQualityPurchaseOrderArrivalGoodDetailPutWarehouseStore(pqpoagdpwStore){
    	 for(var i = 0; i < pqpoagdpwStore.getCount(); i ++){
				if(pqpoagdpwStore.getAt(i).get("rksl") <= 0){
					Ext.Msg.alert("提示", pqpoagdpwStore.getAt(i).get("wlmc")+"入库数量不可为0。");
					return true;
				}
				
				if(!checkBlankChar(pqpoagdpwStore.getAt(i).get("bz"))){
					if(checkStrLength(pqpoagdpwStore.getAt(i).get("bz"), 0, 64)){
						Ext.Msg.alert("提示", pqpoagdpwStore.getAt(i).get("wlmc")+"备注长度不可大于64位。");
						return true;
					}
					if(checkIllegaeChar(pqpoagdpwStore.getAt(i).get("bz"))){
						Ext.Msg.alert("提示", pqpoagdpwStore.getAt(i).get("wlmc")+"备注中不可带有字符"+validateIllegagChar+"。");
						return true;
					}
				}
			}
    	 return false;
     }
    },//  end init
    views : [
        "ERP.proope.quality.view.qualityPurchaseOrderArrivalGoodDetailLayout",
        "ERP.proope.quality.view.qualityPurchaseOrderArrivalGoodDetailGrid"
    ],
    stores : ["ERP.proope.quality.store.qualityPurchaseOrderArrivalGoodDetailStore"],
    models : ["ERP.proope.quality.model.qualityPurchaseOrderArrivalGoodDetailModel"]
});