import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class BigDistrict {
    ArrayList<District>dist = new ArrayList<District>();
    ArrayList<String> latitude = new ArrayList<String>();
    ArrayList<String> longitude = new ArrayList<String>();
    DistrictDirectory districtDirectory = new DistrictDirectory();
    double[] spendingArray = {18.20, 17.22, 19.49, 17.68, 18.81, 18.27, 19.58,18.21, 18.67, 17.49,17.76,19.10, 16.70, 17.99, 17.17, 19.70, 17.79, 17.74,17.95, 15.35, 16.68, 16.28, 18.77, 15.58, 15.87, 14.87, 16.30, 15.79, 16.50, 16.45, 17.35, 17.97 };
    int[] DistrictNames = {32,16,7, 28,18,21,10,26,29,5,6,17,23,13,14,9,30,22,15,20,25,3,19,24,1,11,31,12,8,27,2,4,10};
    public BigDistrict() throws IOException {
        HashMap<Integer,District> hash = new HashMap<>();
        getCoordinate();
        //Districts will be ordered by the index
        for(int i = 0; i <32; i++){
            District district = new District(spendingArray[i],DistrictNames[i]);
            hash.put(DistrictNames[i], district );
        }

        int crimeDistricNum = 0;
        for(int i = 0; i <latitude.size(); i++){
            System.out.println(Double.parseDouble(longitude.get(i)));
            crimeDistricNum = Integer.parseInt(districtDirectory.posToDistrict(Double.parseDouble(latitude.get(i)), Double.parseDouble(longitude.get(i))));
            if(crimeDistricNum != -1) {
                hash.get(crimeDistricNum).addCrime();
            }
        }
        for (int i = 0; i < hash.size(); i++) {
            dist = new ArrayList<>(hash.values());
        }
    }

    public void sortCrimeRate(){
        dist.sort(Comparator.comparingDouble(District::getCrimeRate));
    }

    public void sortSpendingPerDistrict(){
        dist.sort(Comparator.comparingDouble(District::getSpendingRate));
    }
    public ArrayList<District> getDistrictOrderedByCrime()
    {
        sortCrimeRate();
        return dist;
    }
    public ArrayList<District> getDistrictOrderedBySpending()
    {
        sortSpendingPerDistrict();
        return dist;
    }


    public void getCoordinate() throws IOException {
        File csvFile = new File("C:\\Users\\adama\\Downloads\\NYPD.csv"); //Switch this out as needed
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String line;
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] cols;
            cols = line.split(",");
            latitude.add(cols[0]);
            longitude.add(cols[1]);

        }
    }
}
