package com.github.baloise.rocketchatrestclient.model;

/**
 * Represents an object that has the value "_id".
 *
 * @since 0.0.1
 * @version 0.1.0
 */
public abstract class Identified implements Comparable<Identified> {
    private String _id;

    /**
     * Sets the "_id" of this object, it is ugly however it is required for the deserialization.
     * 
     * @param id the value to set
     */
    public void set_id(String id) {
        this._id = id;
    }

    /**
     * Gets the "_id" field of this record.
     * 
     * @return the _id value of this record
     */
    public String getId() {
        return this._id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Identified) obj)._id.equals(_id);
    }

    @Override
    public int hashCode() {
        return _id.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + "@" + _id;
    }

    @Override
    public int compareTo(Identified o) {
        return toString().compareTo(o.toString());
    }
}
