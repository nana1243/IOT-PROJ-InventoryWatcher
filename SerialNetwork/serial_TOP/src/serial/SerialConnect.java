package serial;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialConnect implements SerialPortEventListener {

	CommPortIdentifier commPortIdentifier;
	CommPort comPort;
	InputStream in;
	BufferedInputStream bin;
	OutputStream out;

	public SerialConnect() {
	}

	public SerialConnect(String portName) throws Exception {
		commPortIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		// 접속 확인 //
		System.out.println("Identififier Verified");

		connect();
		
		System.out.println("Com Port Connected!");
		new Thread(new SerialWrite()).start();
		System.out.println("Start Can Network..");

	}

	public void connect() throws Exception {
		if (commPortIdentifier.isCurrentlyOwned()) {
			// 이미 사용중이면 사용불가 //
			System.out.println("Port is already in use.");
		} else {
			// 5000ms 동안 응답이 없으면 Exception //
			comPort = commPortIdentifier.open(this.getClass().getName(), 5000);
			// comPort 가 SerialPort 가 아니면 작업할 수 없다. //
			if (comPort instanceof SerialPort) {
				SerialPort serialPort = (SerialPort) comPort;
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
				serialPort.setSerialPortParams(921600, // 통신속도
						SerialPort.DATABITS_8, // 데이터 비트 - CAN 통신의 B 타입 데이터길이
						SerialPort.STOPBITS_1, // stop 비트
						SerialPort.PARITY_NONE); // 패리티

				in = serialPort.getInputStream();
				// TCPIP 의 Input 과 Output Stream 과 비슷한 개념 //
				// BufferedInputStream 을 이용해 간편하게 데이터 Read //
				bin = new BufferedInputStream(in);
				out = serialPort.getOutputStream();

			} else {
				System.out.println("This port is not a Serial Port.");
			}
		}
	}


	// CAN 통신으로 데이터가 들어오면 Event 가 발생한다. //
	@Override
	public void serialEvent(SerialPortEvent event) {
		  switch (event.getEventType()) {
		  case SerialPortEvent.BI:
		  case SerialPortEvent.OE:
		  case SerialPortEvent.FE:
		  case SerialPortEvent.PE:
		  case SerialPortEvent.CD:
		  case SerialPortEvent.CTS:
		  case SerialPortEvent.DSR:
		  case SerialPortEvent.RI:
		  case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
		   break;
		  case SerialPortEvent.DATA_AVAILABLE:
		   byte[] readBuffer = new byte[128];

		   try {

		    while (bin.available() > 0) {
		     int numBytes = bin.read(readBuffer);
		    }

		    String ss = new String(readBuffer);
		    System.out.println("Receive Low Data:" + ss + "||");
		    
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		   break;
		  }

	}
	
	// Write 시간이 오래걸리거나 멈출수도 있어서 Thread로 생성 // 
	class SerialWrite implements Runnable{
		
		
		//CAN 은 데이터를 String 으로 주고받는다. //
		String data;
		
		public SerialWrite() {
			// DATA 표준식 :G11A9 시작 \r// 
			// 시작문자 1개 명령문자 1개 데이터문자 ~개 CheckSum 2개 끝 1개  //  
			// ':' = 시작 'G' = 명령 '11' = 데이터 'A9' = CheckSum '\r' = 끝  //  
			this.data = ":G11A9\r";
		}



		@Override
		public void run() {
			byte[] outData = data.getBytes();
			// Can Network Area 에 Data 를 전송 // 
			try {
				out.write(outData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws Exception {
		SerialConnect sc = null;
		try {

			// Serial port COM12 //
			sc = new SerialConnect("COM12");
		} catch (NoSuchPortException e) {

			e.printStackTrace();
		}

	}
	
	

}
