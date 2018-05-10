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
href="javascript:void(0)" gaevent="main/hotmovie">当前热映</A> <SPAN 
class=option-switch__delimiter></SPAN>


 </DIV>
 
<DIV class=recommend-movies__slides-w>

<DIV style="WIDTH: 940px" class="reco-slides J-option-content">
<UL class=reco-slides__slides>
  <LI>
  
  <c:forEach items="${list}"  var="bean">
  
  
   <DIV class=reco-movieinfo><A class=reco-movieinfo__cover 
  href="indexmethod!yingxunupdate?id=${bean.id }" 
  gaevent="main/movieinfo/78233"><IMG 
 src="<%=basePath %>uploadfile/${bean.tupian }" width=156 height=210 border="0"> 
   </A>
  <H3><A class=reco-movieinfo__name  
  href="" target=_blank 
  gaevent="main/movieinfo/78233">${bean.pianming }</A></H3>
 <STRONG class=rates>票价:${bean.piaojia }￥</STRONG><br/>
 放映时间:<br/>${bean.fangyingshijian }
  <A class=btn  href="indexmethod!xuanzuo?id=${bean.id }" 
  gaevent="main/movieinfo/78233">购　票</A> </DIV>
  
  </c:forEach>
  
  
  </LI>

  </UL>

<STRONG class=rates>
${pagerinfo }
</STRONG>
  
 
</DIV>
</DIV>
</DIV>






</DIV><!-- bd end --></DIV><!-- bdw end -->

 </DIV><!-- doc end -->





 </BODY></HTML>


