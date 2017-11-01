package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

import model.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main
{
  public static void main(String[] args) throws Exception
  {    
	  File  datastore = new File("datastore2.xml");
    Serializer serializer = new XMLSerializer(datastore);

  MoviesAPI moviesAPI = new MoviesAPI(serializer);
    if (datastore.isFile())
    {
      moviesAPI.load();
    }
    
    String delims = "[|]";
    Scanner scanner = new Scanner(new File("./moviedata_small/users5.dat"));
    while (scanner.hasNextLine()) {
        String userDetails = scanner.nextLine();
        // parse user details string
        String[] userTokens = userDetails.split(delims);

        if (userTokens.length == 7) {
            System.out.println("UserID: " + userTokens[0] + ",First Name:" + userTokens[1]);
        } else {
            scanner.close();
            throw new IOException("Invalid member length: " + userTokens.length);
        }
    }
    scanner.close();

    /* moviesAPI.createUser("Bart",  "Simpson", "12",  "male", "N/A");
     moviesAPI.createUser("Homer", "Simpson", "34", "male", "Power Plant");
     moviesAPI.createUser("Lisa",  "Simpson", "10",  "female", "N/A");

    Collection<User> users = moviesAPI.getUsers();
    System.out.println(users);

    User homer = moviesAPI.getUserByfName("homer");
  moviesAPI.createRating(homer.id, "001", "0001", 6.9);*/

    moviesAPI.store();
  }
}	
