package com.dasinong.ploughHelper.dao;


import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.dasinong.ploughHelper.model.PetSolu;

public class PetSoluDao extends HibernateDaoSupport{

	public void save(PetSolu petSolu) {
		getHibernateTemplate().save(petSolu);
	}


	public void update(PetSolu petSolu) {
		getHibernateTemplate().update(petSolu);

	}

	public void delete(PetSolu petSolu) {
		getHibernateTemplate().delete(petSolu);
	}

	public PetSolu findByPetSoluName(String petSoluName) {
		List list = getHibernateTemplate().find(
				"from PetSolu where petSoluName=?",petSoluName);
		return (PetSolu) list.get(0);
	}

}
