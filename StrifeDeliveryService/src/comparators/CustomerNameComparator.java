package comparators;

import java.util.Comparator;

import dto.UserViewDTO;

public class CustomerNameComparator implements Comparator<UserViewDTO> {

	@Override
	public int compare(UserViewDTO o1, UserViewDTO o2) {
		return o1.name.compareTo(o2.name);
	}

}
