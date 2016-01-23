/**
 * 右部主显示界面
 */
Ext.define("ERP.app.view.centerView",{
    extend: 'Ext.tab.Panel',
    alias: 'widget.centerview',
    id:'centerid',
    menuAlign:"center",
    //autoDestroy:false,
    //deferredRender:false,
    items:[{
        title:'首页',
        layout:'fit'
    }],
    initComponent:function(){
        this.callParent(arguments);
    }
});
