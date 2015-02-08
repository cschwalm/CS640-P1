import java.io.*;
import java.net.*;

public class Server {
	
	public final int PACKET_LENGTH = 1000;
	
	public void processClientData(int portNum) {
		
		if (portNum < 1024 || portNum > 65535)
			throw new IllegalArgumentException("Invalid Port Number");
		
		Socket client;
		int bytesRead = 0, bytesReadTotal = 0;
		byte[] dataReceived = new byte[PACKET_LENGTH];
		long timeStart, timeEnd;
		
		try {
		
			ServerSocket soc = new ServerSocket(portNum);
			
			if ((client = soc.accept()) != null) {
				
				InputStream data = client.getInputStream();
				timeStart = System.currentTimeMillis();
				
				while (bytesRead != -1) {
				
					bytesRead = data.read(dataReceived, 0, PACKET_LENGTH);
					bytesReadTotal += bytesRead;
				}
				
				timeEnd = System.currentTimeMillis();
				
				client.close();
				soc.close();		
			}		
		
		} catch (IOException ex) {
			System.out.println("IO Error Occurred. Exiting...");
			System.exit(1);
		}
		
		
		
		
	}

}
