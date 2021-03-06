/*
 * $Header: /home/cvs/jakarta-commons/primitives/src/java/org/apache/commons/collections/primitives/ArrayIntList.java,v 1.3 2003/10/16 20:49:36 scolebourne Exp $
 * ====================================================================
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2002-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgement may appear in the software itself,
 *    if and wherever such third-party acknowledgements normally appear.
 *
 * 4. The names "The Jakarta Project", "Commons", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package com.org.apache.commons.collections.primitives;

import java.io.Serializable;

/**
 * An {@link IntList} backed by an array of ints.
*/
public class ArrayIntList extends RandomAccessIntList implements IntList, Serializable {

	// constructors
    //-------------------------------------------------------------------------
	
	/** 
	 * Construct an empty list with the default initial capacity.
	 */
    public ArrayIntList() {
    	this(8);
    }    

	/**
	 * Construct an empty list with the given initial capacity.
	 * @param initialCapacity
	 * @throws IllegalArgumentException - when initialCapacity is negative
	 */
    public ArrayIntList(int initialCapacity) {
    	if(initialCapacity < 0) {
            throw new IllegalArgumentException("capacity " + initialCapacity);
        }
        _data = new int[initialCapacity];
        _size = 0;
    }    

	/**
	 * Constructs a list containing the elements of the given collection, in the order they are returned by that collection's iterator.
	 * @param that: the non-null collection of ints to add
	 * @throws NullPointerException - if that is null
	 */
    public ArrayIntList(IntCollection that) { 
        this(that.size());
        addAll(that);
    }    

    // IntList methods
    //-------------------------------------------------------------------------
    
	/**
	 * Returns the value of the element at the specified position within me.
	 * @param index - the index of the element to return
	 */
    public int get(int index) {
        checkRange(index);
        return _data[index];
    }
    
	/**
	 * Returns the number of elements contained
	 */
    public int size() {
        return _size;
    }
    
	/**
	 * Removes the element at the specified position. Any subsequent elements are shifted to the left, subtracting one from their indices. 
	 * Returns the element that was removed.
	 * @param index - the index of the element to remove
	 * @throws IndexOutOfBoundsException - if the specified index is out of range
	 */
    public int removeElementAt(int index) {
        checkRange(index);
        incrModCount();
        int oldval = _data[index];
        int numtomove = _size - index - 1;
        if(numtomove > 0) {
            System.arraycopy(_data,index+1,_data,index,numtomove);
        }
        _size--;
        return oldval; 
    }
    
	/**
	 * Replaces the element at the specified position in me with the specified element
	 * @param index - the index of the element to change
	 * @param element - the value to be stored at the specified position
	 * @throws IndexOutOfBoundsException - if the specified index is out of range
	 */
    public int set(int index, int element) {
        checkRange(index);
        incrModCount();
        int oldval = _data[index];
        _data[index] = element;
        return oldval;
    }
     
	/**
	 * Inserts the specified element at the specified position. Shifts the element currently at that position (if any) 
	 * and any subsequent elements to the right, increasing their indices.
	 * @param index - the index at which to insert the element
	 * @param element - the value to insert
	 * @throws IndexOutOfBoundsException if the specified index is out of range
	 */
    public void add(int index, int element) {
        checkRangeIncludingEndpoint(index);
        incrModCount();
        ensureCapacity(_size+1);
        int numtomove = _size-index;
        System.arraycopy(_data,index,_data,index+1,numtomove);
        _data[index] = element;
        _size++;
    }

    // capacity methods
    //-------------------------------------------------------------------------
    
	/**
	 * Increases my capacity, if necessary, to ensure that I can hold at least the number of elements 
	 * specified by the minimum capacity argument without growing.
	 * @param mincap - minimum capacity
	 */
    public void ensureCapacity(int mincap) {
        incrModCount();
        if(mincap > _data.length) {
            int newcap = (_data.length * 3)/2 + 1;
            int[] olddata = _data;
            _data = new int[newcap < mincap ? mincap : newcap];
            System.arraycopy(olddata,0,_data,0,_size);
        }
    }

	/**
	 * Reduce my capacity, if necessary, to match my current @_size.
	 */
    public void trimToSize() {
        incrModCount();
        if(_size < _data.length) {
            int[] olddata = _data;
            _data = new int[_size];
            System.arraycopy(olddata,0,_data,0,_size);
        }
    }

    // private methods
    //-------------------------------------------------------------------------

    private final void checkRange(int index) {
        if(index < 0 || index >= _size) {
            throw new IndexOutOfBoundsException("Should be at least 0 and less than " + _size + ", found " + index);
        }
    }

    private final void checkRangeIncludingEndpoint(int index) {
    	if(index < 0 || index > _size) {
            throw new IndexOutOfBoundsException("Should be at least 0 and at most " + _size + ", found " + index);
        }
    }

    // attributes
    //-------------------------------------------------------------------------
    private transient int[] _data = null;
    private int _size = 0;
}