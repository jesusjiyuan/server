
/**
 * Validate illegal char  验证非空
 * @param str
 * @returns
 */
 function checkBlankChar(str){
	 if(str==null) return true;
    var finalStr = str.trim();
    if (finalStr == "") return true; 
    return false;
 }

/**
 * Replace string space. 删除掉字符串中的空格
 */
String.prototype.trim=function(){     
    return this.replace(/(^\s*)|(\s*$)/g, '');  
};

var dateFormatErrorMessage = "格式是：YYYY-MM-DD。";

var validateIllegagChar="%_'";//  非法字符提示信息
/**
 * Validate illegal char  验证非法字符
 * @param str
 * @returns
 */
 function checkIllegaeChar(str){
    var pat=new RegExp("[%_']","i");
    return pat.test(str);
 }
 
 /**
 * Validate chiness  验证汉字
 * @param str
 * @returns
 */
 function checkChiness(str){
 	var reg = /^[\u4e00-\u9fa5]+$/i;
    return reg.test(str);
 }
 
/**
 * Validate email  验证邮箱
 * @param str
 * @returns
 */
  function checkEmail(str){
 	var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return reg.test(str);
 }
 
/**
 * Validate qq  验证qq
 * @param str
 * @returns
 */
function checkQq(str){
 	var reg = /^[1-9]\d{4,8}$/;
    return reg.test(str);
 }
 
/**
 * Validate mobile phone  验证手机号
 * @param str
 * @returns
 */
function checkMobPho(str){
   var reg = /^1[3|4|5|8][0-9]\d{4,8}$/;
   return reg.test(str);
}

 /**
  * Validate date format  验证字期格式
  * @param str
  * @returns
  */
function checkDateFormat(str){
    var reg = /^\d{4}-\d{1,2}-\d{1,2}$/;
    return reg.test(str);
}

/**
 * Validate price format  验证价格格式
 * @param str
 * @returns
 */
function checkPriceFormat(str){
	var reg = /^\d+$/;
	return reg.test(str);
}

/**
 * Validate int format  验证正整数
 * @param str
 * @returns
 */
function checkIntFormat(str){
	var reg = /^[1-9]\d*$/;
	return reg.test(str);
}

/**
 * Validate int format  验证小数
 * @param str
 * @returns
 */
function checkDecimal(str){
	var reg = /^-?\d+\.\d+$/;
	return reg.test(str);
}

/**
 * Validate start date before end date.  验证起始日期是否大于结束日期
 * @param stareDateStr
 * @param endDateStr
 * @returns {Boolean}
 */
function checkDateStartThenEnd(stareDateStr, endDateStr){
	 var arys= new Array();  
     if(stareDateStr != null && endDateStr != null) {  
	     arys=stareDateStr.split('-');  
	     var startdate=new Date(arys[0],parseInt(arys[1]-1),arys[2]);   
	     arys=endDateStr.split('-');  
		 var endDate=new Date(arys[0],parseInt(arys[1]-1),arys[2]);   
		 if(startdate > endDate) {
	        return true;  
        }
	}
	return false;
}

/**
* Judge brower style and version
*/
function browserIsIe6Or7(){
	var bro=$.browser;  
    if(bro.msie) {
    	var binfo=bro.version.charAt(0);
    	if('6' == binfo || '7' == binfo){
    		return true;
    	}
    }
    return false;  
}
/**
 * 判断字符串长度是否在最大值和最小值之间
 * @param str  等待验证的字符串
 * @param minLength  最小长度
 * @param maxLength  最大长度
 * @return boolean 
 */
function checkStrLength(str, minLength, maxLength){
	var finalStr = str.trim();
	var strLen = finalStr.length;
	return strLen<minLength || strLen>maxLength;
}


/**
 * 判断字符串是否只包含大小写字母和数字
 * @param str  等待验证的字符串
 * @return boolean 
 */
function checkCharNumber(str){
	var reg = /^[A-Za-z0-9]+$/;
	return !(reg.test(str));
}
/**
 * 判断字符串是否只包含大小写字母,数字和中文
 * @param str  等待验证的字符串
 * @return boolean 
 */
function checkChineseNumber(str){
	var reg = /^[\u0391-\uFFE5A-Za-z0-9]+$/;
	return !(reg.test(str));
}	
