/**
 * Created by sxd on 15/7/7.
 */
/**程序开始执行的位置
 * 相当于window.onload=function(){}
 * */
Ext.onReady(function(){
    /**打开extjs的提示功能*/
    Ext.QuickTips.init();
    /**启动动态加载js*/
    Ext.Loader.setConfig({
        enabled : true
    });
    /**开始执行应用程序*/
    Ext.application({
        name : "ERP",//命名空间
        appFolder : "core",//
        launch:function(){
            Ext.create("Ext.container.Viewport",{
                layout : "fit",
                border : 0,
                items : [{
                    xtype : "mainviewlayout"
                }]
            });
        },
        controllers : ['ERP.app.controller.mainController']
    });
});