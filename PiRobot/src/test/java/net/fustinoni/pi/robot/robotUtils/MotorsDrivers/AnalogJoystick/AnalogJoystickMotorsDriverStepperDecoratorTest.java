package net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick;

import net.fustinoni.pi.robot.component.LeftRightMotors;
import net.fustinoni.pi.robot.device.Motor;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick.JoystickToPowerConverter.LinearConverter;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.AnalogJoystick.JoystickToPowerConverter.StepperDecorator;
import net.fustinoni.pi.robot.robotUtils.MotorsDrivers.MotorsDriverImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author efustinoni
 */
public class AnalogJoystickMotorsDriverStepperDecoratorTest {
    
    int leftMotorSpeed = 0;
    boolean leftMotorisForward = true;
    
    int rightMotorSpeed = 0 ;
    boolean rightMotorisForward = true;
    

    int[] xMin = {0, 29, -100};
    int[] xMax = {100, 227, 100};
    int[] yMin = {0, 38, -100};
    int[] yMax = {100, 227, 100};
    int[] xCenter = {50, 123, 0};
    int[] yCenter = {50, 130, 0};
    
    AnalogJoystickMotorsDriver instance;
    
    public AnalogJoystickMotorsDriverStepperDecoratorTest() {
        instance = getAnalogJoystickMotorsDriver();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of stopMotors method, of class AnalogJoystickMotorsDriver.
     */
//    @Test
//    public void testStopMotors() {
//        System.out.println("stopMotors");
//        instance.stopMotors();
//        assertEquals(leftMotorSpeed, 0);
//        assertTrue(leftMotorisForward);
//        assertEquals(rightMotorSpeed, 0);
//        assertTrue(rightMotorisForward);
//    }

    /**
     * Test of jostickImput method, of class AnalogJoystickMotorsDriverImpl.
     */

    //@Ignore
    @Test
    public void testJostickCenter() {
        
        //center

        for (int i = 0; i< xCenter.length; ++i){
            int x=xCenter[i]; int y=yCenter[i];
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(0, leftMotorSpeed);
            assertTrue(leftMotorisForward);
            assertEquals(0, rightMotorSpeed);
            assertTrue(rightMotorisForward);
        }
        
    }
    
    //@Ignore    
    @Test
    public void testJostickforward() {

    // forward
        for (int i = 0; i< xCenter.length; ++i){
            int x=xCenter[i]; int y=yMax[i];
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(100, leftMotorSpeed);
            assertTrue(leftMotorisForward);
            assertEquals(100, rightMotorSpeed);
            assertTrue(rightMotorisForward);
        }
    }
    
    //@Ignore
    @Test
    public void testJostickHalfForward() {
        
        for (int i = 0; i< xCenter.length; ++i){
            int x=xCenter[i]; int y=yCenter[i] + (yMax[i]-yCenter[i])*50/100;
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(40, leftMotorSpeed);
            assertTrue(leftMotorisForward);
            assertEquals(40, rightMotorSpeed);
            assertTrue(rightMotorisForward);
        }
    }        
    
    //@Ignore
    @Test
    public void testJostickBackward() {
        
        // backward
        for (int i = 0; i< xCenter.length; ++i){
            int x=xCenter[i]; int y=yMin[i];
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(100, leftMotorSpeed);
            assertFalse(leftMotorisForward);
            assertEquals(100, rightMotorSpeed);
            assertFalse(rightMotorisForward);
        }
    }        

    //@Ignore
    @Test
    public void testJostickHalfBackward() {
        
        // backward
        for (int i = 0; i< xCenter.length; ++i){
            int x=xCenter[i]; int y= yMin[i] + (yCenter[i]-yMin[i])*50/100;
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(40, leftMotorSpeed);
            assertFalse(leftMotorisForward);
            assertEquals(40, rightMotorSpeed);
            assertFalse(rightMotorisForward);
        }
    }
    

    //@Ignore
    @Test
    public void testJostickLeft() {

        //left
        for (int i = 0; i< xCenter.length; ++i){
            int x=xMin[i]; int y=yCenter[i];
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(100, leftMotorSpeed);
            assertFalse(leftMotorisForward);
            assertEquals(100, rightMotorSpeed);
            assertTrue(rightMotorisForward);
        }
    }
    
    //@Ignore
    @Test
    public void testJostickHalfLeft() {

        //left
        for (int i = 0; i< xCenter.length; ++i){
            int x=xMin[i] + (xCenter[i]-xMin[i])*50/100; int y=yCenter[i];
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(40, leftMotorSpeed);
            assertFalse(leftMotorisForward);
            assertEquals(40, rightMotorSpeed);
            assertTrue(rightMotorisForward);
        }
    }
    
    //@Ignore
    @Test
    public void testJostickRight() {
        
        //right
        for (int i = 0; i< xCenter.length; ++i){
            int x=xMax[i]; int y=yCenter[i];
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(100, leftMotorSpeed);
            assertTrue(leftMotorisForward);
            assertEquals(100, rightMotorSpeed);
            assertFalse(rightMotorisForward);
        }
    }
    
    //@Ignore
    @Test
    public void testJostickHalfRight() {
        
        //right
        for (int i = 0; i< xCenter.length; ++i){
            int x=xCenter[i] + (xMax[i]-xCenter[i])*50/100; int y=yCenter[i];
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(40, leftMotorSpeed);
            assertTrue(leftMotorisForward);
            assertEquals(40, rightMotorSpeed);
            assertFalse(rightMotorisForward);
        }
    }
    
    //@Ignore
    @Test
    public void testJostickHalfLeftHalfForward() {

        //left
        for (int i = 0; i< xCenter.length; ++i){
            
            
            
            int x=xMin[i] + (xCenter[i]-xMin[i])*50/100; int y= yCenter[i] + (yMax[i]-yCenter[i])*50/100;
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(25, leftMotorSpeed);
            assertTrue(leftMotorisForward);
            assertEquals(40, rightMotorSpeed);
            assertTrue(rightMotorisForward);

        }
    }
    
    //@Ignore
    @Test
    public void testJostickHalfRightHalfForward() {

        //left
        for (int i = 0; i< xCenter.length; ++i){
            
            
            
            int x=xCenter[i] + (xMax[i]-xCenter[i])*50/100; int y= yCenter[i] + (yMax[i]-yCenter[i])*50/100;
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(40, leftMotorSpeed);
            assertTrue(leftMotorisForward);
            assertEquals(25, rightMotorSpeed);
            assertTrue(rightMotorisForward);
        }
    }
    

    //@Ignore
    @Test
    public void testJostickHalfRightHalfBackward() {
        
        for (int i = 0; i< xCenter.length; ++i){
            int x=xCenter[i] + (xMax[i]-xCenter[i])*50/100; int y= yMin[i] + (yCenter[i]-yMin[i])*50/100;
            instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

            assertEquals(40, leftMotorSpeed);
            assertFalse(leftMotorisForward);
            assertEquals(25, rightMotorSpeed);
            assertFalse(rightMotorisForward);
        }

    }
    
    //@Ignore
    @Test
    public void testJostickHalfLeftHalfBackwardHalfLeft() {

        for (int i = 0; i< xCenter.length; ++i){
        int x=x=xMin[i] + (xCenter[i]-xMin[i])*50/100; int y= yMin[i] + (yCenter[i]-yMin[i])*50/100;
        instance.jostickImput(x, y, xMin[i], xMax[i], yMin[i], yMax[i], xCenter[i], yCenter[i]);

        assertEquals(25, leftMotorSpeed);
        assertFalse(leftMotorisForward);
        assertEquals(40, rightMotorSpeed);
        assertFalse(rightMotorisForward);
        }

    }
    
    private AnalogJoystickMotorsDriverImpl getAnalogJoystickMotorsDriver(){
        return new AnalogJoystickMotorsDriverImpl( new MotorsDriverImpl(

            new LeftRightMotors() {

                    @Override
                    public Motor getLeftMotor() {
                        return new Motor() {

                            @Override
                            public void moveBackward(int speed) {
                                System.out.println("");
                                System.out.println("left motor moveBackward " + speed);
                                leftMotorSpeed = speed;
                                leftMotorisForward = false;
                            }

                            @Override
                            public void moveForward(int speed) {
                                System.out.println("");
                                System.out.println("left motor moveForward " + speed);
                                leftMotorSpeed = speed;
                                leftMotorisForward = true;

                            }

                            @Override
                            public void stop() {
                                System.out.println("");
                                System.out.println("left motor STOP");
                                leftMotorSpeed = 0;
                                leftMotorisForward = true;

                            }

                            @Override
                            public int getSpeed() {
                                return leftMotorSpeed;
                            }

                            @Override
                            public boolean isForward() {
                                return leftMotorisForward;
                            }

                        };
                    }

                    @Override
                    public Motor getRightMotor() {
                        return new Motor() {

                            @Override
                            public void moveBackward(int speed) {
                                System.out.println("");
                                System.out.println("right motor moveBackward " + speed);
                                rightMotorSpeed = speed;
                                rightMotorisForward = false;
                            }

                            @Override
                            public void moveForward(int speed) {
                                System.out.println("");
                                System.out.println("right motor moveForward " + speed);
                                rightMotorSpeed = speed;
                                rightMotorisForward = true;
                            }

                            @Override
                            public void stop() {
                                System.out.println("");
                                System.out.println("right motor STOP");
                                rightMotorSpeed = 0;
                                rightMotorisForward = true;
                            }

                            @Override
                            public int getSpeed() {
                                return rightMotorSpeed;
                            }

                            @Override
                            public boolean isForward() {
                                return rightMotorisForward;
                            }
                        };

                    }
                }
        ), new StepperDecorator(new LinearConverter()));
    }
    
    public static void main (String...args){
    
        for (float i = 0 ; i < 100; i+=1)
            System.out.println(i + " " + i * Math.log10(i+1));
        
    }
}

