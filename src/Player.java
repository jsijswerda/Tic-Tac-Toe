public class Player {

    private String name;
    private String mark;
    private Player otherPlayer;
    private Boolean hasTurn;

    public Player (String name, String mark, boolean hasTurn){
        this.name = name;
        this.mark = mark;
        this.hasTurn = hasTurn;
    }

    public Player (String name, Player player, boolean hasTurn){
        this.name = name;
        otherPlayer = player;
        this.hasTurn = hasTurn;
        player2setMark();
    }

    private void player2setMark() {
        if (otherPlayer.getMark().equalsIgnoreCase("X")){
            this.mark = "O";
        }
        else {
            this.mark = "X";
        }
    }


    public String getMark(){
        return mark;
    }

    public String getName(){
        return name;
    }

    void setOtherPlayer(Player player) {
        this.otherPlayer = player;

    }
    public Boolean getHasTurn() {
        return hasTurn;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    public void changeTurn(){
        this.hasTurn = !this.getHasTurn();
        this.getOtherPlayer().hasTurn = !this.getOtherPlayer().getHasTurn();
    }
}
