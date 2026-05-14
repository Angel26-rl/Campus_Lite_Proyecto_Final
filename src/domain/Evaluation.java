package domain;

public abstract class Evaluation {

    protected String evaluationName;
    protected double score;

    public Evaluation(String evaluationName, double score) {

        this.evaluationName = evaluationName;
        this.score = score;
    }

    public String getEvaluationName() {
        return evaluationName;
    }

    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    // Método abstracto
    public abstract double calculateContribution();
    
}

