import java.io.IOException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws IOException {
        DistrictDirectory da = new DistrictDirectory();
        System.out.println(da.posToDistrict(40.726996565757744, -73.91875952199175));

        BigDistrict bg = new BigDistrict();
        ArrayList<District> dist = bg.getDistrictOrderedByCrime();
        System.out.println("ahh");
        System.out.println(dist.get(0).getDistrcitno() + ", " + dist.get(0).getSpendingRate() + "," + dist.get(0).crimeNumber);


    }
}
