package club.annt.paoi.ejiii;

import java.util.*;

public class Res {
    public static Map<String, List<List<Email>>> distribuirEmails(
            final Queue<Email> emails, final Map<String, Integer> filtroSpam) {
        final Map<String, List<List<Email>>> map = new HashMap<>();

        while (!emails.isEmpty()) {
            final Email em = emails.poll();
            final boolean spamContainsRem =
                    filtroSpam.containsKey(em.getRemitente());

            if (!map.containsKey(em.getDestinatario())) {
                /* ArrayList === bandeja de entrada (que tiene inbox y spam) */
                final List<List<Email>> bandeja = new ArrayList<>();
                final List<Email> inboxLL =
                        new LinkedList<>(new PriorityQueue<>(Comparator.comparingInt(
                                Email::getImportancia)));
                final List<Email> spamLL =
                        new LinkedList<>(new PriorityQueue<>(Comparator.comparingInt(
                                (Email e) -> filtroSpam.get(e.getRemitente()))));
                bandeja.add(inboxLL);
                bandeja.add(spamLL);

                if (spamContainsRem) {
                    spamLL.add(em);
                } else {
                    inboxLL.add(em);
                }

                map.put(em.getDestinatario(), bandeja);

            } else {
                if (spamContainsRem) {
                    /* el segundo LL del AL es la carpeta spam */
                    map.get(em.getDestinatario()).get(1).add(em);
                } else {
                    /* el primer LL del AL es la carpeta inbox */
                    map.get(em.getDestinatario()).get(0).add(em);
                }
            }
        }

        return map;
    }
}
