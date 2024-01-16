package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final double GLOBAL_TIMESTEP = 0.02;
    public static final double controllerError = 0.05;

    public static final double rotkP = 0.1;
    public static final double rotkI = 0.0;
    public static final double rotkD = 0.0;

    public static final class MotorConstants {
        public static final int CONTROLLER_ID = 1;
        public static final double neoMomentOfInertia = 2.955E-06; 
    }


    public static final Mode currentMode = Mode.REAL;

    public static enum Mode {
        /** Running on a real robot. */
        REAL,
        /** Running a physics simulator. */
        SIM,
        /** Replaying from a log file. */
        REPLAY
    }
}
