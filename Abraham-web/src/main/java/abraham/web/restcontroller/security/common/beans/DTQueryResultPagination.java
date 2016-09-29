/**
 * 
 */
package abraham.web.restcontroller.security.common.beans;

import java.util.Collection;

/**
 * Query result bean with pagination for DataTables
 * @author panqingrong
 *
 */
public class DTQueryResultPagination <T>{
	private Integer draw;
	private Long recordsTotal;
	private Long recordsFiltered;
	private Collection<T> data;
	private String error;
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Collection<T> getData() {
		return data;
	}
	public void setData(Collection<T> data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
