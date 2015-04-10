/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package circuitsimulator;

/**
 *
 * @author Hassan
 */
import java.io.File;
import javax.swing.filechooser.*;

public class OpenFileFilter extends FileFilter {
    String fileExt = ".txt";

    public OpenFileFilter(String extension) {
        fileExt = extension;
    }

    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return  (f.getName().toLowerCase().endsWith(fileExt)); 
    }

    public String getDescription() {
        return fileExt+" Files Only";
    }
}
