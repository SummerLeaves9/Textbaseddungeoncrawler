public class Room {
    /**
     * chance that this room contains an enemy
     */
    public double enemyChance = .45;
    /**
     * If an enemy is in the room, this is how it will be accessed.
     */
    public Enemy numberOne;
    /**
     * All-purpose constructor
     */
    public Room() {
        double enemyIndicator = Math.random();
        if (enemyChance < enemyIndicator) {
            numberOne = new Enemy();
        }
    }

}
