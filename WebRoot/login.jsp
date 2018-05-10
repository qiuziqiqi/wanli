<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0034)http://tz.meituan.com/dianying/all -->
<HTML><HEAD><META content="IE=7.0000" http-equiv="X-UA-Compatible">
<TITLE>电影票订票网站</TITLE>
<META content="text/html; charset=UTF-8" http-equiv=Content-Type>


<LINK rel=stylesheet href="_files2/combo.css">
<LINK rel=stylesheet href="_files2/combo(1).css">

<META name=msapplication-navbutton-color content=#C3E9F6>
<META name=msapplication-window content=width=device-width;height=device-height>

<META name=GENERATOR content="MSHTML 8.00.7601.18210">
<script type="text/javascript" language="javascript">

function checkform(){
	if(document.getElementById("usernameid").value==""){
		
		alert('用户名不能为空');
		return false;
	}
	
	
	if(document.getElementById("passwordid").value==""){
		
		alert('密码不能为空');
		return false;
	}
	
	


	

	
	
	
	return true;

}


</script>
</HEAD>
<BODY id=index>

<DIV id=doc>

 <%@ include file="head.jsp" %>

<DIV id=bdw class=bdw>
<DIV id=bd class=cf>

<DIV class=recommend-movies>
<DIV class=option-switch>

<A hideFocus 
class="J-option-trigger option-switch__option option-switch__option--selected" 
href="javascript:void(0)" gaevent="main/hotmovie"  >用户登录</A> <SPAN 
class=option-switch__delimiter></SPAN>


 </DIV>
 
<DIV class=recommend-movies__slides-w>

<DIV style="WIDTH: 940px" class="reco-slides J-option-content">
<form action="indexmethod!login" method="post" onsubmit="return checkform()">
    	<table align="center" border="1" cellpadding="5" cellspacing="3" width="100%">
    	<tr>
    	<td>
    	用户名:
    	</td>
    	<td>
    	<input type="text" name="username" size="25" id="usernameid" />
    	</td>
    	</tr>
    	
    	<tr>
    	<td>
    	密码:
    	</td>
    	<td>
    	<input type="password" name="password" size="25" id="passwordid" />
    	</td>
    	</tr>
    	
    	
    	
    	
    	
    	<tr>
    	
    	<td>
    	操作
    	</td>
    	<td >  
    	<input type="submit" value="登录" />
    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	<input type="reset" value="重置" />
    	</td>
    	</tr>
    	
    	</table>
    	
    	</form>
  
 
</DIV>
</DIV>
</DIV>






</DIV><!-- bd end --></DIV><!-- bdw end -->

 </DIV><!-- doc end -->





 </BODY></HTML>


