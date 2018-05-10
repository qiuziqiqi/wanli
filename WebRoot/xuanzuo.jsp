<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0073)http://maoyan.meituan.com/xuanzuo/shop/1550227/show/201308250042512/seats -->
<HTML><HEAD><META content="IE=7.0000" http-equiv="X-UA-Compatible">
<TITLE>电影票订票网站</TITLE>
<META charset=utf-8>
<META content=IE=edge http-equiv=X-UA-Compatible>


<LINK rel=stylesheet 
href="_files/base.4ee25fe9.css">
<META name=GENERATOR content="MSHTML 8.00.7601.18210">
<LINK rel=stylesheet href="_files2/combo.css">
<LINK rel=stylesheet href="_files2/combo(1).css">

<META name=msapplication-navbutton-color content=#C3E9F6>
<META name=msapplication-window content=width=device-width;height=device-height>

<META name=GENERATOR content="MSHTML 8.00.7601.18210">
<script type="text/javascript" language="javascript">
var i =0;
function change(id){


	
		var v = document.getElementById(id).src;

	if(v.indexOf('111.jpg')>0 &i<4){
		document.getElementById(id).src = 'images/222.jpg';
		i++;
	}else if(v.indexOf('222.jpg')>0 ) {
		document.getElementById(id).src = 'images/111.jpg';
		i--;
	}
}

function checkform(){


	
		var v1 = document.getElementById("li1").innerHTML;
		
		document.getElementById("zid1").value=v1;
		
		var v2 = document.getElementById("li2").innerHTML;
		
		document.getElementById("zid2").value=v2;
		
		var v3 = document.getElementById("li3").innerHTML;
		
		document.getElementById("zid3").value=v3;
		
		var v4 = document.getElementById("li4").innerHTML;
		
		document.getElementById("zid4").value=v4;
		
		
		if(document.getElementById("truenameid").value==""){
		
			alert('真实姓名不能为空');
			return false;
		}
		
		if(document.getElementById("phoneid").value==""){
		
		alert('手机不能为空');
		return false;
	}
	
	valid = /^0?1[3,5,8][0,1,2,3,4,5,6,7,8,9]\d{8}$/;
	
	if(!valid.test(document.getElementById("phoneid").value)){
		
		alert('请输入正确的手机格式');
		return false;
	}
	
	return true;
}
		



</script>
</HEAD>
<BODY>


 <%@ include file="head.jsp" %>
<DIV id=bd>
<DIV class=bread-nav><span style="font-size: 25px;font-weight: bold;">在线选座</span> </DIV>
<DIV id=tips>购票成功后，请在观影前去影院的自助取票机兑换取票。</DIV>
<DIV class="pg-choose body cf">
<DIV class="main cf">
<DIV class=main-wrapper>

<DIV id=J_SeatInfo class=seat-info>
<DIV class=seat-item data-poiid="1550227" data-showid="42512" 
data-showdate="2013-08-25" data-sectionname="普通区" data-sectionid="01">
<H2>大银幕</H2>
<DIV class=seat-content>

<c:forEach items="${list}"  var="bean">

${bean }

</c:forEach>





</DIV>
</DIV>
</DIV>

</DIV></DIV>

<DIV class=sidebar>
<DIV class=movie-info><IMG 
src="<%=basePath %>uploadfile/${bean.tupian }" 
width=68 height=102> 
<H2>${bean.pianming }</H2>
<P><SPAN class=t>剩余票数</SPAN>${bean.shengyu }</P>
<P><SPAN class=t>时长</SPAN> ${bean.shichang }</P>
<UL>
  <LI><SPAN class=t>放映厅</SPAN><STRONG>${bean.fangyingting.name }</STRONG> </LI>
  <LI><SPAN class=t>放映时间</SPAN><STRONG> ${bean.fangyingshijian }</STRONG> </LI>
  
  <LI><SPAN class=t>票价</SPAN><STRONG><EM>¥ ${bean.piaojia }</EM>/张 </STRONG></LI></UL></DIV>
<DIV class=choose-seat>

<FORM method=post  action="indexmethod!createorder"  onsubmit="return checkform()">
<P><SPAN class=t>座位</SPAN>点击左侧座位直接选择</P>
<DIV class=cf>
<UL id=J_SeatShow data-fee="0" data-price="${bean.piaojia }">
  <LI id="li1"></LI>
  <LI id="li2"></LI>
  <LI id="li3"></LI>
  <LI id="li4"></LI></UL></DIV>
<P><SPAN class=t>票数</SPAN><SPAN class=val>0</SPAN></P>
<P><SPAN class=t>总价</SPAN><SPAN class=val>¥0</SPAN></P>

<input  type="hidden"     name="yingxunid" value="${bean.id }" />
<input  type="hidden"  id="zid1" name="zid1" />
<input  type="hidden"  id="zid2" name="zid2" />
<input  type="hidden"  id="zid3" name="zid3" />
<input  type="hidden"  id="zid4" name="zid4" />

<P>手机号码:<INPUT maxLength=11 name=phone  id="phoneid" />
</P>

<P>真实姓名:<INPUT  name=truename id="truenameid"/>
</P>


<P id=J_BindMsg class="gray msg"></P>
<INPUT class="btn btn-hot" value=提交订单 type=submit  > 
</FORM>

</DIV></DIV></DIV>
<DIV id=show-time-tips>
<DIV class="hd cf">
<UL>
  <LI class=selected>加载中... </LI></UL></DIV>
<DIV class=bd>
<P>数据加载中，请稍后...</P></DIV></DIV></DIV>




<SCRIPT 
src="_files/jquery.e1288116.js"></SCRIPT>



<SCRIPT 
src="_files/base.f4a42dbf.js"></SCRIPT>

<SCRIPT>
    $(function () {
        Seat.bindMobile('#J_BindMobile');
        // 选座初始化事件
        Seat.attach();
        Seat.showTime('#J_ShowTime');
    });
</SCRIPT>

<SCRIPT>LOG_POS='other';</SCRIPT>
</BODY></HTML>

