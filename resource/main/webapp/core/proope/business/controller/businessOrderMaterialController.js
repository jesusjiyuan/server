/**
 * 根据订单查看物料使用量控制器
 */
Ext.define("ERP.proope.business.controller.businessOrderMaterialController", {
    extend : "Ext.app.Controller",
    init : function() {
     var self = this;
    
     //控制响应
     self.control({
     });
    },
    views : [
        "ERP.proope.business.view.businessOrderMaterialLayout",
        "ERP.proope.business.view.businessOrderMaterialGrid"
    ],
    stores : ["ERP.proope.business.store.businessOrderMaterialStore"],
    models : ["ERP.proope.business.model.businessOrderMaterialModel"]
});