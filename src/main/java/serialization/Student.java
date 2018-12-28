package serialization;

/**
 * Created by U6035183 on 2018/12/28.
 */
import java.io.Serializable;
public class Student implements java.io.Serializable
{
  private int id;
  private String name;

  public Student(int id, String name)
  {
    this.id=id;
    this.name=name;
  }
  public String toString()
  {
    return "Student ID: "+id+"\nStudent Name: "+name;

  }
}