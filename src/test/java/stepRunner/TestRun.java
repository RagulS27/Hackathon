
package stepRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions( 
					features= {".//FeatureFiles/EMI_Calculator.feature"},
					glue="stepDefinition",
					plugin={"pretty","html:report//myreport.html",
							"json:report/myreport.json"		
					        },
					dryRun=false,
					monochrome=true,
					publish=true
		)
public class TestRun {
	
}