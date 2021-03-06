package main.java.edu.piechart.net;

import norsys.netica.Environ;
import norsys.netica.Net;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Node;

public class PieChartNet {

    public static void main(String[] args) {
        try {
            // deprecated. use env.setNetClass(norsys.neticaEx.aliases.Node);
            Node.setConstructorClass("norsys.neticaEx.aliases.Node");

            // Create a new environment for a net, null = with out a password version.
            Environ env = new Environ(null);

            // Create a new net.
            Net net = new Net();
            net.setName("IntendedMessage");

            // Parent node, (node Name, categories)
            Node intendedMessage = new Node("IntendedMessage", "singleSlice, fraction, versus, "
                    + "biggestSlice, majoritySlice, addSlices, twoTiedForBiggest, noMajority, "
                    + "smallestSlice, closeToHalf, numOfParts", net);

            // (node name, categories)
            Node numberOfSlices = new Node("NumberOfSlices", "two, three, four, fiveOrMore", net);
            Node prominence = new Node("Prominence", "yes, no", net);
            Node similarColors = new Node("SimilarColors", "yes, no", net);
            Node multipleSlices = new Node("MultipleSlices", "yes, no", net);

            // childNode.addLink(parent node)
            numberOfSlices.addLink(intendedMessage);
            prominence.addLink(intendedMessage);
            similarColors.addLink(intendedMessage);
            multipleSlices.addLink(intendedMessage);

            // Set title for Netica GUI.
            intendedMessage.setTitle("Intended Message");
            numberOfSlices.setTitle("Number of slices");
            prominence.setTitle("Prominence");
            similarColors.setTitle("Similar Colors");
            multipleSlices.setTitle("Multiple Slices");

            System.out.println(net.getNodes());

            // Add probabilities.....

            // Write network to a dne file.
            Streamer stream = new Streamer("piechart_bayes_data/NetFiles/pieEvidence.dne");
            net.write(stream);

            System.out.println("execution complete");

            // free resources immediately and safely; not strictly necessary, but a good habit
            net.finalize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
