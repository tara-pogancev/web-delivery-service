package comparators;

import java.util.Comparator;

import dto.UserViewDTO;

public class CustomerPointsComparator implements Comparator<UserViewDTO> {

	@Override
	public int compare(UserViewDTO o1, UserViewDTO o2) {
		String p1 = Integer.toString(o1.points);
		String p2 = Integer.toString(o2.points);
		return p1.compareTo(p2);
	}

}