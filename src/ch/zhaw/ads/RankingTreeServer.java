package ch.zhaw.ads;

import java.util.concurrent.atomic.AtomicInteger;

public class RankingTreeServer implements CommandExecutor {

    public Tree<Competitor> createTree(String rankingText) {
        // TODO Implement
    }

    public String createSortedText(Tree<Competitor> competitorTree) {
        AtomicInteger rank = new AtomicInteger(1);
        StringBuilder sb = new StringBuilder();
        competitorTree.traversal() // TODO Implement
        return sb.toString();
    }

    public String execute(String rankingList) {
        Tree<Competitor> competitorTree = createTree(rankingList);
        return "Rangliste (Tree)\n" + createSortedText(competitorTree);
    }

    public static void main(String[] args) {
        String rangliste = "Mueller Stefan;02:31:14\n" +
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
        System.out.println(server.execute(rangliste));
    }
}
