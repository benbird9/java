package serialization;

/**
 * Created by U6035183 on 2018/12/28.
 */
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class StudentServer extends Thread
{
  private ServerSocket serverSocket;

  public StudentServer(int port) throws IOException
  {
    serverSocket = new ServerSocket(port);
    serverSocket.setSoTimeout(20000);//20 seconds
  }

  public void run()
  {
    while(true)
    {
      Socket server = null;
      try
      {
        System.out.println("Server: Waiting for client on port " + serverSocket.getLocalPort() + "...");
        server = serverSocket.accept();
        System.out.println("Server: Just connected to " + server.getRemoteSocketAddress());

        DataInputStream in = new DataInputStream(server.getInputStream());
        System.out.println("Server gets the message from client: "+in.readUTF());

        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF("Sure, here is the object.");

        ObjectOutputStream objectOut = new ObjectOutputStream(server.getOutputStream());
        for(int i =0; true; i++) {
          ArrayList<Student> students = new ArrayList<Student>();
          Student s1 = new Student(i*2,null);
          Student s2 = new Student(i*2 + 1,"Denny");
          students.add(s1);
          students.add(s2);
          objectOut.writeObject(students);
          objectOut.flush();//need it ???
        }



      }catch(SocketTimeoutException s)
      {
        System.out.println("Server: Socket timed out!");
        break;
      }catch(IOException e)
      {
        e.printStackTrace();
        break;
      }
      finally {
        try {
          server.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
  public static void main(String [] args)
  {
    int port = Integer.parseInt("6066");
    try
    {
      Thread t = new StudentServer(port);
      t.start();
    }catch(IOException e)
    {
      e.printStackTrace();
    }
  }
}
