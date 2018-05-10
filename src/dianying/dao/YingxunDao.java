package dianying.dao;

import java.util.List;

import dianying.model.Yingxun;



public interface YingxunDao  {
	
	
	
	public void insertBean(Yingxun bean);
	
	public void deleteBean(Yingxun bean);
	
	public void updateBean(Yingxun bean);

	public Yingxun selectBean(String where);
	
	public List<Yingxun> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
