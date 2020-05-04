package serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import msg.Msg;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import tcpip2.clientArduino;
import tcpip2.clientArduino.Sender;

public class SerialArduino {

	
	// 오류수정 -> public static sender 를 삭제, clientArduino에서 가져와서 쓴다. // 
	public static clientArduino clientArduino;

	public SerialArduino()

	{
		super();
	}

	public void connect(String portName) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		if (portIdentifier.isCurrentlyOwned()) {
			System.out.println("Error: Port is currently in use");
		} else {
			// 클래스 이름을 식별자로 사용하여 포트 오픈
			CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

			if (commPort instanceof SerialPort) {
				// 포트 설정(통신속도 설정. 기본 9600으로 사용)
				SerialPort serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);

				// Input,OutputStream 버퍼 생성 후 오픈
				InputStream in = serialPort.getInputStream();
				OutputStream out = serialPort.getOutputStream();

				// 읽기, 쓰기 쓰레드 작동
				(new Thread(new SerialReader(in))).start();
				// (new Thread(new SerialWriter(out))).start();

			} else {
				System.out.println("Error: Only serial ports are handled by this example.");
			}
		}
	}

	/** */
	// 아두이노 시리얼 데이터 수신 //
	public static class SerialReader implements Runnable {
		InputStream in;
		String message = "";

		public SerialReader(InputStream in) {
			this.in = in;
		}

		public void run() {
			while (true) {
				byte[] buffer = new byte[1024];
				int len = -1;
				try {
					while ((len = this.in.read(buffer)) > -1) {
						
						message = new String(buffer, 0, len);
						if (message.length() >= 6) {
							System.out.print(message);
							System.out.println(message.length());
							System.out.println((message.substring(0, message.length() - 4)));

							double svalue = Double.parseDouble(message.substring(0, message.length() - 4));
							clientArduino = new clientArduino("70.12.224.75", 9999);
							Msg msg = new Msg("ydhArduino", "" + svalue, null);
							System.out.println("Sendmsg: " + msg.getId() + " " + msg.getTxt() + " " + msg.getTid());

							Thread.sleep(1000);

							tcpip2.clientArduino.sender.setMsg(msg);
							new Thread(tcpip2.clientArduino.sender).start();
//							Thread.sleep(1000);
						}
						// System.out.println(message.substring(0,message.length()));

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** */
	// 데이터 송신
	public static class SerialWriter implements Runnable {
		OutputStream out;

		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		public void run() {
			try {
				int c = 0;
				while ((c = System.in.read()) > -1) {
					this.out.write(c);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			(new SerialArduino()).connect("COM13"); // 입력한 포트로 연결
			System.out.println("ArduinoPort Connected");
			// clientArduino = new clientArduino("172.30.1.25",9999); // 태블릿 접속
			// clientArduino = new clientArduino("70.12.224.75",9999); // 태블릿 접속
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}