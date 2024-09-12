import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        KNN knn = new KNN();
        List<Iris> testingData;
        List<Iris> trainingData;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        trainingData = knn.getData("/Users/agata/Desktop/pjatekmess/nai/KNN/src/iris-train-data-110311.txt", true);
        Scanner scanner = new Scanner(System.in);
        int k;

        do {
            System.out.println("Pass k parameter: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Try again");
                scanner.next();
            }
            k = scanner.nextInt();
            scanner.nextLine();
            if (k > trainingData.size()) {
                System.out.println("Too big k parameter. Please try again.");
            }
        } while (k > trainingData.size());

        System.out.println("Enter 'file' to input data from a file or 'keyboard' to pass data directly: ");
        String input;
        do {
            input = scanner.nextLine();
            if (!input.equals("file") && !input.equals("keyboard")) {
                System.out.println("Not recognized command. Please enter 'file' or 'keyboard'.");
            }
        } while (!input.equals("file") && !input.equals("keyboard"));

        if (input.equals("file")) {
            System.out.println("Pass file path");
            String str = scanner.nextLine();
            testingData = knn.getData(str, false);
            double accuracy = knn.doKNN(testingData, trainingData, k);

            System.out.println("Accuracy: " + decimalFormat.format(accuracy * 100) + "%");

        } else if (input.equals("keyboard")) {
            List<Iris> data = new ArrayList<>();

            boolean continueAdding = true;

            while (continueAdding) {
                System.out.println("Enter sepal length: ");
                double sepalLength = scanner.nextDouble();
                System.out.println("Enter sepal width: ");
                double sepalWidth = scanner.nextDouble();
                System.out.println("Enter petal length: ");
                double petalLength = scanner.nextDouble();
                System.out.println("Enter petal width: ");
                double petalWidth = scanner.nextDouble();

                double[] attributes = {sepalLength, sepalWidth, petalLength, petalWidth};
                Iris iris = new Iris(attributes, null);
                data.add(iris);

                scanner.nextLine();

                System.out.println("Do you want to add another vector?(yes/no)");
                String decision = scanner.nextLine().toLowerCase();
                continueAdding = decision.equals("yes");
            }

            knn.doKNN(data, trainingData, k);
        }
    }
}
