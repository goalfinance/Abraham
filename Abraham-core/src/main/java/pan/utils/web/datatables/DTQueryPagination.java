package pan.utils.web.datatables;

import java.util.List;

import lombok.Data;

/**
 * Query bean for DataTables query with pagination.
 * 
 * @author panqingrong
 *
 */
@Data
public class DTQueryPagination {
	/**
	 * Draw counter. This is used by DataTables to ensure that the Ajax returns
	 * from server-side processing requests are drawn in sequence by DataTables
	 * (Ajax requests are asynchronous and thus can return out of sequence).
	 * This is used as part of the draw return parameter (see below).
	 */
	private int draw;

	/**
	 * Paging first record indicator. This is the start point in the current
	 * data set (0 index based - i.e. 0 is the first record).
	 */
	private int start;

	/**
	 * Number of records that the table can display in the current draw. It is
	 * expected that the number of records returned will be equal to this
	 * number, unless the server has fewer records to return. Note that this can
	 * be -1 to indicate that all records should be returned (although that
	 * negates any benefits of server-side processing!)
	 */
	private int length;

	/**
	 * @see
	 */
	private DTQuerySearch search;

	/**
	 * @see
	 */
	private List<DTQueryOrder> order;

	/**
	 * @see
	 */
	private List<DTQueryColumn> columns;

}