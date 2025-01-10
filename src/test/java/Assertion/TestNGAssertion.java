package Assertion;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGAssertion {

    @Test
    public  void HardAssertion(){
        System.out.println("Start");
        Boolean isAlekhyaMale=false;
        Assert.assertTrue(isAlekhyaMale);
        System.out.println("End");
    }

    @Test
    public void softAssertion(){

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(false);
        System.out.println("Start");
        softAssert.assertFalse(true);
        System.out.println("End");
        softAssert.assertAll();


    }
}
