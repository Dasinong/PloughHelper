package com.dasinong.ploughHelper.dao;

import java.util.List;
import java.util.Random;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dasinong.ploughHelper.model.Proverb;

public class ProverbDao extends HibernateDaoSupport implements IProverbDao{

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IProverbDao#save(com.dasinong.ploughHelper.model.Proverb)
	 */
	@Override
	public void save(Proverb proverb) {
		getHibernateTemplate().save(proverb);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IProverbDao#update(com.dasinong.ploughHelper.model.Proverb)
	 */
	@Override
	public void update(Proverb proverb) {
		getHibernateTemplate().update(proverb);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IProverbDao#delete(com.dasinong.ploughHelper.model.Proverb)
	 */
	@Override
	public void delete(Proverb proverb) {
		getHibernateTemplate().delete(proverb);
	}
	
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IProverbDao#findById(java.lang.Long)
	 */
	@Override
	public Proverb findById(Long id) {
		return (Proverb) this.getHibernateTemplate().get(Proverb.class,id);
	}
	
	@Override
	public Proverb findByLunarCalender(String lunar){
		List list = getHibernateTemplate().find(
				"from proverb where lunarCalender=?",lunar);
		if (list==null||list.isEmpty()){
			return null;
		}
		Random r = new Random();
		return (Proverb) list.get(r.nextInt(list.size()));
	}
	
	@Override	
	public Proverb findByWeather(String weather){
		List list = getHibernateTemplate().find(
				"from Proverb where weather=?",weather);
		if (list==null||list.isEmpty()){
			return null;
		}
		Random r = new Random();
		System.out.println("szc:proverbDao: weather:"+weather+" nongyan size:"+list.size());
		return (Proverb) list.get(r.nextInt(list.size()));
	}
	
	@Override
	public Proverb findByMonth(String month){
		List list = getHibernateTemplate().find(
				"from Proverb where month like '%?%'",month);
		if (list==null||list.isEmpty()){
			return null;
		}
		Random r = new Random();
		System.out.println("szc:proverbDao: month:"+month+" nongyan size:"+list.size());
		return (Proverb) list.get(r.nextInt(list.size()));
	}
	
	@Override
	public Proverb findByAccident(){
		List list = getHibernateTemplate().find(
				"from Proverb where month='' and lunarCalender='' and weather=''");
		if (list==null||list.isEmpty()){
			return null;
		}
		Random r = new Random();
		System.out.println("szc:proverbDao: random nongyan size:"+list.size());
		return (Proverb) list.get(r.nextInt(list.size()));
	}
	
}
