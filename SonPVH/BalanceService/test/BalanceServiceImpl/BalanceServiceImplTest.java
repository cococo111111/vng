/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BalanceServiceImpl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author root
 */
public class BalanceServiceImplTest {

    public BalanceServiceImplTest() {
        System.out.println("aaa");
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Inint 1 ");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("end 1 ");
    }

    @Before
    public void setUp() {
        //init: all of testcases
        System.out.println("Inint ");
    }

    @After
    public void tearDown() {
        //end: all of testcases
        System.out.println("end  ");
    }

    /**
     * Test of addMoney method, of class BalanceServiceImpl.
     */
    @Test
    public void testAddMoney() {
        System.out.println("test 1 ");
//        System.out.println("addMoney");
//        BalanceDTO balanceDto = null;
//        BalanceServiceImpl instance = new BalanceServiceImpl();
//        instance.addMoney(balanceDto);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deductMoney method, of class BalanceServiceImpl.
     */
    @Test
    public void testDeductMoney() {
//        System.out.println("deductMoney");
//        BalanceDTO balanceDto = null;
//        BalanceServiceImpl instance = new BalanceServiceImpl();
//        instance.deductMoney(balanceDto);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        System.out.println("test 2 ");
    }
}
