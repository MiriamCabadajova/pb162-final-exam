package cz.muni.fi.pb162.lineage;


import java.io.*;
import java.util.*;

public class Lineage {

    private String nameOfLineage;
    private List<Aristocrat> family = new ArrayList<>();

    public Lineage(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must exist");
        }
        this.nameOfLineage = name;
    }

    public void read(InputStream is) throws IOException, LineageException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        while (br.ready()) {
            String line = br.readLine();
            String[] data = line.split(":");
            if(data.length > 2 || line.isEmpty()) {
                throw new LineageException("wrong type of data");
            }

            String aristocratInfo = data[0];
            String sonsInfo = null;

            if(data.length > 1) {
                sonsInfo = data[1];
            }

            String[] aristocrat = aristocratInfo.split(",");
            if(aristocrat.length != 3) {
                throw new LineageException("wrong type of data - aristocrat");
            }

            int birth = Integer.parseInt(aristocrat[1]);
            int death = Integer.parseInt(aristocrat[2]);

            String[] sons = null;
            if(sonsInfo != null) {
                sons =  sonsInfo.split(",");
            }

            Aristocrat newAristocrat = new Aristocrat(aristocrat[0], birth, death, sons);
            family.add(newAristocrat);

        }
    }

    public void read(File file) throws IOException, LineageException {
        try (InputStream is = new FileInputStream(file)){
            read(is);
        }
    }

    public Aristocrat getAristocrat(String name) {
        for(Aristocrat a : family) {
            if(a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public Aristocrat firstAncestor() {
        List<Aristocrat> elders = new ArrayList<>();
        for(Aristocrat a : family) {

            if(a.getParent() == null) {
                elders.add(a);
            }
        }
        Collections.sort(elders);
        return elders.get(0);

    }

    public void writeFamilyTree(OutputStream os) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        bw.write(nameOfLineage);
        bw.write(":");
        bw.newLine();
        bw.write(writeInfo(firstAncestor(), 0));
        bw.flush();
    }

    public String writeInfo(Aristocrat a, int numOfSpaces) {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < numOfSpaces; i++) {
            str.append(" ");
        }
        str.append(a.toString());
        if(a.getSonsA().size() > 0) {
            str.append("\n");
            for(Aristocrat ar : a.getSonsA()) {
                str.append(writeInfo(ar, numOfSpaces+1));
                str.append("\n");
            }
        }
        return str.toString();
    }
}
