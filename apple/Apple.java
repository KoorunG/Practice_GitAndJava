package apple;

public class Apple {

    Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Apple(){}
    
    public Apple(Color color, int weight){
        this.color = color;
        this.weight = weight;
    }

    enum Color {
        RED,
        GREEN   
    }
    
}
