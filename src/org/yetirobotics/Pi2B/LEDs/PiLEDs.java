package org.yetirobotics.Pi2B.LEDs;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class PiLEDs {
	public static boolean run = true;
	public static String state = "off";
	public static NetworkTable LEDTable;
	public static final int RED_PIN = 1;
	public static final int GREEN_PIN = 2;
	public static final int BLUE_PIN = 3;
	public static final int[] YETI_BLUE = { 11, 178, 244 };
	public static final int[] YETI_GREEN = { 142, 226, 86 };

	public static void main(String[] args) {
		run = true;
		LEDTable = NetworkTable.getTable("LED");
		Gpio.wiringPiSetup();
		SoftPwm.softPwmCreate(RED_PIN, 0, 255);
		SoftPwm.softPwmCreate(GREEN_PIN, 0, 255);
		SoftPwm.softPwmCreate(BLUE_PIN, 0, 255);

		while (run) {
			state = LEDTable.getString("state", "off");
			switch (state) {
			case "flashBlue":
				flashBlue();
				break;
			case "flashGreen":
				flashBlue();
				break;
			}
		}
	}

	public static void flashBlue() {
		for (int i = 0; i <= 100; i++) {
			SoftPwm.softPwmWrite(RED_PIN, YETI_BLUE[0] / i);
			SoftPwm.softPwmWrite(GREEN_PIN, YETI_BLUE[1] / i);
			SoftPwm.softPwmWrite(BLUE_PIN, YETI_BLUE[2] / i);
			sleep(500);
		}
		for (int i = 100; i > 0; i--) {
			SoftPwm.softPwmWrite(RED_PIN, YETI_BLUE[0] / i);
			SoftPwm.softPwmWrite(GREEN_PIN, YETI_BLUE[1] / i);
			SoftPwm.softPwmWrite(BLUE_PIN, YETI_BLUE[2] / i);
			sleep(500);
		}
	}

	public static void flashGreen() {
		for (int i = 0; i <= 100; i++) {
			SoftPwm.softPwmWrite(RED_PIN, YETI_GREEN[0] / i);
			SoftPwm.softPwmWrite(GREEN_PIN, YETI_GREEN[1] / i);
			SoftPwm.softPwmWrite(BLUE_PIN, YETI_GREEN[2] / i);
			sleep(500);
		}
		for (int i = 100; i > 0; i--) {
			SoftPwm.softPwmWrite(RED_PIN, YETI_GREEN[0] / i);
			SoftPwm.softPwmWrite(GREEN_PIN, YETI_GREEN[1] / i);
			SoftPwm.softPwmWrite(BLUE_PIN, YETI_GREEN[2] / i);
			sleep(500);
		}
	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
