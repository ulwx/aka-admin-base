
String.prototype.replaceAll = function(s1,s2){   
    return this.replace(new RegExp(s1,"gm"),s2);   
};

String.prototype.endWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length)
	     return false;
	  if(this.substring(this.length-s.length)==s)
	     return true;
	  else
	     return false;
};
	 
String.prototype.startWith=function(s){
	  if(s==null||s==""||this.length==0||s.length>this.length)
	   return false;
	  if(this.substr(0,s.length)==s)
	     return true;
	  else
	     return false;
};

function isInteger(obj) {
	 return typeof obj === 'number' && obj%1 === 0
}

function arrayStrToInt(array){
	if(array!=null && array.length>0){
		for(var i=0; i<array.length; i++){
		   var v=parseInt(array[i]);
		   if(isInteger(v) ){
			   array[i] =v;
		   }else{
			   array[i] =null;
		   }
		}
		return array;
	}else{
		return [];
	}
}

function arrayIntToStr(array){
	if(array!=null && array.length>0){
		for(var i=0; i<array.length; i++){
		   var v=array[i]+"";
		   array[i] =v;
		}
		return array;
	}else{
		return [];
	}
}

if (!Array.indexOf) {  
    Array.prototype.indexOf = function (obj) {  
        for (var i = 0; i < this.length; i++) {  
            if (this[i] == obj) {  
                return i;  
            }  
        }  
        return -1;  
    }  
} 
/**
 * 判断是否为数组类型
 * @returns {String}
 */
function isArray(obj){
	 if(typeof obj=="object"&&obj.constructor==Array){
		 return true;
	 }
	 return false;
}

function formatDateTime(date) {
	var year = date.getFullYear();
	var month = ('0' + (date.getMonth() + 1)).slice(-2);
	var day = ('0' + date.getDate()).slice(-2);
	var hours = ('0' + date.getHours()).slice(-2);
	var minutes = ('0' + date.getMinutes()).slice(-2);
	var seconds = ('0' + date.getSeconds()).slice(-2);
	return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds;
}
function getDate(date){
	var myDate
	if(date){
		myDate=date;
	}else{
		myDate = new Date();
	}
	
	var year = myDate.getFullYear()+"";
	var month = myDate.getMonth();
	month = month+1;
	month = month+"";
	if(month.length==1){
		month = "0"+month;
	}
	var day = myDate.getDate();
	day = day+"";
	if(day.length==1){
		day = "0"+day;
	}
	return year+"-"+month+"-"+day;
}
function getMonthDay(date){
	var myDate
	if(date){
		myDate=date;
	}else{
		myDate = new Date();
	}
	var year = myDate.getFullYear()+"";
	var month = myDate.getMonth();
	month = month+1;
	month = month+"";
	if(month.length==1){
		month = "0"+month;
	}
	var day = myDate.getDate();
	day = day+"";
	if(day.length==1){
		day = "0"+day;
	}
	return month+"-"+day;
}
/**jquery扩展****/
if(jQuery){
	
	/**
	 * 序列化元素下的input元素，生成key-value的json对象
	 */
	jQuery.fn.serializeObject = function() {
		var o = {};
		var a = this.serializeArray();
		$.each(a, function() {
			if (o[this.name]) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
	};
}
/*******/
//This function creates a new anchor element and uses location
//properties (inherent) to get the desired URL data. Some String
//operations are used (to normalize results across browsers).
/*
 var myURL = parseURL('http://abc.com:8080/dir/index.html?id=255&m=hello#top');

 myURL.file;     // = 'index.html'
 myURL.hash;     // = 'top'
 myURL.host;     // = 'abc.com'
 myURL.query;    // = '?id=255&m=hello'
 myURL.params;   // = Object = { id: 255, m: hello }
 myURL.path;     // = '/dir/index.html'
 myURL.segments; // = Array = ['dir', 'index.html']
 myURL.port;     // = '8080'
 myURL.protocol; // = 'http'
 myURL.source;   // = 'http://abc.com:8080/dir/index.html?id=255&m=hello#top'
 */
function parseURL(url) {
	var a = document.createElement('a');
	a.href = url;
	return {
		source : url, 
		protocol : a.protocol.replace(':', ''),
		host : a.hostname,
		port : a.port,
		query : a.search,
		params : (function() {
			
			return parseSearchString(a.search);
		})(),
		file : (a.pathname.match(/\/([^\/?#]+)$/i) || [ , '' ])[1],
		hash : a.hash.replace('#', ''),
		path : a.pathname.replace(/^([^\/])/, '/$1'),
		relative : (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [ , '' ])[1],
		segments : a.pathname.replace(/^\//, '').split('/')
	};
}
function getMiddlePosition(iHeight, iWidth){
	var iTop = ($(window).height() - 60 - iHeight) / 2; //获得窗口的垂直位置;
	var iLeft = ($(window).width() - 20 - iWidth) / 2; //获得窗口的水平位置;
	var position={};
	position.left=iLeft;
	position.top=iTop;
	return position;
}

function parseSearchString(searchStr){
	var ret = {}, seg = searchStr.replace(/^\?/, '').split('&'), len = seg.length, i = 0, s;
	for (; i < len; i++) {
		if (!seg[i]) {
			continue;
		}
		s = seg[i].split('=');
		
		if(s[1]!=null && typeof s[1] =='string'){
			s[1]=s[1].replaceAll("\\+"," ");
		}
		
		var elv=decodeURIComponent(s[1]);
		if(elv!=null && typeof elv =='string'){
			//elv=elv.replaceAll("\\+"," ");
		}
		if(isNull(ret[s[0]])){
			ret[s[0]] =elv;
		}else{
			if(isArray(ret[s[0]])){
				ret[s[0]].push(elv);
			}else{
				ret[s[0]]=[ret[s[0]]];
				ret[s[0]].push(elv);
			}
		}
	}
	return ret;
}
function openWindow(url, name, iWidth, iHeight) {

	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  //获得窗口的水平位置;

	var win = window
			.open(
					url,
					name,
					'height='
							+ iHeight
							+ ',width='
							+ iWidth
							+ ',top='
							+ iTop
							+ ',left='
							+ iLeft
							+ ',menubar=no,scrollbars=yes,resizable=yes,status=no,toolbar=no ,location=no');

	win.focus();

	return win;
}

function fillsize(percent){
	var bodyWidth = document.body.clientWidth;
	return (bodyWidth-90)*percent;
}



function debuglog(msg){
	console.log(msg);
}

//格式化货币值
function formatCurrency(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
	num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
	cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + '￥' + num + '.' + cents);
	}

//格式化货币值
function formatRemoteCurrency(num) {
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num))
	num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10)
	cents = "0" + cents;
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
	num = num.substring(0,num.length-(4*i+3))+','+
	num.substring(num.length-(4*i+3));
	return (((sign)?'':'-') + num + '.' + cents);
}



function syncGet(url,myheaders){
	if(myheaders){
	}else{
		myheaders={};
	}
	var ret = '';
	$.ajax({
    	dataType: 'text',
    	type: 'GET',
    	async: false,
    	cache: false,
		headers: myheaders,
    	url: url,
    	success: function(data){
    		ret=data;
    	},
    	error: function(jqXHR, textStatus, errorThrown){
    		$("body").hideLoading();
			ret=$.toJSON({
				status:0,
				message: "提示"+jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown
			});
			console.log(ret)
			ret="";
    		//alert("提示", jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown);
  		  
    	}
    });
	return ret;
}
function htmlEncode(value){
	return $('<div/>').text(value).html();
}
//Html解码获取Html实体
function htmlDecode(value){
	return $('<div/>').html(value).text();
}
function escapeString(str) {
	return str
		.replace(/\\/g, '\\\\')   // 反斜杠
		.replace(/'/g, "\\'")     // 单引号
		.replace(/"/g, '\\"')     // 双引号
		.replace(/\n/g, '\\n')    // 换行符
		.replace(/\r/g, '\\r')    // 回车符
		.replace(/\t/g, '\\t')    // 制表符
		.replace(/\b/g, '\\b')    // 退格符
		.replace(/\f/g, '\\f')    // 换页符
		.replace(/\v/g, '\\v')    // 垂直制表符
		.replace(/\0/g, '\\0');   // null 字符
}
function syncPostJSON(url,postData,myheaders){
	if(myheaders){
	}else{
		myheaders={};
	}
	var ret = null;
	$.ajax({
    	dataType: 'text',
    	type: 'POST',
    	async: false,
    	data: postData,
    	cache: false,
		headers: myheaders,
    	url: url,
    	success: function(data){
    		ret = $.evalJSON($.trim(data));
    	},
    	error: function(jqXHR, textStatus, errorThrown){
    		$("body").hideLoading();
			ret={
				status:0,
				message: "提示"+jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown
			}
;
    		//alert("提示", jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown);
    		  
    	}
    });
	return ret;
};
function syncGetJSON(url,myheaders){
	if(myheaders){
	}else{
		myheaders={};
	}
	var ret = null;
	$.ajax({
    	dataType: 'text',
    	type: 'GET',
    	async: false,
    	cache: false,
		headers: myheaders,
    	url: url,
    	success: function(data){
    		ret = $.evalJSON($.trim(data));
    	},
    	error: function(jqXHR, textStatus, errorThrown){
    		$("body").hideLoading();
			ret={
				status:0,
				message: "提示"+jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown
			}

    		//alert("提示", jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown);
  		  
    	}
    });
	return ret;
};

function syncPost(url,postData,myheaders){
	if(myheaders){
	}else{
		myheaders={};
	}
	var ret = '';
	$.ajax({
    	dataType: 'text',
    	type: 'POST',
    	async: false,
    	data: postData,
    	cache: false,
		headers: myheaders,
    	url: url,
    	success: function(data){
    		//ret = $.evalJSON($.trim(data));
    		ret=data;
    	},
    	error: function(jqXHR, textStatus, errorThrown){
    		$("body").hideLoading();
			ret=$.toJSON({
				status:0,
				message: "提示"+jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown
			});
			console.log(ret)
			ret="";
    		//$.messager.alert("提示", jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown);
    		  
    	}
    });
	return ret;
};
function syncPost(url,postData,contentType,myheaders){
	if(myheaders){
	}else{
		myheaders={};
	}
	var ret = '';
	$.ajax({
		contentType: contentType,
		dataType: 'text',
		type: 'POST',
		async: false,
		headers: myheaders,
		data: postData,
		cache: false,
		url: url,
		success: function(data){
			//ret = $.evalJSON($.trim(data));
			ret=data;
		},
		error: function(jqXHR, textStatus, errorThrown){
			$("body").hideLoading();
			ret=$.toJSON({
				status:0,
				message: "提示"+jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown
			});
			console.log(ret)
			ret="";
			//$.messager.alert("提示", jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown);

		}
	});
	return ret;
};
/**
 * 
 * @param url
 * @param fun ：格式为 function(data){...}
 * @returns {String}
 */
function asynGet(url,fun,myheaders){
	if(myheaders){
	}else{
		myheaders={};
	}
	$.ajax({
    	dataType: 'text',
    	type: 'GET',
    	async: true,
    	cache: false,
		headers: myheaders,
    	url: url,
    	success: fun,
    	error: function(jqXHR, textStatus, errorThrown){
    		$("body").hideLoading();
    		$.messager.alert("提示", jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown);
    	}
    });
}

/**
 * 
 * @param url
 * @param fun ：格式为 function(data){...}
 * @returns {String}
 */
function asynPost(url,fun,myheaders){
	if(myheaders){
	}else{
		myheaders={};
	}
	$.ajax({
    	dataType: 'text',
    	type: 'POST',
    	async: true,
    	cache: false,
		headers: myheaders,
    	url: url,
    	success: fun,
    	error: function(jqXHR, textStatus, errorThrown){
    		$("body").hideLoading();
    		$.messager.alert("提示", jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown);
    	}
    });
}

/**
 * 
 * @param url
 * @params:参数格式{"base_code":''}
 * @param fun ：格式为 function(data){...}
 * @returns {String}
 */
function asynPostParam(URL,params,fun,myheaders){
	if(myheaders){
	}else{
		myheaders={};
	}
	$.ajax({
    	dataType: 'text',
    	type: 'POST',
    	async: true,
    	cache: false,
    	url: URL,
		headers: myheaders,
    	data:params,
    	success: fun,
    	error: function(jqXHR, textStatus, errorThrown){
    		$("body").hideLoading();
    		$.messager.alert("提示", jqXHR.readyState+","+jqXHR.status+","+jqXHR.statusText +","+jqXHR.responseText+","+textStatus+","+errorThrown);
    	}
    });
}



 /**获得日期部分*/
function DateGetPart(dateStr){
	var value=$.trim(dateStr);
	if(value.length>10) {
		value=value.substring(0,10);
	}
	return value;
}

/**
 * 对json对象格式化输出，输出的格式比较美观
 * 用法 ：format_str = JsonUti.convertToString(jsonObj); 
 */
var JsonUti = {
	    //定义换行符
	    n: "\n",
	    //定义制表符
	    t: "   ",
	    //转换String
	    convertToString: function(obj) {
	        return JsonUti.__writeObj(obj, 1);
	    },
	    //写对象
	    __writeObj: function(obj //对象
	    , level //层次（基数为1）
	    , isInArray) { //此对象是否在一个集合内
	        //如果为空，直接输出null
	        if (obj == null) {
	            return "null";
	        }
	        //为普通类型，直接输出值
	        if (obj.constructor == Number || obj.constructor == Date || obj.constructor == String || obj.constructor == Boolean) {
	            var v = obj.toString();
	            var tab = isInArray ? JsonUti.__repeatStr(JsonUti.t, level - 1) : "";
	            if (obj.constructor == String || obj.constructor == Date) {
	                //时间格式化只是单纯输出字符串，而不是Date对象
	                return tab + ("\"" + v + "\"");
	            }
	            else if (obj.constructor == Boolean) {
	                return tab + v.toLowerCase();
	            }
	            else {
	                return tab + (v);
	            }
	        }
	        //写Json对象，缓存字符串
	        var currentObjStrings = [];
	        //遍历属性
	        for (var name in obj) {
	            var temp = [];
	            //格式化Tab
	            var paddingTab = JsonUti.__repeatStr(JsonUti.t, level);
	            temp.push(paddingTab);
	            //写出属性名
	            temp.push(name + " : ");
	            var val = obj[name];
	            if (val == null) {
	                temp.push("null");
	            }
	            else {
	                var c = val.constructor;
	                if (c == Array) { //如果为集合，循环内部对象
	                    temp.push(JsonUti.n + paddingTab + "[" + JsonUti.n);
	                    var levelUp = level + 2; //层级+2
	                    var tempArrValue = []; //集合元素相关字符串缓存片段
	                    for (var i = 0; i < val.length; i++) {
	                        //递归写对象
	                        tempArrValue.push(JsonUti.__writeObj(val[i], levelUp, true));
	                    }
	                    temp.push(tempArrValue.join("," + JsonUti.n));
	                    temp.push(JsonUti.n + paddingTab + "]");
	                }
	                else if (c == Function) {
	                    temp.push("[Function]");
	                }
	                else {
	                    //递归写对象
	                    temp.push(JsonUti.__writeObj(val, level + 1));
	                }
	            }
	            //加入当前对象“属性”字符串
	            currentObjStrings.push(temp.join(""));
	        }
	        return (level > 1 && !isInArray ? JsonUti.n: "") //如果Json对象是内部，就要换行格式化
	        + JsonUti.__repeatStr(JsonUti.t, level - 1) + "{" + JsonUti.n //加层次Tab格式化
	        + currentObjStrings.join("," + JsonUti.n) //串联所有属性值
	        + JsonUti.n + JsonUti.__repeatStr(JsonUti.t, level - 1) + "}"; //封闭对象
	    },
	    __isArray: function(obj) {
	        if (obj) {
	            return obj.constructor == Array;
	        }
	        return false;
	    },
	    __repeatStr: function(str, times) {
	        var newStr = [];
	        if (times > 0) {
	            for (var i = 0; i < times; i++) {
	                newStr.push(str);
	            }
	        }
	        return newStr.join("");
	    }
	};





/**************
 var myurl=new MyURL.URL("http://www.baidu.com?a=1");
    
    myurl.set("b","hello"); //添加了b=hello
    alert (myurl.url());
    
    myurl.remove("b"); //删除了b
    
    alert(myurl.get ("a"));//取参数a的值，这里得到1
    
    myurl.set("a",23); //修改a的值为23
    
    alert (myurl.url());
 ****************/
var MyURL=(function(lg){
    var objURL=function(url){
        this.ourl=url||window.location.href;
        this.href="";//?前面部分
        this.params={};//url参数对象
        this.jing="";//#及后面部分
        this.init();
    }
    //分析url,得到?前面存入this.href,参数解析为this.params对象，#号及后面存入this.jing
    objURL.prototype.init=function(){
        var str=this.ourl;
        var index=str.indexOf("#");
        if(index>0){
            this.jing=str.substr(index);
            str=str.substring(0,index);
        }
        index=str.indexOf("?");
        if(index>0){
            this.href=str.substring(0,index);
            str=str.substr(index+1);
            var parts=str.split("&");
            for(var i=0;i<parts.length;i++){
                var kv=parts[i].split("=");
                var oldv=this.params[kv[0]];
                if(oldv!=null){
                	if(!isArray(oldv)){
                		oldv=[oldv];
                	}
                	oldv.push(kv[1]);
                }else{
                	oldv=kv[1];
                }
                this.params[kv[0]]=oldv;
            }
        }
        else{
            this.href=this.ourl;
            this.params={};
        }
    }
	    //只是修改this.params
    objURL.prototype.add=function(key,val){
		var oldv=this.params[key];;
		if(oldv!=null){
			if(!isArray(oldv)){
				oldv=[oldv];
			}
			if(isArray(val)){
				oldv=oldv.concat(val);
			}else{
				oldv.push(val);
			}
			
		}else{
			oldv=val;
		}
		this.params[key]=oldv;
    }
    //只是修改this.params
    objURL.prototype.set=function(key,val){
        this.params[key]=val;
    }
    //只是设置this.params
    objURL.prototype.remove=function(key){
        this.params[key]=undefined;
    }
    objURL.prototype.putAll=function(map){
    	for(var k in map){
    		if(map[k]){
	    		var v=map[k];
	    		this.add(k,v);
    		}
    	}
    }
    //根据三部分组成操作后的url
    objURL.prototype.url=function(){
        var strurl=this.href;
        var objps=[];//这里用数组组织,再做join操作
        for(var k in this.params){
            if(this.params[k]){
				if(isArray(this.params[k])){
					for(var j=0; j<this.params[k].length; j++){
						objps.push(k+"="+this.params[k][j]);
					}
				}else{
					objps.push(k+"="+this.params[k]);
				}
                
            }
        }
        if(objps.length>0){
            strurl+="?"+objps.join("&");
        }
        if(this.jing.length>0){
            strurl+=this.jing;
        }
        return strurl;
    }
    //得到参数值
    objURL.prototype.get=function(key){
        return this.params[key];
    }    
    lg.URL=objURL;
    return lg;
}(MyURL||{}));



/**
拷贝方法
src:源
isDeep:是否是深拷贝. true:是深拷贝  false：浅拷贝
**/
function myclone(src,isDeep, des){
	if(isDeep==undefined){
		isDeep=true;
	}
	if(des==null){
		des={};
	}
	return  $.extend( isDeep, des, src );
}

function objToQueryStr(obj){
   return  $.param(obj,true);
}


function stopBubble(e) { 
	//如果提供了事件对象，则这是一个非IE浏览器 
	if ( e && e.stopPropagation ) 
		//因此它支持W3C的stopPropagation()方法 
		e.stopPropagation(); 
	else 
		//否则，我们需要使用IE的方式来取消事件冒泡 
		window.event.cancelBubble = true; 
	
}

//阻止浏览器的默认行为
function stopDefault( e ) { //阻止默认浏览器动作(W3C) 
	if ( e && e.preventDefault ) e.preventDefault(); 
	//IE中阻止函数器默认动作的方式 
	else window.event.returnValue = false; 
	
	return false; 
}
	
	
/***********************************************************/
/**定义js对象的方法，例子使用如下*/
(function() {
    // 当前是否处于创建类的阶段
    var initializing = false;
    jClass = function() { }; //在全局window里定义了JClass
    jClass.extend = function(prop) {
        // 如果调用当前函数的对象（这里是函数）不是Class，则是父类
        var baseClass = null;
        if (this !== jClass) {
            baseClass = this;
        }
        // 本次调用所创建的类（构造函数）
        function F() {
            // 如果当前处于实例化类的阶段，则调用init原型函数
            if (!initializing) {
                // 如果父类存在，则实例对象的baseprototype指向父类的原型
                // 这就提供了在实例对象中调用父类方法的途径
                if (baseClass) {
                    this._superprototype = baseClass.prototype;
                }
                this.init.apply(this, arguments);
            }
        }
        // 如果此类需要从其它类扩展
        if (baseClass) {
            initializing = true;
            F.prototype = new baseClass();
            F.prototype.constructor = F;
            initializing = false;
        }
        // 新创建的类自动附加extend函数
        F.extend = arguments.callee;

        // 覆盖父类的同名函数
        for (var name in prop) {
            if (prop.hasOwnProperty(name)) {
                // 如果此类继承自父类baseClass并且父类原型中存在同名函数name
                if (baseClass &&
                typeof (prop[name]) === "function" &&
                typeof (F.prototype[name]) === "function" &&
                /\b_super\b/.test(prop[name])) {
                    // 重定义函数name - 
                    // 首先在函数上下文设置this._super指向父类原型中的同名函数
                    // 然后调用函数prop[name]，返回函数结果
                    // 注意：这里的自执行函数创建了一个上下文，这个上下文返回另一个函数，
                    // 此函数中可以应用此上下文中的变量，这就是闭包（Closure）。
                    // 这是JavaScript框架开发中常用的技巧。
                    F.prototype[name] = (function(name, fn) {
                        return function() {
                            this._super = baseClass.prototype[name];
                            return fn.apply(this, arguments);
                        };
                    })(name, prop[name]);
                } else {
                    F.prototype[name] = prop[name];
                }
            }
        }
        return F;
    };
})();
/**使用的例子
// 经过改造的jClass
var Person = jClass.extend({
    init: function(name) {
        this.name = name;
    },
    getName: function(prefix) {
        return prefix + this.name;
    }
});
var Employee = Person.extend({
    init: function(name, employeeID) {
        //  调用父类的方法
        this._super(name);
        this.employeeID = employeeID;
    },
    getEmployeeIDName: function() {
        // 注意：我们还可以通过这种方式调用父类中的其他函数
        var sname=this.getName(); //自身对象方法的调用
        var name = this._superprototype.getName.call(this, "Employee name: ");
        return name + ", Employee ID: " + this.employeeID;
    },
    getName: function() {
        //  调用父类的方法
        return this._super("Employee name: ");
    }
});

var zhang = new Employee("ZhangSan", "1234");
console.log(zhang.getName());   // "Employee name: ZhangSan"
console.log(zhang.getEmployeeIDName()); // "Employee name: ZhangSan, Employee ID: 1234"
**/

/****************************************************************/


/**
 * 生成uuid的函数
 */
function myuuid(len, radix) {
	if (typeof crypto === 'object') {
		if (typeof crypto.randomUUID === 'function') {
			return crypto.randomUUID();
		}
		if (typeof crypto.getRandomValues === 'function' && typeof Uint8Array === 'function') {
			const callback = (c) => {
				const num = Number(c);
				return (num ^ (crypto.getRandomValues(new Uint8Array(1))[0] & (15 >> (num / 4)))).toString(16);
			};
			return ([1e7]+-1e3+-4e3+-8e3+-1e11).replace(/[018]/g, callback);
		}
	}
	let timestamp = new Date().getTime();
	let perforNow = (typeof performance !== 'undefined' && performance.now && performance.now() * 1000) || 0;
	return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
		let random = Math.random() * 16;
		if (timestamp > 0) {
			random = (timestamp + random) % 16 | 0;
			timestamp = Math.floor(timestamp / 16);
		} else {
			random = (perforNow + random) % 16 | 0;
			perforNow = Math.floor(perforNow / 16);
		}
		return (c === 'x' ? random : (random & 0x3) | 0x8).toString(16);
	});
  }



/** 
* 获取浏览器版本 
*/  
/** 
 * 获取浏览器 
 */  
 function getMyBrowserType(){
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
   // alert(userAgent);
    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器
    var isIE = userAgent.indexOf("MSIE") > -1 ; //判断是否IE浏览器
    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器
    var isSafari = userAgent.indexOf("Safari") > -1; //判断是否Safari浏览器
    var isChrome = userAgent.indexOf("Chrome") > -1; //判断Chrome浏览器
    var isEdge = userAgent.indexOf("Edge") > -1; //判断是否IE的Edge浏览器
    var isIE11 = (/Trident\/7\./).test(navigator.userAgent);
    var ret='';
    if(isIE)
    {
	    var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
	    reIE.test(userAgent);
	    var fIEVersion = parseFloat(RegExp["$1"]);
	    if(fIEVersion == 7)
	    { ret= "ie7";}
	    else if(fIEVersion == 8)
	    { ret= "ie8";}
	    else if(fIEVersion == 9)
	    { ret= "ie9";}
	    else if(fIEVersion == 10)
	    { ret= "ie10";}
	    else
	    { ret= "0"}//IE版本过低
	 }else if (isFF) {
		 ret= "firefox";
    }else if (isOpera) {
    	ret= "opera";
    }else if(isChrome){
    	ret= "chrome";
    }else if(isSafari){
    	ret= "safari";
    }else if(isEdge){
    	ret= "ieedge";
    }else if(isIE11){
    	ret= "ie11";
    }
    
    //alert(ret);
    return ret;
 }
 


function getRsaPublicKey(){
	var publickey="MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIv+drAd9Tv9WgSu9pEM3LPTbJAQ6CT4KdVVTxxZ9AY8h0cDSdlfzJZkjv1q7K7SPsajwoExXf4fQZl7n0OENwMCAwEAAQ==";
	return publickey;
}

function isNull(exp){
	if ( typeof(exp)=="undefined" || exp==undefined || exp==null )
	{
		return true;
	}else{
		return false;
	}
}



function isArrayEmpty(array){
	if(isNull(array) ){
		return true;
	}
	
	if($.type(array)=='array'){
		if(array==null || array.length==0){
			return true;
		}
	}
	
	return false;
	
}
function isEmpty(exp){
	if(isNull(exp) ){
		return true;
	}else{
		if($.type(exp)=='string'){
			if($.trim(exp)==''){
				return true;
			}
		}else if($.type(exp)=='array'){
			if(exp==null || exp.length==0){
				return true;
			}
		}

		
		return false;
	}
}

function isEmptyObj(obj){
	return Object.prototype.isPrototypeOf(obj) && Object.keys(obj).length === 0
}

/**
 ** 加法函数，用来得到精确的加法结果
 ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
 ** 调用：accAdd(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
function accAdd(arg1, arg2) {
    var r1, r2, m, c;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        } else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    } else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
}

//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg) {
    return accAdd(arg, this);
};

/**
 ** 减法函数，用来得到精确的减法结果
 ** 说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
 ** 调用：accSub(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
function accSub(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

// 给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.sub = function (arg) {
    return accMul(arg, this);
};

/**
 ** 乘法函数，用来得到精确的乘法结果
 ** 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
 ** 调用：accMul(arg1,arg2)
 ** 返回值：arg1乘以 arg2的精确结果
 **/
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}

// 给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function (arg) {
    return accMul(arg, this);
};

/** 
 ** 除法函数，用来得到精确的除法结果
 ** 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
 ** 调用：accDiv(arg1,arg2)
 ** 返回值：arg1除以arg2的精确结果
 **/
function accDiv(arg1, arg2) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
    }
    try {
        t2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
    }
    with (Math) {
        r1 = Number(arg1.toString().replace(".", ""));
        r2 = Number(arg2.toString().replace(".", ""));
        return (r1 / r2) * pow(10, t2 - t1);
    }
}

//给Number类型增加一个div方法，调用起来更加方便。
Number.prototype.div = function (arg) {
    return accDiv(this, arg);
};

function escapeJquery(srcString) {
	// 转义之后的结果
	var escapseResult = srcString;

	// javascript正则表达式中的特殊字符
	var jsSpecialChars = ["\\", "^", "$", "*", "?", ".", "+", "(", ")", "[",
		"]", "|", "{", "}"];

	// jquery中的特殊字符,不是正则表达式中的特殊字符
	var jquerySpecialChars = ["~", "`", "@", "#", "%", "&", "=", "'", "\"",
		":", ";", "<", ">", ",", "/"];

	for (var i = 0; i < jsSpecialChars.length; i++) {
		escapseResult = escapseResult.replace(new RegExp("\\"
			+ jsSpecialChars[i], "g"), "\\"
			+ jsSpecialChars[i]);
	}

	for (var i = 0; i < jquerySpecialChars.length; i++) {
		escapseResult = escapseResult.replace(new RegExp(jquerySpecialChars[i],
			"g"), "\\" + jquerySpecialChars[i]);
	}

	return escapseResult;
}