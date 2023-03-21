
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        double correctCount = 0;

        int k = Integer.parseInt(args[0]);


        List<List<String>> trainSet = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                trainSet.add(Arrays.asList(values));
            }
        }

        List<List<String>> testSet = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(args[2]))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                testSet.add(Arrays.asList(values));
            }
        }
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("1. Test z pliku.\n2. Wprowadź dane do przetestowania.\n3. Wyjście\n>>> ");
            String stringIn = scanner.next();
            int choice = 0;
            try {
                choice = Integer.parseInt(stringIn);
            }catch (NumberFormatException ignored){
            }

            if (choice == 1){

                while (true){
                    System.out.println("Proszę podać k: ");
                    try {
                        k = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e){
                        System.out.println("Podano nieprawidłową wartość");
                    }
                }
                KNNCalculator knnCalculator = new KNNCalculator(k, trainSet);
                for (int i=0; i<testSet.size(); i++){
                    String guessed = knnCalculator.calcKNN(testSet.get(i));
                    //System.out.println(guessed + " - " + testSet.get(i).get(testSet.get(i).size()-1));
                    if (guessed.equals(testSet.get(i).get(testSet.get(i).size()-1))){
                        correctCount++;
                    }
                }

                System.out.println("Dla k=" + k + ": ");
                System.out.println("Accuracy: " + (correctCount/testSet.size())*100 + "%\n");
                correctCount = 0;

            } else if (choice == 2){
                String[] vals;
                while (true){
                    System.out.println("Podaj dane: (np. 5.1;3.5;1.4;0.2)");
                    String input = scanner.next();
                    vals = input.split(";");
                    try {
                        for (String value : vals){
                            System.out.print(Double.parseDouble(value) + ", ");
                        }
                        break;
                    } catch (NumberFormatException e){
                        System.out.println("Podano nieprawdiłowe dane");
                    }
                }

                List<String> input = new ArrayList<>();
                for (String val : vals){
                    input.add(val);
                }

                KNNCalculator knnCalculator = new KNNCalculator(k, trainSet);
                System.out.println("\n"+knnCalculator.calcKNN(input) + "\n");

            }else if (choice == 3){
                break;
            }
            else {
                System.out.println("Podano nieprawidłową opcję, proszę wybrać ponownie");
            }
        }



    }



}
