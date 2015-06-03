package org.eclipse.kura.example.hello_osgi;

import java.io.IOException;

import jdk.dio.ClosedDeviceException;
import jdk.dio.DeviceManager;
import jdk.dio.DeviceNotFoundException;
import jdk.dio.UnavailableDeviceException;
import jdk.dio.gpio.GPIOPin;

import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloOsgi {
	
	GPIOPin led1;

    private static final Logger s_logger = LoggerFactory.getLogger(HelloOsgi.class);

    private static final String APP_ID = "org.eclipse.kura.example.hello_osgi";

    protected void activate(ComponentContext componentContext) throws DeviceNotFoundException, UnavailableDeviceException, IOException {

    	s_logger.info("Bundle " + APP_ID + " is starting...");
    	  try {
    	      // 17 indicates GPIO_GEN_0 on Raspberry P B+
    	      led1 = (GPIOPin) DeviceManager.open(23);

    	      led1.setValue(true);
    	    } catch (DeviceNotFoundException e) {
    	      // TODO Auto-generated catch block
    	      e.printStackTrace();
    	    } catch (UnavailableDeviceException e) {
    	      // TODO Auto-generated catch block
    	      e.printStackTrace();
    	    } catch (IOException e) {
    	      // TODO Auto-generated catch block
    	      e.printStackTrace();
    	  }
    	  s_logger.info("Bundle " + APP_ID + " has started!"); 

    }

    protected void deactivate(ComponentContext componentContext) {
    	try {
			led1.setValue(false);
			led1.close();
		} catch (UnavailableDeviceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClosedDeviceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        s_logger.info("Bundle " + APP_ID + " has stopped!");

    }

}