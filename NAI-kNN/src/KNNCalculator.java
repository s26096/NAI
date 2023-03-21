import java.util.*;

public class KNNCalculator {
    private int k;
    private List<List<String>> train;

    public KNNCalculator(int k, List<List<String>> train){
        this.k = k;
        this.train = train;
    }

    public String calcKNN(List<String> testRow){
        HashMap<String, Integer> sortNames = new HashMap<>();
        for (List<String> rows : train){
            sortNames.put(rows.get(rows.size()-1), 0);
        }
        //ArrayList<String> names = new ArrayList<>(sortNames.keySet());

        HashMap<String, Integer> counter = new HashMap<>();
        for (String s : sortNames.keySet()){
            counter.put(s, 0);
        }


        HashMap<Integer, Double> map  = new HashMap<Integer, Double>();
        int j=0;
        for (List<String> trainRows : train){
            double[] trainVals = new double[trainRows.size()-1];
            for (int i=0; i<trainRows.size()-1; i++){
                trainVals[i] = Double.parseDouble(trainRows.get(i));
            }
            double x = 0;
            for (int i=0; i<trainVals.length; i++){
                x += Math.pow(Double.parseDouble(testRow.get(i))-trainVals[i], 2);
            }

            System.out.println(Math.sqrt(x));

            map.put(j, Math.sqrt(x));
            j++;

        }

        ArrayList<Double> list = new ArrayList<>();
        for (Map.Entry<Integer, Double> mapRow : map.entrySet()){
            list.add(mapRow.getValue());
        }
        Collections.sort(list);

        for (int i=0; i<k; i++){
            for (Map.Entry<Integer, Double> mapRow : map.entrySet()) {
                if (mapRow.getValue().equals(list.get(i))) {
                    String name = train.get(mapRow.getKey()).get(train.get(mapRow.getKey()).size()-1);
                    for (Map.Entry<String, Integer> s : counter.entrySet()){
                        if (s.getKey().equals(name)){
                            s.setValue(s.getValue()+1);
                        }
                    }
                }
            }
        }
        String res = "";
        int max = 0;
        for (Map.Entry<String, Integer> s : counter.entrySet()){
            //System.out.println(s.getKey() + "  - " + s.getValue());
            if (s.getValue() > max)
                max = s.getValue();
        }
        for (Map.Entry<String, Integer> s : counter.entrySet()){
            if (s.getValue() == max){
                 res = s.getKey();
                break;
            }
        }


        return res;



    }
}
