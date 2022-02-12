import org.junit.Test;

import org.firstinspires.ftc.teamcode.Util.CoordinateSystem.Coordinate;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class TestMain {
    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        Coordinate first = new Coordinate(0,2);
        Coordinate second = new Coordinate(0,2);
        assertTrue(first.equals(second));
    }
}
