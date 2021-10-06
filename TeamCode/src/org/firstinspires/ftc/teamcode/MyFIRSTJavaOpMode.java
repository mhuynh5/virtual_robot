package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class MyFIRSTJavaOpMode extends LinearOpMode {
    private BNO055IMU imu;
    private DcMotor motorTest;
    private DistanceSensor sensorColorRange;
    private Servo servoTest;


    @Override
    public void runOpMode() {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        motorTest = hardwareMap.get(DcMotor.class, "back_right_motor");
        sensorColorRange = hardwareMap.get(DistanceSensor.class, "front_distance");
        servoTest = hardwareMap.get(Servo.class, "back_servo");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        double tgtPower = 0;
        while (opModeIsActive()) {
            tgtPower = -this.gamepad1.left_stick_y;
            motorTest.setPower(tgtPower);
            motorTest.setPower(tgtPower);
            // check to see if we need to move the servo.
            if(gamepad1.y) {
                // move to 0 degrees.
                servoTest.setPosition(0);
            } else if (gamepad1.x || gamepad1.b) {
                // move to 90 degrees.
                servoTest.setPosition(0.5);
            } else if (gamepad1.a) {
                // move to 180 degrees.
                servoTest.setPosition(1);
            }
            telemetry.addData("Servo Position", servoTest.getPosition());
            telemetry.addData("Target Power", tgtPower);
            telemetry.addData("Motor Power", motorTest.getPower());
            telemetry.addData("Distance (cm)", sensorColorRange.getDistance(DistanceUnit.CM));
            // set digital channel to input mode.
            digitalTouch.setMode(DigitalChannel.Mode.INPUT);

            telemetry.addData("Status", "Running");
            telemetry.update();
            // Wait for the game to start (driver presses PLAY)
            waitForStart();
        }
    }
}