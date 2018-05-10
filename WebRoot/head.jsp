<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<DIV id=hdw>
<DIV id=hd>
<DIV id=site-top class=cf>

<DIV class=city-info>
<span style="font-size: 30px;font-weight: bold;color: white;">
电影票订票网站
</span>

</DIV>
<br/><br/>
<DIV class=site-info>
</DIV>
<DIV class=search-w>
<DIV class="search cf">

<FORM class="search-form J-search-form" method=post name=searchForm action="index">
<INPUT class=s-text tabIndex=1 name="pianming" onFocus="this.value=''" value="请输入片名进行搜索" x-webkit-speech autocomplete="off"> 
<INPUT hideFocus class=s-submit value=搜索 type=submit> 

</FORM>
<DIV class=s-hot></DIV></DIV>
<DIV style="DISPLAY: none" class="search-suggest J-search-suggest">
<UL class="search-suggest__list J-search-suggest-list"></UL></DIV></DIV></DIV>
<DIV id=site-nav class=site-nav>
<DIV class="nav-wrapper cf">
<UL class=nav>
  <LI><A href="./" >
  <SPAN 
  class=nav-label>首页</SPAN></A></LI>

  
  <LI >
  <A href="indexmethod!orderlist" ><SPAN 
  class=nav-label>我的订单</SPAN></A></LI>
 
  
   <%
  
  if(session.getAttribute("user")==null){
	%>
	
	 <LI>
  <A href="register.jsp" >
  <SPAN class=nav-label>用户注册</SPAN></A></LI>
	
  <LI>
  <A href="login.jsp"  ><SPAN class=nav-label>用户登录</SPAN></A></LI>
  
   <LI>
  <A href="manage/login.jsp" >
  <SPAN class=nav-label>管理后台</SPAN></A></LI>
  
 <% 	  
  }
  %>
  <%
  
  if(session.getAttribute("user")!=null){
	%>
	 <LI>
  <A href="indexmethod!loginout"  ><SPAN class=nav-label>退出登录</SPAN></A></LI>
   <LI>
  <A href=""  ><SPAN class=nav-label>欢迎${user.username }登录本网站</SPAN></A>
  
<% 	  
  }
  %>
 

  </UL>
</DIV></DIV></DIV>
</DIV>