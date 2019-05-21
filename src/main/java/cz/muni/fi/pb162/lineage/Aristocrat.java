package cz.muni.fi.pb162.lineage;

import java.util.ArrayList;
import java.util.List;

public class Aristocrat implements Comparable<Aristocrat>{

    private String name;
    private int birth = 0;
    private int death = 0;
    private List<Aristocrat> sons = new ArrayList<>();
    private Aristocrat parent = null;

    public Aristocrat(String name) {
        this.name = name;
    }

    public Aristocrat(String name, int birth, int death, String[] sons) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must exist");
        }
        if(birth > death) {
            throw new IllegalArgumentException("Year of birth must be smaller than death");
        }

        this.name = name;
        this.birth = birth;
        this.death = death;
        if(sons != null) {
            for(String s : sons) {
                Aristocrat son = new Aristocrat(s);
                son.parent = this;
                this.sons.add(son);
            }
        }

    }

    public List<Aristocrat> getSonsA() {
        return sons;
    }

    public List<String> getSons() {
        List<String> res = new ArrayList<>();
        for(Aristocrat a : sons) {
            res.add(a.getName());
        }
        return res;
    }

    public void setParent(Aristocrat parent) {
        this.parent = parent;
    }

    public Aristocrat getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(birth);
        res.append('-');
        res.append(death);
        res.append(" ");
        res.append(name);
        return res.toString();
    }

    @Override
    public int compareTo(Aristocrat o) {

        if (this.birth < o.birth) {
            return -1;
        }
        if(this.birth > o.birth) {
            return 1;
        }
        return this.name.compareTo(o.name);

    }
}
