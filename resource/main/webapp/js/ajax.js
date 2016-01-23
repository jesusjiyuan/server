
/**
 * Ajax分页查询基本方法
 * @param urlParam  请求url
 * @param syncParam  Ajax请求sync参数
 * @param paramsParam 请求的参数集合
 * @param successRoleBack  成功回调
 * @param failureRoleBack  失败回调
 * @return
 */
function ajaxPageQueryBase(urlParam, asyncParam, paramsParam, successRoleBack, failureRoleBack){
	Ext.Ajax.request({
        url: root+urlParam,
        async:asyncParam,
        params: paramsParam,
        method: 'POST',
        success: function (response, options) {
        	var jsonResult = strToJson(response.responseText);
        	if(jsonResult instanceof Array){
            	if('0' != jsonResult[0].state){
                    Ext.MessageBox.alert('提示', jsonResult[0].mes);
                    return;
                }
            }
        	successRoleBack(jsonResult);
        },
        failure: function (response, options) {
            Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
            failureRoleBack();
        }
    });
}

/**
 * Ajax分页查询
 * @param urlParam
 * @param paramsParam
 * @param successRoleBack
 * @return
 */
function ajaxPageQuery(urlParam, paramsParam, successRoleBack){
	ajaxPageQueryBase(urlParam, true, paramsParam, successRoleBack, function(){});
}

/**
 * 在div中显示分页控件
 * @param page
 * @param pageDivId  显示page控件的divId
 * @param pageFunction  分页响应的JS方法
 * @return
 */
function showPage(page, pageDivId, pageFucntion){
	var pageStr = "共"+page.itemTotal+"条记录&nbsp;"+page.currentPage+"/"+page.pageTotal+"条记录&nbsp";
	if(parseInt(page.currentPage) > 1){
		pageStr = pageStr + "<a href='#' onclick='"+pageFucntion+"(1, "+page.pageSize+");return false;'>首页</a>&nbsp";
		pageStr = pageStr + "<a href='#' onclick='"+pageFucntion+"("+page.previouPage+", "+page.pageSize+");return false;'>上一页</a>&nbsp";
	}else{
		pageStr = pageStr + "首页&nbsp上一页&nbsp";
	}
	if(parseInt(page.currentPage) < parseInt(page.pageTotal)){
		pageStr = pageStr + "<a href='#' onclick='"+pageFucntion+"("+page.nextPage+", "+page.pageSize+");return false;'>下一页</a>&nbsp";
		pageStr = pageStr + "<a href='#' onclick='"+pageFucntion+"("+page.pageTotal+", "+page.pageSize+");return false;'>尾页</a>";
	}else{
		pageStr = pageStr + "下一页&nbsp尾页";
	}
	document.getElementById(pageDivId).innerHTML=pageStr;
}

/**
 * 查询全部基本方法
 * @param urlParam
 * @param syncParam
 * @param paramsParam
 * @param successRoleBack
 * @param failureRoleBack
 * @return
 */
function ajaxQueryAllBase(urlParam, asyncParam, paramsParam, successRoleBack, failureRoleBack){
	Ext.Ajax.request({
        url: root+urlParam,
        async:asyncParam,
        params: paramsParam,
        method: 'POST',
        success: function (response, options) {
        	var jsonResult = strToJson(response.responseText);
        	var jsonObj = eval(jsonResult);
            if(jsonObj.length == 0){
                successRoleBack(jsonObj);
            	Ext.MessageBox.alert('提示', "无记录。");
            	return;
            }
        	if('1' == jsonObj[0].state){
        		Ext.MessageBox.alert('提示', jsonObj[0].mes);
        		return;
        	}
        	successRoleBack(jsonObj);
        },
        failure: function (response, options) {
            Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
            failureRoleBack();
        }
    });
}

/**
 * 查询全部记录
 * @param urlParam
 * @param paramsParam
 * @param successRoleBack
 * @return
 */
function ajaxQueryAll(urlParam, paramsParam, successRoleBack){
	ajaxQueryAllBase(urlParam, true, paramsParam, successRoleBack, function(){});
}

/**
 * 查询实体时的基本方法
 * @param urlParam
 * @param syncParam
 * @param paramsParam
 * @param successRoleBack
 * @param failureRoleBack
 * @return
 */
function ajaxQueryEntityBase(urlParam, asyncParam, paramsParam, successRoleBack, failureRoleBack){
	Ext.Ajax.request({
        url: root+urlParam,
        async:asyncParam,
        params: paramsParam,
        method: 'POST',
        success: function (response, options) {
            var jsonResult = strToJson(response.responseText);
            var jsonObj = eval(jsonResult);
            if(jsonObj instanceof Array){
            	if('0' != jsonObj[0].state){
                    Ext.MessageBox.alert('提示', jsonObj[0].mes);
                    return;
                }
            }
            successRoleBack(jsonResult);
        },
        failure: function (response, options) {
            Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
            failureRoleBack();
        }
    });
}

/**
 * 查询实体
 * @param urlParam
 * @param paramsParam
 * @param successRoleBack
 * @return
 */
function ajaxQueryEntity(urlParam, paramsParam, successRoleBack){
	ajaxQueryEntityBase(urlParam, true, paramsParam, successRoleBack, function(){});
}

/**
 * 增删改操作基本方法
 * @param urlParam
 * @param syncParam
 * @param paramsParam
 * @param successRoleBack
 * @param failureRoleBack
 * @return
 */
function ajaxOperaBase(urlParam, asyncParam, paramsParam, successRoleBack, failureRoleBack){
	Ext.Ajax.request({
        url: root+urlParam,
        async:asyncParam,
        params: paramsParam,
        method: 'POST',
        success: function (response, options) {
			//alert(response.responseText);
            var jsonResult = strToJson(response.responseText);
            var jsonObj = eval(jsonResult);

            if('0' != jsonObj[0].state){
                Ext.MessageBox.alert('提示', jsonObj[0].mes);
                return;
            }
            successRoleBack();
        },
        failure: function (response, options) {
            Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
            failureRoleBack();
        }
    });
}

/**
 * 增删改操作
 * @param urlParam
 * @param paramsParam
 * @param successRoleBack
 * @return
 */
function ajaxOpera(urlParam, paramsParam, successRoleBack){
	ajaxOperaBase(urlParam, true, paramsParam, successRoleBack, function(){});
}

/**
 * str转为json对象
 * @param str
 * @returns
 */
function strToJson(str){
	var json = (new Function("return " + str))();
	return json;
}
