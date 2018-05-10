package dianying.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//放映厅
@Entity
@Table(name="t_Fangyingting")
public class Fangyingting {

	@Id
	@GeneratedValue
	private int id;
	
	private int deletestatus;//是否删除的状态，0表示未删除，1表示删除
	
	
	private String name;//放映厅名字
	
	private int zuoweishu;//座位数
	
	private int hang;//行
	
	private int lie;//列
	

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getZuoweishu() {
		return zuoweishu;
	}

	public void setZuoweishu(int zuoweishu) {
		this.zuoweishu = zuoweishu;
	}

	public int getHang() {
		return hang;
	}

	public void setHang(int hang) {
		this.hang = hang;
	}

	public int getLie() {
		return lie;
	}

	public void setLie(int lie) {
		this.lie = lie;
	}
	
	
	
	
}
