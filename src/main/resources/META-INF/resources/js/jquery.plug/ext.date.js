
Date.prototype.getWeek = function() {
	var date = this;
	var date2 = new Date(date.getTime());
	date2.setMonth(0, 1);
	var day1 = date.getDay();
	if (day1 == 0)
		day1 = 7;
	var day2 = date2.getDay();
	if (day2 == 0)
		day2 = 7;
	d = Math.round((date.getTime() - date2.getTime() + (day2 - day1)* (24 * 60 * 60 * 1000)) / 86400000);
	return Math.ceil(d / 7) + 1;
};


Date.prototype.format = function (format) {
	if (!format)
		format = "yyyy-MM-dd HH:mm:ss";

	var o = {
		"M+" : this.getMonth() + 1, //月
		"d+" : this.getDate(), //天
		"H+" : this.getHours(), //小时
		"m+" : this.getMinutes(), //分钟
		"s+" : this.getSeconds(), //秒
		"W+" : this.getWeek(), //周
		"q+" : Math.floor((this.getMonth() + 3) / 3), //季度
		"S" : this.getMilliseconds() //毫秒
	};

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}

	for (var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}

	return format;
};


Date.prototype.isLeapYear = function (year) {
	if(!year)
		year = this.getFullYear();
	return ((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0); 
};


Date.prototype.getDaysInMonth = function (year, month) {
	if(!year)
		year = this.getFullYear();
	if(!month)
		month = this.getMonth() + 1
	return [31, (this.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
};


Date.prototype.add = function(milliseconds) {
	if (!milliseconds)
		milliseconds = 0;
	var time = this.getTime();
	var ntime = time + milliseconds;
	return new Date(ntime);
};

Date.prototype.addSeconds = function(seconds){
	if (!seconds)
		seconds = 0;
	return this.add(seconds*1000);
};

Date.prototype.addMinutes = function (minutes) { 
     return this.add(minutes * 60000);
};

Date.prototype.addHours = function (hours) { 
	return this.add(hours * 3600000);
};

Date.prototype.addDays = function (days) {
	return this.add(days * 24 * 3600000);
};

Date.prototype.addWeeks = function (weeks) { 
	return this.addDays(weeks * 7);
};

Date.prototype.addMonths = function (months) {
	var n = this.getDate();
	var d = new Date();
	d.setDate(1);
	d.setMonth(this.getMonth() + months * 1);
	d.setDate(Math.min(n, this.getDaysInMonth(this.getFullYear(), this.getMonth())));
	return d;
};

Date.prototype.addYears = function (years) {
	return this.addMonths(years * 12);
};
