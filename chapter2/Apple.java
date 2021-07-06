package chapter2;

public class Apple {

    Color color;
    int weight;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple(){}

    public Apple(Integer weight){
        this.weight = weight;
    }
    
    public Apple(Color color, int weight){
        this.color = color;
        this.weight = weight;
    }

    public enum Color {
        RED,
        GREEN;
    }
    
}
