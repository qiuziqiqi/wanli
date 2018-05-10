package dianying.dao.impl;

import java.sql.SQLException;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dianying.dao.FangyingtingDao;
import dianying.model.Fangyingting;









public class FangyingtingDaoImpl extends HibernateDaoSupport implements  FangyingtingDao{


	public void deleteBean(Fangyingting bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Fangyingting bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Fangyingting selectBean(String where) {
		List<Fangyingting> list = this.getHibernateTemplate().find("from Fangyingting " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Fangyingting "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Fangyingting> selectBeanList(final int start,final int limit,final String where) {
		return (List<Fangyingting>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Fangyingting> list = session.createQuery("from Fangyingting "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Fangyingting bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
