
package cz.muni.fi.pb162.lineage;

import java.io.File;
import java.io.IOException;

/**
 *
 *
 * @author Ales  Zlamal
 */
public class Demo {
    
    public static void main(String[] args) throws LineageException, IOException {
        Lineage hab = new Lineage("Habsburkove");
        
        hab.read(new File("Habsburkove.txt"));
        
        System.out.println("Rudolf II.: " + hab.getAristocrat("Rudolf II."));
        System.out.println("Nobody: " + hab.getAristocrat("Nobody"));
        System.out.println("First ancestor: " + hab.firstAncestor());
        System.out.println();
        
        hab.writeFamilyTree(System.out);
    }

}
