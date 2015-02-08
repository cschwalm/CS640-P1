import java.util.ArrayList;

public class ArgumentSet {
	private ArrayList<Argument> arguments;
	private boolean isValid;
	private String[] commandArgs;
	
	public ArgumentSet(String[] args) {
		this.arguments = new ArrayList<Argument>();
		this.commandArgs = args;
		this.isValid = false;
	}
	
	public boolean addArgument(Argument argument) {
		for(Argument a : arguments){
			if(a.name.equals(argument.name))
				return false;
		}
		
		arguments.add(argument);
		return true;
	}
	
	public void parse() {
		for(Argument a : arguments) {
			a.parseFrom(commandArgs);
		}
	}
	
	public boolean validate() {
		this.isValid = true;
		
		for(Argument a : arguments) {
			if(a.isMandatory && !a.isPresent) {
				this.isValid = false;
			}
			
			if(a.hasValue && a.isPresent && a.value == null)
				this.isValid = false;
			
			if(a.isPresent && a.mutex != null && (this.getArgument(a.mutex).isPresent))
				this.isValid = false;
		}
		
		for(int i = 0; i < commandArgs.length; i++) {
			if((commandArgs[i].charAt(0) == '-') && !this.inArgumentSet(commandArgs[i]))
				this.isValid = false;
		}
		
		return this.isValid;
	}
	
	public boolean inArgumentSet(String argName) {
		
		for(Argument a: arguments) {
			if(argName.equals("-" + a.name))
				return true;
		}
		
		return false;
	}
	
	public Argument getArgument(String argName) {
		for(Argument a: arguments) {
			if(a.name.equals(argName)) {
				return a;
			}
		}
		
		return null;
	}
	
	public String getValue(String argName) {
		for(Argument a: arguments) {
			if(a.name.equals(argName)) {
				return a.value;
			}
		}
		
		return null;
	}
	
	public boolean isValid() {
		return this.isValid;
	}
	
	
	
}
