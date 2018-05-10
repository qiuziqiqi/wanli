package dianying.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//影讯
@Entity
@Table(name="t_Yingxun")
public class Yingxun {

	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//是否删除的状态，0表示未删除，1表示删除
	
	private String pianming;//片名
	
	private String piaojia;//票价 
	
	private String shichang;//时长
	
	private String gongsi;//公司
	
	private String yuyan;//语言
	
	private String daoyan;//导演
	
	private String zhuyan;//主演
	

@Column(name="juqing", columnDefinition="TEXT")
	private String juqing;//剧情
	
	private String tupian;//图片
	
	private String fangyingshijian;//放映时间
	
	private String jieshushijian;//结束时间
	
	@ManyToOne
	@JoinColumn(name="fangyingtingid")
	private Fangyingting fangyingting;
	
	private Date createtime;
	
	private int shengyu;//剩余票数

	public int getShengyu() {
		return shengyu;
	}

	public void setShengyu(int shengyu) {
		this.shengyu = shengyu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public String getPianming() {
		return pianming;
	}

	public void setPianming(String pianming) {
		this.pianming = pianming;
	}

	public String getPiaojia() {
		return piaojia;
	}

	public void setPiaojia(String piaojia) {
		this.piaojia = piaojia;
	}

	public String getShichang() {
		return shichang;
	}

	public void setShichang(String shichang) {
		this.shichang = shichang;
	}

	public String getGongsi() {
		return gongsi;
	}

	public void setGongsi(String gongsi) {
		this.gongsi = gongsi;
	}

	public String getYuyan() {
		return yuyan;
	}

	public void setYuyan(String yuyan) {
		this.yuyan = yuyan;
	}

	public String getDaoyan() {
		return daoyan;
	}

	public void setDaoyan(String daoyan) {
		this.daoyan = daoyan;
	}

	public String getZhuyan() {
		return zhuyan;
	}

	public void setZhuyan(String zhuyan) {
		this.zhuyan = zhuyan;
	}


	public String getJuqing() {
		return juqing;
	}

	public void setJuqing(String juqing) {
		this.juqing = juqing;
	}

	public String getTupian() {
		return tupian;
	}

	public void setTupian(String tupian) {
		this.tupian = tupian;
	}

	public String getFangyingshijian() {
		return fangyingshijian;
	}

	public void setFangyingshijian(String fangyingshijian) {
		this.fangyingshijian = fangyingshijian;
	}

	

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Fangyingting getFangyingting() {
		return fangyingting;
	}

	public void setFangyingting(Fangyingting fangyingting) {
		this.fangyingting = fangyingting;
	}

	public String getJieshushijian() {
		return jieshushijian;
	}

	public void setJieshushijian(String jieshushijian) {
		this.jieshushijian = jieshushijian;
	}

	

	

	

	


	
	
	
	
}
