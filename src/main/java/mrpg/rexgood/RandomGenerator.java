package mrpg.rexgood;
import java.util.Random;
public class RandomGenerator{//랜덤값 생성
        public static Integer MakeNumber(int min,int max) {
            Random random = new Random();
            int randomNumber = random.nextInt(max - min + 1) + min;
            return randomNumber;
        }
}