import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    static volatile boolean timeOver = false;
    static volatile int remainingTime = 30;

    public static void main(String[] stmts) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int target = random.nextInt(100) +1;

        int maxTry = 3;
        int atmpts = 0;
        boolean won = false;

        Thread timerThread = new Thread(() -> {
            try {
                while (remainingTime > 0) {
                    Thread.sleep(1000);
                    remainingTime--;
                }
                timeOver = true;
                System.out.println("\n Time is over!!");
            } catch(InterruptedException e ) {

            }
        });
        timerThread.start();

        System.out.println("A number between 1 - 100");
        System.out.println(" Try to find the target number!!");
        System.out.println("You can enter" + maxTry + " attempts;");
        System.out.println("Total Time: 30 seconds.\n");


while(atmpts < maxTry && !timeOver){
    System.out.print("Enter your guessing number: ");
    String input = scanner.nextLine();

    if(timeOver) break;

    try{
        int guess = Integer.parseInt(input.trim());
        atmpts++;

    if(guess == target) {
        System.out.println("Congratulations!! Correct Number...");
        won = true;
        break;
    }else if (guess < target) {
        System.out.println(" Too LOWW!!");
    } else{
        System.out.println("Too HIGH!!");
    }
    System.out.println("Attempts left: " + (maxTry - atmpts));
    System.out.println("Time left: " + remainingTime + "seconds");
} catch (NumberFormatException e){
        System.out.println("Invalid input. Please enter a number.");
    }
}
if(timerThread.isAlive()){
    timerThread.interrupt();
}

if(!won && timeOver) {
    System.out.println("Time is OVER!! The correct number: " + target);
} else if (!won && !timeOver){
    System.out.println("No attempts left! The correct number:" + target);
}
scanner.close();
    }

}