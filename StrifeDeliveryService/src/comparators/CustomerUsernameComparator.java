package comparators;

import java.util.Comparator;

import dto.UserViewDTO;

public class CustomerUsernameComparator implements Comparator<UserViewDTO> {

	@Override
	public int compare(UserViewDTO o1, UserViewDTO o2) {
		return o1.id.compareTo(o2.id);
	}

}
