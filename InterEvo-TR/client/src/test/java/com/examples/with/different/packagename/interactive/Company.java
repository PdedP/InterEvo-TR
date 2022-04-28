/**
 * 
 */
package com.examples.with.different.packagename.interactive;

/**
 * @author Gordon Fraser
 * 
 */
public class Company extends Owner {

	private final String name;

	public Company(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
