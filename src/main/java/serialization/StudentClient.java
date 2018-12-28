package serialization;

/**
 * Created by U6035183 on 2018/12/28.
 */
import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class StudentClient
{
  public static void main(String [] args)
  {
    String serverName = "localhost";
    int port = Integer.parseInt("6066");
    try
    {
      System.out.println("Client: Connecting to " + serverName + " on port " + port);
      Socket client = new Socket(serverName, port);
      System.out.println("Client: Just connected to " + client.getRemoteSocketAddress());

      OutputStream outToServer = client.getOutputStream();
      DataOutputStream out = new DataOutputStream(outToServer);
      out.writeUTF("Hello Server, can you give me a list of students?" + client.getLocalSocketAddress());


      InputStream inFromServer = client.getInputStream();
      DataInputStream in = new DataInputStream(inFromServer);
      System.out.println("Client gets the message from the server: "+in.readUTF());

      ObjectInputStream objectIn = new ObjectInputStream(inFromServer);
      while(true) {
        ArrayList<Student> students = (ArrayList<Student>) objectIn.readObject();
        for(int i=0; i<students.size(); i++)
        {
          System.out.println(students.get(i).toString());
        }
      }


//      client.close();

    }catch(Exception e)
    {
      e.printStackTrace();
      System.out.println(e);
    }
  }
}
