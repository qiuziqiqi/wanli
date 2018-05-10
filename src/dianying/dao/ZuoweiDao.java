package dianying.dao;

import java.util.List;

import dianying.model.Zuowei;



public interface ZuoweiDao  {
	
	
	
	public void insertBean(Zuowei bean);
	
	public void deleteBean(Zuowei bean);
	
	public void updateBean(Zuowei bean);

	public Zuowei selectBean(String where);
	
	public List<Zuowei> selectBeanList(final int start, final int limit,final String where);
	
	public int selectBeanCount(final String where);
	
	public void deleteLosts(List<Zuowei> list);
	
	public void saveLosts(List<Zuowei> list);
	
	
}
