package com.dasinong.ploughHelper.dao;


import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.dasinong.ploughHelper.model.PetSolu;

public class PetSoluDao extends HibernateDaoSupport implements IPetSoluDao{

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetSoluDao#save(com.dasinong.ploughHelper.model.PetSolu)
	 */
	@Override
	public void save(PetSolu petSolu) {
		getHibernateTemplate().save(petSolu);
	}


	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetSoluDao#update(com.dasinong.ploughHelper.model.PetSolu)
	 */
	@Override
	public void update(PetSolu petSolu) {
		getHibernateTemplate().update(petSolu);

	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetSoluDao#delete(com.dasinong.ploughHelper.model.PetSolu)
	 */
	@Override
	public void delete(PetSolu petSolu) {
		getHibernateTemplate().delete(petSolu);
	}

	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.dao.IPetSoluDao#findByPetSoluName(java.lang.String)
	 */
	@Override
	public PetSolu findByPetSoluName(String petSoluName) {
		List list = getHibernateTemplate().find(
				"from PetSolu where petSoluName=?",petSoluName);
		if (list==null||list.isEmpty()){
			return null;
		}
		return (PetSolu) list.get(0);
	}

}
