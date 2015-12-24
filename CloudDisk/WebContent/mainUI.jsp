<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>云主页</title>
<link rel="stylesheet" type="text/css" href="bootstrap-3.3.0/dist/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="jquery-ui/themes/excite-bike/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="css/mainUI.css" />
<link rel="stylesheet" type="text/css" href="DataTable/css/jquery.dataTables_themeroller.css" /> 
<link rel="stylesheet" type="text/css" href="WU/webuploader.css" />
<link rel="stylesheet" type="text/css" href="css/image.css" />

<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="jquery-ui/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.form.js"></script>
<script type="text/javascript" src="bootstrap-3.3.0/dist/js/bootstrap.js"></script>
<script type="text/javascript" src="js/fileselect.js"></script>
<script type="text/javascript" src="js/mainUI.js"></script>
<script type="text/javascript" src="DataTable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/fileupload.js"></script>
<script type="text/javascript" src="WU/webuploader.js"></script>
<script type="text/javascript" src="layer/layer.min.js"></script>
<script type="text/javascript" src="layer/extend/layer.ext.js"></script>
<script type="text/javascript" src="js/image.js"></script>
</head>
<body >
	<!--头部开始  -->
	<div class="top">
		<div id="header">
			<span class="glyphicon glyphicon-leaf" style="margin-left:80px;"></span>	
			<span>CloudDisk</span>	
			<script language="javascript">     
			   var date = new Date(<%=new java.util.Date().getTime()%>);   
			   function run() {       
			   date.setSeconds(date.getSeconds() + 1);          
			   document.getElementById("currentTime").innerHTML = date.toLocaleString();      }     
			   window.setInterval("run();", 1000); 
			 </script>
            <time id="currentTime"></time> 		
			<div id="usermanager">
				<img class="img-circle" src="img/user.png" />
			</div>
		</div>

		<ol class="user-menu" hidden>
			<li id="nickname">${username }</li>
			<li><a href="javascript:void(0)"><span class="glyphicon glyphicon-user"
					style="color: rgb(255, 140, 60);"> </span>个人中心</a></li>
			<li><a href="login.html"><span class="glyphicon glyphicon-refresh"
					style="color: rgb(255, 140, 60);"></span>切换账号</a></li>
			<li><a href="login.html"><span class="glyphicon glyphicon-off"
					style="color: rgb(255, 140, 60);"> </span>退出</a></li>
		</ol>
	</div>
	<!--头部结束  -->

    <div>
	<!--网盘主界面开始  -->
	<div class="networkdisk"  >
		<!--网盘左边栏开始 -->
		<div class="networkdisk-left">
			<div class="btn-group-vertical">
				<a  href="javascript:void(0)" type="button" style="width: 200px;" 
					onclick="listallfile(this)"
					class="btn btn-info btn-lg networkdisk-left-btn ">
					<span class="glyphicon glyphicon-folder-close"
						style="color: #3C94D6;"> </span> 
					所有文件
				</a>
				<a href="javascript:void(0)" type="button" onclick="listpartfile(this,'video','视频')"
					class="btn btn-info btn-lg networkdisk-left-btn">
					<span class="glyphicon glyphicon-facetime-video"
						style="color: #3C94D6;"> </span> 视频
				</a>
				<a href="javascript:void(0)" type="button" onclick="listpartfile(this,'text','文档')"
					class="btn btn-info btn-lg networkdisk-left-btn">
					<span class="glyphicon glyphicon-file" style="color: #3C94D6;">
					</span> 文档
				</a>
				<a href="javascript:void(0)" type="button" onclick="listpartfile(this,'image','图片')"
					class="btn btn-info btn-lg networkdisk-left-btn">
					<span class="glyphicon glyphicon-picture" style="color: #3C94D6;">
					</span> 图片
				</a>
				<a href="javascript:void(0)" type="button" onclick="listpartfile(this,'lock','加密')"
					class="btn btn-info btn-lg networkdisk-left-btn">
					<span class="glyphicon glyphicon-lock" style="color: #3C94D6;">
					</span> 加密文件
				</a>
			</div>



		</div>
		<!--网盘左边栏结束  -->

		<!--网盘中间栏开始  -->
		<div class="networkdisk-center">
			<div class="networkdisk-center-top">
				 <form class="networkdisk-search pull-left" role="form">
					<div class="input-group input-group-sm">
						<span class="input-group-btn">
							<button class="btn btn-info " type="button">
								<span class="glyphicon glyphicon-search"
									style="color: #3C94D6; font-size: 12px;"> </span>搜索
							</button>
						</span>
			            <input id="filesearch" type="text" class="form-control " placeholder="请输入你要搜索的文件">
					</div>
				</form> 
			   <div class="networkdisk-fileopration" hidden>
			        <li><a class="btn btn-default"><span class="glyphicon glyphicon-download"
					style="color:#9094CF;" > </span>下   载</a></li>
			        <li><a class="btn btn-default" ><span class="glyphicon glyphicon-retweet"
					style="color: #9094CF;"></span>  移   动</a></li>
					<li><a class="btn btn-default" ><span class="glyphicon glyphicon-remove"
					style="color:#9094CF;"> </span>  删   除</a></li>
					<li><a class="btn btn-default"><span class="glyphicon glyphicon-pencil"
					style="color:#9094CF;"> </span>  重命名</a></li>
					<li><a class="btn btn-default"><span class="glyphicon glyphicon-lock"
					style="color:#9094CF;"> </span>  保险箱</a></li>	
			   </div>
				
				<div class="btn-group networkdisk-button">
					<button type="button" class="btn btn-default" 
						onclick="fileupload(this);">
						<span class="glyphicon glyphicon-upload"
							style="color: rgb(44, 87, 153);"></span> 文件上传
					</button>
					<button  type="button" class="btn btn-default"
					    onclick="newfolder();">
						<span class="glyphicon glyphicon-download"
							style="color: rgb(44, 87, 153);"></span> 新建文件夹
					</button>
				</div>
			</div>



			<div class="networkdisk-center-center ">

				<div tabindex="2" id="filepages">
				   <div id="filepage-all">
				      <div  class="well well-sm ">
				         <div class="filepages-all-btn pull-left" ></div>
				         <img class="filepages-change-all point img-circle" src="img/change.jpg" width="20px" height="20px"></img>
				         
				      </div>
				      
				      
				      <div id="filepages-all-mainui" class=""></div>
				   </div>
				   <div id="filepage-text"></div>
				   <div id="filepage-photo"></div>
				   <div id="filepage-voide"></div>
				   <div id="filepage-box"></div>


				
				</div>
	
			</div>

		</div>
		<!--网盘中间栏结束  -->

	</div>
	<!--网盘主界面结束  -->
   
    </div>
	<!--上传模态框开始  -->
	<div id="uploadmodal" hidden>
	   

	</div>

	<!--上传模态框结束   -->
	
	
    
</body>
</html>