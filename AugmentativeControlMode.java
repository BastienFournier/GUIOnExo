package us.ihmc.augmentativeExoskeletons.control.highestLevelStates;

public enum AugmentativeControlMode
{
   DO_NOTHING,
   JOINT_SETPOINT,
   PLACE_AND_HOLD,
   SQUAT,
   BLENDING_GRAV_COMP,
   GRAV_COMP_WRENCH,
   FGEN_WALKING
   ;

   public static final AugmentativeControlMode[] values = values();

   public byte toByte()
   {
      return (byte) ordinal();
   }

   public static AugmentativeControlMode fromByte(byte enumAsByte)
   {
      if (enumAsByte == -1)
         return null;
      return values[enumAsByte];
   }
}
