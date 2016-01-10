<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
</head>
<body>
<script type="text/javascript">  
    $(document).ready(function(){  
        var saveDataAry=[];  
        var data1={"id":"1","username":"test","password":"gz"};  
        var data2={"id":"2","username":"ququ","password":"gr"};  
        saveDataAry.push(data1);  
        saveDataAry.push(data2);         
        $.ajax({ 
            type:"POST", 
            url:"saveuser", 
            dataType:"json",      
            contentType:"application/json",               
            data:JSON.stringify(saveDataAry), 
            success:function(data){ 
                   alert(data)                    
            } 
         }); 
    });  
</script>
</body>
</html>