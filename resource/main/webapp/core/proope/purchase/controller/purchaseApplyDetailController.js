/**
 * 采购申请明细控制器
 */
Ext.define("ERP.proope.purchase.controller.purchaseApplyDetailController", {
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
     
     Ext.define('purchaseApplyDetailCreateOrderModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'cgsqmxdm', type: 'string'},
            {name: 'wldm', type: 'string'},
            {name: 'wlmc', type: 'string'},
            {name: 'sqsl', type: 'float'},
            {name: 'ydgsl', type: 'float'},
            {name: 'dj', type: 'float'},
            {name: 'dgsl', type: 'float'},
            {name: 'bmmc', type: 'string'}
        ]
     });
     
     var supplierComboboxFields = [{name: 'dm'}, {name: 'gysmc'}, {name: 'lxdh'}];  //  供应商combobox对应的字段
     //  查询出供应商
	 function findSupplierFieldList(){
	 	var supplierArray = new Array();
	    var itemData;
	    ajaxQueryAll("/proopeBasedata/findAllSupplier.do", {},
	        function (supplierResultList) {
	            for (var i = 0; i < supplierResultList.length; i++) {
	                itemData = new Array();
	                itemData.push(supplierResultList[i].dm);
	                itemData.push(supplierResultList[i].gysmc);
	                itemData.push(supplierResultList[i].lxdh);
	                supplierArray.push(itemData);
	            }
	        });
	    return supplierArray;
	 }
	 //  生成部门下拉框中的数据
	 function supplierComboboxFieldCreate() {
	    var supplierComboboxStore = Ext.create('Ext.data.ArrayStore', {
	        fields: supplierComboboxFields,
	        data: findSupplierFieldList()
	    });
	    return supplierComboboxStore;
	 }
     
     var purchaseApplyDetailCreateOrderStore;  //全局创建订单store
     //控制响应
     self.control({
     	"proopepurchasepurchaseapplydetailgrid button[id=purchaseApplyDetailCreateOrderButton]" : {
            click : function(btn) {
            	var records = btn.up("proopepurchasepurchaseapplydetailgrid").getSelectionModel().getSelection();
               	if(records.length == 0){
               		Ext.Msg.alert("提示", "未选中待采购的物料记录。");
               		return;
               	}
               	
               	if(Ext.getCmp('proopePurchaseApplyDetailCreateOrderWindow')){
            		Ext.getCmp('proopePurchaseApplyDetailCreateOrderWindow').show();
            		showOrderDetail(records);
            		return;
            	}
               	
               	function showOrderDetail(recordList){
               		purchaseApplyDetailCreateOrderStore.removeAll();
               		for(var i = 0; i < recordList.length; i ++){
               			var r = Ext.create('purchaseApplyDetailCreateOrderModel', {
               				cgsqmxdm: recordList[i].get('dm'),
               				wldm: recordList[i].get('wldm'),
		                    wlmc: recordList[i].get('wlmc'),
		                    sqsl: recordList[i].get('sqsl'),
		                    ydgsl: recordList[i].get('ycgsl'),
		                    dj: 1,
		                    dgsl: recordList[i].get('sqsl'),
		                    bmmc: recordList[i].get('bmmc')
		                });
		                purchaseApplyDetailCreateOrderStore.insert(0, r);
               		}
               	}
               	
               	// 创建新增订单store
			    purchaseApplyDetailCreateOrderStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'purchaseApplyDetailCreateOrderModel'
			    });
			    
			    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			        clicksToEdit: 1
			    });
			    
			    var gridForm = Ext.create('Ext.grid.Panel', {
				        store: purchaseApplyDetailCreateOrderStore,
				        columns: [
				        	{header: '采购申请明细代码',dataIndex: 'cgsqmxdm',hidden:true},
					        {header: '物料代码',dataIndex: 'wldm',hidden:true},
					        {header: '物料名称',dataIndex: 'wlmc',width:150},
					        {header: '申请数量',dataIndex: 'sqsl',width: 70},
					        {header: '已订购数量',dataIndex: 'ydgsl',width: 70},
					        {
					            header: '订购数量',
					            dataIndex: 'dgsl',
					            width: 70,
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
					            width: 70,
					            align: 'right',
					            editor: {
					                xtype: 'numberfield',
					                allowBlank: false,
					                minValue: 0
					            }
					        },
					        {header: '部门名称',dataIndex: 'bmmc',width: 100}
				        ],
				        columnLines: true,
				        width: 500,
				        height: 200,
				        frame: true,
				        plugins: [cellEditing],
				        iconCls: 'icon-grid'
				    });
                	
                	var purchaseApplySupplierStore = supplierComboboxFieldCreate();
                    var createPurchaseOrderWindow = Ext.create('Ext.form.Panel', {
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
					            name: 'purchaseApplyCreateOrderSupplierCombobox',
					            id: 'purchaseApplyCreateOrderSupplierCombobox',
					            fieldLabel: '供应商',
					            labelWidth: 90,
					            width: 350,
					            store: purchaseApplySupplierStore,
					            valueField: 'dm',
					            displayField: 'gysmc',
					            typeAhead: true,
					            allowBlank: true,
					            forceSelection: true
					        },
					        { fieldLabel: "收货方",id: "purchaseApplyCreateOrderSupplierShfFiled"},
	                        { fieldLabel: "收货方联系人", id: "purchaseApplyCreateOrderSupplierShflxrFiled" },
	                        { fieldLabel: "收货方电话",id: "purchaseApplyCreateOrderSupplierShfdhFiled"},  
					        gridForm
                        ],
                        buttons: [{
                            text: '创建',
                            handler: function(){
                                var gysdm = Ext.getCmp("purchaseApplyCreateOrderSupplierCombobox").getValue();
                                var shf = Ext.getCmp("purchaseApplyCreateOrderSupplierShfFiled").getValue();
                                var shflxr = Ext.getCmp("purchaseApplyCreateOrderSupplierShflxrFiled").getValue();
                                var shfdh = Ext.getCmp("purchaseApplyCreateOrderSupplierShfdhFiled").getValue();
                                if(checkBlankChar(gysdm)){
                                	Ext.Msg.alert("提示", "未选择供应商。");
                       			 	return true;
                                }
                                
                                var gysmc = '';
                                var lxdh = '';
                                purchaseApplySupplierStore.each(function(r){
                                    if(r.data['dm'] == gysdm){
                                    	gysmc = r.data['gysmc'];
                                    	lxdh = r.data['lxdh'];
                                    }
                                });
                                
                                if(validatePurchaseApplyDetailCreateOrderStore(purchaseApplyDetailCreateOrderStore)){return;}
                                
                                var param = strToJson('{"purchaseOrderResult.gysdm":"'+gysdm+'"}');
                                param["purchaseOrderResult.gysmc"]=gysmc;
                                param["purchaseOrderResult.gysdh"]=lxdh;
                                param["purchaseOrderResult.gyscz"]="gyscz";
                                param["purchaseOrderResult.shf"]=shf;
								param["purchaseOrderResult.shflxr"]=shflxr;
								param["purchaseOrderResult.shfdh"]=shfdh;
								param["purchaseOrderResult.shfcz"]="shfcz";
								for(var i = 0; i < purchaseApplyDetailCreateOrderStore.getCount(); i ++){
									param["purchaseOrderDetailResultList["+i+"].cgsqmxdm"]=purchaseApplyDetailCreateOrderStore.getAt(i).get("cgsqmxdm");
									param["purchaseOrderDetailResultList["+i+"].wldm"]=purchaseApplyDetailCreateOrderStore.getAt(i).get("wldm");
									param["purchaseOrderDetailResultList["+i+"].wlmc"]=purchaseApplyDetailCreateOrderStore.getAt(i).get("wlmc");
									param["purchaseOrderDetailResultList["+i+"].wlgg"]="wlgg";
									param["purchaseOrderDetailResultList["+i+"].wldw"]="wldw";
									param["purchaseOrderDetailResultList["+i+"].dj"]=purchaseApplyDetailCreateOrderStore.getAt(i).get("dj");
									param["purchaseOrderDetailResultList["+i+"].sqsl"]=purchaseApplyDetailCreateOrderStore.getAt(i).get("sqsl");
									param["purchaseOrderDetailResultList["+i+"].ydgsl"]=purchaseApplyDetailCreateOrderStore.getAt(i).get("ydgsl");
									param["purchaseOrderDetailResultList["+i+"].dgsl"]=purchaseApplyDetailCreateOrderStore.getAt(i).get("dgsl");
								}
								ajaxOpera("/proopePurchase/createPurchaseOrder.do", param, function(){
									Ext.Msg.alert("提示", "采购订单创建成功。", function(){
										Ext.getCmp('proopePurchaseApplyDetailCreateOrderWindow').close();
										btn.up("proopepurchasepurchaseapplydetailgrid").getStore().load();
									});
								});
                            }
                        },' ',{
                            text:'关闭',
                            handler: function(){
                                Ext.getCmp('proopePurchaseApplyDetailCreateOrderWindow').close();
                            }
                        }]
                    });
                    showPupWindow(createPurchaseOrderWindow, "创建采购订单",600,380,'proopePurchaseApplyDetailCreateOrderWindow');
			    	showOrderDetail(records);
            }//  end create order
        }
     });//  end control
     
     function validatePurchaseApplyDetailCreateOrderStore(purchaseApplyDetailCreateOrderStore){
    	 for(var i = 0; i < purchaseApplyDetailCreateOrderStore.getCount(); i ++){
    		 if(purchaseApplyDetailCreateOrderStore.getAt(i).get("dj") <= 0){
    			 Ext.Msg.alert("提示","单价不可为0。");
    			 return true;
    		 }
    		 if(purchaseApplyDetailCreateOrderStore.getAt(i).get("dgsl") <= 0){
    			 Ext.Msg.alert("提示","订购数量不可为0。");
    			 return true;
    		 }
		}
    	 return false;
     }
    },//  end init
    views : [
        "ERP.proope.purchase.view.purchaseApplyDetailLayout",
        "ERP.proope.purchase.view.purchaseApplyDetailGrid"
    ],
    stores : ["ERP.proope.purchase.store.purchaseApplyDetailStore"],
    models : ["ERP.proope.purchase.model.purchaseApplyDetailModel"]
});