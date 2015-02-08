import java.io.*;
import java.net.*;

public class Client {
	
	public String hostname;
	public int port, time;
	private final int PACKET_SIZE = 1000;
	
	Client(String hostname, int port, int time) {
		this.hostname = hostname;
		this.port = port;
		this.time = time;
	}
	
	public void run() {
		
		byte[] buffer = new byte[PACKET_SIZE];
		int bytesWritten = 0;
		
		try {
			Socket socket = new Socket(this.hostname, this.port);
			OutputStream outStream = socket.getOutputStream();
			
			long startTime = System.currentTimeMillis();
			long endTime;
			
			while((endTime = System.currentTimeMillis()) < (startTime + this.time)) {
				outStream.write(buffer);
				bytesWritten++;
			}
			
			outStream.close();
			socket.close();
			
			double speed = (bytesWritten * 8.0 / (endTime - startTime));
			
			System.out.println("sent=" + bytesWritten + " KB rate=" + speed + " Mbps" );
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
