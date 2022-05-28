package pibotlib.lib;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.analog.AnalogOutput;
import com.pi4j.io.gpio.analog.AnalogOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmType;
import pibotlib.utils.DriverStationState;

public class Robot implements Runnable{

    Context context;
    Pwm pwm;
    DigitalOutputConfigBuilder pinConfig;
    DigitalOutput pin,pin2;

    public Robot(){
        //System.out.println("robot init");
        //context = Pi4J.newAutoContext();
        //pinConfig = DigitalOutput.newConfigBuilder(context)
        //        .id("led")
        //        .name("LED Flasher")
        //        .address(4)
        //        .shutdown(DigitalState.LOW)
        //        .initial(DigitalState.LOW)
        //        .provider("pigpio-digital-output");
        //pin = context.create(pinConfig);
        //pin.high();
        ////pwm = context.create(buildPwmConfig(context,12));
        ////pwm.on(50,1);
    }

    public void runRobot(){
        //if (DriverStationState.getState().equals("Enabled")){
        //    System.out.println("robot running");
        //    context = Pi4J.newAutoContext();
        //    pinConfig = DigitalOutput.newConfigBuilder(context)
        //            .id("led")
        //            .name("LED Flasher")
        //            .address(4)
        //            .shutdown(DigitalState.LOW)
        //            .initial(DigitalState.LOW)
        //            .provider("pigpio-digital-output");
        //    pin = context.create(pinConfig);
        //    pin.high();
        //}
        //if (DriverStationState.getState().equals("Disabled")){
        //    pin.low();
        //}else {
        //    context.shutdown();
        //}
        //pwm.on(50,1);
    }

    protected static PwmConfig buildPwmConfig(Context pi4j, int address) {
        return Pwm.newConfigBuilder(pi4j)
                .id("BCM" + address)
                .name("Buzzer")
                .address(address)
                .pwmType(PwmType.HARDWARE)
                .provider("pigpio-pwm")
                .initial(0)
                .shutdown(0)
                .build();
    }

    public static DigitalOutputConfigBuilder outputConfigBuilder(Context context, int adress){
        return  DigitalOutput.newConfigBuilder(context)
                .id("led")
                .name("LED Flasher")
                .address(adress)
                .shutdown(DigitalState.LOW)
                .initial(DigitalState.LOW)
                .provider("pigpio-digital-output");
    }

    @Override
    public void run() {
        context = Pi4J.newAutoContext();
        pwm = context.create(buildPwmConfig(context,2));
        pin = context.create(outputConfigBuilder(context,3));
        pin2 = context.create(outputConfigBuilder(context,4));

        while (true) {
            if (DriverStationState.getState().equals("Enabled")) {
                pin.high();
                pin2.high();
                pwm.on(50,1);
            }
            if (DriverStationState.getState().equals("Disabled")) {
                pin.low();
                pin2.low();
                pwm.on(0,1);
            }
        }
        //pwm = context.create(buildPwmConfig(context,12));
        //pwm.on(50,1);
    }
}
