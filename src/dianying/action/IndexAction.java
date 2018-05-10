package dianying.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dianying.dao.OrderDao;
import dianying.dao.UserDao;
import dianying.dao.YingxunDao;
import dianying.dao.ZuoweiDao;
import dianying.model.Order;
import dianying.model.User;
import dianying.model.Yingxun;
import dianying.model.Zuowei;
import dianying.util.Pager;
import dianying.util.Util;

public class IndexAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String url = "./";

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
	private YingxunDao  yingxunDao;
	
	public YingxunDao getYingxunDao() {
		return yingxunDao;
	}


	public void setYingxunDao(YingxunDao yingxunDao) {
		this.yingxunDao = yingxunDao;
	}


	// 网站首页
	public String index() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Util.init(request);
		
		
		String pianming = request.getParameter("pianming");

		
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (pianming != null && !"".equals(pianming)) {

			sb.append("pianming like '%" + pianming + "%'");
			sb.append(" and ");
			request.setAttribute("pianming", pianming);
		}
		
		String t1 = Util.getTime();
		
		sb.append("  fangyingshijian>'"+t1+"' and  deletestatus=0 order by id desc ");
		//sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();


		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = yingxunDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		List<Yingxun> list = yingxunDao.selectBeanList((currentpage - 1)* pagesize, pagesize, where);

		request.setAttribute("list",list );
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize,
				currentpage, "index", "共有" + total + "条记录"));
		

		return "success";
	}
	
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 用户注册操作
	public void register() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String truename = request.getParameter("truename");

		User bean = userDao.selectBean("  where deletestatus=0 and username='"
				+ username + "'");

		if (bean == null) {
			bean = new User();
			bean.setCreatetime(new Date());
			bean.setLianxifangshi(phone);

			bean.setPassword(password);

			bean.setTruename(truename);
			bean.setUsername(username);

			userDao.insertBean(bean);
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			PrintWriter writer = response.getWriter();
			writer
					.print("<script  language='javascript'>alert('注册成功');window.location.href='index'; </script>");
		} else {
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			PrintWriter writer = response.getWriter();
			writer
					.print("<script  language='javascript'>alert('用户名已经存在，注册失败！');window.location.href='register.jsp'; </script>");
		}

	}
	
	
	// 用户登录操作
	public void login() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User bean = userDao.selectBean("  where deletestatus=0 and username='"
				+ username + "' and password='" + password + "'");
		if (bean != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", bean);
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			PrintWriter writer = response.getWriter();
			writer
					.print("<script  language='javascript'>alert('登录成功！');window.location.href='index'; </script>");
		} else {
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			PrintWriter writer = response.getWriter();
			writer
					.print("<script  language='javascript'>alert('用户名或者密码错误！登录失败');window.location.href='index'; </script>");
		}

	}

	// 用户退出操作
	public void loginout() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		PrintWriter writer = response.getWriter();
		writer
				.print("<script  language='javascript'>alert('退出成功！');window.location.href='index'; </script>");

	}
	
	private static List<Yingxun> suiji(List<Yingxun> list, int num) {
		Collections.shuffle(list);
		List<Yingxun> list2 = new ArrayList<Yingxun>();
		if (list.size() <= num) {
			num = list.size();
		}
		for (int i = 0; i < num; i++) {
			list2.add(list.get(i));
		}
		return list2;
	}
	
	// 跳转到查看影讯信息页面
	public String yingxunupdate() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Yingxun bean = yingxunDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		String t1 = Util.getTime();
		
		
		//随机推荐放映的同导演的2部电影
		List<Yingxun> list = yingxunDao.selectBeanList(0, 9999, " where id!="+bean.getId()+" and  daoyan like '%"+bean.getDaoyan()+"%' and fangyingshijian>'"+t1+"' and  deletestatus=0 ");
		
		List<Yingxun> list2 = this.suiji(list, 2);
		request.setAttribute("list", list2);
		this.setUrl("yingxun.jsp");
		return SUCCESS;
	}
	
	
	// 跳转到选座位页面
	public String xuanzuo() throws IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			PrintWriter writer = response.getWriter();
			writer.print("<script  language='javascript'>alert('请先登录');window.location.href='login.jsp'; </script>");
			return null;
		}
		Yingxun bean = yingxunDao.selectBean(" where id= "
				+ request.getParameter("id"));
		request.setAttribute("bean", bean);
		
		List<Zuowei> list = zuoweiDao.selectBeanList(0, 99999, " where  deletestatus=0 and yingxun.id= "+bean.getId()+"  order by pai,zuo");
		
		
		List<String> list2 = new ArrayList<String>();
		
		for(int i=1;i<bean.getFangyingting().getHang()+1;i++){
			StringBuffer sb = new StringBuffer();
			sb.append("<P>");
			sb.append("<SPAN class=num>"+i+"</SPAN>");
			for(Zuowei z:list){
				if(z.getPai()==i){
					
					if("空座".equals(z.getZhuangtai())){
						sb.append("<A hideFocus class='seat active' title="+z.getZuowei()+" href='javascript:;' data-column='"+z.getZuo()+"' data-row='"+z.getPai()+"' data-type='N' data-no=''>");
						sb.append("<img src='images/111.jpg'  id='"+z.getPai()+"9999"+z.getZuo()+"'  onclick='change("+z.getPai()+"9999"+z.getZuo()+")'/>");
						sb.append("</A>");
					}else{
						sb.append("<A hideFocus class='seat active' title="+z.getZuowei()+" href='javascript:void(0)' >");
						sb.append("<img src='images/333.jpg'  onclick=''/>");
						sb.append("</A>");
					}
					
				}
				
			}
			sb.append("</P>");
			list2.add(sb.toString());
		}
		
		request.setAttribute("list", list2);
		this.setUrl("xuanzuo.jsp");
		return SUCCESS;
	}
	
	
	
	private ZuoweiDao zuoweiDao;

	public ZuoweiDao getZuoweiDao() {
		return zuoweiDao;
	}


	public void setZuoweiDao(ZuoweiDao zuoweiDao) {
		this.zuoweiDao = zuoweiDao;
	}
	

	
	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}


	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	
	//生成订单
	public void createorder() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('请先登录！');window.location.href='login.jsp';</script>");
			return ;
		}
		String yingxunid = request.getParameter("yingxunid");
		String zid1 = request.getParameter("zid1");
		String zid2 = request.getParameter("zid2");
		String zid3 = request.getParameter("zid3");
		String zid4 = request.getParameter("zid4");
		String phone = request.getParameter("phone");
		String truename = request.getParameter("truename");
		
		Yingxun yx = yingxunDao.selectBean(" where id= "+yingxunid);
		

		
		List<Zuowei> list = new ArrayList<Zuowei>();
		if (zid1 != null && !"".equals(zid1)) {
			Zuowei zw = zuoweiDao.selectBean(" where yingxun.id= "+yx.getId()+" and zuowei='"+zid1+"' and deletestatus=0 ");
			if(zw!=null)
			list.add(zw);
		}
		if (zid2 != null && !"".equals(zid2)) {
			Zuowei zw = zuoweiDao.selectBean(" where yingxun.id= "+yx.getId()+" and zuowei='"+zid2+"' and deletestatus=0 ");
			if(zw!=null)
			list.add(zw);
		}
		if (zid3 != null && !"".equals(zid3)) {
			Zuowei zw = zuoweiDao.selectBean(" where yingxun.id= "+yx.getId()+" and zuowei='"+zid3+"' and deletestatus=0 ");
			if(zw!=null)
			list.add(zw);
		}
		if (zid4 != null && !"".equals(zid4)) {
			Zuowei zw = zuoweiDao.selectBean(" where yingxun.id= "+yx.getId()+" and zuowei='"+zid4+"' and deletestatus=0 ");
			if(zw!=null)
			list.add(zw);
		}
		for(Zuowei z:list){
			Order bean = new Order();
			bean.setCreatetime(new Date());
			bean.setJiazong(yx.getPiaojia());
			bean.setOrderid(new Date().getTime()+"");
			bean.setPhone(phone);
			bean.setTruename(truename);
			bean.setUser(user);
			bean.setZhuangtai("已提交");
			bean.setZuowei(z);
			orderDao.insertBean(bean);
			z.setZhuangtai("已订座");
			zuoweiDao.updateBean(z);
			yx.setShengyu(yx.getShengyu()-1);
			yingxunDao.updateBean(yx);
			
		}
		
		
		response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
		response
				.getWriter()
				.print(
						"<script language=javascript>alert('购买成功');window.location.href='indexmethod!orderlist';</script>");
	}
	
	
	//我的订单
	public String orderlist () throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null){
			response.setCharacterEncoding("gbk");response.setContentType("text/html; charset=gbk");
			response
					.getWriter()
					.print(
							"<script language=javascript>alert('请先登录！');window.location.href='login.jsp';</script>");
			return null;
		}
		
		int currentpage = 1;
		int pagesize = 10;
		if(request.getParameter("pagenum")!=null){
			currentpage= Integer.parseInt(request.getParameter("pagenum"));
		}

		int total = orderDao.selectBeanCount(" where user.id= "+user.getId());
		request.setAttribute("orderlist",orderDao.selectBeanList((currentpage-1)*pagesize, pagesize, " where user.id= "+user.getId()+" order by id desc "));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize, currentpage, "indexmethod!orderlist","共有"+total+"单订单"));
		this.setUrl("orderlist.jsp");
		return SUCCESS;
	}
	
	
	
	
	

}
