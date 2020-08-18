package test_app;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class TC_set_bugged_app_edge {
    private WebDriver wd;
    private String url;
    private String CardHolder;
    private String CardNumber;
    private String Cvv;



        @Before
        public void setUp() {
            File file = new File("C:/Users/razor/IdeaProjects/Test_set/src/main/resources/msedgedriver.exe");
            System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
            wd = new EdgeDriver();
            url = "https://bugged-tester-recruitment-task.netlify.app/";
            CardHolder = "Jan Testowy";
            CardNumber = "2233114400002589";
            Cvv = "127";


        }

    @After
    public void tearDown() {
        wd.quit();
    }

        @Test
        public void IsBankTransferCorrect() {
            wd.get(url);
            wd.findElement(By.className("MuiSelect-select")).click();
            wd.findElement(By.xpath("//li[(text()='Bank transfer')]")).click();
            wd.findElement(By.className("MuiButton-contained")).click();
            WebDriverWait wait = new WebDriverWait(wd,10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("MuiAlert-message")));
            Assert.assertTrue(wd.getPageSource().contains("Paid by bank"));

        }

    @Test
    public void NotEnoughDigitsToCardNumber() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys(CardHolder);
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys("223311440000");
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys(Cvv);
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.xpath("//div[(text()='2021')]")).click();
        wd.findElement(By.xpath("//div[text()='Aug']")).click();
        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertFalse(wd.getPageSource().contains("Paid by card"));

    }


    @Test
    public void CvvBelowThreeDigits() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys(CardHolder);
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys("18");
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.xpath("//div[(text()='2021')]")).click();
        wd.findElement(By.xpath("//div[text()='Aug']")).click();
        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertFalse(wd.getPageSource().contains("Paid by card"));

    }

    @Test
    public void CvvAboveThreeDigits() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys(CardHolder);
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys("1155");
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.className("MuiPickersYear-yearSelected")).click();
        wd.findElement(By.className("MuiPickersMonth-monthSelected")).click();

        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertFalse(wd.getPageSource().contains("Paid by card"));

    }



    @Test
    public void CardHolderTwoChar() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys("To");
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys(Cvv);
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.className("MuiPickersYear-yearSelected")).click();
        wd.findElement(By.className("MuiPickersMonth-monthSelected")).click();

        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertFalse(wd.getPageSource().contains("Paid by card"));

    }


    @Test
    public void CardHolderBadChar() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys("!!");
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys(Cvv);
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.className("MuiPickersYear-yearSelected")).click();
        wd.findElement(By.className("MuiPickersMonth-monthSelected")).click();

        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertFalse(wd.getPageSource().contains("Paid by card"));

    }
    @Test
    public void AllProcessWithPaidByCardIsFine() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys(CardHolder);
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys(Cvv);
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.xpath("//div[(text()='2024')]")).click();
        wd.findElement(By.xpath("//div[text()='Sep']")).click();

        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertTrue(wd.getPageSource().contains("Paid by card"));

    }
    @Test
    public void CardExpDateInPaymentDetailFine() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys(CardHolder);
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys(Cvv);
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.xpath("//div[(text()='2024')]")).click();
        wd.findElement(By.xpath("//div[text()='Sep']")).click();

        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertTrue(wd.getPageSource().contains("09/2024"));

    }
    @Test
    public void IconReviewYourOrderInCheckoutIsNotEnabled() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys(CardHolder);
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys(Cvv);
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.xpath("//div[(text()='2024')]")).click();
        wd.findElement(By.xpath("//div[text()='Sep']")).click();
        wd.findElement(By.className("MuiButton-contained")).click();
        wd.findElement(By.className("Mui-disabled")).isDisplayed();
        Assert.assertTrue(wd.getPageSource().contains("Paid by card"));

    }
    @Test
    public void GivenCheckBoxRememberCreditCardDetailsEnabled() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys(CardHolder);
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys(Cvv);
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.xpath("//div[(text()='2024')]")).click();
        wd.findElement(By.xpath("//div[text()='Sep']")).click();
        wd.findElement(By.id("saveCard")).click();
        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertTrue(wd.getPageSource().contains("Paid by card"));

    }

    @Test
    public void CardHolderBadCharWithName() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys("Tomasz!#$Kaniecki!");
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys(Cvv);
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.className("MuiPickersYear-yearSelected")).click();
        wd.findElement(By.className("MuiPickersMonth-monthSelected")).click();

        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertTrue(wd.getPageSource().contains("Paid by card"));

    }

    @Test
    public void ClearButtonInCardExpDateIsPresent() {
        wd.get(url);
        WebElement holderName = wd.findElement(By.id("cardOwnerName"));
        holderName.clear();
        holderName.sendKeys(CardHolder);
        WebElement numberCard = wd.findElement(By.id("cardNumber"));
        numberCard.clear();
        numberCard.sendKeys(CardNumber);
        WebElement cvv = wd.findElement(By.id("cvv"));
        cvv.clear();
        cvv.sendKeys(Cvv);
        wd.findElement(By.id("cardExpDate")).click();
        wd.findElement(By.xpath("//div[(text()='2024')]")).click();
        wd.findElement(By.xpath("//span[text()='Clear']")).click();
        wd.findElement(By.className("MuiButton-contained")).click();
        Assert.assertFalse(wd.getPageSource().contains("Paid by card"));

    }
    }

