package practice.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;

public class CreateOrgTest extends BaseClass {
	
		@Test
		public void createOrg() {
			System.out.println("execute create org and verify");
		}
		
		@Test
		public void createOrgWithIndustry() {
			System.out.println("execute org with industry and verify");
		}	
	}

