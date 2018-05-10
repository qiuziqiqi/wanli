package dianying.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import dianying.dao.ZuoweiDao;
import dianying.model.Zuowei;









public class ZuoweiDaoImpl extends HibernateDaoSupport implements  ZuoweiDao{


	public void deleteBean(Zuowei bean) {
		this.getHibernateTemplate().delete(bean);
		
	}

	public void insertBean(Zuowei bean) {
		this.getHibernateTemplate().save(bean);
		
	}

	@SuppressWarnings("unchecked")
	public Zuowei selectBean(String where) {
		List<Zuowei> list = this.getHibernateTemplate().find("from Zuowei " +where);
		if(list.size()==0){
			return null;
		}
		return list.get(0);
	}

	public int selectBeanCount(String where) {
		long count = (Long)this.getHibernateTemplate().find("select count(*) from Zuowei "+where).get(0);
		return (int)count;
	}

	@SuppressWarnings("unchecked")
	public List<Zuowei> selectBeanList(final int start,final int limit,final String where) {
		return (List<Zuowei>)this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(final Session session)throws HibernateException, SQLException {				
				List<Zuowei> list = session.createQuery("from Zuowei "+where)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
				return list;
			}
		});
	}

	public void updateBean(Zuowei bean) {
		this.getHibernateTemplate().update(bean);
		
	}
	
	public void deleteLosts(List<Zuowei> list) {

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		int i = 0;
		for (Zuowei Zuowei : list) {
			System.out.println("start!!");
			if (session.contains(Zuowei)) {
				System.out.println("yes,it's here");
				continue;
			} else {
				session.update(Zuowei);
				i++;
				System.out.println("start--");
			}

			if (i % 100 == 0) {
				session.getTransaction().commit();
				session.flush();
				session.clear();
				session.beginTransaction();
			}

		}

		System.out.println(i);
		session.getTransaction().commit();
		session.close();
	}

	public void saveLosts(List<Zuowei> list) {

		Session session = this.getSessionFactory().openSession();
		session.beginTransaction();
		int i = 0;
		for (Zuowei Zuowei : list) {
			System.out.println("start!!");
			if (session.contains(Zuowei)) {
				System.out.println("yes,it's here");
				continue;
			} else {
				session.save(Zuowei);
				i++;
				System.out.println("start--");
			}

			if (i % 100 == 0) {
				session.getTransaction().commit();
				session.flush();
				session.clear();
				session.beginTransaction();
			}

		}

		System.out.println(i);
		session.getTransaction().commit();
		session.close();
	}
	
	
}
