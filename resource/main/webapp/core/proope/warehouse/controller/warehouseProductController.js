/**
 * 产品库存量控制器
 */
Ext.define("ERP.proope.warehouse.controller.warehouseProductController", {
    extend : "Ext.app.Controller",
    init : function() {
     var self = this;
     
     //控制响应
     self.control({
     });
    },
    views : [
        "ERP.proope.warehouse.view.warehouseProductLayout",
        "ERP.proope.warehouse.view.warehouseProductGrid"
    ],
    stores : ["ERP.proope.warehouse.store.warehouseProductStore"],
    models : ["ERP.proope.warehouse.model.warehouseProductModel"]
});