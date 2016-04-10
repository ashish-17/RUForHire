/**
 * 
 */
package com.ruforhire.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * @author ashish
 *
 */
@DynamicUpdate
@Table(name="TEMPLATE")
public class Template {

	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
}
