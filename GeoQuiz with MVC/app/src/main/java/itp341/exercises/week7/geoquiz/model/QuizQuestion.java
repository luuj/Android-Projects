package itp341.exercises.week7.geoquiz.model;


public class QuizQuestion {
    private int quizquestion;
    private int answer;

    public QuizQuestion(int quizquestion, int answer) {
        this.quizquestion = quizquestion;
        this.answer = answer;
    }

    public int getQuizquestion() {
        return quizquestion;
    }

    public int getAnswer() {
        return answer;
    }

    public void setQuizquestion(int quizquestion) {
        this.quizquestion = quizquestion;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "quizquestion=" + quizquestion +
                ", answer=" + answer +
                '}';
    }
}
