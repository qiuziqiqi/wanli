package dianying.dao.impl;

import java.sql.SQLException;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dianying.dao.YingxunDao;
import dianying.model.Yingxun;









public class YingxunDaoImpl extends HibernateDaoSupport implements  YingxunDao{


	public void deleteBean(Yingxun bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Yingxun bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Yingxun selectBean(String where) {
		List<Yingxun> list = this.getHibernateTemplate().find("from Yingxun " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Yingxun "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Yingxun> selectBeanList(final int start,final int limit,final String where) {
		return (List<Yingxun>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Yingxun> list = session.createQuery("from Yingxun "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Yingxun bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	
}
