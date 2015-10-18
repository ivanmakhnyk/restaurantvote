package org.imakhnyk.interview.menuvoting.frontend.model;

/**
 * DTO model for frontend
 * 
 * @author Ivan Makhnyk
 *
 */
public class VoteStatItem {
	private long count;
	private long menuId;
	private String menuName;

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

}
