/**
 *
 */
package pan.utils.data;

import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author panqingrong
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

    public static Sort newSort(List<Order> orders) {
        if (orders != null && false == orders.isEmpty()) {
            List<org.springframework.data.domain.Sort.Order> os = new ArrayList<Sort.Order>();
            for (Order order : orders) {
                Sort.Direction dir = order.getDir().equals(Order.DESC) ? Sort.Direction.DESC : Sort.Direction.ASC;
                os.add(new org.springframework.data.domain.Sort.Order(dir, order.getColumn()));
            }
            return new Sort(os);
        } else {
            return null;
        }
    }
}
