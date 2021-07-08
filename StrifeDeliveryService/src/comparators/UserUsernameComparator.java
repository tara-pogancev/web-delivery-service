package comparators;

import java.util.Comparator;

import model.User;

public class UserUsernameComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		return o1.getId().compareTo(o2.getId());
	}

}