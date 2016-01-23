/**系统主程序界面布局类*/

Ext.define("ERP.app.view.mainViewLayout", {
    extend: 'Ext.panel.Panel',
    border: 0,
    layout: 'border',
    alias: 'widget.mainviewlayout',
    items: [
        {
        region: 'north',
        xtype: 'topview'
        }, {
        xtype: 'westview',
        region: 'west'
    }, {
        xtype: 'panel',
        region: 'center',
        layout: 'fit',
        border: 0,
        margin:'0 2 2 0',
        items: [{
            xtype: 'centerview'
        }]
    }],
    initComponent: function () {
        this.callParent(arguments);
    }
});