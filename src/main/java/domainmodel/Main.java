package domainmodel;

import ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.startProgram();
    }
}

/*
Hvordan tildeler man medlemmer svømmeresultater?
Hvordan kan hver medlem have flere resultat-tider pr disciplin - lige meget om det er træning eller stævne
*/

/** TODO
top 5

tilføje flere resultater (skal kunne gemmes i swim_result.csv)
 -Kan vi få addSwimResult og addTraningResult sammen på en måde så der ikke er så mange gentagelser?

oprettelse af members - lidt pænere?
gør kode pænt
*/