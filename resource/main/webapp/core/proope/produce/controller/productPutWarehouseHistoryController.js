/**
 * 产品入库历史
 */
Ext.define("ERP.proope.produce.controller.productPutWarehouseHistoryController", {
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
     
     Ext.define('proopeProduceProducePutWarehouseHistoryDetailWindowModel', {
         extend: 'Ext.data.Model',
         fields: [
             {name: 'dm', type: 'string'},
             {name: 'cprklsdm', type: 'string'},
             {name: 'rksqlldm', type: 'string'},
             {name: 'rksqmxlldm', type: 'string'},
             {name: 'cpdm', type: 'string'},
             {name: 'cpmc', type: 'string'},
             {name: 'sl', type: 'float'},
             {name: 'bz', type: 'string'}
         ]
      });
     
     var proopeProduceProducePutWarehouseHistoryDetailWindowStore;  //  设置为全局变量，在每次打开弹窗时，要清空该store中的数据
     
     //控制响应
     self.control({
    	 "proopeproduceproductputwarehousehistorygrid button[id=proopeProductProducePutWarehouseHistoryDetailbarButton]" : {
             click : function(btn) {
            	var records = btn.up("proopeproduceproductputwarehousehistorygrid").getSelectionModel().getSelection();
              	if(records.length == 0){
              		Ext.Msg.alert("提示", "未选中记录。");
              		return;
              	}
              	
              	if(Ext.getCmp('proopeProduceProducePutWarehouseHistoryDetailWindow')){
             		Ext.getCmp('proopeProduceProducePutWarehouseHistoryDetailWindow').show();
             		findProopeProducePutWarehouseHistoryDetail(records[0]);
             		return;
             	 }
              	
              	function findProopeProducePutWarehouseHistoryDetail(record){
               		Ext.getCmp('proopeProduceProducePutWarehouseHistoryRkbmLabel').setText("入库部门："+
               			record.get('bmmc') + "    创建日期：" + record.get('cjrqYMDHMS'), true);
              		proopeProduceProducePutWarehouseHistoryDetailWindowStore.removeAll();
          			ajaxQueryAll("/proopeProduce/findProductPutWarehouseDetailHistoryListByPpwhId.do", {"productPutWarehouseHistoryId":record.get('dm')}, 
            			function(productPutWarehouseDetailHistoryEntityList){
            				for (var i = 0; i < productPutWarehouseDetailHistoryEntityList.length; i++) {
            			        var r = Ext.create('proopeProduceProducePutWarehouseHistoryDetailWindowModel', {
            			        	dm: productPutWarehouseDetailHistoryEntityList[i].dm,
            			        	cprklsdm: productPutWarehouseDetailHistoryEntityList[i].cprklsdm,
            			        	rksqlldm: productPutWarehouseDetailHistoryEntityList[i].rksqlldm,
            			        	rksqmxlldm: productPutWarehouseDetailHistoryEntityList[i].rksqmxlldm,
            			        	cpdm: productPutWarehouseDetailHistoryEntityList[i].cpdm,
            			        	cpmc: productPutWarehouseDetailHistoryEntityList[i].cpmc,
            			        	sl: productPutWarehouseDetailHistoryEntityList[i].sl,
				                    bz: productPutWarehouseDetailHistoryEntityList[i].bz
				                });
            			        proopeProduceProducePutWarehouseHistoryDetailWindowStore.insert(0, r);
				            }
            			});
               	}
              	
              	proopeProduceProducePutWarehouseHistoryDetailWindowStore = Ext.create('Ext.data.Store', {
			        autoDestroy: true,
			        model: 'proopeProduceProducePutWarehouseHistoryDetailWindowModel'
			    });
              	
              	var historyGridForm = Ext.create('Ext.grid.Panel', {
			        store: proopeProduceProducePutWarehouseHistoryDetailWindowStore,
			        columns: [
			        	{header: '代码',dataIndex: 'dm', hidden:true}, 
				        {header: '产品入库历史代码',dataIndex: 'cprklsdm',hidden:true},
				        {header: '入库申请历史代码',dataIndex: 'rksqlldm',hidden:true},
				        {header: '入库申请明细历史代码',dataIndex: 'rksqmxlldm',hidden:true},
				        {header: '产品代码',dataIndex: 'cpdm',hidden:true},
				        {header: '产品名称',dataIndex: 'cpmc',width:200}, 
				        {header: '数量',dataIndex: 'sl',width:80},
				        {header: '备注',dataIndex: 'bz',width:200}
			        ],
			        columnLines: true,
			        width: 500,
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
		                    id: "proopeProduceProducePutWarehouseHistoryRkbmLabel",
		                    text: "入库部门："
				        },
				        historyGridForm
                    ],

                    buttons: [{
                        text:'关闭',
                        handler: function(){
                            Ext.getCmp('proopeProduceProducePutWarehouseHistoryDetailWindow').close();
                        }
                    }]
                });
                showPupWindow(historyWindow, "采购订单历史明细",600,380,'proopeProduceProducePutWarehouseHistoryDetailWindow');
                findProopeProducePutWarehouseHistoryDetail(records[0]);
             }
    	 }, "proopeproduceproductputwarehousehistorygrid button[id=proopeProductProducePutWarehouseHistoryReportTbarButton]" : {
             click : function(btn) {
            	alert("pph report");
             }
    	 }
     });//  end control
    },//   end init
    views : [
        "ERP.proope.produce.view.productPutWarehouseHistoryLayout",
        "ERP.proope.produce.view.productPutWarehouseHistoryGrid"
    ],
    stores : ["ERP.proope.produce.store.productPutWarehouseHistoryStore"],
    models : ["ERP.proope.produce.model.productPutWarehouseHistoryModel"]
});