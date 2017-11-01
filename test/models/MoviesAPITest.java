package models;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.MoviesAPI;
import model.Rating;
import model.User;

import static models.Fixtures.users;
import static models.Fixtures.ratings;
import static models.Fixtures.movie;

public class MoviesAPITest {
	
	private MoviesAPI movies;
	
	 @Before
	  public void setup()
	  {
	    movies = new MoviesAPI();
	    for (User user : users)
	    {
	      movies.createUser(user.fName, user.lName, user.Age, user.Gender , user.Job);
	    }
	  }
	 
	 @After
	  public void tearDown()
	  {
	    movies = null;
	  }
	 
	 @Test
	  public void testUser()
	  {
	    assertEquals (users.length, movies.getUsers().size());
	    movies.createUser("homer", "simpson", "32", "Male", "Power Plant");
	    assertEquals (users.length+1, movies.getUsers().size());
	    assertEquals (users[0], movies.getUserByfName(users[0].fName));
	  }  
	 
	 @Test
	  public void testUsers()
	  {
	    assertEquals (users.length, movies.getUsers().size());
	    for (User user: users)
	    {
	      User eachUser = movies.getUserByfName(user.fName);
	      assertEquals (user, eachUser);
	      assertNotSame(user, eachUser);
	    }
	  }
	 
	 @Test
	  public void testDeleteUsers()
	  {
	    assertEquals (users.length, movies.getUsers().size());
	    User marge = movies.getUserByfName("marge");
	    movies.deleteUser(marge.id);
	    assertEquals (users.length-1, movies.getUsers().size());    
	  }  
	 
	 @Test
	  public void testAddRating()
	  {
	    User marge = movies.getUserByfName("marge");
	    assertNotNull(marge);
	    Rating rating = movies.createRating(marge.id, ratings[0].userId, ratings[0].movieId, ratings[0].ratingLeft);
	    assertNotNull(rating);
	    Rating returnedRating = movies.getRating(rating.Id);
	    assertNotNull(returnedRating);
	    assertEquals(ratings[0],  returnedRating);
	    assertNotSame(ratings[0], returnedRating);
	  }
}
	 
	 
	 