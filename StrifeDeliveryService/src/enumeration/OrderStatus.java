package enumeration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum OrderStatus {
	
	PROCESSING, 
	PREPARATION, 
	AWAITING_DELIVERER, 
	TRANSPORT, 
	DELIVERED, 
	CANCELED,
	REVIEWED

}
