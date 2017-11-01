package controllers;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;


import model.Movies;
import model.Rating;
import model.User;
import utils.Serializer;

public class MoviesAPI

{

	private Serializer serializer;
	
	 private static Map<Long, User> userIndex = new HashMap<Long, User>();
	 private Map<String, User> fNameIndex = new HashMap<String, User>();
	 private static Map<Long, Rating> ratingIndex = new HashMap<Long, Rating>();
	 
	 public MoviesAPI() 
	 {}
	 
	 public MoviesAPI(Serializer serializer)
	  {
	    this.serializer = serializer;
	  }
	 
	 @SuppressWarnings("unchecked")
	  public void load() throws Exception
	  {
	   serializer.read();
	    {
	      
	      userIndex       = (Map<Long, User>)    serializer.pop() ;
	      fNameIndex      = (Map<String, User>)  serializer.pop();
	      ratingIndex     = (Map<Long, Rating>)  serializer.pop();
	     }
	  }
	 
	 void store() throws Exception
	  {
	 
		  serializer.push(userIndex);
		  serializer.push(fNameIndex);
		  serializer.push(ratingIndex);
		  serializer.write();
	 }
	 

	  public Collection<User> getUsers ()
	  {
	    return userIndex.values();
	  }

  public  void deleteUsers() 
  {
	  userIndex.clear();
	  fNameIndex.clear();
  }

  public User createUser(String fName, String lName, String age, String gender, String job ) 
  {
    User user = new User (fName, lName, age, gender, job );
    userIndex.put(user.id, user);
    fNameIndex.put(fName, user);
    return user;
  }
  
  public User getUserByfName(String fName) {
		
		return fNameIndex.get(fName) ;
	}
  
  public User getUser(Long id) 
  {
	  return userIndex.get(id);
  }

  public void deleteUser(Long id) 
  {
	  User user = userIndex.remove(id);
	  fNameIndex.remove(user.fName);
  }

  public static Rating createRating(Long id ,String userId, String movieId, double ratingLeft) 
  {
	  
	  Rating rating = null;
	  Optional<User> user= Optional.fromNullable(userIndex.get(id));
	  if(user.isPresent())
	  {
		  rating = new Rating (userId, movieId , ratingLeft);
		  user.get().ratings.put(rating.Id, rating);
		  ratingIndex.put(rating.Id, rating);
	  }
	  return rating;
	  
   }
  
 
  
  public Rating getRating(long id)
  {
	  return ratingIndex.get(id);
  }
  
  
  
  public void addMovie (long id , String name, String date ,String link )
  {
	  Optional<Rating> rating = Optional.fromNullable(ratingIndex.get(id));
	  if(rating.isPresent())
	  {
		  rating.get().route.add(new Movies(name, date, link));
	  }
  }
}