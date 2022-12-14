package pibotlib.lib.addons;

import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmType;

/**
 *allows for an LED to serve as an "indicator" that thje robot is on/enabled/disabled etc...*/
public class RobotStateLight {

    Context context;
    PwmConfig config;
    Pwm pwm;

    public RobotStateLight(Context context, int pwmChannel){
        this.context = context;
        config = Pwm.newConfigBuilder(context)
                .id("BCM " + pwmChannel)
                .name("RSL")
                .address(pwmChannel)
                .pwmType(PwmType.SOFTWARE)
                .provider("pigpio-pwm")
                .initial(0)
                .shutdown(0)
                .build();
        pwm = context.create(config);
    }

    /**
     *will blink the LED*/
    public void blinkRSL(){
        pwm.on(10,1000);
    }

    /**
     *will turn off the LED*/
    public void shutDown(){
        pwm.off();
    }
}
