/**
 * 
 */
package abraham.web.sitemesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * @author panqingrong
 *
 */
public class DefaultSiteMeshFilter extends ConfigurableSiteMeshFilter {

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		super.applyCustomConfiguration(builder);
		
		builder.addDecoratorPath("/data/**", "/common/decorator/app_frame");
		builder.addExcludedPath("/restapis/**");
		builder.addExcludedPath("/assets/**");
		builder.addExcludedPath("/common/**");
		builder.addExcludedPath("/data/**/*modal");
		
	}
	

}
