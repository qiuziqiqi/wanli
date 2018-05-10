<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE></TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<STYLE type=text/css> 
{
	FONT-SIZE: 12px
}
#menuTree A {
	COLOR: #566984; TEXT-DECORATION: none
}
</STYLE>
<SCRIPT src="images/Left.files/TreeNode.js" type=text/javascript></SCRIPT>
<SCRIPT src="images/Left.files/Tree.js" type=text/javascript></SCRIPT>
<META content="MSHTML 6.00.2900.5848" name=GENERATOR>
</HEAD>
<BODY 
style="BACKGROUND-POSITION-Y: -120px; BACKGROUND-IMAGE: url(images/bg.gif); BACKGROUND-REPEAT: repeat-x">
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%">
  <TBODY>
    <TR>
      <TD width=10 height=29><IMG src="images/Left.files/bg_left_tl.gif"></TD>
      <TD 
    style="FONT-SIZE: 18px; BACKGROUND-IMAGE: url(images/bg_left_tc.gif); COLOR: white; FONT-FAMILY: system">Main 
        Menu</TD>
      <TD width=10><IMG src="images/Left.files/bg_left_tr.gif"></TD>
    </TR>
    <TR>
      <TD style="BACKGROUND-IMAGE: url(images/bg_left_ls.gif)"></TD>
      <TD id=menuTree 
    style="PADDING-RIGHT: 10px; PADDING-LEFT: 10px; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; HEIGHT: 100%; BACKGROUND-COLOR: white" 
    vAlign=top></TD>
      <TD style="BACKGROUND-IMAGE: url(images/bg_left_rs.gif)"></TD>
    </TR>
    <TR>
      <TD width=10><IMG src="images/Left.files/bg_left_bl.gif"></TD>
      <TD style="BACKGROUND-IMAGE: url(images/bg_left_bc.gif)"></TD>
      <TD width=10><IMG 
src="images/Left.files/bg_left_br.gif"></TD>
    </TR>
  </TBODY>
</TABLE>
<SCRIPT type=text/javascript>

var tree = null;

var root = new TreeNode('系统菜单');




var fun1 = new TreeNode('常规管理');
var fun2 = new TreeNode('放映厅管理', 'method!fangyingtinglist', 'tree_node.gif', null, 'tree_node.gif', null);
fun1.add(fun2);

var fun3 = new TreeNode('影讯管理', 'method!yingxunlist', 'tree_node.gif', null, 'tree_node.gif', null);
fun1.add(fun3);


root.add(fun1);

var fun4 = new TreeNode('经营管理');
var fun5 = new TreeNode('售票管理', 'method!yingxunlist2', 'tree_node.gif', null, 'tree_node.gif', null);
fun4.add(fun5);


var fun6 = new TreeNode('订单管理', 'method!orderlist', 'tree_node.gif', null, 'tree_node.gif', null);
fun4.add(fun6);


root.add(fun4);

var fun7 = new TreeNode('注册用户管理');
var fun8 = new TreeNode('注册用户管理', 'method!userlist', 'tree_node.gif', null, 'tree_node.gif', null);
fun7.add(fun8);


root.add(fun7);







tree = new Tree(root);tree.show('menuTree');


</SCRIPT>
</BODY>
</HTML>



