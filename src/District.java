public class District {
        double crimeNumber = 0;
        double spendingRate = 0;
        int distrcitno = -1;
        public District(double spendingRate, int distrcitno){
            this.spendingRate = spendingRate;
            this.distrcitno = distrcitno;
        }
        public double getCrimeRate (){
            return crimeNumber;
        }
        public void addCrime(){
            crimeNumber++;
        }
        public double getSpendingRate (){
            return spendingRate;
        }
        public double setCrimeRate(double x){
            crimeNumber = x;
            return spendingRate;
        }
        public double setSpendingRate(double x){
            spendingRate = x;
            return spendingRate;
        }
        public String toString(){
            String joe =  String.valueOf(crimeNumber) + String.valueOf(spendingRate);
            return joe;
        }
        public int getDistrcitno() {
            return distrcitno;
        }
}

