import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {

    static Random rn = new Random();
    static int populationSize=6;
    static double crossOverProb=0.75;
    static double mutationProb=0.01;
    
    
    public static void main(String[] args) {
        
       ArrayList<Individual> population = preparePopulation(populationSize);

        // calculate and set the fitness of each individual
        for (int i=0;i<population.size();i++){
            setFitness(population.get(i));
        }

        //Sorting the population (criteria : fitness)
         Collections.sort(population, individualFitness);

       //Display each individual of the population and their fitness
        for(Individual indi:population){
            System.out.println("Individual: "+indi.getIndividual()+" Fitness: "+indi.getFitness());
        }
        
        crossOverGeneration(population);
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
        
    	String lastestIndividualToString = "";
        for(int i=0;i<arrInvestmentDecision.size();i++){
        	lastestIndividualToString+=arrInvestmentDecision.get(i);
        }
        return lastestIndividualToString;
     
    }

    public static ArrayList<Individual> preparePopulation(int populationSize){

        ArrayList<Individual> arrPopulation = new ArrayList<Individual>();
        ArrayList<Integer> arrInvestment = new ArrayList<>();

        while (arrPopulation.size()<populationSize){
            arrInvestment = prepareInvestmentArray();
            if (areAllConstraintSatisfied(arrPopulation,arrInvestment)){
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

    public static boolean areAllConstraintSatisfied(ArrayList<Individual> arrPopulation,ArrayList<Integer> arrInvestmentDecision){
        if (isConstraintForYearOneSatisfied(arrInvestmentDecision)&&
            isConstraintForYearTwoSatisfied(arrInvestmentDecision)&&
            isConstraintForYearThreeSatisfied(arrInvestmentDecision)&&
            !isExistingInPopulation(arrPopulation,arrInvestmentDecision)){
            return true;
        }
        return false;
    }

    public static boolean isExistingInPopulation(ArrayList<Individual> arrPopulation,ArrayList<Integer> arrInvestmentDecision){   
    	Boolean investmentExists=false;         //false - not existing and true - exist
    	String latestInvestment=makeStringFromArray(arrInvestmentDecision);
    	for(Individual ind:arrPopulation){
    		if(ind.toString().equals(latestInvestment))
    		{
       			investmentExists=true;
    			break;
    		}
    			
    	}
        return investmentExists;
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
    public static void setFitness(Individual individual) {
        ArrayList<Integer> parent = individual.getIndividual();
        float fitness =  (float) (0.2 * parent.get(0)
                + 0.3 * parent.get(1)
                + 0.5 * parent.get(2)
                + 0.1 * parent.get(3));
        individual.setFitness(fitness);
    }

    
    //CrossOver Function
    public static void crossOverGeneration(ArrayList<Individual> arrPopulation){
    	
    	//1. fetch the top fittest 
    	//int parent1Index=0;
    	//int parent2Index=1;
    	
    	//System.out.println(arrPopulation.get(parent1Index)+" "+arrPopulation.get(parent2Index));
    	for (int parent1Index=0;parent1Index<arrPopulation.size();parent1Index++){
    		 
    		//for(int parent2Index)
    		
    	}
    	
    }



}
