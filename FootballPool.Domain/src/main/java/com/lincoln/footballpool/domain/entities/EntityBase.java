package com.lincoln.footballpool.domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityBase {

	//Member Variables-------------------------------------------------------------
	
	/**
	 * The unique id of the entity.
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	//TODO: Add member variable involved with versioning the entity.  
	//Research how this can be done with JPA!!
	
	//Getters/Setter-------------------------------------------------------------
	
	/**
	 * Gets the unique id of the entity.
	 * 
	 * @return unique id of the entity.
	 */
	public Long getId() {
		return this.id;
	}
	
	//Public Methods-------------------------------------------------------------

	 /**
     * Override of {@code equals} method from {@code Object} that determines
     * whether or not the supplied object is equivalent from a data standpoint
     * to this instance.
     *
     * @param obj object to compare with this object.
     *
     * @return true if the supplied instance is the same as this instance;
     * otherwise false
     */
    @Override
    public boolean equals(final Object obj) {

        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EntityBase)) {
            return false;
        }

        //Cast object to compare to Install instance.
        EntityBase instanceToCompare = (EntityBase) obj;
        
        return instanceToCompare.getId() == this.getId();
    }
    
    /**
     * Override of {@code hashCode} method of type {@code Object} that computes
     * a hash code value based on values of the member variables of the
     * instance.
     *
     * @return hash code value computed based on the values of the member
     * variables of the instance.
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + this.id.hashCode();
        
        return result;
    }
}
