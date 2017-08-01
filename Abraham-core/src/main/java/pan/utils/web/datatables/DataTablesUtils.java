package pan.utils.web.datatables;

import org.springframework.data.domain.Page;
import pan.utils.data.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panqingrong on 21/10/2016.
 */
public class DataTablesUtils<T> {

    public DTQueryResultPagination<T> convertDataTablesQueryResult(Integer reqDraw, Page<T> pagedResult) {
        DTQueryResultPagination<T> queryResult = new DTQueryResultPagination<T>();
        queryResult.setData(pagedResult.getContent());
        queryResult.setDraw(reqDraw);
        queryResult.setRecordsTotal(pagedResult.getTotalElements());
        queryResult.setRecordsFiltered(pagedResult.getTotalElements());

        return queryResult;
    }

    public int calcPageNumber(DTQueryPagination dtQuery) {
        return (dtQuery.getStart() / dtQuery.getLength());
    }

    public List<Order> getOrders(DTQueryPagination dtQuery) {
        List<Order> orders = new ArrayList<Order>();
        for (DTQueryOrder dqo : dtQuery.getOrder()) {
            DTQueryColumn dqColumn = dtQuery.getColumns().get(dqo.getColumn());
            orders.add(new Order(dqo.getDir(), dqColumn.getData()));
        }
        return orders;
    }
}
