package model;
import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;


public class User {
	
 public String fName, lName, Age, Gender, Job ;
 public long id;
 static Long   counter = 0l;
 
 public Map<Long , Rating> ratings = new HashMap<Long , Rating>();
 
 public User() {
	 
 }

public User(String fName, String lName, String age, String gender, String job ) {
	
	this.fName = fName;
	this.lName = lName;
	this.Age = age;
	this.Gender = gender;
	this.Job = job;
	this.id = counter++ ;
}

@Override
public String toString()
{
  return toStringHelper(this).addValue(fName)
                             .addValue(lName)
                             .addValue(Age)
                             .addValue(Gender)   
                             .addValue(Job) 
                             .addValue(id) 
                             .toString();
}


@Override  
public int hashCode()  
{  
   return Objects.hashCode(this.fName, this.lName, this.Age, this.Gender , this.Job);  
}

@Override  
public boolean equals(final Object obj)
{
	if (obj instanceof User)
	{
		final User other = (User) obj;
		return Objects.equal(fName, other.fName)
				&& Objects.equal(lName, other.lName)
				&& Objects.equal(Age, other.Age)
				&& Objects.equal(Gender, other.Gender)
				&& Objects.equal(Job, other.Job)
				&& Objects.equal(id, other.id);
	}
	else
	{
		return false;
	}
}

	}