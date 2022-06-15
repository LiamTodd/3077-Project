package view.forms.questionaire;

import view.forms.FormView;

import java.util.Scanner;
/**
 * Class to represent interview form used to decide upon test to recommend
 */
public class InterviewFormView extends FormView {
    private String[] interviewAnswers;
    private String testAdvised;
    private String symptonsOne = '\u2022' + "Breathlessness at rest and/or unable to speak in sentences \n " +
            '\u2022' + "Unconscious, faint or drowsy \n " +
            '\u2022' + "Skin turning pale or blue \n" +
            '\u2022' + "Cold and clammy or pale and mottled skin" +
            '\u2022' + "Pain or pressure in the chest lasting more than 10 minutes\n" +
            '\u2022' + "Confusion\n" +
            '\u2022' + "Passing no urine ('wee') or a lot less urine than usual\n" +
            '\u2022' + "Coughing up blood\n\n";
    private String symptonsTwo = '\u2022' + "Shortness of breath whilst walking around, such as noticeably having to breathe more heavily whilst walking around the house\n" +
            '\u2022' + "Persistent fever above 38oC and not responding to treatment\n" +
            '\u2022' + "Persistent and worsening cough regularly producing mucus\n" +
            '\u2022' + "Struggling to get out of bed, and feeling dizzy or weak\n\n";
    private String symptonsThree = '\u2022' + "Mild upper respiratory tract symptoms such as a congested or runny nose, sneezing, or a scratchy or sore throat\n" +
            '\u2022' + "Cough\n" +
            '\u2022' + "New aches and pains, or lethargy or weakness, without shortness of breath\n" +
            '\u2022' + "Mild headache\n" +
            '\u2022' + "Mild fever that responds to treatment\n" +
            '\u2022' + "Loss of smell or taste\n" +
            '\u2022' + "Loss of appetite\n" +
            '\u2022' + "Nausea\n" +
            '\u2022' + "Occasional vomiting or diarrhoea\n" +
            '\u2022' + "No symptoms at all\n\n";
    private String riskFactors = '\u2022' + "Aged 65 and over (if Aboriginal and Torres Strait Islander, 50 and over)\n" +
            '\u2022' + "Unvaccinated or partially vaccinated\n" +
            '\u2022' + "Pregnant\n" +
            '\u2022' + "Rural or remote, or not close to medical care\n" +
            '\u2022' + "Safety concerns or the person's ability to manage COVID-19 at home\n\n";
    private String conditions = '\u2022' + "Diabetes\n" +
            '\u2022' + "Respiratory disease (including asthma, COPD and bronchiectasis)\n" +
            '\u2022' + "Cardiovascular disease (including high blood pressure)\n" +
            '\u2022' + "Obesity or is significantly overweight\n" +
            '\u2022' + "Renal failure\n" +
            '\u2022' + "Immunocompromising conditions (see below)\n\n";

    public InterviewFormView(Scanner scanner) {
        super(scanner);
        interviewAnswers = new String[6];
    }

    public String getTestAdvised() {
        return testAdvised;
    }
    /**
     * Asks relevant questionaire and stores answers
     */
    @Override
    public void printForm() throws Exception {
        System.out.println("What is the patient's age:");
        interviewAnswers[0] = scanner.nextLine();

        System.out.println("Do you, or the person with symptoms, have any of the following symptoms? (Y/N)\n" + symptonsOne);
        interviewAnswers[1] = scanner.nextLine();

        System.out.println("Do you, or the person with symptoms, have any of the following symptoms? (Y/N)\n" + symptonsTwo);
        interviewAnswers[2] = scanner.nextLine();

        System.out.println("Do you, or the person with symptoms, have any of the following symptoms? (Y/N)\n" + symptonsThree);
        interviewAnswers[3] = scanner.nextLine();

        System.out.println("Do any of the following risk factors apply to you or the person with symptoms? (Y/N)\n" + riskFactors);
        interviewAnswers[4] = scanner.nextLine();

        System.out.println("Do you, or the person with symptoms, have any of the following conditions? (Y/N)\n" + conditions);
        interviewAnswers[5] = scanner.nextLine();

        testAdvised = new AdviseTest(interviewAnswers).adviseTest(); // Decide which test to advise based on answers
        System.out.println(testAdvised);
    }


}
