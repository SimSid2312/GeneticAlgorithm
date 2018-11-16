import java.util.ArrayList;

public class Individual {

    ArrayList<Integer> individual;
    float fitness;

    public Individual(ArrayList<Integer> individual){
        this.individual=individual;

    }


    public ArrayList<Integer> getIndividual(){

        return individual;
    }

    public void setFitness(float fitness){
        this.fitness = fitness;
    }

    public float getFitness(){

        return fitness;
    }

}
