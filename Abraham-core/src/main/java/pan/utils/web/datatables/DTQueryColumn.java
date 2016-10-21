/**
 * 
 */
package pan.utils.web.datatables;

import lombok.Data;

/**
 * @author panqingrong
 *
 */
@Data
public class DTQueryColumn {
	private String data;
	private String name;
	private boolean searchable;
	private boolean orderable;
	private DTQuerySearch search;
	private boolean regex;
}
