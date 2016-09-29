/**
 * 
 */
package pan.utils.data;

import lombok.Data;

/**
 * @author panqingrong
 *
 */
@Data
public class Order {
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	private String column;
	private String dir;
	public Order(String dir, String column) {
		super();
		this.column = column;
		this.dir = dir;
	}
}
