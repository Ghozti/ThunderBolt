package pibotlib.lib.drives;

import pibotlib.lib.motorcontrollers.MotorController;

/**
 *used to handle driving logic*/
public class DifferentialDrive extends ChassisDrive{

    public DifferentialDrive(MotorController leftcontroller, MotorController rightController) {
        super(leftcontroller, rightController);
    }

    /**
     *will handle the logic required to drive the robot using an x and y value provided from the controller*/
    public void arcadeDrive(double x, double y){

        if(Math.abs(x/100) < .1 && Math.abs(y/100) < .1){
            getLeftController().motor1Kill();
            getLeftController().motor2Kill();
            getRightController().motor1Kill();
            getRightController().motor2Kill();
        }else {
            if (x > 0 && Math.abs(x) > Math.abs(y)){//forward
                getLeftController().motor1Forward(x);
                getLeftController().motor2Forward(x);
                getRightController().motor1Forward(x);
                getRightController().motor2Forward(x);
            }else if (x < 0 && Math.abs(x) > Math.abs(y)){//backward
                getLeftController().motor1Backward(-x);
                getLeftController().motor2Backward(-x);
                getRightController().motor1Backward(-x);
                getRightController().motor2Backward(-x);
            }

            if(y < 0 && Math.abs(y) > Math.abs(x)) {//left turn
                getLeftController().motor1Backward(-y);
                getLeftController().motor2Backward(-y);
                getRightController().motor1Forward(-y);
                getRightController().motor2Forward(-y);
            }else if(y > 0 && Math.abs(y) > Math.abs(x)) {//right turn
                getRightController().motor1Backward(y);
                getRightController().motor2Backward(y);
                getLeftController().motor1Forward(y);
                getLeftController().motor2Forward(y);
            }
        }
    }
}
