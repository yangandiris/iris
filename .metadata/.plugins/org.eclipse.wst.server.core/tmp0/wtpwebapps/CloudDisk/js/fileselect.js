document.oncontextmenu = function() {
	return false;
}


// ----------- 区域选择关键方法 -----------------------
$(document).ready(function() {
	var bgClass='filelist-item-over'
	var select='.filelist'
	$("#filepages").on('mousedown',select, function(event) {
		_this = $(this);
		$('#filehandlemodal').remove();
		if (event.ctrlKey) { // Ctrl键多选
			$('.networkdisk-fileopration').show();
			$('.networkdisk-fileopration').find('li:eq(3)').hide()
			if ($(this).find("img").attr("src") == "img/bg/floder.png") {
			    $('.networkdisk-fileopration').find('li:eq(0)').hide()
		    }
			if (this.className.indexOf(bgClass) == -1) {
				this.className += " " + bgClass;
			} else {
				this.className = this.className.replace(bgClass, "");
			}
					
		} else if (event.which == 3) {// 右键弹出文件操作框
			var path=$(this).find("span").html();
			var parent=$(this).find("parent").html();
			var fileid=$(this).find("fileid").html();
			var isfolder = '';
			var folder = '<li><a target="_blank" href="'
					+ path
					+ '" download "><span class="glyphicon glyphicon-download"'
					+ 'style="color:#B261B3;" > </span>  下   载</a></li>'
			if ($(this).find("img").attr("src") != "img/bg/floder.png") {

				isfolder = folder;
				
			}

			var fileevt = '<div id="filehandlemodal" tabindex="1" hidden>'
					+ '<ol class="file-menu">'
					+ isfolder
					+ '<li><a href="javascript:void(0)" ><span class="glyphicon glyphicon-retweet"'
					+ 'style="color: #B261B3;"></span>  移   动</a></li>'
					+ '<li><a href="javascript:void(0)" ><span class="glyphicon glyphicon-remove"'
					+ 'style="color:#B261B3;"> </span>  删   除</a></li>'
					+ '<li><a href="javascript:void(0)"><span class="glyphicon glyphicon-pencil"'
					+ 'style="color:#B261B3;"> </span>  重命名</a></li>'
					+ '<li><a href="javascript:void(0)"><span class="glyphicon glyphicon-lock"'
					+ 'style="color:#B261B3;"> </span>  保险箱</a></li>	'
					+ '</ol>' + '</div>';
			$("body").append(fileevt);
			$('#filehandlemodal').find('li:eq(0)').mousedown(function(event) {
				event.stopPropagation();
			})
			$('#filehandlemodal').find('li:eq(-1)').mousedown(function(event) {
				event.stopPropagation();
				filelock();
			})
			// 文件重命名
			$('#filehandlemodal').find('li:eq(-2)').mousedown(function(event) {
				event.stopPropagation();
				filerename();
			})
			// 文件删除
			$('#filehandlemodal').find('li:eq(-3)').mousedown(function(event) {
		        event.stopPropagation();
				deletefile();
			})
			//文件移动
			$('#filehandlemodal').find('li:eq(-4)').mousedown(function(event) {
				event.stopPropagation();
				filemove();						
			})

			$(select).removeClass(bgClass);
			this.className += " " + bgClass;
			$('#filehandlemodal').show();
			$('#filehandlemodal').position({
						my : "left top",
						at : "right",
						of : event

					})
		
				
		} else {// 单选
            //$(select).removeClass(bgClass);
			_this.siblings().removeClass(bgClass);
        	$('.networkdisk-fileopration').show();
			$('.networkdisk-fileopration').find('li:eq(3)').show();
			if ($(this).find("img").attr("src") == "img/bg/floder.png") {

				$('.networkdisk-fileopration').find('li:eq(0)').hide()
				
			}else{
				$('.networkdisk-fileopration').find('li:eq(0)').show()
			}

			if (this.className.indexOf(bgClass) == -1) {
				this.className += " " + bgClass;

			} else {
				this.className = this.className.replace(bgClass, "");
			}

		}
		 event.stopPropagation();//阻止事件冒泡
		
	})
$(document).on('blur','#filehandlemodal',function(){
			    $('#filehandlemodal').remove();
			})
$(document).on('mousedown',function(){
			//$('.filelist').removeClass(bgClass);
			$('.networkdisk-fileopration').hide();
			$('#filehandlemodal').remove();
			$('.networkdisk-fileopration').find('li:eq(0)').show()
		})
//给文件删除，重命名，移动等绑定事件
$(document).on('mousedown','.networkdisk-fileopration a',function(event){
			  event.stopPropagation();
			  var val=$.trim($(this).text());
			  var files=$('.filelist-item-over');
			  var _this=$($('.filelist-item-over')[0]);
				
			  if("删   除"==val){
				  deletefile();				
			    }
			  if("移   动"==val){
				  filemove();
				}
			  if("重命名"==val){//重命名操作
				  filerename();
				}
			  if("保险箱"==val){
				  filelock();
				}
			  if("下   载"==val){
				for(var i=0;i<files.length;i++){
					var _this=$(files[i])
					var url=_this.find('span').html();
					var oldname=$(files[i]).find('a').html();
					var a='<a download hidden href="'+url+'"><span id="filedld"></span></a>'
			        $("body").append(a);
					$("#filedld").click();
					$("#filedld").remove();
				}	
			}
			
						
			
		})
})


function addEvent(eventType, eventFunc, eventObj) {
	eventObj = eventObj || document;

	if (window.attachEvent)
		eventObj.attachEvent("on" + eventType, eventFunc);
	if (window.addEventListener)
		eventObj.addEventListener(eventType, eventFunc, false);
}
function clearEventBubble(evt) {
	evt = evt || window.event;
	if (evt.stopPropagation)
		evt.stopPropagation();
	else
		evt.cancelBubble = true;
	if (evt.preventDefault)
		evt.preventDefault();
	else
		evt.returnValue = false;
}

function deletefile(){//删除函数
	var files=$('.filelist-item-over');
	$.layer({
		shade : [0.5, '#000'],
		area : ['auto', 'auto'],
		closeBtn : [1, false],
		dialog : {
			msg : '确认删除这些文件吗？',
			btns : 2,
			type : 4,
			btn : ['确认', '取消'],
			yes : function(index) {
				var deleteitem=[];
				var f=[];
				for(var i=0;i<files.length;i++){
						f.push($(files[i]))
						var parent=$(files[i]).find('parent').html();
						var fileid=$(files[i]).find('fileid').html();
						deleteitem.push(fileid+':'+parent);
				}
				$.post("filedelete.action", {
					'deleteitem' : JSON.stringify(deleteitem)
				}, function() {
					
					for(var i=0;i<files.length;i++){
						f[i].remove();
					}
					
					
		       });
			},
			no : function() {

			}
		}
	})
}
function filerename(){//重命名函数
	var _this=$($('.filelist-item-over')[0]);
	var fileid=_this.find('fileid').html();
	layer.prompt({
		title : '请重新命名',
		val : _this.find('a').html()
	}, function(newname, index) {
		var ok=true;
		for(var i=0;i<_this.siblings().length;i++){
			if($(_this.siblings()[i]).find('a').html()==newname){
				ok=false;
			}
		}
		if(ok){
			$.post("filerename.action", {
				'path' : fileid,
				'name' : newname
			}, function(data) {
				_this.find('a').html(newname)
				layer.close(index)
			})
		}else{
			layer.close(index);
			layer.msg("该文件名已存在", 2, {type: 3, shade: [0, '#000']});
		}
		

	});
}

function filemove(){//移动函数
	$.layer({
		type:2,
		shade : [0.5, '#000'],
		area : ['300px', '500px'],
		closeBtn : [1, true],
		iframe: {
		    src: 'filenodes.html',
		    scrolling: 'auto'
		}
				
	
	});
}	
function filelock(){//加入保险箱
	var files=$('.filelist-item-over');
	
	for(var i=0;i<files.length;i++){
		$.post('lockfile.action',{fid:$(files[i]).find('fileid').html()},function(data){
			 $('#filehandlemodal').remove();
			 files.remove();
		})
	   
	}
		
	
}
function stopmove(){
	layer.msg("亲，请移步其他文件夹", 1, {type: 3, shade: [0, '#000']});
}