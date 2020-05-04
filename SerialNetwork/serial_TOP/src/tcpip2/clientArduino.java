package tcpip2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import msg.Msg;

public class clientArduino {

	public static Socket socket;
	public static Sender sender;
	public static Receiver receiver;

	String myIp;
	int myPort;
	boolean aflag = true;
	Msg msg;

	public clientArduino() {

	}

	public clientArduino(String address, int port) {
		while (aflag) {
			try {
				socket = new Socket(address, port);
				aflag = false;
			} catch (Exception e) {
				System.out.println("ReConnecting...");
				try {
					Thread.sleep(1000);
					socket = new Socket(address, port);
					aflag = false;
					System.out.println("Connected Server: " + address);
					break;
				} catch (Exception e1) {
					System.out.println("Error : Connecting server Failed..");
				} // second try&catch end
			} // while end
		} // first try&catch end

		// socket 이 생성되면 sender 생성 하여 socket 값 인풋 //
		System.out.println("Connected Server: " + address);
		System.out.println("Server port: " + port);
		// sender = new Sender(socket);
		// new Receiver(socket).start();

		myIp = address;
		myPort = port;

		sender = new Sender(socket);
		Msg msg = new Msg("ydhArduino", null, null); // 이부분 조금 애매함.접속 메시지//

		System.out.println("First connect Sender run: " + msg.getId());
		sender.setMsg(msg);
		sender.setIp(address);
		sender.setPort(port);

		new Thread(sender).start();
		// receiver = new Receiver(socket);
		// receiver.setIp(myIp);
		// receiver.setPort(myPort);
		// receiver.start();

	}

	public Msg getMsgFromReceiver() {
		return receiver.getMsg();
	}

	public class Sender implements Runnable {
		OutputStream os;
		ObjectOutput oos;
		Socket socket;
		String IP;
		int PORT;
		Msg msg;

		public Sender() {
		}

		public Sender(Socket socket) {
			this.socket = socket;
			try {
				os = socket.getOutputStream();
				oos = new ObjectOutputStream(os);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public Msg getMsg() {
			return msg;
		}

		public void setMsg(Msg msg) {
			System.out.println("socket close? : " + socket.isConnected());
			this.msg = msg;
			System.out.println("get From Arduino:" + msg.getId() + msg.getTxt() + msg.getTid());
		}

		public void setIp(String ip) {
			this.IP = ip;
		}

		public void setPort(int port) {
			this.PORT = port;
		}

		@Override
		public void run() {
			if (oos != null) {
				try {
					oos.writeObject(msg);
					System.out.println("sender run:" + " id: " + msg.getId() + " txt: " + msg.getTxt() + " target: "
							+ msg.getTid());
					System.out.println("msg Sended to Server");
				} catch (IOException e) {
					System.out.println("Error while sending msg");
					if (oos != null) {
						try {
							oos.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						try {
							socket = new Socket(IP, PORT);
							return;
						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
			System.out.println("Sender run END");
		}
	}

	public class Receiver extends Thread {
		InputStream is;
		ObjectInputStream ois;
		Socket socket;
		Sender sender;
		String IP;
		int PORT;
		String CID;
		Msg message;

		public Receiver() {
		}

		public Receiver(Socket socket) {
			this.socket = socket;
			try {
				is = socket.getInputStream();
				ois = new ObjectInputStream(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void setSender(Sender sender) {
			this.sender = sender;

			CID = sender.getMsg().getId();
		}

		public Msg getMsg() {
			return this.message;
		}

		public void setIp(String ip) {
			this.IP = ip;
		}

		public void setPort(int port) {
			this.PORT = port;
		}

		@Override
		public void run() {

			Msg msg = null;
			while (ois != null) {
				try {
					msg = (Msg) ois.readObject();
					System.out.println("get id: " + msg.getId());
					System.out.println("get txt : " + msg.getTxt());
					System.out.println("get target : " + msg.getTid());
					this.message = msg;
					if (msg.getId().equals("car1")) {
						if (msg.getTid().equals("speed")) {
							// newSpeed.getData(Integer.parseInt(msg.getTxt()));
							System.out.println(("msg Sended to :" + msg.getId() + "target : " + msg.getTid()
									+ ", message : " + msg.getTxt()));
						} else if (msg.getTid().equals("engine") && msg.getTxt().equals("0")) {
							// newSpeed.getData(2);
							System.out.println("msg Sended to speed by engine stopped " + "message" + 2);
						}
					}
				} catch (Exception e) {
					while (true) {
						try {

							Thread.sleep(1000);

							System.out.println("Trying to reconnect to server");
							// clientArduino = new clientArduino("172.30.1.25",9999);

							if (ois != null) {
								ois.close();
							}
							if (socket != null) {
								socket.close();
							}
							System.out.println("catch() IP : " + IP);
							System.out.println("catch() PORT : " + PORT);
							System.out.println("catch() CID : " + CID);
							break;
						} catch (Exception e1) {
							System.out.println("Receiver run() catch() while(true) catch()");
							e1.printStackTrace();
						}
					}
					return;
				}
//				if (ois != null) {
//					try {
//						ois.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
			}

		}
	}

}