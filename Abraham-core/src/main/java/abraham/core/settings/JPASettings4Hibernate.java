/**
 *
 */
package abraham.core.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author panqingrong
 */
@Component
@ConfigurationProperties(prefix = "abraham.jpa-hibernate")
public class JPASettings4Hibernate {
    private boolean generateDdl;
    private boolean showSql;
    private List<String> packagesToScan;
    private String dialect;

    public boolean getGenerateDdl() {
        return generateDdl;
    }

    public void setGenerateDdl(boolean generateDdl) {
        this.generateDdl = generateDdl;
    }

    public boolean getShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public List<String> getPackagesToScan() {
        return packagesToScan;
    }

    public void setPackagesToScan(List<String> packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }
}
