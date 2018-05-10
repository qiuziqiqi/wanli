package dianying.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dianying.dao.FangyingtingDao;
import dianying.dao.OrderDao;
import dianying.dao.UserDao;
import dianying.dao.YingxunDao;
import dianying.dao.ZuoweiDao;
import dianying.model.Fangyingting;
import dianying.model.Order;
import dianying.model.User;
import dianying.model.Yingxun;
import dianying.model.Zuowei;
import dianying.util.Pager;
import dianying.util.Util;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private UserDao userDao;

	private String url = "./";

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	
	
	
//登入请求
	public String login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectBean(" where username = '" + username
				+ "' and password= '" + password + "'  and role=1 and deletestatus=0 ");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("manage", user);
			this.setUrl("manage/index.jsp");
			return "redirect";
		} else {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");
			response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');window.location.href='login.jsp';</script>");
		}
		return null;
	}
//用户退出
	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("manage");
		this.setUrl("login.jsp");
		return SUCCESS;
	}
//跳转到修改密码页面
	public String changepwd() {
		this.setUrl("user/user.jsp");
		return SUCCESS;
	}
//修改密码操作
	public void changepwd2() throws IOException {
HttpServletRequest request = ServletActionContext.getRequest();
		
		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("manage");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(" where username= '"+u.getUsername()+"' and password= '"+password1+"' and deletestatus=0");
		if(bean!=null){
			bean.setPassword(password2);
			userDao.updateBean(bean);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('修改成功');</script>");
		}else{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('用户名或者密码错误');</script>");
		}
	}
	
	private YingxunDao  yingxunDao;

	public YingxunDao getYingxunDao() {
		return yingxunDao;
	}

	public void setYingxunDao(YingxunDao yingxunDao) {
		this.yingxunDao = yingxunDao;
	}
	
	

	
	private FangyingtingDao  fangyingtingDao;

	public FangyingtingDao getFangyingtingDao() {
		return fangyingtingDao;
	}

	public void setFangyingtingDao(FangyingtingDao fangyingtingDao) {
		this.fangyingtingDao = fangyingtingDao;
	}
	
	
	//放映厅列表
	public String fangyingtinglist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (name != null && !"".equals(name)) {

			sb.append("name like '%" + name + "%'");
			sb.append(" and ");
			request.setAttribute("name", name);
		}

		
		
		
		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 6;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = fangyingtingDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", fangyingtingDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!fangyingtinglist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!fangyingtinglist");
		request.setAttribute("url2", "method!fangyingting");
		request.setAttribute("title", "放映厅管理");
		this.setUrl("fangyingting/fangyingtinglist.jsp");
		return SUCCESS;

	}
//跳转到添加放映厅页面
	public String fangyingtingadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!fangyingtingadd2");
		request.setAttribute("title", "放映厅添加");
		this.setUrl("fangyingting/fangyingtingadd.jsp");
		return SUCCESS;
	}
//添加放映厅操作
	public void fangyingtingadd2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String hang = request.getParameter("hang");
		String lie = request.getParameter("lie");
		

		Fangyingting bean = new Fangyingting();
		bean.setName(name);
		bean.setHang(Integer.parseInt(hang));
		bean.setLie(Integer.parseInt(lie));
		bean.setZuoweishu(Integer.parseInt(hang)*Integer.parseInt(lie));
		
		fangyingtingDao.insertBean(bean);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!fangyingtinglist';</script>");
	}

//删除放映厅操作
	public void fangyingtingdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Fangyingting bean = fangyingtingDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		fangyingtingDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!fangyingtinglist';</script>");
	}
	
	//跳转到查看放映厅页面
	public String fangyingtingupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Fangyingting bean = fangyingtingDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "放映厅查看");
		this.setUrl("fangyingting/fangyingtingupdate3.jsp");
		return SUCCESS;
	}
	
	
	//跳转到更新放映厅页面
	public String fangyingtingupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Fangyingting bean = fangyingtingDao.selectBean(" where id= "+request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!fangyingtingupdate2");
		request.setAttribute("title", "放映厅修改");
		this.setUrl("fangyingting/fangyingtingupdate.jsp");
		return SUCCESS;
	}
//更新放映厅操作
	public void fangyingtingupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");
		String hang = request.getParameter("hang");
		String lie = request.getParameter("lie");


		Fangyingting bean = fangyingtingDao.selectBean(" where id= "+request.getParameter("id"));
		bean.setName(name);
		bean.setHang(Integer.parseInt(hang));
		bean.setLie(Integer.parseInt(lie));
		bean.setZuoweishu(Integer.parseInt(hang)*Integer.parseInt(lie));
		
		fangyingtingDao.updateBean(bean);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!fangyingtinglist';</script>");
	}
	
	
	//跳转到座位预览页面
	public String fangyingtingupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Fangyingting bean = fangyingtingDao.selectBean(" where id= "
				+ request.getParameter("id"));
		
		List<String> list = new ArrayList<String>();
		
		for(int i=0;i<bean.getHang();i++){
			StringBuffer sb = new StringBuffer();
			sb.append("<tr>");
			sb.append("<td>"+(i+1)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
			for(int j=0;j<bean.getLie();j++){
				sb.append("<td><img src='fangyingting/111.jpg'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
			}
			sb.append("</tr>");
			list.add(sb.toString());
		}
		
		
		request.setAttribute("list", list);
		request.setAttribute("title", "座位预览");
		this.setUrl("fangyingting/fangyingtingupdate5.jsp");
		return SUCCESS;
	}
	
	//影讯列表
	public String yingxunlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pianming = request.getParameter("pianming");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (pianming != null && !"".equals(pianming)) {

			sb.append("pianming like '%" + pianming + "%'");
			sb.append(" and ");
			request.setAttribute("pianming", pianming);
		}

		
		
		
		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 6;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = yingxunDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", yingxunDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!yingxunlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!yingxunlist");
		request.setAttribute("url2", "method!yingxun");
		request.setAttribute("title", "影讯管理");
		this.setUrl("yingxun/yingxunlist.jsp");
		return SUCCESS;

	}
//跳转到添加影讯页面
	public String yingxunadd() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("url", "method!yingxunadd2");
		request.setAttribute("fangyingtinglist", fangyingtingDao.selectBeanList(0, 9999, " where deletestatus=0 "));
		request.setAttribute("title", "影讯添加");
		this.setUrl("yingxun/yingxunadd.jsp");
		return SUCCESS;
	}
//添加影讯操作
	public void yingxunadd2() throws IOException, NumberFormatException, ParseException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		String fangyingting = request.getParameter("fangyingting");
		String daoyan = request.getParameter("daoyan");
		
		String gongsi = request.getParameter("gongsi");
		String juqing = request.getParameter("juqing");
		String pianming = request.getParameter("pianming");
		String piaojia = request.getParameter("piaojia");
		
		String zhuyan = request.getParameter("zhuyan");
		String yuyan = request.getParameter("yuyan");
		
		
		String fangyingshijian = request.getParameter("fangyingshijian");//放映时间
		String shichang = request.getParameter("shichang");//时长
		String jieshushijian = Util.yunsuan(fangyingshijian, Integer.parseInt(shichang));//获取结束时间
		
		Date t1 = Util.getTime4(fangyingshijian);//放映时间
		
		long lt1 = t1.getTime();
		
		Date t2  = new Date();
		
		long lt2 = t2.getTime(); 
		
		if(lt1<lt2){
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('操作失败，放映时间必须大于当前时间');window.location.href='method!yingxunadd';</script>");
			return;
		}
		
		//fangyingshijian  放映时间在某个时间范围
		Yingxun yx = yingxunDao.selectBean(" where fangyingting.id="+fangyingting+" and   '"+fangyingshijian+"'>=fangyingshijian and  '"+fangyingshijian+"'<= jieshushijian ");
		if(yx!=null){
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('操作失败，在该时间段有放映的电影');window.location.href='method!yingxunadd';</script>");
			return;
		}
		//jieshushijian  结束时间在某个时间方位
		yx = yingxunDao.selectBean(" where  fangyingting.id="+fangyingting+" and '"+jieshushijian+"'>=fangyingshijian and  '"+jieshushijian+"'<= jieshushijian ");
		if(yx!=null){
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('操作失败，在该时间段有放映的电影');window.location.href='method!yingxunadd';</script>");
			return;
		}
		//包含整个放映区间
		
		yx = yingxunDao.selectBean(" where  fangyingting.id="+fangyingting+" and '"+fangyingshijian+"'<=fangyingshijian and  '"+jieshushijian+"'>= jieshushijian ");
		if(yx!=null){
			response
			.getWriter()
			.print(
					"<script language=javascript>alert('操作失败，在该时间段有放映的电影');window.location.href='method!yingxunadd';</script>");
			return;
		}
		
		String savapath = ServletActionContext.getServletContext().getRealPath("/")+"/uploadfile/";
		String time = Util.getTime2();
		String imgpath = time+".jpg";
		File file = new File(savapath+imgpath);
		Util.copyFile(uploadfile, file);
		Yingxun bean = new Yingxun();
		bean.setFangyingting(fangyingtingDao.selectBean(" where id= "+fangyingting));
		bean.setCreatetime(new Date());
		bean.setDaoyan(daoyan);
		bean.setDeletestatus(0);
		bean.setFangyingshijian(fangyingshijian);
		bean.setGongsi(gongsi);
		bean.setJuqing(juqing);
		bean.setPianming(pianming);
		bean.setPiaojia(piaojia);
		bean.setShichang(shichang);
		bean.setTupian(imgpath);
		bean.setYuyan(yuyan);
		bean.setZhuyan(zhuyan);
		bean.setShengyu(bean.getFangyingting().getZuoweishu());
		bean.setJieshushijian(jieshushijian);
		yingxunDao.insertBean(bean);
		Fangyingting fyt = bean.getFangyingting();
		
		List<Zuowei> list = new ArrayList<Zuowei>();
		
		for(int i=0;i<fyt.getHang();i++){
			
			for(int j=1;j<fyt.getLie()+1;j++){
				Zuowei z = new Zuowei();
				z.setYingxun(bean);
				z.setZhuangtai("空座");
				z.setZuowei((i+1)+"排"+j+"座");
				z.setPai((i+1));
				z.setZuo(j);
				list.add(z);
			}
		}
		zuoweiDao.saveLosts(list);
		
		
		
		
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!yingxunlist';</script>");
	}

//删除影讯操作
	public void yingxundelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Yingxun bean = yingxunDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		yingxunDao.updateBean(bean);
		
		List<Zuowei> list = zuoweiDao.selectBeanList(0, 9999, " where yingxun.id= "+bean.getId() +" and deletestatus=0 ");
		zuoweiDao.deleteLosts(list);
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!yingxunlist';</script>");
	}
	
	//跳转到查看影讯页面
	public String yingxunupdate3() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Yingxun bean = yingxunDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "影讯查看");
		this.setUrl("yingxun/yingxunupdate3.jsp");
		return SUCCESS;
	}
	
	
	//跳转到更新影讯页面
	public String yingxunupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("fangyingtinglist", fangyingtingDao.selectBeanList(0, 9999, " where deletestatus=0 "));
		Yingxun bean = yingxunDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!yingxunupdate2");
		request.setAttribute("title", "影讯修改");
		this.setUrl("yingxun/yingxunupdate.jsp");
		return SUCCESS;
	}
//更新影讯操作
	public void yingxunupdate2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
	
		String daoyan = request.getParameter("daoyan");
		String gongsi = request.getParameter("gongsi");
		String juqing = request.getParameter("juqing");
		String pianming = request.getParameter("pianming");
		String piaojia = request.getParameter("piaojia");
		
		String zhuyan = request.getParameter("zhuyan");
		String yuyan = request.getParameter("yuyan");
		

		Yingxun bean = yingxunDao.selectBean(" where id= "+request.getParameter("id"));

		bean.setDaoyan(daoyan);
		bean.setGongsi(gongsi);
		bean.setJuqing(juqing);
		bean.setPianming(pianming);
		bean.setPiaojia(piaojia);

		bean.setYuyan(yuyan);
		bean.setZhuyan(zhuyan);
		if(uploadfile!=null){
			String savapath = ServletActionContext.getServletContext().getRealPath("/")+"/uploadfile/";
			String time = Util.getTime2();
			String imgpath = time+".jpg";
			File file = new File(savapath+imgpath);
			Util.copyFile(uploadfile, file);
			bean.setTupian(imgpath);
		}
		
		
		yingxunDao.updateBean(bean);
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!yingxunlist';</script>");
	}
	
	private File uploadfile;
	
	
	public File getUploadfile() {
		return uploadfile;
	}


	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}
	
	
	private ZuoweiDao zuoweiDao;

	public ZuoweiDao getZuoweiDao() {
		return zuoweiDao;
	}

	public void setZuoweiDao(ZuoweiDao zuoweiDao) {
		this.zuoweiDao = zuoweiDao;
	}
	
	
	//经营列表
	public String yingxunlist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String pianming = request.getParameter("pianming");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (pianming != null && !"".equals(pianming)) {

			sb.append("pianming like '%" + pianming + "%'");
			sb.append(" and ");
			request.setAttribute("pianming", pianming);
		}

		
		
		
		sb.append("  deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 6;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = yingxunDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", yingxunDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!yingxunlist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!yingxunlist2");
		request.setAttribute("url2", "method!yingxun");
		request.setAttribute("title", "经营列表");
		this.setUrl("yingxun/yingxunlist2.jsp");
		return SUCCESS;

	}
	
	
	
	//跳转到查看座位页面
	public String yingxunupdate5() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Yingxun bean = yingxunDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		List<Zuowei> list = zuoweiDao.selectBeanList(0, 99999, " where  deletestatus=0 and yingxun.id= "+bean.getId()+"  order by pai,zuo");
		
		
		List<String> list2 = new ArrayList<String>();
		
		for(int i=1;i<bean.getFangyingting().getHang()+1;i++){
			StringBuffer sb = new StringBuffer();
			sb.append("<tr>");
			sb.append("<td>"+(i+1)+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
			for(Zuowei z:list){
				if(z.getPai()==i){
					
			
					
					if("空座".equals(z.getZhuangtai())){
						sb.append("<td><img src='images/111.jpg'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
					}else{
						sb.append("<td><img src='images/333.jpg'/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
					}
					
				}
				
			}
			sb.append("</tr>");
			list2.add(sb.toString());
		}
		
		request.setAttribute("list", list2);
		request.setAttribute("title", "座位查看");
		this.setUrl("yingxun/yingxunupdate5.jsp");
		return SUCCESS;
	}
	
	
	//订单列表
	public String orderlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String orderid = request.getParameter("orderid");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (orderid != null && !"".equals(orderid)) {

			sb.append("orderid like '%" + orderid + "%'");
			sb.append(" and ");
			request.setAttribute("orderid", orderid);
		}

		
		
		
		sb.append("  1=1 order by zhuangtai desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 6;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = orderDao.selectBeanCount(where.replaceAll("order by zhuangtai desc", ""));
		request.setAttribute("list", orderDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!orderlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!orderlist");
		request.setAttribute("url2", "method!order");
		request.setAttribute("title", "订单管理");
		this.setUrl("order/orderlist.jsp");
		return SUCCESS;

	}


//删除订单操作
	public void orderdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		Order bean = orderDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setZhuangtai("已取票");
		orderDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!orderlist';</script>");
	}
	
	
	private OrderDao  orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	
	//用户列表
	public String userlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}

		
		
		
		sb.append("   role=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 6;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1)
				* pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "method!userlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "用户管理");
		this.setUrl("user/userlist.jsp");
		return SUCCESS;

	}


//锁定用户操作
	public void userdelete() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(1);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
	}
	
	//解锁用户操作
	public void userdelete2() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		User bean = userDao.selectBean(" where id= "
				+ request.getParameter("id"));
		bean.setDeletestatus(0);
		userDao.updateBean(bean);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
	}
}
