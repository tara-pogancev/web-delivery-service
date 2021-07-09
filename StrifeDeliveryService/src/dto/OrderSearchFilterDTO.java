package dto;

import java.util.Date;

public class OrderSearchFilterDTO {

	public String searchField;
	public String sort;
	public String status;
	public float priceMin;
	public float priceMax;
	public String restType;
	public Date startDate;
	public Date endDate;
	
	@Override
	public String toString() {
		return "OrderSearchFilterDTO [searchField=" + searchField + ", sort=" + sort + ", status=" + status
				+ ", priceMin=" + priceMin + ", priceMax=" + priceMax + ", restType=" + restType + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}
	
	
	
}
