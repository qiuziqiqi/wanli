package dianying.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
//座位
@Entity
@BatchSize(size = 100)
@Table(name="t_Zuowei")
public class Zuowei {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="yingxunid")
	private Yingxun yingxun;
	
	private String zuowei;//座位信息
	
	private String zhuangtai;//订购状态  空座  已订座
	
	private int deletestatus;//是否删除的状态，0表示未删除，1表示删除
	
	private int pai;//排
	
	private int zuo;//座
	

	

	public int getZuo() {
		return zuo;
	}

	public void setZuo(int zuo) {
		this.zuo = zuo;
	}

	public int getPai() {
		return pai;
	}

	public void setPai(int pai) {
		this.pai = pai;
	}

	public int getDeletestatus() {
		return deletestatus;
	}

	public void setDeletestatus(int deletestatus) {
		this.deletestatus = deletestatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Yingxun getYingxun() {
		return yingxun;
	}

	public void setYingxun(Yingxun yingxun) {
		this.yingxun = yingxun;
	}

	public String getZuowei() {
		return zuowei;
	}

	public void setZuowei(String zuowei) {
		this.zuowei = zuowei;
	}

	public String getZhuangtai() {
		return zhuangtai;
	}

	public void setZhuangtai(String zhuangtai) {
		this.zhuangtai = zhuangtai;
	}
	
	
	
	
	


	
	

	


	
	
	
	
}
