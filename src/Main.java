import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

    }

    public static String makeStringFromArray(ArrayList<Integer> arrInvestmentDecision){
        return "";
    }

    public static ArrayList<ArrayList<Integer>> preparePopulation(){
        ArrayList<ArrayList<Integer>> arrPopulation = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> arrInvestment = new ArrayList<>();
        while (arrPopulation.size()<=20){
            arrInvestment = prepareInvestmentArray();
            if (areAllConstraintSatisfied(arrInvestment)){
                arrPopulation.add(arrInvestment);
            }
        }
        return arrPopulation;
    }

    public static ArrayList<Integer> prepareInvestmentArray(){
        ArrayList<Integer> arrInvestment = new ArrayList<>();
        Random rn = new Random();
        for (int i = 0 ; i < 4; i++){
            arrInvestment.add(Math.abs(rn.nextInt()%2));
        }
        return arrInvestment;
    }

    public static boolean areAllConstraintSatisfied(ArrayList<Integer> arrInvestmentDecision){
        if (isConstraintForYearOneSatisfied(arrInvestmentDecision)&&
                (isConstraintForYearTwoSatisfied(arrInvestmentDecision)&&
                        isConstraintForYearThreeSatisfied(arrInvestmentDecision))){
            return true;
        }
        return false;
    }

    public static boolean isConstraintForYearOneSatisfied(ArrayList<Integer> arrInvestmentDecision){
        if(0.5*arrInvestmentDecision.get(0)
                +1.0*arrInvestmentDecision.get(1)
                +1.5*arrInvestmentDecision.get(2)
                +0.1*arrInvestmentDecision.get(3)<=3.1){
            return true;
        }
        return false;
    }

    public static boolean isConstraintForYearTwoSatisfied(ArrayList<Integer> arrInvestmentDecision){
        if(0.3*arrInvestmentDecision.get(0)
                +0.8*arrInvestmentDecision.get(1)
                +1.5*arrInvestmentDecision.get(2)
                +0.4*arrInvestmentDecision.get(3)<=2.5){
            return true;
        }
        return false;
    }

    public static boolean isConstraintForYearThreeSatisfied(ArrayList<Integer> arrInvestmentDecision){
        if(0.2*arrInvestmentDecision.get(0)
                +0.2*arrInvestmentDecision.get(1)
                +0.3*arrInvestmentDecision.get(2)
                +0.1*arrInvestmentDecision.get(3)<=0.4){
            return true;
        }
        return false;
    }


}
