public class Iperfer {

	public static void main(String[] args) {
		
		if (!args[1].equals("-c")) {
			
			/* Check for valid flags. */
			if (!args[2].equals("-h") || !args[1].equals("-p") || !args[1].equals("-t"))
				handleError("Error: missing or additional arguments");
			
			
			
		} else if (!args[1].equals("-s")) {
			
		
		} else {
			handleError("Error: missing or additional arguments");
		}

	}
	
	public static void handleError(String msg) {
		
		System.err.println(msg);
		System.exit(1);
	}

}