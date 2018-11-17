import java.util.ArrayList;

public class Individual {

	ArrayList<Integer> individual;
	float fitness;
	
	public Individual(ArrayList<Integer> individual){		
		this.individual=individual;
		
	}
	
	
	public ArrayList<Integer> getIndividual(){
	
		return this.individual;
	}
	
	public void setFitness(float fitness){
		this.fitness = fitness;
	}
	
	public float getFitness(){
		
		return this.fitness;
	}
	
	@Override
	public String toString() {
		String individualToString = "";
        for(int i=0;i<this.individual.size();i++){
        	individualToString+=this.individual.get(i);
        }
        return individualToString;
    } 

}
