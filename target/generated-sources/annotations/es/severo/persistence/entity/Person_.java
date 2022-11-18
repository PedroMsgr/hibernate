package es.severo.persistence.entity;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile ListAttribute<Person, Address> addresses;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, Long> id;

	public static final String ADDRESSES = "addresses";
	public static final String NAME = "name";
	public static final String ID = "id";

}

