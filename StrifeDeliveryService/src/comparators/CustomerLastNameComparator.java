package comparators;

import java.util.Comparator;

import dto.UserViewDTO;

public class CustomerLastNameComparator implements Comparator<UserViewDTO> {

	@Override
	public int compare(UserViewDTO o1, UserViewDTO o2) {
		return o1.lastName.compareTo(o2.lastName);
	}

}