package models;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Rating;

public class RatingTest
{
  private Rating[] ratings =
  {
    new Rating ("01",  "001", 5),
    new Rating ("02",  "002", 3 ),
    new Rating ("03",   "003", 4),
    new Rating ("04",  "004",  1),
    new Rating ("05", "005", 2)
  };

  Rating test = new Rating ("01",  "002", 5);

  
@Test
  public void testCreate()
  {
    assertEquals ("01",          test.userId);
    assertEquals ("001",        test.movieId);
    assertEquals ( 5 ,  test.ratingLeft, 5);    
  }

  @Test
  public void testToString()
  {
    assertEquals ("Rating{" + test.userId + ", 01, 001 , 5, []}", test.toString());
  }

public Rating[] getRatings() {
	return ratings;
}

public void setRatings(Rating[] ratings) {
	this.ratings = ratings;
}
}