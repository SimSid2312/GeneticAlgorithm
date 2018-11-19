import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {

    static Random rn = new Random();
    static int populationSize=6;
    static double crossOverProb=0.75;
    static double mutationProb=0.01;
    static ArrayList<Individual> arrPopulation = new ArrayList<>();
    static int sameFitnessCount = 0;
    static float lastMaxFitness = -1;
    static float currentMaxFitness = 0;
    static float lastAverageFitness = -1;
    static float currentAverageFitness = 0;

    public static void main(String[] args) {

        arrPopulation = preparePopulation(populationSize);

        for (int i=0;i<arrPopulation.size();i++){
            setFitness(arrPopulation.get(i));
        }

        Collections.sort(arrPopulation, individualFitness);
        displayMutationCrossoverProbabilities();
        displayCurrentPopulation();
        
        
        while (sameFitnessCount<5){        	
            lastMaxFitness = arrPopulation.get(0).getFitness();
            lastAverageFitness = averageFitness();
            Collections.sort(arrPopulation, individualFitness);// this can be commented
            generationCheck();
            displayMutationCrossoverProbabilities();
            displayCurrentPopulation();
            
            currentAverageFitness = averageFitness();
            currentMaxFitness = arrPopulation.get(0).getFitness();
            if (lastMaxFitness == currentMaxFitness && lastAverageFitness == currentAverageFitness){
                sameFitnessCount++;
            }else{
            	System.out.println("Resetting fit count");
                sameFitnessCount = 0;
            }
        }
        
        System.out.println("\n\n***Final: "+arrPopulation.get(0)+" fitness: "+arrPopulation.get(0).fitness+"***");

    }

    public static float averageFitness(){
        float fitness = 0;

        for (Individual individual : arrPopulation){
            fitness = fitness + individual.getFitness();
        }

        Float averageFitness =  fitness / arrPopulation.size();

        return averageFitness;
    }

    public static void displayMutationCrossoverProbabilities(){
       	System.out.println("Mutation Probability : "+mutationProb+" CrossOver Probability: "+crossOverProb+" Population Size:"+arrPopulation.size());
    }
    
    public static void displayCurrentPopulation(){
    	
    	 for(Individual indi:arrPopulation){
             System.out.println("Individual: "+indi.getIndividual()+" Fitness: "+indi.getFitness());
         }
    }

    public static boolean areAllElementsOfArrayZero(ArrayList<Integer> arrIndividual){
        boolean areElementsSame = arrIndividual.stream().distinct().limit(2).count() <= 1 && arrIndividual.get(0) == 0;
        return areElementsSame;
    }
    
    public static Comparator<Individual> individualFitness = new Comparator<Individual>() {

        public int compare(Individual ind1, Individual ind2) {

            float fit1 =  ind1.getFitness();
            float fit2 =  ind2.getFitness();
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
            //We dont want 0000
            while (areAllElementsOfArrayZero(arrInvestment)){
                arrInvestment = prepareInvestmentArray();
            }

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
    		if(ind.toString().equals(latestInvestment)) {
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


    public static void setFitness(Individual individual) {
        ArrayList<Integer> parent = individual.getIndividual();
        float fitness =  (float) (0.2 * parent.get(0)
                + 0.3 * parent.get(1)
                + 0.5 * parent.get(2)
                + 0.1 * parent.get(3));
        individual.setFitness(fitness);
    }



    public static void generationCheck(){

        float random = rn.nextFloat() * (1 - 0);

        ArrayList<Individual> arrParents = new ArrayList<Individual>();
        ArrayList<Integer> parent1 = new ArrayList<Integer>();
        ArrayList<Integer> parent2 = new ArrayList<Integer>();
        for (int i: arrPopulation.get(0).getIndividual()){
        	parent1.add(i);	
        }
        for (int i:arrPopulation.get(1).getIndividual()){
        	parent2.add(i);
        }

        arrParents.add(new Individual(parent1));
        arrParents.add(new Individual(parent2));
        System.out.println("Selected Parents : "+arrParents);

    	if(random<=crossOverProb){
            doCrossOver(arrParents);
        }else{
            doMutation(arrParents);
        }

    }

    public static void doCrossOver(ArrayList<Individual> arrParents){
        Individual childOne = new Individual(arrParents.get(0).getIndividual());
        Individual childTwo = new Individual(arrParents.get(1).getIndividual());
       
        int random=rn.nextInt(3 - 1 + 1) + 1;
//        System.out.println("random : "+random);
        for (int i = 0 ; i < 4; i++){
        	   if(i<(random)) {
              	 System.out.println("Cross over at index: "+ i+" child one :"+childOne.getIndividual().get(i)+" child two: "+childTwo.getIndividual().get(i));
              	 int childOneBit=childOne.getIndividual().get(i);
              	 childOne.getIndividual().set(i,childTwo.getIndividual().get(i)); 
              	 childTwo.getIndividual().set(i,childOneBit);
        	   }
        }
        
        System.out.println("Child One " +childOne);
        System.out.println("Child Two " +childTwo);
//        System.out.println("Current Population :"+arrPopulation);
        manipulateChildCreation(childOne,0);
        manipulateChildCreation(childTwo,1);
    }

    public static void doMutation(ArrayList<Individual> arrParents){
        for(Individual ind:arrParents){
            for(int i=0;i<ind.getIndividual().size();i++){
                float random = 0 + rn.nextFloat() * (1 - 0);
                if(random <=mutationProb ) {
                	System.out.println("For Individual : "+ ind+" Mutating at index : "+ i+" random num: "+random);
                    if (ind.getIndividual().get(i) == 1)
                        ind.getIndividual().set(i,0);
                    else
                        ind.getIndividual().set(i, 1);
                  }
            }
        }
       // System.out.println("Mutation" +arrParents);
      //  System.out.println("Current Population :"+arrPopulation);
        manipulateChildCreation(arrParents.get(0),0);
        manipulateChildCreation(arrParents.get(1),1);
     //   System.out.println("After constraint check :"+arrPopulation);
    }

    public static void manipulateChildCreation(Individual individual,int index){
    	
        if (areAllConstraintSatisfied(arrPopulation,individual.getIndividual()) && !areAllElementsOfArrayZero(individual.getIndividual())){
            arrPopulation.remove(index);
            setFitness(individual);//cal and set the fitness of the new kid!!
            arrPopulation.add(individual);
            Collections.sort(arrPopulation, individualFitness);
            System.out.println("child "+individual.getIndividual()+" added");
        }
    }

}


