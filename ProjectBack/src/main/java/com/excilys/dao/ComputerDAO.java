package com.excilys.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.excilys.bean.Computer;
import com.excilys.bean.ListComputer;
import com.excilys.bean.QCompany;
import com.excilys.bean.QComputer;
import com.excilys.repository.ComputerRepository;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.path.PathBuilder;

@Repository
public class ComputerDAO implements IComputerDAO {
	@PersistenceContext
	private EntityManager ent;

	@Autowired
	private ComputerRepository repo;

	public void addComputer(Computer pComputer) {
		repo.save(pComputer);
	}

	public List<Computer> getComputers() {
		return repo.findAll();
	}

	public Computer getComputerById(int pIdComputer) {
		return repo.findOne(pIdComputer);
	}

	public void deleteComputer(int pIdComputer) {
		repo.delete(pIdComputer);
	}

	public void updateComputer(Computer pComputer) {
		repo.save(pComputer);
	}

	public ListComputer getComputers(String parameter,String searchC, Pageable page2){
		QComputer comp = QComputer.computer;
		QCompany companyQ = QCompany.company;
		
		BooleanBuilder bb = new BooleanBuilder();

		if (searchC != null && searchC!="") {
			bb.and(QComputer.computer.company.nameCompany
					.containsIgnoreCase(searchC));
		}
		if (parameter != null && parameter!="") {
			bb.and(QComputer.computer.nameComputer
					.containsIgnoreCase(parameter));
		}
		
		JPAQuery query = new JPAQuery(ent).from(comp).leftJoin(comp.company,companyQ).fetch().where(bb);
		
		//nombre d'element
		int i = (int) query.count();

		//permet de passer du sort pageable au sort de querydsl
		for (Sort.Order o : page2.getSort()) {
            query.orderBy(new OrderSpecifier(o.isAscending() ? com.mysema.query.types.Order.ASC
                    : com.mysema.query.types.Order.DESC, new PathBuilder(Computer.class, o.getProperty())));
        }
		
		//creation de la liste
		List<Computer> j = query.offset(page2.getOffset()).limit(10).list(comp);
		
		
		Page<Computer> pc2 = new PageImpl<Computer>(j,page2, i);
		ListComputer l = new ListComputer(pc2.getContent(),
				(int) pc2.getTotalElements());
		return l;
	}


	public ListComputer getComputers(Pageable page2){
		QComputer comp = QComputer.computer;
		QCompany companyQ = QCompany.company;
		JPAQuery query = new JPAQuery(ent).from(comp).leftJoin(comp.company,companyQ).fetch();
		
		//nombre d'element
		int i = (int) query.count();
		
		//permet de passer du sort pageable au sort de querydsl
		for (Sort.Order o : page2.getSort()) {
            query.orderBy(new OrderSpecifier(o.isAscending() ? com.mysema.query.types.Order.ASC
                    : com.mysema.query.types.Order.DESC, new PathBuilder(Computer.class, o.getProperty())));
        }
		
		//creation de la liste
		List<Computer> j = query.offset(page2.getOffset()).limit(10).list(comp);
		
		
		Page<Computer> pc2 = new PageImpl<Computer>(j,page2, i);
		ListComputer l = new ListComputer(pc2.getContent(),
				(int) pc2.getTotalElements());
		return l;
	}

	public boolean existComputer(int pIdComputer) {
		return repo.exists(pIdComputer);
	}
}