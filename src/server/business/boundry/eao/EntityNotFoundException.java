package server.business.boundry.eao;

public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = 9088439368548696861L;
	
	private String className;
	
	private Long id;

	public EntityNotFoundException(Class<?> clazz, Long id) {
		super("Entity of class " + clazz.getSimpleName() + " with id " + id + " not found");
		this.className = clazz.getSimpleName();
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public Long getId() {
		return id;
	}
}