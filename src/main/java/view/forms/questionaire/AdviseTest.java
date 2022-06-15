package view.forms.questionaire;

import java.util.Objects;

/**
 * Class to decide whether to advise a RAT or PCR
 */
public class AdviseTest {
    private String[] answers;
    private String RATadvice = "Based on your answers, we recommend you get a RAT";
    private String PCRadvice = "Based on your answers, we recommend you get a PCR";

    public AdviseTest(String[] answers) {
        this.answers = answers;
    }

    /**
     * Check questions patient answered yes to, to decide test to advise
     * @return Test recommended
     */
     public String adviseTest(){
        int count = 0;
        if (Integer.parseInt(answers[0]) > 35){ // Check if patient is older than 35 years of age
            count += 1; // Consider PCR if patient is risky
        }

        for(int i=1; i<answers.length; i++){ // Iterate through Y/N answers
            if(Objects.equals(answers[i], "Y")){  // check if answered yes
                count+=1;
            }
        }
        if(count >=1){ // Recommend PCR if patient is risky, given answered yes to one or more questions
            return PCRadvice;
        }else{
            return RATadvice;
        }
    }
}
