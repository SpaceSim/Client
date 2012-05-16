package net.spacesim.util;

public class Math {

	public static final double E = 2.718281828459045D;
	public static final double PI = 3.141592653589793D;
	private static long negativeZeroFloatBits = Float.floatToIntBits(-0.0F);
	private static long negativeZeroDoubleBits = Double.doubleToLongBits(-0.0D);

	public static double sin(double paramDouble) {
		return StrictMath.sin(paramDouble);
	}

	public static double cos(double paramDouble) {
		return StrictMath.cos(paramDouble);
	}

	public static double tan(double paramDouble) {
		return StrictMath.tan(paramDouble);
	}

	public static double asin(double paramDouble) {
		return StrictMath.asin(paramDouble);
	}

	public static double acos(double paramDouble) {
		return StrictMath.acos(paramDouble);
	}

	public static double atan(double paramDouble) {
		return StrictMath.atan(paramDouble);
	}

	public static double toRadians(double paramDouble) {
		return paramDouble / 180.0D * 3.141592653589793D;
	}

	public static double toDegrees(double paramDouble) {
		return paramDouble * 180.0D / 3.141592653589793D;
	}

	public static double exp(double paramDouble) {
		return StrictMath.exp(paramDouble);
	}

	public static double log(double paramDouble) {
		return StrictMath.log(paramDouble);
	}

	public static double log10(double paramDouble) {
		return StrictMath.log10(paramDouble);
	}

	public static double sqrt(double paramDouble) {
		return StrictMath.sqrt(paramDouble);
	}

	public static double cbrt(double paramDouble) {
		return StrictMath.cbrt(paramDouble);
	}

	public static double IEEEremainder(double paramDouble1, double paramDouble2) {
		return StrictMath.IEEEremainder(paramDouble1, paramDouble2);
	}

	public static double ceil(double paramDouble) {
		return StrictMath.ceil(paramDouble);
	}

	public static double floor(double paramDouble) {
		return StrictMath.floor(paramDouble);
	}

	public static double rint(double paramDouble) {
		return StrictMath.rint(paramDouble);
	}

	public static double atan2(double paramDouble1, double paramDouble2) {
		return StrictMath.atan2(paramDouble1, paramDouble2);
	}

	public static double pow(double paramDouble1, double paramDouble2) {
		if(paramDouble2 == 0.5) return sqrt(paramDouble1);
		return StrictMath.pow(paramDouble1, paramDouble2);
	}
	
	public static double pow(double paramDouble1, int paramInt) {
		double ret = paramDouble1;
		for(int i = 0; i < paramInt - 1; i++) {
			ret *= paramDouble1;
		}
		return ret;
	}

	public static int round(float paramFloat) {
		return (int) floor(paramFloat + 0.5F);
	}

	public static long round(double paramDouble) {
		return java.lang.Math.round(paramDouble);
	}

	public static int abs(int paramInt) {
		return paramInt < 0 ? -paramInt : paramInt;
	}

	public static long abs(long paramLong) {
		return paramLong < 0L ? -paramLong : paramLong;
	}

	public static float abs(float paramFloat) {
		return paramFloat <= 0.0F ? 0.0F - paramFloat : paramFloat;
	}

	public static double abs(double paramDouble) {
		return paramDouble <= 0.0D ? 0.0D - paramDouble : paramDouble;
	}

	public static int max(int paramInt1, int paramInt2) {
		return paramInt1 >= paramInt2 ? paramInt1 : paramInt2;
	}

	public static long max(long paramLong1, long paramLong2) {
		return paramLong1 >= paramLong2 ? paramLong1 : paramLong2;
	}

	public static float max(float paramFloat1, float paramFloat2) {
		if (paramFloat1 != paramFloat1)
			return paramFloat1;
		if ((paramFloat1 == 0.0F) && (paramFloat2 == 0.0F)
				&& (Float.floatToIntBits(paramFloat1) == negativeZeroFloatBits))
			return paramFloat2;
		return paramFloat1 >= paramFloat2 ? paramFloat1 : paramFloat2;
	}

	public static double max(double paramDouble1, double paramDouble2) {
		if (paramDouble1 != paramDouble1)
			return paramDouble1;
		if ((paramDouble1 == 0.0D)
				&& (paramDouble2 == 0.0D)
				&& (Double.doubleToLongBits(paramDouble1) == negativeZeroDoubleBits))
			return paramDouble2;
		return paramDouble1 >= paramDouble2 ? paramDouble1 : paramDouble2;
	}

	public static int min(int paramInt1, int paramInt2) {
		return paramInt1 <= paramInt2 ? paramInt1 : paramInt2;
	}

	public static long min(long paramLong1, long paramLong2) {
		return paramLong1 <= paramLong2 ? paramLong1 : paramLong2;
	}

	public static float min(float paramFloat1, float paramFloat2) {
		if (paramFloat1 != paramFloat1)
			return paramFloat1;
		if ((paramFloat1 == 0.0F) && (paramFloat2 == 0.0F)
				&& (Float.floatToIntBits(paramFloat2) == negativeZeroFloatBits))
			return paramFloat2;
		return paramFloat1 <= paramFloat2 ? paramFloat1 : paramFloat2;
	}

	public static double min(double paramDouble1, double paramDouble2) {
		if (paramDouble1 != paramDouble1)
			return paramDouble1;
		if ((paramDouble1 == 0.0D)
				&& (paramDouble2 == 0.0D)
				&& (Double.doubleToLongBits(paramDouble2) == negativeZeroDoubleBits))
			return paramDouble2;
		return paramDouble1 <= paramDouble2 ? paramDouble1 : paramDouble2;
	}

	public static double ulp(double paramDouble) {
		return java.lang.Math.ulp(paramDouble);
	}

	public static float ulp(float paramFloat) {
		return java.lang.Math.ulp(paramFloat);
	}

	public static double signum(double paramDouble) {
		return java.lang.Math.signum(paramDouble);
	}

	public static float signum(float paramFloat) {
		return java.lang.Math.signum(paramFloat);
	}

	public static double sinh(double paramDouble) {
		return StrictMath.sinh(paramDouble);
	}

	public static double cosh(double paramDouble) {
		return StrictMath.cosh(paramDouble);
	}

	public static double tanh(double paramDouble) {
		return StrictMath.tanh(paramDouble);
	}

	public static double hypot(double paramDouble1, double paramDouble2) {
		return StrictMath.hypot(paramDouble1, paramDouble2);
	}

	public static double expm1(double paramDouble) {
		return StrictMath.expm1(paramDouble);
	}

	public static double log1p(double paramDouble) {
		return StrictMath.log1p(paramDouble);
	}

	public static double copySign(double paramDouble1, double paramDouble2) {
		return java.lang.Math.copySign(paramDouble1, paramDouble2);
	}

	public static float copySign(float paramFloat1, float paramFloat2) {
		return java.lang.Math.copySign(paramFloat1, paramFloat2);
	}

	public static int getExponent(float paramFloat) {
		return java.lang.Math.getExponent(paramFloat);
	}

	public static int getExponent(double paramDouble) {
		return java.lang.Math.getExponent(paramDouble);
	}

	public static double nextAfter(double paramDouble1, double paramDouble2) {
		return java.lang.Math.nextAfter(paramDouble1, paramDouble2);
	}

	public static float nextAfter(float paramFloat, double paramDouble) {
		return java.lang.Math.nextAfter(paramFloat, paramDouble);
	}

	public static double nextUp(double paramDouble) {
		return java.lang.Math.nextUp(paramDouble);
	}

	public static float nextUp(float paramFloat) {
		return java.lang.Math.nextUp(paramFloat);
	}

	public static double scalb(double paramDouble, int paramInt) {
		return java.lang.Math.scalb(paramDouble, paramInt);
	}

	public static float scalb(float paramFloat, int paramInt) {
		return java.lang.Math.scalb(paramFloat, paramInt);
	}
}
