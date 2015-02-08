public class Iperfer {

	public static void main(String[] args) {
		
		// Parse and validate all of the arguments
		ArgumentSet arguments = new ArgumentSet(args);
		arguments.addArgument(new Argument("c", "s", false, false, "Client Flag"));
		arguments.addArgument(new Argument("s", "c", false, false, "Server Flag"));
		arguments.addArgument(new Argument("h", false, true, "Server hostname"));
		arguments.addArgument(new Argument("p", true, true, "Server Port"));
		arguments.addArgument(new Argument("t", false, true, "Test run time"));
		arguments.parse();
		arguments.validate();
		
		if(!arguments.isValid()) {
			System.out.println("Error: missing or additional arguments");
			System.exit(1);
		}
		
		int port = new Integer(arguments.getValue("p"));
		
		if(port < 1024 || port > 65535) {
			System.out.println("Error: port number must be in the range 1024 to 65535");
			System.exit(1);
		}
		
		if(arguments.getArgument("c").isPresent) {		   	// In Client Mode
			String hostname = arguments.getValue("h");
			int time = new Integer(arguments.getValue("t"));
			
			Client client = new Client(hostname, port, time);
			client.run();
		} else if (arguments.getArgument("s").isPresent) { 	// In Server Mode
			
		} else {											// No mode
			System.out.println("Error: missing or additional arguments");
			System.exit(1);
		}
		
	}

}