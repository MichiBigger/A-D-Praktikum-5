package ch.zhaw.ads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * RankingTreeServer – erstellt aus einer Eingabe­liste (Name;Zeit)
 * einen sortierten Binärbaum und liefert eine formatierte Rangliste.
 */
public class RankingTreeServer implements CommandExecutor {

    /**
     * Liest Zeilen im Format "Name;HH:mm:ss", baut einen SortedBinaryTree von Competitor
     * und gibt ihn zurück.
     */
    public Tree<Competitor> createTree(String rankingText) {
        SortedBinaryTree<Competitor> tree = new SortedBinaryTree<>();
        String[] lines = rankingText.split("\\r?\\n");
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            String[] parts = line.split(";");
            String name = parts[0].trim();
            String time = parts[1].trim();
            // Rang wird später in createSortedText gesetzt
            tree.add(new Competitor(0, name, time));
        }
        return tree;
    }

    /**
     * Traversiert den Baum INORDER (aufsteigende Zeiten), setzt Rang und
     * liefert eine String-Zeile pro Competitor im Format "Rang Name Zeit".
     */
    public String createSortedText(Tree<Competitor> competitorTree) {
        AtomicInteger rank = new AtomicInteger(1);
        StringBuilder sb = new StringBuilder();

        // Überschrift optional weglassen, da Tests nur die Zeilen vergleichen
        competitorTree
                .traversal()
                .inorder(c -> {
                    c.setRank(rank.getAndIncrement());
                    sb.append(c)   // Competitor.toString liefert "rank name time"
                            .append("\n");
                });

        return sb.toString();
    }

    /**
     * Aus CommandExecutor: kombiniert createTree + createSortedText
     */
    @Override
    public String execute(String command) throws Exception {
        Tree<Competitor> tree = createTree(command);
        return "Rangliste (Tree)\n" + createSortedText(tree);
    }

    /**
     * Main zum manuellen Testen der Ausgabe.
     */
    public static void main(String[] args) throws Exception {
        String input =
                "Mueller Stefan;02:31:14\n" +
                        "Marti Adrian;02:30:09\n" +
                        "Kiptum Daniel;02:11:31\n" +
                        "Ancay Tarcis;02:20:02\n" +
                        "Kreibuhl Christian;02:21:47\n" +
                        "Ott Michael;02:33:48\n" +
                        "Menzi Christoph;02:27:26\n" +
                        "Oliver Ruben;02:32:12\n" +
                        "Elmer Beat;02:33:53\n" +
                        "Kuehni Martin;02:33:36\n";

        RankingTreeServer server = new RankingTreeServer();
        System.out.println(server.execute(input));
    }
}
