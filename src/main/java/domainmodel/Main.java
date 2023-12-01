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
Economy regnestykke - igennem filehandler eller controller?
Må vi kun kalde på en klasse i hver klasse? eller må database godt kende til flere klasser?
Skal hver liste have deres egen filehandler/loaddata? skal de være 2 filehandler klasser eller kan loaddata metoderne være i samme klasse?

-Skal man manuelt skrive gæld ind eller skal det være automatiseret? (Skal være automatiseret)

 */
