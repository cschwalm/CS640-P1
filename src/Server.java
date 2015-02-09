import java.io.*;
import java.net.*;

public class Server {
	
	public final int PACKET_LENGTH = 1000;
	
	private int portNum;
	
	public Server(int portNum) {
		
		if (portNum < 1024 || portNum > 65535)
			throw new IllegalArgumentException("Invalid Port Number");
		
		this.portNum = portNum;
	}
	
	public void processClientData() {
			
		Socket client;
		int bytesRead = 0, bytesReadTotal = 0, time = 0;
		double rate = 0.0, rateBits = 0.0;
		byte[] dataReceived = new byte[PACKET_LENGTH];
		long timeStart = 0, timeEnd = 0;
		
		try {
		
			ServerSocket soc = new ServerSocket(portNum);
			
			if ((client = soc.accept()) != null) {
				
				InputStream data = client.getInputStream();
				timeStart = System.currentTimeMillis();
				
				while (bytesRead != -1) {
				
					bytesRead = data.read(dataReceived, 0, PACKET_LENGTH);
					
					if (bytesRead != -1)
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
				
		time = (int) ((timeEnd - timeStart) / 1000);
		rate = (bytesReadTotal / 1000000) / (double) time;
		rateBits = rate * 8;
		
		System.out.println("received=" + (bytesReadTotal / 1000) + " KB rate=" + rateBits + " Mbps");
	}
}