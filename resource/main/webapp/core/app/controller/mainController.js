/**主控制器*/
Ext.define("ERP.app.controller.mainController",{
    extend : "Ext.app.Controller",
    views : [
        "ERP.app.view.topView",
        "ERP.app.view.westView",
        "ERP.app.view.centerView",
        "ERP.app.view.mainViewLayout"
    ],
    store : [],
    model : [],
    init : function(){
        var self=this;
        var proopePurchasePurchaseApplyInitFlag = true;//  标记采购申请tab是否需要init
        var proopePurchasePurchaseApplyHistoryInitFlag = true;//  标记采购申请历史tab是否需要init
        var proopePurchasePurchaseApplyDetailInitFlag = true;//  标记采购申请明细tab是否需要init
        var proopePurchasePurchaseOrderInitFlag = true;//  标记采购订单tab是否需要init
        var proopePurchasePurchaseOrderHistoryInitFlag = true;//  标记采购订单历史tab是否需要init
        var proopePurchasePutWarehouseInitFlag = true;//  标记采购入库tab是否需要init
        var proopePurchaseArrivalGoodOrderDetailInitFlag = true;  //  订单明细，创建到货用
        var proopePurchaseArrivalGoodHistoryInitFlag = true;  //  到货历史记录
        
        var proopeBusinessNotSubmitSalesOrderInitFlag = true;//  未提交销售订单
        var proopeBusinessProductForBomInitFlag = true;//  BOM表管理
        var proopeBusinessOrderMaterialInitFlag = true;//  销售订单-物料
        var proopeBusinessNotProcessSalesOrderInitFlag = true;//  未处理销售订单
        
        var proopeProduceReceiveMaterialHistoryInitFlag = true;  //  领料历史记录
        var proopeProduceProductPutWarehouseApplyLeftInitFlag = true;  //  产品入库申请
        var proopeProduceProductPutWarehouseHistoryInitFlag = true;  //  产品入库历史
        
        var proopeQualityArrivalGoodDetailInitFlag = true;//  品保采购订单到货入库明细tab是否需要init
        var proopeQualityQualityProductDetailInitFlag = true;//  品保采购产品入库待检明细tab是否需要init
        
        var proopeWarehouseWarehouseMaterialInitFlag = true;//  物料库存量明细tab是否需要init
        var proopeWarehouseWarehouseProductInitFlag = true;//  产品库存量明细tab是否需要init
        
        /**公用添加页面的方法*/
        self.addFunItem=function(funInfo){
            if(funInfo){
                var mainView=funInfo.mainView;
                var funPanel=mainView.down(funInfo.funViewXtype);
                if(!funPanel){
                	if("proopepurchasepurchaseapplylayout" == funInfo.funViewXtype && proopePurchasePurchaseApplyInitFlag){
                		//  采购申请
                		self.application.getController(funInfo.funController).init();
                		proopePurchasePurchaseApplyInitFlag = false;
                	}else if("proopepurchasepurchaseapplyhistorylayout" == funInfo.funViewXtype && proopePurchasePurchaseApplyHistoryInitFlag){
                		//  采购申请历史
                		self.application.getController(funInfo.funController).init();
                		proopePurchasePurchaseApplyHistoryInitFlag = false;
                	}else if("proopepurchasepurchaseapplydetaillayout" == funInfo.funViewXtype && proopePurchasePurchaseApplyDetailInitFlag){
                		//  采购申请明细
                		self.application.getController(funInfo.funController).init();
                		proopePurchasePurchaseApplyDetailInitFlag = false;
                	}else if("proopepurchasepurchaseorderlayout" == funInfo.funViewXtype && proopePurchasePurchaseOrderInitFlag){
                		//  采购订单
                		self.application.getController(funInfo.funController).init();
                		proopePurchasePurchaseOrderInitFlag = false;
                	}else if("proopepurchasepurchaseorderhistorylayout" == funInfo.funViewXtype && proopePurchasePurchaseOrderHistoryInitFlag){
                		//  采购订单历史
                		self.application.getController(funInfo.funController).init();
                		proopePurchasePurchaseOrderHistoryInitFlag = false;
                	}else if("proopepurchaseputwarehouselayout" == funInfo.funViewXtype && proopePurchasePutWarehouseInitFlag){
                		//  入库单
                		self.application.getController(funInfo.funController).init();
                		proopePurchasePutWarehouseInitFlag = false;
                	}else if("proopepurchasearrivalgoodorderdetaillayout" == funInfo.funViewXtype && proopePurchaseArrivalGoodOrderDetailInitFlag){
                		//  确认采购订单到货
                		self.application.getController(funInfo.funController).init();
                		proopePurchaseArrivalGoodOrderDetailInitFlag = false;
                	}else if("proopepurchasearrivalsgoodshistorylayout" == funInfo.funViewXtype && proopePurchaseArrivalGoodHistoryInitFlag){
                		//  采购订单到货历史记录
                		self.application.getController(funInfo.funController).init();
                		proopePurchaseArrivalGoodHistoryInitFlag = false;
                	}
                	
                	
                	else if("proopebusinessnotsubmitsalesorderlayout" == funInfo.funViewXtype && proopeBusinessNotSubmitSalesOrderInitFlag){
                		//  未提交销售订单
                		self.application.getController(funInfo.funController).init();
                		proopeBusinessNotSubmitSalesOrderInitFlag = false;
                	}else if("proopebusinessproductforbomlayout" == funInfo.funViewXtype && proopeBusinessProductForBomInitFlag){
                		//  BOM表管理
                		self.application.getController(funInfo.funController).init();
                		proopeBusinessProductForBomInitFlag = false;
                	}else if("proopebusinessordermateriallayout" == funInfo.funViewXtype && proopeBusinessOrderMaterialInitFlag){
                		//  销售订单-物料
                		self.application.getController(funInfo.funController).init();
                		proopeBusinessOrderMaterialInitFlag = false;
                	}else if("proopebusinessnotprocesssalesorderlayout" == funInfo.funViewXtype && proopeBusinessNotProcessSalesOrderInitFlag){
                		//  未处理销售订单
                		self.application.getController(funInfo.funController).init();
                		proopeBusinessNotProcessSalesOrderInitFlag  = false;
                	}
                	
                	else if("proopeproducereceivematerialhistorylayout" == funInfo.funViewXtype && proopeProduceReceiveMaterialHistoryInitFlag){
                		//  领料历史记录
                		self.application.getController(funInfo.funController).init();
                		proopeProduceReceiveMaterialHistoryInitFlag = false;
                	}else if("proopeproduceproduceputwarehouseapplylayout" == funInfo.funViewXtype && proopeProduceProductPutWarehouseApplyLeftInitFlag){
                		//   产品入库申请
                		self.application.getController(funInfo.funController).init();
                		proopeProduceProductPutWarehouseApplyLeftInitFlag = false;
                	}else if("proopeproduceproductputwarehousehistorylayout" == funInfo.funViewXtype && proopeProduceProductPutWarehouseHistoryInitFlag){
                		//  产品入库历史
                		self.application.getController(funInfo.funController).init();
                		proopeProduceProductPutWarehouseHistoryInitFlag = false;
                	}
                	
                	else if("proopequalitypurchaseorderarrivalgoodlayout" == funInfo.funViewXtype && proopeQualityArrivalGoodDetailInitFlag){
                		//  物料入库待检采购订单明细
                		self.application.getController(funInfo.funController).init();
                		proopeQualityArrivalGoodDetailInitFlag = false;
                	}else if("proopequalityqualityproducedetaillayout" == funInfo.funViewXtype && proopeQualityQualityProductDetailInitFlag){
                		//  产品入库待检
                		self.application.getController(funInfo.funController).init();
                		proopeQualityQualityProductDetailInitFlag = false;
                	}
                	
                	else if("proopewarehousewarehousemateriallayout" == funInfo.funViewXtype && proopeWarehouseWarehouseMaterialInitFlag){
                		//  物料库存量
                		self.application.getController(funInfo.funController).init();
                		proopeWarehouseWarehouseMaterialInitFlag = false;
                	}else if("proopewarehousewarehouseproductlayout" == funInfo.funViewXtype && proopeWarehouseWarehouseProductInitFlag){
                		//  产品库存量
                		self.application.getController(funInfo.funController).init();
                		proopeWarehouseWarehouseProductInitFlag = false;
                	}
                	
                    funPanel=Ext.create(funInfo.funViewName);
                    mainView.add(funPanel);
                    mainView.setActiveTab(funPanel);
                }else{
                    mainView.setActiveTab(funPanel);
                }
            }
        };
        
        /**
         * 控制部分
         */
        this.control({
            "westview treepanel":{
                itemclick:function(tree,record,item,index,e,eOpts){
                    var mainView=tree.up("mainviewlayout").down("centerview");
                    
                    if(record.data["id"]=="proopePurchasePurchaseApplyLeftMenuId"){
                    	/**采购申请*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopepurchasepurchaseapplylayout",
                            funController:"ERP.proope.purchase.controller.purchaseApplyController",
                            funViewName:"ERP.proope.purchase.view.purchaseApplyLayout"
                        });
                    }else if(record.data["id"]=="proopePurchasePurchaseApplyHistoryLeftMenuId"){
                    	/**采购申请历史*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopepurchasepurchaseapplyhistorylayout",
                            funController:"ERP.proope.purchase.controller.purchaseApplyHistoryController",
                            funViewName:"ERP.proope.purchase.view.purchaseApplyHistoryLayout"
                        });
                    }else if(record.data["id"]=="proopePurchasePurchaseApplyDetailLeftMenuId"){
                    	/**采购申请明细*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopepurchasepurchaseapplydetaillayout",
                            funController:"ERP.proope.purchase.controller.purchaseApplyDetailController",
                            funViewName:"ERP.proope.purchase.view.purchaseApplyDetailLayout"
                        });
                    }else if(record.data["id"]=="proopePurchasePurchaseOrderLeftMenuId"){
                    	/**采购订单*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopepurchasepurchaseorderlayout",
                            funController:"ERP.proope.purchase.controller.purchaseOrderController",
                            funViewName:"ERP.proope.purchase.view.purchaseOrderLayout"
                        });
                    }else if(record.data["id"]=="proopePurchasePurchaseOrderHistoryLeftMenuId"){
                    	/**采购订单历史*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopepurchasepurchaseorderhistorylayout",
                            funController:"ERP.proope.purchase.controller.purchaseOrderHistoryController",
                            funViewName:"ERP.proope.purchase.view.purchaseOrderHistoryLayout"
                        });
                    }else if(record.data["id"]=="proopePurchasePutWarehouseLeftMenuId"){
                    	/**入库单*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopepurchaseputwarehouselayout",
                            funController:"ERP.proope.purchase.controller.purchasePutWarehouseController",
                            funViewName:"ERP.proope.purchase.view.purchasePutWarehouseLayout"
                        });
                    }else if(record.data["id"]=="proopePurchaseArrivalGoodOrderDetailLeftMenuId"){
                    	/**创建采购订单到货*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopepurchasearrivalgoodorderdetaillayout",
                            funController:"ERP.proope.purchase.controller.purchaseOrderDetailController",
                            funViewName:"ERP.proope.purchase.view.purchaseOrderDetailLayout"
                        });
                    }else if(record.data["id"]=="proopePurchaseArrivalsGoodsHistoryLeftMenuId"){
                    	/**采购订单到货历史记录*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopepurchasearrivalsgoodshistorylayout",
                            funController:"ERP.proope.purchase.controller.purchaseArrivalsGoodsHistoryController",
                            funViewName:"ERP.proope.purchase.view.purchaseArrivalsGoodsHistoryLayout"
                        });
                    }
                    
                    
                    else if(record.data["id"]=="proopeBusinessNotSubmitSalesOrderLeftMenuId"){
                    	/**未提交销售订单*/
                    	self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopebusinessnotsubmitsalesorderlayout",
                            funController:"ERP.proope.business.controller.businessNotSubmitSalesOrderController",
                            funViewName:"ERP.proope.business.view.businessNotSubmitSalesOrderLayout"
                        });
                    }else if(record.data["id"]=="proopeBusinessProductForBomLeftMenuId"){
                    	/**BOM表管理*/
                    	self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopebusinessproductforbomlayout",
                            funController:"ERP.proope.business.controller.businessProductForBomController",
                            funViewName:"ERP.proope.business.view.businessProductForBomLayout"
                        });
                    }else if(record.data["id"]=="proopeBusinessOrderMaterialLeftMenuId"){
                    	/**订单－物料查询*/
                    	self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopebusinessordermateriallayout",
                            funController:"ERP.proope.business.controller.businessOrderMaterialController",
                            funViewName:"ERP.proope.business.view.businessOrderMaterialLayout"
                        });
                    }else if(record.data["id"]=="proopeBusinessNotProcessSalesOrderLeftMenuId"){
                    	/**待处理销售订单*/
                    	self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopebusinessnotprocesssalesorderlayout",
                            funController:"ERP.proope.business.controller.businessNotProcessSalesOrderController",
                            funViewName:"ERP.proope.business.view.businessNotProcessSalesOrderLayout"
                        });
                    }
                    
                    else if(record.data["id"]=="proopeProduceReceiveMaterialHistoryLeftMenuId"){
                    	/**取料管理，即领料历史记录*/
                    	self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopeproducereceivematerialhistorylayout",
                            funController:"ERP.proope.produce.controller.receiveMaterialHistoryController",
                            funViewName:"ERP.proope.produce.view.receiveMaterialHistoryLayout"
                        });
                    }else if(record.data["id"]=="proopeProduceProductPutWarehouseApplyLeftMenuId"){
                    	/**产品入库申请*/
                    	self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopeproduceproduceputwarehouseapplylayout",
                            funController:"ERP.proope.produce.controller.producePutWarehouseApplyController",
                            funViewName:"ERP.proope.produce.view.producePutWarehouseApplyLayout"
                        });
                    }else if(record.data["id"]=="proopeProduceProductPutWarehouseHistoryLeftMenuId"){
                    	/**产品入库单历史记录*/
                    	self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopeproduceproductputwarehousehistorylayout",
                            funController:"ERP.proope.produce.controller.productPutWarehouseHistoryController",
                            funViewName:"ERP.proope.produce.view.productPutWarehouseHistoryLayout"
                        });
                    }
                    
                    else if(record.data["id"]=="proopeQualityMaterialPutWarehouseCheckLeftMenuId"){
                    	/**品保采购订单到货明细明细  即入库检验*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopequalitypurchaseorderarrivalgoodlayout",
                            funController:"ERP.proope.quality.controller.qualityPurchaseOrderArrivalGoodDetailController",
                            funViewName:"ERP.proope.quality.view.qualityPurchaseOrderArrivalGoodDetailLayout"
                        });
                    }else if(record.data["id"]=="proopeQualityProductPutWarehouseCheckLeftMenuId"){
                    	/**品保入库产品明细  即入库检验*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopequalityqualityproducedetaillayout",
                            funController:"ERP.proope.quality.controller.qualityProduceDetailController",
                            funViewName:"ERP.proope.quality.view.qualityProduceDetailLayout"
                        });
                    }
                    
                    else if(record.data["id"]=="proopeWarehouseMaterialLeftMenuId"){
                    	/**物料库存量*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopewarehousewarehousemateriallayout",
                            funController:"ERP.proope.warehouse.controller.warehouseMaterialController",
                            funViewName:"ERP.proope.warehouse.view.warehouseMaterialLayout"
                        });
                    }else if(record.data["id"]=="proopeWarehouseProductLeftMenuId"){
                    	/**产品库存量*/
                        self.addFunItem({
                            mainView:mainView,
                            funViewXtype:"proopewarehousewarehouseproductlayout",
                            funController:"ERP.proope.warehouse.controller.warehouseProductController",
                            funViewName:"ERP.proope.warehouse.view.warehouseProductLayout"
                        });
                    }
                    
                    
                    
                }
            }
        });
    }
});