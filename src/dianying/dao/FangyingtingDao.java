package dianying.dao;

import java.util.List;

import dianying.model.Fangyingting;



public interface FangyingtingDao  {
	
	
	
	public void insertBean(Fangyingting bean);
	
	public void deleteBean(Fangyingting bean);
	
	public void updateBean(Fangyingting bean);

	public Fangyingting selectBean(String where);
	
	public List<Fangyingting> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	
}
