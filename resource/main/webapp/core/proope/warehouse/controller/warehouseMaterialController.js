/**
 * 物料库存量控制器
 */
Ext.define("ERP.proope.warehouse.controller.warehouseMaterialController", {
    extend : "Ext.app.Controller",
    init : function() {
     var self = this;
     
     //控制响应
     self.control({
     });
    },
    views : [
        "ERP.proope.warehouse.view.warehouseMaterialLayout",
        "ERP.proope.warehouse.view.warehouseMaterialGrid"
    ],
    stores : ["ERP.proope.warehouse.store.warehouseMaterialStore"],
    models : ["ERP.proope.warehouse.model.warehouseMaterialModel"]
});