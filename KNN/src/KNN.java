import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KNN {

    public List<Iris> getData(String filePath, boolean isTrainingData) throws IOException {

        List<Iris> data = new ArrayList<>();
        String line;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        while ((line = bufferedReader.readLine()) != null) {
            String[] strVal = line.replace("{", "").replace("}", "").split(",");
            double[] values = new double[strVal.length - 1];

            String flag = isTrainingData ? convertClassFlag(strVal[strVal.length - 1]) : strVal[strVal.length - 1];

            for (int i = 0; i < strVal.length - 1; i++) {
                values[i] = Double.parseDouble(strVal[i]);
            }
            data.add(new Iris(values, flag));
        }

        bufferedReader.close();
        return data;
    }

    public String convertClassFlag(String flag) {
        return switch (flag) {
            case "0" -> "Iris-setosa";
            case "1" -> "Iris-versicolor";
            case "2" -> "Iris-virginica";
            default -> "Unknown";
        };
    }

    public List<Distance> euclideanDistance(List<Iris> trainingData, double[] testedVector){

        double distance = 0;
        List<Distance> distances = new ArrayList<>();

        for(Iris iris: trainingData) {
            for (int i = 0; i < testedVector.length; i++) {
                distance += Math.pow(testedVector[i] - iris.getAtributes()[i], 2);
            }
           distances.add(new Distance(Math.sqrt(distance),iris.getFlag()));
            distance = 0;

        }
    return distances;
    }

    public List<Distance> getKNearest(List<Distance> distances, int k){

        Collections.sort(distances, Comparator.comparingDouble(Distance::getDistance));

        return distances.subList(0, k);
    }
    public String getFlag(List<Distance> distances){

        Map<String, Integer> flagCounter = new HashMap<>();

        for(Distance distance : distances){
            flagCounter.put(distance.getFlag(), flagCounter.getOrDefault(distance.getFlag(), 0)+1);
        }
        String mostCommonFlag = "";
        int max = 0;

        for (Map.Entry<String, Integer> map : flagCounter.entrySet()) {
            if (map.getValue() > max) {
                mostCommonFlag = map.getKey();
                max = map.getValue();
            }
        }

        return mostCommonFlag;

    }
    public double doKNN(List<Iris> testingData, List<Iris> trainingData, int k){

        double accuracyCounter = 0;

        for(Iris iris: testingData){
                List<Distance> distances = euclideanDistance(trainingData, iris.getAtributes());
                List<Distance> nearestNeighbors = getKNearest(distances, k);
                String estimatedSpecies = getFlag(nearestNeighbors);
                String correctAnswer = iris.getFlag();
            System.out.println(iris);
            System.out.println("Predicted species: "+ estimatedSpecies);
            if(correctAnswer!=null)System.out.println("Correct answer: "+ correctAnswer);
            System.out.println();
            if(estimatedSpecies.equals(correctAnswer)){
                ++accuracyCounter;
            }
        }
    return accuracyCounter/testingData.size();}
}