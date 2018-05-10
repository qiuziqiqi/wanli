package dianying.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//订单
@Entity
@Table(name="t_Order")
public class Order {
	
	@Id
	@GeneratedValue
	private int id;//主键
	
	private String orderid;//订单号
	
	@ManyToOne
	@JoinColumn(name="zuoweiid")
	private Zuowei zuowei;//座位
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;
	
	private String truename;//姓名
	
	private String phone;//电话
	
	
	private String jiazong;//价格
	
	private Date createtime;//下单时间
	
	private String zhuangtai;//订单状态

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Zuowei getZuowei() {
		return zuowei;
	}

	public void setZuowei(Zuowei zuowei) {
		this.zuowei = zuowei;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJiazong() {
		return jiazong;
	}

	public void setJiazong(String jiazong) {
		this.jiazong = jiazong;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	


	
	
	
	
	
	
}
