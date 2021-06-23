package enumeration;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public enum CommentState {
	WAITING, 
	APPROVED, 
	DENIED
}
