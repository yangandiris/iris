  /* 上传图片 */
    function UploadImage() {
    	
    }
    UploadImage.prototype = {
        init: function () {
            this.initContainer();
            this.initUploader();
            
        },
        initContainer: function () {
            this.$queue =$('.filelist1');
        },
        /* 初始化容器 */
        initUploader: function () {
            var _this = this,
                $ = jQuery,    // just in case. Make sure it's not an other libaray.
                $wrap = $('#upload'),
                parent=$('.filepages-all-btn').find('button:eq(-1)').find('parent').text(),
            // 图片容器
                $queue = $wrap.find('.filelist1'),
            // 状态栏，包括进度和控制按钮
                $statusBar = $wrap.find('.statusBar'),
            // 文件总体选择信息。
                $info = $statusBar.find('.info'),
            // 上传按钮
                $upload = $wrap.find('.uploadBtn'),
            // 上传按钮
                $filePickerBtn = $wrap.find('.filePickerBtn'),
            // 上传按钮
                $filePickerBlock = $wrap.find('.filePickerBlock'),
            // 没选择文件之前的内容。
                $placeHolder = $wrap.find('.placeholder'),
            // 总体进度条
                $progress = $statusBar.find('.progress').hide(),
            // 添加的文件数量
                fileCount = 0,
            // 添加的文件总大小
                fileSize = 0,
            // 优化retina, 在retina下这个值是2
                ratio = window.devicePixelRatio || 1,
            // 缩略图大小
                thumbnailWidth = 113 * ratio,
                thumbnailHeight = 113 * ratio,
            // 可能有pedding, ready, uploading, confirm, done.
                state = '',
            // 所有文件的进度信息，key为file id
                percentages = {},
                supportTransition = (function () {
                    var s = document.createElement('p').style,
                        r = 'transition' in s ||
                            'WebkitTransition' in s ||
                            'MozTransition' in s ||
                            'msTransition' in s ||
                            'OTransition' in s;
                    s = null;
                    return r;
                })(),
            // WebUploader实例
                uploader,
                actionUrl = 'Upload',
               // acceptExtensions = ([".png", ".jpg", ".jpeg", ".gif", ".bmp"] || []).join('').replace(/\./g, ',').replace(/^[,]/, ''),
              
                imageCompressBorder = 1600;

            if (!WebUploader.Uploader.support()) {
                $('#filePickerReady').after($('<div>').html('您的浏览器不支持！')).hide();
                return;
            } 
           
            uploader = _this.uploader = WebUploader.create({
                pick: {
                    id: '#filePickerReady'
                },
                swf: 'WU/Uploader.swf',
                server:  actionUrl,
                fileVal: 'upfile',
               //   duplicate: true,
                fileSingleSizeLimit:1024*1024*1024*2,   // 默认 2 M
                compress:false,
               /* compress:{//压缩上传图片
                    width: imageCompressBorder,
                    height: imageCompressBorder,
                    // 图片质量，只有type为`image/jpeg`的时候才有效。
                    quality: 90,
                    // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
                    allowMagnify: false,
                    // 是否允许裁剪。
                    crop: false,
                    // 是否保留头部meta信息。
                    preserveHeaders: true
                },*/
                prepareNextFile:true,// [默认值：false] 是否允许在文件传输时提前把下一个文件准备好。 对于一个文件的准备工作比较耗时，比如图片压缩，md5序列化。 如果能提前在当前文件传输期处理，可以节省总体耗时。
                chunked:true,// [可选] [默认值：false] 是否要分片处理大文件上传。
                chunkSize :1024*1024*10,// [可选] [默认值：5242880] 如果要分片，分多大一片？ 默认大小为5M.
                chunkRetry:2,//[可选] [默认值：2] 如果某个分片由于网络问题出错，允许自动重传多少次？
                threads :3,// [可选] [默认值：3] 上传并发数。允许同时最大上传进程数。
                formData:{"parent":parent} //[可选] [默认值：{}] 文件上传请求的参数表，每次发送都会发送此对象中的参数。
               // fileVal {Object} [可选] [默认值：'file'] 设置文件上传域的name。
               // method {Object} [可选] [默认值：'POST'] 文件上传方式，POST或者GET。
               // sendAsBinary :true// [可选] [默认值：false] 是否已二进制的流的方式发送文件，这样整个上传内容php://input都为文件内容， 其他参数在$_GET数组中。
                //fileNumLimit {int} [可选] [默认值：undefined] 验证文件总数量, 超出则不允许加入队列。
                //fileSizeLimit {int} [可选] [默认值：undefined] 验证文件总大小是否超出限制, 超出则不允许加入队列。
                //fileSingleSizeLimit {int} [可选] [默认值：undefined] 验证单个文件大小是否超出限制, 超出则不允许加入队列。
               // duplicate {Boolean} [可选] [默认值：undefined] 去重， 根据文件名字、文件大小和最后修改时间来生成hash Key.
            });
            uploader.addButton({
                id: '#filePickerBlock'
            });
            uploader.addButton({
                id: '#filePickerBtn',
                label: '添加文件'
            });

            setState('pedding');

            // 当有文件添加进来时执行，负责view的创建
            function addFile(file) {
            	
                var $li = $('<li id="' + file.id + '">' +
                        '<p class="title">' + file.name + '</p>' +
                        '<p class="imgWrap"></p>' +
                        '<p class="progress"><span></span></p>' +
                        '</li>'),

                    $btns = $('<div class="file-panel">' +
                        '<span class="cancel">' + imglang.uploadDelete + '</span>' +
                        '<span class="rotateRight">' + imglang.uploadTurnRight + '</span>' +
                        '<span class="rotateLeft">' + imglang.uploadTurnLeft + '</span></div>').appendTo($li),
                    $prgress = $li.find('p.progress span'),
                    $wrap = $li.find('p.imgWrap'),
                    $info = $('<p class="error"></p>').hide().appendTo($li),

                    showError = function (code) {
                        switch (code) {
                            case 'exceed_size':
                                text = imglang.errorExceedSize;
                                break;
                            case 'interrupt':
                                text = imglang.errorInterrupt;
                                break;
                            case 'http':
                                text = imglang.errorHttp;
                                break;
                            case 'not_allow_type':
                                text = imglang.errorFileType;
                                break;
                            default:
                                text = imglang.errorUploadRetry;
                                break;
                        }
                        $info.text(text).show();
                    };
                
                if (file.getStatus() === 'invalid') {
                    showError(file.statusText);
                } else {
                    $wrap.text(imglang.uploadPreview);
                    if ($.support.ie && $.support.version <= 7) {
                        $wrap.text(imglang.uploadNoPreview);
                    } else {
                        uploader.makeThumb(file, function (error, src) {
                            if (error) {
                            	 if('unknown'==filetype(file.ext)){
                           		  src='img/bg/unknown.png'
                           	  }else{
                           		  src='img/bg/'+file.ext+'.png'
                           	  }
                           	  var $img = $('<img style="width:100%;height:100%" src="' + src + '">');
                                 $wrap.empty().append($img);
                               
                            }else if(!src){
                            	 $wrap.text(imglang.uploadNoPreview);
                            }else {
                                var $img = $('<img src="' + src + '">');
                                $wrap.empty().append($img);
                                $img.on('error', function () {
                                    $wrap.text(imglang.uploadNoPreview);
                                });
                            }
                        }, thumbnailWidth, thumbnailHeight);
                    }
                    percentages[ file.id ] = [ file.size, 0 ];
                    file.rotation = 0;

                    /* 检查文件格式 */
//                    if (!file.ext || acceptExtensions.indexOf(file.ext.toLowerCase()) == -1) {
//                       showError('not_allow_type');
//                        uploader.removeFile(file);
//                   }
                }

                file.on('statuschange', function (cur, prev) {
                    if (prev === 'progress') {
                        $prgress.hide().width(0);
                    } else if (prev === 'queued') {
                        $li.off('mouseenter mouseleave');
                        $btns.remove();
                    }
                    // 成功
                    if (cur === 'error' || cur === 'invalid') {
                        showError(file.statusText);
                        percentages[ file.id ][ 1 ] = 1;
                    } else if (cur === 'interrupt') {
                        showError('interrupt');
                    } else if (cur === 'queued') {
                        percentages[ file.id ][ 1 ] = 0;
                    } else if (cur === 'progress') {
                        $info.hide();
                        $prgress.css('display', 'block');
                    } else if (cur === 'complete') {
                    }

                    $li.removeClass('state-' + prev).addClass('state-' + cur);
                });

                $li.on('mouseenter', function () {
                    $btns.stop().animate({height: 30});
                });
                $li.on('mouseleave', function () {
                    $btns.stop().animate({height: 0});
                });

                $btns.on('click', 'span', function () {
                    var index = $(this).index(),
                        deg;

                    switch (index) {
                        case 0:
                            uploader.removeFile(file);
                            return;
                        case 1:
                            file.rotation += 90;
                            break;
                        case 2:
                            file.rotation -= 90;
                            break;
                    }

                    if (supportTransition) {
                        deg = 'rotate(' + file.rotation + 'deg)';
                        $wrap.css({
                            '-webkit-transform': deg,
                            '-mos-transform': deg,
                            '-o-transform': deg,
                            'transform': deg
                        });
                    } else {
                        $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
                    }

                });

                $li.insertBefore($filePickerBlock);
                
            }

            // 负责view的销毁
            function removeFile(file) {
                var $li = $('#' + file.id);
                delete percentages[ file.id ];
                updateTotalProgress();
                $li.off().find('.file-panel').off().end().remove();
            }

            function updateTotalProgress() {
                var loaded = 0,
                    total = 0,
                    spans = $progress.children(),
                    percent;

                $.each(percentages, function (k, v) {
                    total += v[ 0 ];
                    loaded += v[ 0 ] * v[ 1 ];
                });

                percent = total ? loaded / total : 0;

                spans.eq(0).text(Math.round(percent * 100) + '%');
                spans.eq(1).css('width', Math.round(percent * 100) + '%');
                updateStatus();
            }

            function setState(val, files) {

                if (val != state) {

                    var stats = uploader.getStats();

                    $upload.removeClass('state-' + state);
                    $upload.addClass('state-' + val);

                    switch (val) {

                        /* 未选择文件 */
                        case 'pedding':
                            $queue.addClass('element-invisible');
                            $statusBar.addClass('element-invisible');
                            $placeHolder.removeClass('element-invisible');
                            $progress.hide(); $info.hide();
                            uploader.refresh();
                            break;

                        /* 可以开始上传 */
                        case 'ready':
                            $placeHolder.addClass('element-invisible');
                            $queue.removeClass('element-invisible');
                            $statusBar.removeClass('element-invisible');
                            $progress.hide(); $info.show();
                            $upload.text(imglang.uploadStart);
                            uploader.refresh();
                            break;

                        /* 上传中 */
                        case 'uploading':
                            $progress.show(); $info.hide();
                            $upload.text(imglang.uploadPause);
                            break;

                        /* 暂停上传 */
                        case 'paused':
                            $progress.show(); $info.hide();
                            $upload.text(imglang.uploadContinue);
                            break;

                        case 'confirm':
                            $progress.show(); $info.hide();
                            $upload.text(imglang.uploadStart);

                            stats = uploader.getStats();
                            if (stats.successNum && !stats.uploadFailNum) {
                                setState('finish');
                                return;
                            }
                            break;

                        case 'finish':
                            $progress.hide(); $info.show();
                            if (stats.uploadFailNum) {
                                $upload.text(imglang.uploadRetry);
                            } else {
                                $upload.text(imglang.uploadStart);
                            }
                            break;
                    }

                    state = val;
                    updateStatus();

                }

                if (!_this.getQueueCount()) {
                    $upload.addClass('disabled')
                } else {
                    $upload.removeClass('disabled')
                }

            }

            function updateStatus() {
                var text = '', stats;

                if (state === 'ready') {
                    text = imglang.updateStatusReady.replace('_', fileCount).replace('_KB', WebUploader.formatSize(fileSize));
                } else if (state === 'confirm') {
                    stats = uploader.getStats();
                    if (stats.uploadFailNum) {
                        text = imglang.updateStatusConfirm.replace('_', stats.successNum).replace('_', stats.successNum);
                    }
                } else {
                    stats = uploader.getStats();
                    text = imglang.updateStatusFinish.replace('_', fileCount).
                        replace('_KB', WebUploader.formatSize(fileSize)).
                        replace('_', stats.successNum);

                    if (stats.uploadFailNum) {
                        text += imglang.updateStatusError.replace('_', stats.uploadFailNum);
                    }
                }

                $info.html(text);
            }

            uploader.on('beforeFileQueued',function(file){
            	
            	
            })
            uploader.on('fileQueued', function (file) {
                fileCount++;
                fileSize += file.size;
                if (fileCount === 1) {
                    $placeHolder.addClass('element-invisible');
                    $statusBar.show();
                }
                var loadi = layer.load(0);
                uploader.md5File( file ).then(function(val) {
                	layer.close(loadi);
             		file['md5']=val;
             		if(file['size']>=1024*1024*3){
		         			$.ajax({
		            		type:'POST',
		            		url:'vilidmd5.action',
		            		data:{'md5':val},
		            		async:false,
		            		success:function(data){
		            			if(data.ret==1){
		            				uploader.skipFile( file );
		            				existfile.push(file)
		            			}
		            		
		            		  }
		            	    })
             		}
             		addFile(file);
                 });
            	  
              
              
            	
                
            });
            
            
            uploader.on('fileDequeued', function (file) {
                fileCount--;
                fileSize -= file.size;

                removeFile(file);
                updateTotalProgress();
            });

            uploader.on('filesQueued', function (file) {
                if (!uploader.isInProgress() && (state == 'pedding' || state == 'finish' || state == 'confirm' || state == 'ready')) {
                    setState('ready');
                }
                updateTotalProgress();
            });

            uploader.on('all', function (type, files) {
                switch (type) {
                    case 'uploadFinished':
                        setState('confirm', files);
                        break;
                    case 'startUpload':
                        uploader.option('server', actionUrl);
                        setState('uploading', files);
                        break;
                    case 'stopUpload':
                        setState('paused', files);
                        break;
                }
            });
            uploader.on('uploadStart', function (file) {
            	
             		
            });
           
            uploader.on('uploadBeforeSend', function (file, data, header) {
            	//这里可以通过data对象添加POST参数
            	data['md5']=file['file']['md5'];
                header['X_Requested_With'] = 'XMLHttpRequest';
            });


            uploader.on('uploadProgress', function (file, percentage) {
            	if(file.id){
            		 var $li = $('#' + file.id),
                     $percent = $li.find('.progress span');

                 $percent.css('width', percentage * 100 + '%');
                 percentages[ file.id ][ 1 ] = percentage;
                 updateTotalProgress();
            	}
               
            });

            uploader.on('uploadSuccess', function (file, ret) {
                var $file = $('#' + file.id);
              
                    if (ret==1) {
                        $file.append('<span class="success"></span>');
                    } else {
                        $file.find('.error').text(imglang.errorServerUpload).show();
                    }
               
            });

            uploader.on('uploadError', function (file, code) {
            });
            uploader.on('error', function (code, file) {
                if (code == 'Q_TYPE_DENIED' || code == 'F_EXCEED_SIZE') {
                    addFile(file);
                }
            });
            uploader.on('uploadComplete', function (file, ret) {
            	$('.filepages-all-btn').find('button:eq(-1)').click()
            });

            $upload.on('click', function () {
            	uploadexistfile();
                if ($(this).hasClass('disabled')) {
                    return false;
                }

                if (state === 'ready') {
                    uploader.upload();
                } else if (state === 'paused') {
                    uploader.upload();
                } else if (state === 'uploading') {
                   uploader.stop(true);
                	//alert('暂停上传，点击继续');
                }
            });

            $upload.addClass('state-' + state);
            updateTotalProgress();
        },
        getQueueCount: function () {
            var file, i, status, readyFile = 0, files = this.uploader.getFiles();
            for (i = 0; file = files[i++]; ) {
                status = file.getStatus();
                if (status=='inited'||status == 'queued' || status == 'uploading' || status == 'progress'){
                	readyFile++;
                }
            }
            return readyFile;
        },
        destroy: function () {
        	$('#upload').remove();
        }
    };
    var imglang={
    		'uploadSelectFile':'点击选择图片',
            'uploadAddFile':'继续添加',
            'uploadStart':'开始上传',
            'uploadPause':'暂停上传',
            'uploadContinue':'继续上传',
            'uploadRetry':'重试上传',
            'uploadDelete':'删除',
            'uploadTurnLeft':'向左旋转',
            'uploadTurnRight':'向右旋转',
            'uploadPreview':'预览中',
            'uploadNoPreview':'不能预览',
            'updateStatusReady': '选中_个文件，共_KB。',
            'updateStatusConfirm': '已成功上传_张照片，_张照片上传失败',
            'updateStatusFinish': '共_个（_KB），_个上传成功',
            'updateStatusError': '，_个上传失败。',
            'errorNotSupport': 'WebUploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器。',
            'errorLoadConfig': '后端配置项没有正常加载，上传插件不能正常使用！',
            'errorExceedSize':'文件大小超出',
            'errorFileType':'文件格式不允许',
            'errorInterrupt':'文件传输中断',
            'errorUploadRetry':'上传失败，请重试',
            'errorHttp':'http请求错误',
            'errorServerUpload':'服务器返回出错',
            'remoteLockError':"宽高不正确,不能所定比例",
            'numError':"请输入正确的长度或者宽度值！例如：123，400",
            'imageUrlError':"不允许的图片格式或者图片域！",
            'imageLoadError':"图片加载失败！请检查链接地址或网络状态！",
            'searchRemind':"请输入搜索关键词",
            'searchLoading':"图片加载中，请稍后……",
            'searchRetry':" :( ，抱歉，没有找到图片！请重试一次！"
}
    function filetype(ext){
    	var type=['avi','bat','cab','dat','dll','doc','docx'
	           ,'mp3','mp4','mpeg','mpg','ppt','psd','rar'
	           ,'txt','wav','wma','zip','xlsx','rm','rmvb','exe','pdf'],
	           ret='unknown';
    	
    	for(var i=0;i<type.length;i++){
    		if(ext==type[i]){
    			ret=ext
    		}
    	}
    	return ret;
    }
    var existfile=[];
    function uploadexistfile(){
    	$.each(existfile,function(i,value) {  
    		$.ajax({
        		type:'POST',
        		url:'vilidmd5.action',
        		data:{'md5':value.md5,
        			  'name':value.name,
        			  'parent':$('.filepages-all-btn').find('button:eq(-1)').find('parent').text()
        			  },
        		async:false,
        		success:function(data){
        			 var $file = $('#' + value.id);
                     
                     if (data.ret==1) {
                         $file.append('<span class="success"></span>');
                     } else {
                         $file.find('.error').text(imglang.errorServerUpload).show();
                     }
        		
        		}
        	}) 
           
          }); 
     	
    	
    
    	
    }
 