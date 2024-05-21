import java.io.*;
import java.net.*; 
class udpchatserver
{

public static int clientport = 8040,serverport = 8050; public static void main(String args[]) throws Exception
{

DatagramSocket SrvSoc = new DatagramSocket(clientport); byte[] SData = new byte[1024];


BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); System.out.println("Server Ready");

while (true)

{

byte[] RData = new byte[1024];

DatagramPacket RPack = new DatagramPacket(RData,RData.length);

SrvSoc.receive(RPack);

String Text = new String(RPack.getData());

if (Text.trim().length() == 0)

break;

System.out.println("\nFrom Client <<< " + Text );

System.out.print("Msg to Cleint : " );

String srvmsg = br.readLine();

InetAddress IPAddr = RPack.getAddress();

SData = srvmsg.getBytes();

DatagramPacket SPack = new DatagramPacket(SData,SData.length,IPAddr, serverport); SrvSoc.send(SPack);

}

System.out.println("\nClient Quits\n");

SrvSoc.close();

}

}

/*This Java program implements a simple UDP chat server that communicates with a UDP client. The server listens for messages from the client and responds to each message. Here is a detailed breakdown of the program:

### Key Components:
1. **Ports**:
   - `clientport`: The port on which the server listens for incoming data from the client (8040).
   - `serverport`: The port on which the server sends data back to the client (8050).

2. **Main Function**:
   - Initializes a `DatagramSocket` to receive messages from the client.
   - Uses a `BufferedReader` to read server responses from the console.

### Program Flow:
1. **Socket Initialization**:
   - `DatagramSocket SrvSoc = new DatagramSocket(clientport);` initializes the socket to listen on `clientport`.

2. **Server Ready Message**:
   - The server prints "Server Ready" to indicate it is ready to receive messages.

3. **Main Loop**:
   - The program enters an infinite loop where it waits for messages from the client, processes them, and sends responses back.

4. **Receiving Data**:
   - The server prepares a `DatagramPacket` to receive data from the client.
   - `DatagramPacket RPack = new DatagramPacket(RData, RData.length);` prepares a packet for incoming data.
   - `SrvSoc.receive(RPack);` blocks and waits for a packet from the client.
   - The received data is converted to a string: `String Text = new String(RPack.getData());`.
   - If the received message is empty (the client pressed Enter without typing), the server breaks the loop and stops.

5. **Processing Data**:
   - The server prints the received message: `System.out.println("\nFrom Client <<< " + Text);`.
   - The server prompts the user for a response: `System.out.print("Msg to Client: ");`.
   - The server reads the response from the console.

6. **Sending Data**:
   - The server retrieves the client's IP address from the received packet: `InetAddress IPAddr = RPack.getAddress();`.
   - The server's response is converted to bytes and sent back to the client: `SData = srvmsg.getBytes();`.
   - `DatagramPacket SPack = new DatagramPacket(SData, SData.length, IPAddr, serverport);` creates a packet addressed to the client's port and IP address.
   - `SrvSoc.send(SPack);` sends the packet.

7. **Exit Condition**:
   - If the client sends an empty message, the server prints "Client Quits" and exits the loop.
   - The socket is closed with `SrvSoc.close();`.

### Example Interaction:
- The server starts and waits for messages from the client.
- The client sends "Hello, Server" to the server.
- The server receives "Hello, Server" and prints "From Client <<< Hello, Server".
- The server prompts the user to type a response, e.g., "Hello, Client".
- The server sends "Hello, Client" back to the client.
- The interaction continues until the client sends an empty message, indicating that the client has quit.

### Potential Enhancements:
- Error handling for socket communication (e.g., network errors, unreachable client).
- Support for multiple clients and handling concurrency.
- Better user interface (e.g., graphical interface).
- Enhanced protocol with message acknowledgment and error correction.

Overall, this program is a basic implementation of a UDP-based chat server, illustrating how to receive and send messages using UDP sockets in Java.*/
