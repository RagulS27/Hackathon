
package stepRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions( 
					features= {".//FeatureFiles/EMI_Calculator.feature"},
					glue="stepDefinition",
					plugin={"pretty","html:report//myreport.html",
							"rerun:target/rerun.txt",
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
					        },
					dryRun=false,
					monochrome=true,
					publish=true
		)
public class TestRun {
	
}