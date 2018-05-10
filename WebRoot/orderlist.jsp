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
href="javascript:void(0)" gaevent="main/hotmovie"  >我的订单</A> <SPAN 
class=option-switch__delimiter></SPAN>


 </DIV>
 
<DIV class=recommend-movies__slides-w>

<DIV style="WIDTH: 940px" class="reco-slides J-option-content">
<TABLE  border=1 cellSpacing=0 cellPadding=0 width="100%">
  <THEAD>
  <TR class=chose-tt>
    <TH >订单号</TH>
    <TH >下单时间</TH>
    <TH >电影名字</TH>
    <TH >放映时间</TH>
    <TH >放映厅</TH>
    <TH >座位</TH>
    <TH >票价</TH>

    <TH >订单状态</TH>
    <TH >操作</TH></TR></THEAD>
  <TBODY>
  
  <c:forEach items="${orderlist}" var="beanbean">
  <TR class=blocks begtime="00:05" ttype="D601" name="checi">
    <TD>${beanbean.orderid }</STRONG></TD>
     <TD>${fn:substring(beanbean.createtime,0, 19)}</TD>
    <TD>${beanbean.zuowei.yingxun.pianming }</TD>
     <TD>${beanbean.zuowei.yingxun.fangyingshijian }</TD>
      <TD>${beanbean.zuowei.yingxun.fangyingting.name }</TD>
       <TD>${beanbean.zuowei.zuowei }</TD>
        <TD>${beanbean.zuowei.yingxun.piaojia }</TD>
        <TD>${beanbean.zhuangtai }</TD>


    <TD>
    <a href="method!order"></a>
    <INPUT class=list-yd  value=电影详细信息 type="button" onclick="javascript:window.location.href='indexmethod!yingxunupdate?id=${beanbean.zuowei.yingxun.id }';"  /> 
    </TD>
  </TR>
  </c:forEach>
   <TR class=chose-tt>
    <Td colspan="9">${pagerinfo }</Td>
  </TR>

      </TBODY>
      
      </TABLE>
  
 
</DIV>
</DIV>
</DIV>






</DIV><!-- bd end --></DIV><!-- bdw end -->

 </DIV><!-- doc end -->





 </BODY></HTML>


