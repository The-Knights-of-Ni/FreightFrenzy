package org.firstinspires.ftc.teamcode.Util;

import java.util.Arrays;

import static org.firstinspires.ftc.teamcode.Util.TrcUtil.normalizeInPlace;

public class MathUtil {
    /**
     * This method calculates the modulo of two numbers. Unlike the <code>%</code> operator, this
     * returns a number in the range [0, b). For some reason, in Java, the <code>%</code> operator
     * actually does remainder, which means the result is in the range (-b, b).
     *
     * @param a specifies the dividend.
     * @param b specifies the divisor.
     * @return the modulo in the range [0, b)
     */
    public static double modulo(double a, double b) {
        return ((a % b) + b) % b;
    } // modulo

    /**
     * This method sums an array of numbers.
     *
     * @param nums specifies the array of numbers to be summed.
     * @return sum of the numbers.
     */
    public static double sum(double... nums) {
        double total = 0.0;

        for (double num : nums) {
            total += num;
        }

        return total;
    } // sum

    /**
     * This method calculates and returns the median of the numbers in the given array.
     *
     * @param num specifies the number array.
     * @return median of numbers in the array.
     */
    public static double median(double... num) {
        double m = 0.0;

        if (num.length > 0) {
            double[] nums = num.clone();

            Arrays.sort(nums);
            if (nums.length % 2 == 0) {
                m = average(nums[(nums.length / 2) - 1], nums[nums.length / 2]);
            } else {
                m = nums[nums.length / 2];
            }
        }

        return m;
    } // median

    /**
     * This method calculates and returns the average of the numbers in the given array.
     *
     * @param nums specifies the number array.
     * @return average of all numbers in the array. If the array is empty, return 0.
     */
    public static double average(double... nums) {
        return nums.length == 0 ? 0.0 : sum(nums) / nums.length;
    }

    /**
     * This method calculates the magnitudes of the given array of numbers.
     *
     * @param nums specifies the number array.
     * @return magnitude of all numbers in the array.
     */
    public static double magnitude(double... nums) {
        double total = 0.0;

        for (double num : nums) {
            total += num * num;
        }

        return Math.sqrt(total);
        // return Math.sqrt(Arrays.stream(nums).map(e -> e*e).sum());
    } // magnitude

    /**
     * This method returns the maximum magnitude of numbers in the specified array.
     *
     * @param nums specifies the number array.
     * @return maximum magnitude of the numbers in the array.
     */
    public static double maxMagnitude(double... nums) {
        double maxMag = Math.abs(nums[0]);

        for (double num : nums) {
            double magnitude = Math.abs(num);
            if (magnitude > maxMag) {
                maxMag = magnitude;
            }
        }

        return maxMag;
    } // maxMagnitude

    /**
     * This method returns a bit mask of the least significant set bit.
     *
     * @param data specifies the data to find the least significant set bit.
     * @return bit mask that has only the least significant set bit.
     */
    public static int leastSignificantSetBit(int data) {
        int bitMask = 0;

        if (data != 0) {
            bitMask = data & ~(data ^ -data);
        }

        return bitMask;
    }

    /**
     * This method returns the bit position of the least significant set bit of the given data.
     *
     * @param data specifies the data to determine its least significant set bit position.
     * @return 0-based least significant set bit position. -1 if no set bit.
     */
    public static int leastSignificantSetBitPosition(int data) {
        int pos = -1;

        if (data != 0) {
            for (int i = 0; ; i++) {
                if ((data & (1 << i)) != 0) {
                    pos = i;
                    break;
                }
            }
        }

        return pos;
    }

    /**
     * This method returns the bit position of the most significant set bit of the given data.
     *
     * @param data specifies the data to determine its most significant set bit position.
     * @return 0-based most significant set bit position. -1 if no set bit.
     */
    public static int mostSignificantSetBitPosition(int data) {
        int pos = -1;

        if (data != 0) {
            for (int i = 0; ; i++) {
                if ((data & (0x80000000 >> i)) != 0) {
                    pos = 31 - i;
                    break;
                }
            }
        }

        return pos;
    }

    /**
     * This method normalizes the given array of numbers such that no number exceeds +/- 1.0.
     *
     * @param nums specifies the number array.
     * @return normalized number array.
     */
    public static double[] normalize(double... nums) {
        double[] result = nums.clone();
        normalizeInPlace(result);

        return result;
    }

    /**
     * This method rounds a double to the nearest integer.
     *
     * @param num Number to round.
     * @return Rounded to the nearest integer.
     */
    public static int round(double num) {
        return (int) Math.floor(num + 0.5);
    }
}
