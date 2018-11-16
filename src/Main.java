import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {

    static Random rn = new Random();
    public static void main(String[] args) {

        //random population of size  - 8
        ArrayList<Individual> population = preparePopulation(8);

        // calculate and set the fitness of each individual
        for (int i=0;i<population.size();i++){
            fitness(population.get(i));
        }

        //Sorting the population (criteria : fitness)
        Collections.sort(population, individualFitness);

        //Display each individual of the population and their fitness
        for(Individual indi:population){

            System.out.println("Individual: "+indi.getIndividual()+" Fitness: "+indi.getFitness());
        }



    }


    // Comparator Function - Sorting Individual Objects Fitness Property
    public static Comparator<Individual> individualFitness = new Comparator<Individual>() {

        public int compare(Individual ind1, Individual ind2) {

            float fit1 =  ind1.getFitness();
            float fit2 =  ind2.getFitness();

            //Descending order sorting--
            if (fit1>fit2)
                return -1;
            else if (fit2>fit1)
                return 1;
            else
                return 0;
        }
    };




    public static String makeStringFromArray(ArrayList<Integer> arrInvestmentDecision){
        return "";
    }

    public static ArrayList<Individual> preparePopulation(int populationSize){

        ArrayList<Individual> arrPopulation = new ArrayList<Individual>();
        ArrayList<Integer> arrInvestment = new ArrayList<>();

        while (arrPopulation.size()<populationSize){
            arrInvestment = prepareInvestmentArray();
            if (areAllConstraintSatisfied(arrInvestment)){
                arrPopulation.add(new Individual(arrInvestment));
            }
        }
        return arrPopulation;
    }

    public static ArrayList<Integer> prepareInvestmentArray(){
        ArrayList<Integer> arrInvestment = new ArrayList<>();

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

    //Fitness Function
    public static void fitness(Individual individual) {
        ArrayList<Integer> parent = individual.getIndividual();
        float fitness =  (float) (0.2 * parent.get(0)
                + 0.3 * parent.get(1)
                + 0.5 * parent.get(2)
                + 0.1 * parent.get(3));
        individual.setFitness(fitness);
    }





}
