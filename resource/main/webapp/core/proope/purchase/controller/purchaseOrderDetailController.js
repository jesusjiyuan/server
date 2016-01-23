/**
 * 采购订单明细，创建到货记录
 */
Ext.define("ERP.proope.purchase.controller.purchaseOrderDetailController", {
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
     
     Ext.define('purchaseOrderDetailCreateArrivalGoodWindowModel', {
        extend: 'Ext.data.Model',
        fields: [
            {name: 'cgddmxdm', type: 'string'},
            {name: 'cgsqmxdm', type: 'string'},
            {name: 'wldm', type: 'string'},
            {name: 'wlmc', type: 'string'},
            {name: 'sqsl', type: 'float'},
            {name: 'dgsl', type: 'float'},
            {name: 'ydhsl', type: 'float'},
            {name: 'dhsl', type: 'float'},
            {name: 'gysmc', type: 'string'}
        ]
     });
     
     var purchaseOrderDetailCreateArrivalGoodWindowStore;  //全局store
     
     //控制响应
     self.control({
     	"proopepurchasearrivalgoodsorderdetailgrid button[id=prropePurchaseOrderDetailCreateArrivalGoodTbarButton]" : {
           click : function(btn) {
        	   var records = btn.up("proopepurchasearrivalgoodsorderdetailgrid").getSelectionModel().getSelection();
               if(records.length == 0){
             		Ext.Msg.alert("提示", "未选中任何物料记录。");
             		return;
               }
               
               if(Ext.getCmp('proopeOrderDetailCreateArrivalGoodWindow')){
	           		Ext.getCmp('proopeOrderDetailCreateArrivalGoodWindow').show();
	           		showOrderDetailCreateArrivalGood(records);
	           		return;
	           	}
              	
              	function showOrderDetailCreateArrivalGood(recordList){
              		purchaseOrderDetailCreateArrivalGoodWindowStore.removeAll();
              		for(var i = 0; i < recordList.length; i ++){
              			var r = Ext.create('purchaseOrderDetailCreateArrivalGoodWindowModel', {
              				cgddmxdm: recordList[i].get('dm'),
              				cgsqmxdm: recordList[i].get('cgsqmxdm'),
              				wldm: recordList[i].get('wldm'),
		                    wlmc: recordList[i].get('wlmc'),
		                    sqsl: recordList[i].get('sqsl'),
		                    dgsl: recordList[i].get('dgsl'),
		                    ydhsl: recordList[i].get('ydhsl'),
		                    dhsl: 1,
		                    gysmc: recordList[i].get('gysmc')
		                });
              			purchaseOrderDetailCreateArrivalGoodWindowStore.insert(0, r);
              		}
              	}
              	
              	// 创建全局store
              	purchaseOrderDetailCreateArrivalGoodWindowStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'purchaseOrderDetailCreateArrivalGoodWindowModel'
			    });
			    
			    var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
			        clicksToEdit: 1
			    });
			    
			    var gridForm = Ext.create('Ext.grid.Panel', {
				        store: purchaseOrderDetailCreateArrivalGoodWindowStore,
				        columns: [
							{header: '采购订单明细代码',dataIndex: 'cgddmxdm',hidden:true},
				        	{header: '采购申请明细代码',dataIndex: 'cgsqmxdm',hidden:true},
					        {header: '物料代码',dataIndex: 'wldm',hidden:true},
					        {header: '物料名称',dataIndex: 'wlmc', width: 160},
					        {header: '申请数量',dataIndex: 'sqsl',width: 70},
					        {header: '订购数量',dataIndex: 'dgsl',width: 70},
					        {header: '已到货数量',dataIndex: 'ydhsl',width: 70},
					        {
					            header: '到货数量',
					            dataIndex: 'dhsl',
					            width: 70,
					            align: 'right',
					            editor: {
					                xtype: 'numberfield',
					                allowBlank: false,
					                minValue: 0
					            }
					        },
					        {header: '供应商名称',dataIndex: 'gysmc',width: 180}
				        ],
				        columnLines: true,
				        width: 630,
				        height: 245,
				        frame: true,
				        plugins: [cellEditing],
				        iconCls: 'icon-grid'
				    });
               	
               	
                   var purchaseOrderDetailCreateArrivalGoodWindow = Ext.create('Ext.form.Panel', {
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
					            html: "确认列表中的记录已到货"
					        },
					        gridForm
                       ],
                       buttons: [{
                           text: '创建',
                           handler: function(){
                        	   if(validatePurchaseOrderDetailCreateArrivalGoodWindowStore(purchaseOrderDetailCreateArrivalGoodWindowStore)) {return;}
                                var param = strToJson("{}");
								for(var i = 0; i < purchaseOrderDetailCreateArrivalGoodWindowStore.getCount(); i ++){
									param["purchaseArrivalsGoodsDetailResultList["+i+"].cgsqmxdm"]=purchaseOrderDetailCreateArrivalGoodWindowStore.getAt(i).get("cgsqmxdm");
									param["purchaseArrivalsGoodsDetailResultList["+i+"].cgddmxdm"]=purchaseOrderDetailCreateArrivalGoodWindowStore.getAt(i).get("cgddmxdm");
									param["purchaseArrivalsGoodsDetailResultList["+i+"].wldm"]=purchaseOrderDetailCreateArrivalGoodWindowStore.getAt(i).get("wldm");
									param["purchaseArrivalsGoodsDetailResultList["+i+"].wlmc"]=purchaseOrderDetailCreateArrivalGoodWindowStore.getAt(i).get("wlmc");
									param["purchaseArrivalsGoodsDetailResultList["+i+"].sqsl"]=purchaseOrderDetailCreateArrivalGoodWindowStore.getAt(i).get("sqsl");
									param["purchaseArrivalsGoodsDetailResultList["+i+"].dgsl"]=purchaseOrderDetailCreateArrivalGoodWindowStore.getAt(i).get("dgsl");
									param["purchaseArrivalsGoodsDetailResultList["+i+"].ydhsl"]=purchaseOrderDetailCreateArrivalGoodWindowStore.getAt(i).get("ydhsl");
									param["purchaseArrivalsGoodsDetailResultList["+i+"].dhsl"]=purchaseOrderDetailCreateArrivalGoodWindowStore.getAt(i).get("dhsl");
								}
								ajaxOpera("/proopePurchase/createArrivalsGoods.do", param, function(){
									Ext.Msg.alert("提示", "到货记录创建成功。", function(){
										Ext.getCmp('proopeOrderDetailCreateArrivalGoodWindow').close();
										btn.up("proopepurchasearrivalgoodsorderdetailgrid").getStore().load();
									});
								});
                           }
                       },' ',{
                           text:'关闭',
                           handler: function(){
                               Ext.getCmp('proopeOrderDetailCreateArrivalGoodWindow').close();
                           }
                       }]
                   });
                   showPupWindow(purchaseOrderDetailCreateArrivalGoodWindow, "创建采购到货记录",700,380,'proopeOrderDetailCreateArrivalGoodWindow');
			       showOrderDetailCreateArrivalGood(records);
               
            }//  end create arrival good button click
         }
     });  //  end control
     
     function validatePurchaseOrderDetailCreateArrivalGoodWindowStore(purchaseOrderDetailCreateArrivalGoodStore){
    	 for(var i = 0; i < purchaseOrderDetailCreateArrivalGoodStore.getCount(); i ++){
    		 if(purchaseOrderDetailCreateArrivalGoodStore.getAt(i).get("dhsl") <= 0){
    			 Ext.Msg.alert("提示","到货数量不可为0。");
    			 return true;
    		 }
		 }
    	 return false;
     }
    },//  end init
    views : [
        "ERP.proope.purchase.view.purchaseOrderDetailLayout",
        "ERP.proope.purchase.view.purchaseOrderDetailGrid"
    ],
    stores : ["ERP.proope.purchase.store.purchaseOrderDetailStore"],
    models : ["ERP.proope.purchase.model.purchaseOrderDetailModel"]
});