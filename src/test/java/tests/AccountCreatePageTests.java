package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.util.Random;

/**
 * Created by Inka on 22-Dec-18.
 */
public class AccountCreatePageTests extends TestBase {
    HomePageHelper homePage;
    AccountCreatePageHelper accountCreatePage;
    ProfilePageHelper profilePage;
    MenuPageHelper menuPage;
    LoginPageHelper loginPage;


    @BeforeMethod
    public void initPage() {
        homePage = PageFactory
                .initElements(driver, HomePageHelper.class);
        accountCreatePage = PageFactory
                .initElements(driver, AccountCreatePageHelper
                        .class);
        profilePage = PageFactory.initElements(driver,
                ProfilePageHelper.class);
        menuPage = PageFactory
                .initElements(driver, MenuPageHelper.class);
        loginPage = PageFactory
                .initElements(driver,LoginPageHelper.class);
    }

    @Test
    public void createNewAccount(){
        homePage.waitUntilPageLoad()
                .pressCreateAccountButton();
        accountCreatePage.waitUntilPageLoad()
                .enterValueToFieldEmailRandom() //enterValueToFieldEmail(email1)
                .enterValueToFieldPassword("example")
                .enterValueToFieldRepPassword("example")
                .pressRegistrationButton();
        profilePage.waitUntilPageLoad()
                .menuButtonClick();
        menuPage.waitUntilPageLoad()
                .pressLogOutButton();
        homePage.waitUntilPageLoad();
        Assert.assertEquals(homePage.getHeader(), "Shabbat in the family circle");
    }

    @Test
    public void createNewAccountAndLogin(){
        String email1 = accountCreatePage.latinDigitString(8) + "@gmail.com";
        homePage.waitUntilPageLoad()
                .pressCreateAccountButton();
        accountCreatePage.waitUntilPageLoad()
                .enterValueToFieldEmail(email1)
                .enterValueToFieldPassword("example")
                .enterValueToFieldRepPassword("example")
                .pressRegistrationButton();
        profilePage.waitUntilPageLoad()
                .menuButtonClick();
        menuPage.waitUntilPageLoad()
                .pressLogOutButton();
        homePage.waitUntilPageLoad();
        Assert.assertEquals(homePage.getHeader(), "Shabbat in the family circle");

        homePage.pressLoginButton();
        loginPage.enterValueToFieldEmail(email1)
                .enterValueToFieldPassword("example")
                .pressLogInButton();
        profilePage.waitUntilPageLoad();
        Assert.assertEquals(profilePage.getHeader(),"Registration");

        profilePage.menuButtonClick();
        menuPage.waitUntilPageLoad();
        menuPage.pressLogOutButton();
        homePage.waitUntilPageLoad();

        Assert.assertEquals(homePage.getHeader(),"Shabbat in the family circle");

    }


    /*public static String latinDigitString(int length){
        String str = "";
        char ch;
        int number;
        Random gen = new Random();
        int i = 0;
        do {
            number = '0' + gen.nextInt('z' - '0' +1);
            if ((number <= '9') || (number >= 'A' && number <= 'Z') || (number >= 'a'))
            {
                str = str + (char)number;
            }
        }while(str.length()<length);
        return str;
    }*/
}
