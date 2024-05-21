import java.io.*;
import java.net.*; 
class udpchatclient
{

public static int clientport = 8040,serverport = 8050; public static void main(String args[]) throws Exception
{

BufferedReader br = new BufferedReader(new InputStreamReader (System.in)); DatagramSocket CliSoc = new DatagramSocket(serverport);

InetAddress IPAddr;

String Text;

if (args.length == 0)

IPAddr = InetAddress.getLocalHost();

else

IPAddr = InetAddress.getByName(args[0]);

byte[] SData = new byte[1024];

System.out.println("Press Enter without text to quit");

while (true)

{

System.out.print("\nEnter text for server : ");

Text = br.readLine();

 
SData = Text.getBytes();

DatagramPacket SPack = new DatagramPacket(SData,SData.length, IPAddr, clientport ); CliSoc.send(SPack);

if (Text.trim().length() == 0)

break;

byte[] RData = new byte[1024];

DatagramPacket RPack = new DatagramPacket(RData,RData.length);

CliSoc.receive(RPack);

String Echo = new String(RPack.getData()) ;

Echo = Echo.trim();

System.out.println("From Server <<< " + Echo);

}

CliSoc.close();

}

}


/*This Java program implements a simple UDP chat client. It communicates with a UDP server, sending and receiving messages. Let's break down its functionality step by step:

### Key Components:
1. **Ports**:
   - `clientport`: The port on which the client sends data (8040).
   - `serverport`: The port on which the client listens for responses (8050).

2. **Main Function**:
   - Reads input from the command line or standard input.
   - Initializes a `DatagramSocket` to communicate over UDP.
   - Determines the IP address of the server, which can be specified as a command line argument or defaults to the local host.

### Program Flow:
1. **Input Handling**:
   - The program uses a `BufferedReader` to read user input from the console.
   
2. **Socket Initialization**:
   - `DatagramSocket CliSoc = new DatagramSocket(serverport);` initializes the socket to listen on `serverport`.

3. **IP Address Determination**:
   - If an argument is provided, it uses that as the server's IP address.
   - If no argument is provided, it defaults to the local host's IP address.

4. **Main Loop**:
   - The program enters an infinite loop where it reads input from the user, sends it to the server, and waits for a response.
   - The loop continues until the user presses Enter without typing any text (empty input).

5. **Sending Data**:
   - The user's input is converted to bytes and sent to the server using a `DatagramPacket`.
   - `DatagramPacket SPack = new DatagramPacket(SData, SData.length, IPAddr, clientport);` creates a packet addressed to the server's port and IP address.
   - `CliSoc.send(SPack);` sends the packet.

6. **Receiving Data**:
   - The program waits for a response from the server.
   - `DatagramPacket RPack = new DatagramPacket(RData, RData.length);` prepares a packet to receive data.
   - `CliSoc.receive(RPack);` receives the response from the server.
   - The response is converted back to a string and displayed to the user.

7. **Exit Condition**:
   - The loop exits if the user inputs an empty string (presses Enter without typing any text).
   - The socket is closed with `CliSoc.close();`.

### Example Interaction:
- The user starts the client.
- The user is prompted to enter text.
- The user types "Hello, Server" and presses Enter.
- The client sends "Hello, Server" to the server.
- The server echoes back the message.
- The client receives the echo and prints "From Server <<< Hello, Server".
- The user can continue to send messages or press Enter without typing to quit.

### Potential Enhancements:
- Error handling for socket communication (e.g., network errors, unreachable server).
- Support for multiple clients and handling concurrency.
- Better user interface (e.g., graphical interface).
- Enhanced protocol with message acknowledgment and error correction.

Overall, this program is a basic implementation of a UDP-based chat client, illustrating how to send and receive messages using UDP sockets in Java.*/
