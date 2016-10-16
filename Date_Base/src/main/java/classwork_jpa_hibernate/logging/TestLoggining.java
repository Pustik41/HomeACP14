package classwork_jpa_hibernate.logging;


import org.apache.log4j.*;
import org.apache.log4j.xml.XMLLayout;

import java.io.IOException;

public class TestLoggining {

    public static void main(String[] args) throws IOException {

        //create
        Logger parent = Logger.getLogger("classwork_jpa_hibernate");
        parent.setLevel(Level.ERROR);
        parent.addAppender(new ConsoleAppender());

        Logger child1 = Logger.getLogger("classwork_jpa_hibernate.logging");
        child1.setLevel(Level.WARN);
        child1.addAppender(new ConsoleAppender());
        Logger child2 = Logger.getLogger(TestLoggining.class);

        //config
        child2.setLevel(Level.INFO); // level info and up
        child2.addAppender(new FileAppender(new XMLLayout(), "log.xml"));  //write to xml file
        child2.addAppender(new ConsoleAppender(new PatternLayout("%r [%t] %-5p %c %x - %m%n"))); //write to console

        //using

        child2.debug("child2 debug");
        child2.trace("child2 trace");
        child2.info("child2 info");
    }
}
