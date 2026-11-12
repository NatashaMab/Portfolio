package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testData.ExcelReader;
import utils.Base;

public class GroupCreationTest extends Base {


    @DataProvider(name = "groupCreationData")
    public Object[][] groupCreationData() {

        Object[][] loginData = ExcelReader.getLoginDataFromExcel(
                "src/test/java/testData/testData.xlsx",
                "AdminLoginDetails"
        );

        Object[][] groupData = ExcelReader.getGroupCreationDetailsFromExcel(
                "src/test/java/testData/testData.xlsx",
                "GroupCreationElements"
        );

        Object[][] combinedData = new Object[loginData.length][
                loginData[0].length + groupData[0].length
                ];

        for (int i = 0; i < loginData.length; i++) {

            System.arraycopy(loginData[i], 0, combinedData[i],
                    0, loginData[i].length);

            System.arraycopy(groupData[i], 0, combinedData[i],
                    loginData[i].length, groupData[i].length);
        }

        return combinedData;
    }

    @Test(dataProvider = "groupCreationData" )
    public void groupCreationTest(String email, String password, String name, String description, String year, String maximumCapacity, String startDate, String endDate){

        landingpage.verifyOnLandingPage();
        landingpage.navigateToLoginPage();
        loginpage.verifyOnLoginPage();
        loginpage.provideLoginDetails(email, password);
        loginpage.clickLogin();
        dashboardpage.verifyOnDashboardPage();
        dashboardpage.navigateToAdminPanelPage();
        adminpanelpage.verifyOnAdminPanelPage();
        adminpanelpage.navigateToCreateGroup();
        adminpanelpage.provideGroupCreationElements(name,description,year, maximumCapacity, startDate,endDate);
        adminpanelpage.clickCreateGroup();

    }

}
