/**
 * 功能模块导航界面
 */
Ext.define("ERP.app.view.westView",{
    extend: 'Ext.panel.Panel',
    alias: 'widget.westview',
    collapsible: true,
    split: true,
    margin: '0 0 2 2',
    width: 200,
    minWidth: 175,
    maxWidth: 400,
    title:"功能模块导航",
    layout : 'accordion',
    layoutConfig :{
        collapsible: true,
        animCollapse: true,
        split: true,
        activeOnTop: true
    },
    items:[{
        title:"采购",
        items:[{
            xtype:"treepanel",
            rootVisible : false,// 不展示根节点
            displayField : "text",
            border:0,
            root: {
                expanded: true,
                children: [
                    {
                        id:"proopePurchasePurchaseApplyLeftMenuId",
                        text: "采购申请",
                        leaf: true
                    },
                    {
                        id:"proopePurchasePurchaseApplyHistoryLeftMenuId",
                        text: "采购申请历史",
                        leaf: true
                    },
                    {
                        id:"proopePurchasePurchaseApplyDetailLeftMenuId",
                        text: "采购申请明细",
                        leaf: true
                    },
                    {
                        id:"proopePurchasePurchaseOrderLeftMenuId",
                        text: "未完成采购订单",
                        leaf: true
                    },
                    {
                        id:"proopePurchaseArrivalGoodOrderDetailLeftMenuId",
                        text: "到货确认",
                        leaf: true
                    },
                    {
                        id:"proopePurchaseArrivalsGoodsHistoryLeftMenuId",
                        text: "到货历史记录",
                        leaf: true
                    },
                    {
                        id:"proopePurchasePurchaseOrderHistoryLeftMenuId",
                        text: "采购订单历史记录",
                        leaf: true
                    },
                    {
                        id:"proopePurchasePutWarehouseLeftMenuId",
                        text: "入库单",
                        leaf: true
                    }
                ]
            }
        }]
    },{
        title:"业务",
        items:[{
            xtype:"treepanel",
            rootVisible : false,
            displayField : "text",
            border:0,
            root: {
                expanded: true,
                children: [
                    {
                        id:"proopeBusinessNotSubmitSalesOrderLeftMenuId",
                        text: "业务订单",
                        leaf: true
                    },
                    {
                        id:"proopeBusinessProductForBomLeftMenuId",
                        text: "BOM表管理",
                        leaf: true
                    },
                    {
                        id:"proopeBusinessOrderMaterialLeftMenuId",
                        text: "业务订单用料表",
                        leaf: true
                    },
                    {
                        id:"proopeBusinessNotProcessSalesOrderLeftMenuId",
                        text: "订单出货",
                        leaf: true
                    }
                ]
            }
        }]
    },{
        title:"生产",
        items:[{
            xtype:"treepanel",
            rootVisible : false,
            displayField : "text",
            border:0,
            root: {
                expanded: true,
                children: [
                	{
                        id:"proopeProduceReceiveMaterialHistoryLeftMenuId",
                        text: "取料管理",
                        leaf: true
                    },
                    {
                        id:"proopeProduceProductPutWarehouseApplyLeftMenuId",
                        text: "入库申请",
                        leaf: true
                    },
                    {
                        id:"proopeProduceProductPutWarehouseHistoryLeftMenuId",
                        text: "成品入库单",
                        leaf: true
                    }
                ]
            }
        }]
    },{
        title:"品保",
        items:[{
            xtype:"treepanel",
            rootVisible : false,
            displayField : "text",
            border:0,
            root: {
                expanded: true,
                children: [
                    {
                        id:"proopeQualityMaterialPutWarehouseCheckLeftMenuId",
                        text: "物料入库待检",
                        leaf: true
                    },
                    {
                        id:"proopeQualityProductPutWarehouseCheckLeftMenuId",
                        text: "产品入库待检",
                        leaf: true
                    }
                ]
            }
        }]
    },{
        title:"仓储",
        items:[{
            xtype:"treepanel",
            rootVisible : false,
            displayField : "text",
            border:0,
            root: {
                expanded: true,
                children: [
                    {
                        id:"proopeWarehouseMaterialLeftMenuId",
                        text: "物料库存",
                        leaf: true
                    },
                    {
                        id:"proopeWarehouseProductLeftMenuId",
                        text: "产品库存",
                        leaf: true
                    }
                ]
            }
        }]
    }],
    initComponent: function(){
        this.callParent(arguments);
    }
});



