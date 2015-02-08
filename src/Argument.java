package argmageddon;

public class Argument {
	public boolean isMandatory, hasValue, isPresent;
	public String name, mutex, value, description;
	
	public Argument(String name, boolean isMandatory, boolean hasValue, String description) {
		this.name = name;
		this.isMandatory = isMandatory;
		this.hasValue = hasValue;
		this.description = description;
		this.isPresent = false;
	}
	
	public Argument(String name, String mutex, boolean isMandatory, boolean hasValue, String description) {
		this(name, isMandatory, hasValue, description);
		this.mutex = mutex;
	}
	
	public boolean parseFrom(String[] args) {
		
		for(int i = 0; i < args.length; i++) {
			if(args[i].equals("-" + this.name)){
				if(i < (args.length - 1) && this.hasValue && args[i + 1].charAt(0) != '-') {
					this.value = args[i + 1];
					i++;
				}
				this.isPresent = true;
				return true;
			}
		}
		return false;
	}
}
