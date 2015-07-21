package powerball.jotace.org.powerball;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class BallsUtils {

    private static Set<Integer> winningNumbers = new HashSet<>();
    private static SecureRandom randomGenerator = new SecureRandom();

    public static String getWinningBall() {
        int winningBall = randomGenerator.nextInt(59) + 1;
        while(!winningNumbers.add(winningBall)) {
            winningBall = randomGenerator.nextInt(59) + 1;
        }

        return Integer.toString(winningBall);
    }

    public static String getPowerBall() {
        return Integer.toString(randomGenerator.nextInt(35) + 1);
    }

    public static void resetWinningNumbers() {
        winningNumbers = new HashSet<>();
    }

} // end


