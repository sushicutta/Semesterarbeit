package server.business.entity;

public abstract class Entity {

	public static boolean isId(Long id) {
		return (id != null && id > 0);
	}

	public boolean hasId() {
		return isId(getId());
	}

	public abstract Long getId();
	
}