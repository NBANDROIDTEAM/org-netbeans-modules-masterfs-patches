/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.arsi.netbeans.masterfs.patches.netbeanskiller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arsi
 */
public class NetbeansKiller {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //    System.setProperty("java.io.tmpdir", "/media/ramdisk/tmp");
        for (int i = 0; i < 10; i++) {
            Path sourceFile = null;
            Path linkFile = null;
            try {
                sourceFile = Files.createTempFile("symlink", "test");
                linkFile = Files.createTempFile("symlink", "test_link");
                System.out.println(linkFile);
                Files.delete(linkFile);
                Files.createSymbolicLink(linkFile, sourceFile);
                Files.delete(sourceFile);
            } catch (InternalError e) {
            } catch (IOException e) {
            } catch (UnsupportedOperationException e) {
            }
        }
        while (true) {
            Path sourceFile = null;
            Path linkFile = null;
            try {
                sourceFile = Files.createTempFile("symlink", "test");
                linkFile = Files.createTempFile("symlink", "test_link");
                System.out.println(linkFile);
                Files.delete(linkFile);
                Files.createSymbolicLink(linkFile, sourceFile);
            } catch (InternalError e) {
            } catch (IOException e) {
            } catch (UnsupportedOperationException e) {
            } finally {
                try {
                    if (sourceFile != null && sourceFile.toFile().exists()) {
                        Files.delete(sourceFile);
                    }
                    if (linkFile != null) {
                        Files.deleteIfExists(linkFile);
                    }
                } catch (IOException e) {
                    // We don't really need to handle this.
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(NetbeansKiller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
