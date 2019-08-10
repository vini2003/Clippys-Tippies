package clippys_tippies;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TipHandler {
    List<String> tipList = new ArrayList();
    File tipFile;
    Scanner tipScanner;
    Logger logger = LogManager.getLogger();

    public TipHandler() {
        readFile();
        processFile();
    }

    public void readFile() {
        try {
            tipFile = FabricLoader.getInstance().getConfigDirectory().toPath().resolve("simple_tips.txt").toFile();

            if (!tipFile.exists()) {
                tipFile.createNewFile();
            }
        } catch(IOException exception) {
            logger.log(Level.WARN, "[Simple Tips] Failed to load config file %s: %s", "simple_tips.txt", exception);
        }
    }

    public void processFile() {
        try {
            tipScanner = new Scanner(tipFile);
        } catch (FileNotFoundException exception) {
            logger.log(Level.WARN, "[Simple Tips] Failed to scan config file %s: %s", "simple_tips.txt", exception);
        }
        while (tipScanner.hasNextLine()) {
            String line = tipScanner.nextLine();
            tipList.add(line);
        }
    }

    public String getRandomTip() {
        if (tipList.isEmpty()) {
            logger.log(Level.WARN, "[Simple Tips] Failed to display tip: no tips found!");
            return "You're meant to add tips to simple_tips.txt!";
        }
        else {
            Random generator = new Random();
            int random = generator.nextInt(tipList.size());
            return (String)tipList.get(random);
        }
    }
}