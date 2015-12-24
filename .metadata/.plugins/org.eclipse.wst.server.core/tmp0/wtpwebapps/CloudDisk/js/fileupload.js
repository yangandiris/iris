$(document).ready(function() {
	
	$('.networkdisk-button button').attr('disabled',false);
    $("#filepage-all").find("img").click(function(){
       var pname=$('.filepages-all-btn').find('button:eq(-1)').find('parent').text();
       if(max_min=="max"){
    	   max_min="min";
       }else{
    	   max_min="max";
       }
       if(pname=='video'||pname=='text'||pname=='image'||pname=='lock'){
    	   $.post("listpartfile.action?path="+username+"&action="+pname,function(data){
    		    getfilelist(data.file);
    	        
    		    
    		})
       }else{
    	   $.post("listallfile.action?pname="+pname,function(data){
    	       getfilelist(data.file);
    	     
    	    })	
       }
      
    }).tooltip({title:"视图切换",placement:'bottom'})
	  
 
});
//文件上传模态框
function fileupload(_this) {
	var html=' <div id="upload" class="panel focus">'+
			    '<div id="queueList" class="queueList">'+
				   ' <div class="statusBar element-invisible">'+
				        '<div class="progress">'+
				            '<span class="text">0%</span>'+
				            '<span class="percentage"></span>'+
				        '</div><div class="info"></div>'+
				        '<div class="btns">'+
				            '<div id="filePickerBtn"></div>'+
				            '<div class="uploadBtn" ><var id="lang_start_upload"></var></div>'+
				        '</div>'+
				    '</div>'+
				    '<div id="dndArea" class="placeholder">'+
				        '<div class="filePickerContainer">'+
				            '<div id="filePickerReady">选择文件</div>'+
				        '</div>'+
				    '</div>'+
				    '<ul class="filelist1 element-invisible">'+
				        '<li id="filePickerBlock" class="filePickerBlock"></li>'+
				    '</ul>'+
			   '</div>'+
			'</div>';
    $('#uploadmodal').html(html);
  
	layer.full=function(){}
	$(_this).attr( "disabled","disabled");
	var upload=$.layer({
		type : 1,
		title : [ '文件上传', "background:#ccc;" ],
		shade : [ 0.5, '#000' ],
		area : [ '800px', 'auto' ],
		closeBtn : [ 0, true ],
		shift : 'top',
		maxmin : true,
		min : function(layero) {
			$(layero).find('.xubox_title em').html('<div id="progressbar"><div class="progress-label">文件上传中</div></div>')
			$( "#progressbar" ).progressbar({value:false});

			$(".xubox_shade").hide();
			$('.xubox_max').show();
			$('.xubox_layer').animate({left:'100px',top:'580px'},500);

			
		},
		full : function(layero) {
		},
		restore : function(layero) {
			$('.xubox_max').hide()
			$(".xubox_shade").show();
			$(layero).find('.xubox_title em').html('文件上传')

		},
		close:function(upload){
			$(_this).attr( "disabled",false);
			up.destroy();
		},
		page : {
			dom : '#uploadmodal'
		}
	})
	$('.xubox_max').hide()
	
	var up=new UploadImage();
	up.init();
	
	
}

//初始化获取所有文件
var num=0;
var max_min='max'
function listallfile(_this){
	num=0;
	$('#filepage-all').siblings().hide();
	$("#filesearch").val('');//清空过滤框
	$(_this).siblings().removeAttr("disabled").removeClass('networkdisk-left-btn-hover');
	$(_this).attr("disabled","disabled").addClass('networkdisk-left-btn-hover');
	  $.post("listallfile.action?pname="+username,function(data){
	       var btn='<button  class="btn btn-link file-ct-btn" onclick="backpage(this)" ><parent class="hidden">'+username+'</parent>所有文件</button>';
	       $('.filepages-all-btn').html(btn);
	       ++num;
	       getfilelist(data.file);
	     
	    })		
	$('#filepage-all').show();  
}
//获取子文件页面
function getchildnode(_this){
	$("#filesearch").val('');//清空过滤框
	var pname=$(_this).find("parent").html();
	$.post("listallfile.action?pname="+pname,function(data){
		 //导航按钮
	    var prebtn='';
	    var btn='';
	    
    	if(num==1){
    		prebtn='<button  class="btn btn-link file-ct-btn" onclick="backprepage(this)" >返回上一级|</button>' ;   
            ++num;
    	}
        btn=prebtn+$('.filepages-all-btn').html()+'<button  class="btn btn-link file-ct-btn" onclick="backpage(this)" >>'+
            $(_this).find('a').html()+'<parent class="hidden">'+pname+'</parent></button>';
	      
	   $('.filepages-all-btn').html(btn)
	   getfilelist(data.file,_this);
	  
	})
}
//获取指定类型文件
function listpartfile(_this,action,type){
	$(_this).siblings().removeAttr("disabled").removeClass('networkdisk-left-btn-hover');
	$(_this).attr("disabled","disabled").addClass('networkdisk-left-btn-hover');
	if(action=='lock'&&isloginlock==0){
		
		layer.prompt({
			title : '请输入密码',
			type:1
			
		}, function(psw, index) {
			$.post('login.action',{
				'username':username,
				'password':psw
			},function(data){
				if(data.msg=='yes'){
					isloginlock=1;
					$.post("listpartfile.action?path="+username+"&action="+action,function(data){
				        var btn='<button  class="btn btn-link file-ct-btn" onclick="backpage(this)" ><parent class="hidden">'+action+'</parent>'+type+'</button>';
					    $('.filepages-all-btn').html(btn);
					    getfilelist(data.file);  
					})
					
				}else{
					layer.msg("密码错误", 1,function(){
						$('.btn-group-vertical a:eq(-1)').click();
					});
				}
			})
		})
			
	
		
	}else{
		$.post("listpartfile.action?path="+username+"&action="+action,function(data){
	        var btn='<button  class="btn btn-link file-ct-btn" onclick="backpage(this)" ><parent class="hidden">'+action+'</parent>'+type+'</button>';
		    $('.filepages-all-btn').html(btn);
		    getfilelist(data.file);  
		})
	}
	
}
//列出文件
function getfilelist(result){ 
	$('#filepages-all-mainui').html(''); 
    if("max"==max_min){
	    //文件页面	max	
		$.each(result, function(i) {
			     var isfolder='';
			     var src='';
			     var image='';
			     if(result[i].isfolder==1){
			        isfolder='ondblclick="getchildnode(this)"';
			        src='img/bg/floder.png';
			     }else if(result[i].f_type.substr(0,5)=='image'){
			    	 src='fileupload/imgfix'+result[i].f_url.substring(10);
			    	 image='imagefile'
			    	 
			     }else{
			    	 src='img/bg/'+getfiletype(result[i].f_name)+'.png';
			     }
			     var html='<div tabindex="1"  class="filelist filelist-item point pull-left '+image+'"'+isfolder+'>'+
			     '<span class="hidden">'+result[i].f_url+'</span>'+
			     '<parent class="hidden">'+result[i].f_parent+'/'+result[i].f_name+'</parent>'+
			     '<fileid class="hidden">'+result[i].id+'</fileid>'+
			     '<img class="filelist-item-img"  width="80px" height="67" src="'+src+'" alt="加载中.." >'+
			     '<div class="filelist-item-name" onmouseover="showfilemsg(this)"><a>' +result[i].f_name+'</a></div></div> ';  
	  
	            $('#filepages-all-mainui').append(html); 
	           
	        }); 		
    }else{
	     //页面文件min
	     var table='<table id="filetable" class="table table-hover table-font"><thead>'+
						'<tr><th class="point" onclick="chooseallfile()">全选</th>'+
							'<th> <a class="afont-style">文件名</a></th>'+
						    '<th> <a class="afont-style">文件大小</a></th>'+
							'<th><a class="afont-style">修改日期</a></th>'+
						'</tr></thead><tbody>';
	
		
		 $.each(result, function(i) { 
			 var isfolder='';
		     var src='';
		     var image=''
		     if(result[i].isfolder==1){
		        isfolder='ondblclick="getchildnode(this)"';
		        src='img/bg/floder.png';
		     }else if(result[i].f_type.substr(0,5)=='image'){
		    	 src=result[i].f_url;
		    	 image='imagefile'
		     }else{
		    	 src='img/bg/'+getfiletype(result[i].f_name)+'.png';
		     }
			 table+='<tr tabindex="1" class="filelist point '+image+'"'+isfolder+'><td><img class="filelist-item-img" width="30px" height="20" src="'+src+'" alt="加载中.." ></td>'+
                    '<td><span class="hidden">'+result[i].f_url+'</span>'+
	                    '<parent class="hidden">'+result[i].f_parent+'/'+result[i].f_name+'</parent>'+
	                    '<fileid class="hidden">'+result[i].id+'</fileid>'+
	                    '<a>'+result[i].f_name+'</a></td>'+
					'<td>'+result[i].f_size+'</td>'+
					'<td>'+result[i].f_date+'</td>'+
			        '</tr>';

			    });
		
		
		table+='</tbody></table>';
		$('#filepages-all-mainui').append(table);
		
	    $("#filetable").dataTable(datatableoptions);
}
	    layer.photosPage({
	        parent: '.imagefile',
	        title: 'ColudWork图片在线浏览'
	    });

		$("#filesearch").keyup(function(){	     
	        if ($(this).val() == "") {
	            $(" .table-font tbody tr").show();
	            $(".filelist-item").show()
	        } else {
	            $(" .table-font tbody tr").hide().filter(":contains('"+( $(this).val() )+"')").show();
	            $(".filelist-item").hide().filter(":contains('"+( $(this).val() )+"')").show();
	        }
	   }).keyup(); 	
}

var datatableoptions={
        "bPaginate": true, //开关，是否显示分页器	             
        "bInfo": true, //开关，是否显示表格的一些信息
        "bFilter": false, //开关，是否启用客户端过滤器      
        "bJQueryUI": true, //开关，是否启用JQueryUI风格
        "bLengthChange": false, //开关，是否显示每页大小的下拉框
        //"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        "bProcessing": false,//是否显示“正在处理”这个提示信息
        "bScrollInfinite": false,//以指定是否无限滚动
        "sScrollY":"425px",
        "bSort": true, //开关，是否启用各列具有按列排序的功能
        "bSortClasses": true,
        "bStateSave": true, //开关，是否打开客户端状态记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或重新打开浏览器，之前的状态都是保存下来的- ------当值为true时aoColumnDefs不能隐藏列          
        "bAutoWidth": false, //自适应宽度            
        "sPaginationType": "full_numbers",
        "iDisplayLength":50,
        "bDestroy": true,
        "aoColumnDefs": [ { "bSortable": false, "aTargets": [ 0 ] },
                          { "sWidth": "6px", "aTargets": [ 0 ] }
                        ],  
         "aoColumns" : [
				    null,
				    { "sWidth": "350px"},
				    { "sWidth": "200px"},
				    { "sWidth": "200px"}],               
        "oLanguage": {
            "sProcessing": "正在加载中......",
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "对不起，查询不到相关数据！",
            "sEmptyTable": "表中无数据存在！",
            "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
            "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
            "sSearch": "Search",
            "sInfoEmpty": "显示0到0条记录",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上一页",
                "sNext": "下一页",                       
                "sLast": "末页"
            }
        }//多语言配置
};
//返回指定页面
function backpage(_this){
	$("#filesearch").val('');//清空过滤框
	
	$(_this).nextAll().remove()
	var pname=$(_this).find('parent').text();
	if(pname==username){
		$('.networkdisk-left').find('a:eq(0)').click();
	}else if(pname=='text'){
		$('.networkdisk-left').find('a:eq(2)').click();
	}else if(pname=='video'){
		$('.networkdisk-left').find('a:eq(1)').click();
	}else if(pname=='image'){
		$('.networkdisk-left').find('a:eq(3)').click();
	}else if(pname=='lock'){
		//$('.networkdisk-left').find('a:eq(3)').click();
	}else{
		$.post("listallfile.action?pname="+pname,function(data){
		       getfilelist(data.file);
		     
		})	
	}
	 
}
//返回上一级页面
function backprepage(_this){
	$("#filesearch").val('');//清空过滤框
	
	$(_this).parent().find('button:eq(-2)').click();
}
//显示文件信息
function showfilemsg(_this){
	var title=$(_this).find('a').html();
	if($(_this).find('a').html().length>6){
	    $(_this).tooltip({title:title,placement:'top'}).tooltip('show')
		
	}
}
//选取所有文件
function chooseallfile(){
	$('.filelist').addClass('filelist-item-over');
}
//新建文件夹
function newfolder(){
	  var parent=$('.filepages-all-btn').find('button:eq(-1)').find('parent').text();
	  var url=
	  layer.prompt({
			title : '请输入新建文件夹名'
		}, function(filename, index) {
			$.post("newfolder.action", {
						'parent' : parent,
						'filename' : filename
					}, function() {
						layer.close(index);
						$.post("listallfile.action?pname="+parent,function(data){
						       getfilelist(data.file);
						     
						})	
					})

		});
	  
	  
}
//获取文件类型
var alltype=['avi','bat','cab','dat','dll','doc','docx'
	           ,'mp3','mp4','mpeg','mpg','ppt','psd','rar'
	           ,'txt','wav','wma','zip','xlsx','rmvb','rm','exe','pdf']
function  getfiletype(filename) {  
	           
	          var type="unknown"  
	          var dot = filename.lastIndexOf('.'); 	          
	          if (dot!=-1) {
	            	for(var i=0;i<alltype.length;i++){
	            		if(filename.substring(dot + 1)==alltype[i]){
	            			type=alltype[i];
	            		}
	            	}	             
	            } 
	        
	        return type;   
	    }   
//验证加密密码
function vilidlockfile(){
	var ok=false;
	layer.prompt({
		title : '请输入密码',
		type:1
		
	}, function(psw, index) {
		$.post('login.action',{
			'username':username,
			'password':psw
		},function(data){
			if(data.msg=='yes'){
				ok=true;
				
			}else{
				layer.msg("密码错误", 1,function(){
					vilidlockfile();
				});
			}
		})
	})
    return ok;
}