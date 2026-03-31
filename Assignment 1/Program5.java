class Match {
    String team1, team2;

    Match(String t1, String t2) {
        team1 = t1;
        team2 = t2;
    }
}

class CricketMatch extends Match {
    int overs;

    CricketMatch(String t1, String t2, int overs) {
        super(t1, t2);
        this.overs = overs;
    }

    void display() {
        System.out.println(team1 + " vs " + team2);
        System.out.println("Overs: " + overs);
    }
}

 class Main5 {
    public static void main(String[] args) {
        CricketMatch match = new CricketMatch(args[0], args[1], Integer.parseInt(args[2]));
        match.display();
    }
}