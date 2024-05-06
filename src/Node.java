public class Node implements Comparable<Node> {
    private String word;
    private int cost;
    private Node parent; // Reference to the parent node

    public Node(String word, int cost, Node parent) {
        this.word = word;
        this.cost = cost;
        this.parent = parent;
    }

    // Getter and setter methods for word and cost
    public String getWord() {
        return word;
    }

    public int getCost() {
        return cost;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    // Implementing compareTo method to compare nodes based on cost
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

