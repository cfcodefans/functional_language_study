package cf.study.scala.collections;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by fan on 2017/2/21.
 */
public class JavaCollectionPrefTests {
    private static Logger log = LogManager.getLogger(JavaCollectionPrefTests.class);

    @Test
    public void testListAdd() {
        StopWatch sw = new StopWatch();
        for (int t = 0; t < 10; t++) {
            sw.start();
            List<Integer> list = new LinkedList<Integer>();
            for (int i = 0; i < 500000; i++) list.add(i);
            log.info("LinkedList.add(500000)\t" + sw.getTime());
            sw.stop();
            sw.reset();
        }

        log.info("");

        for (int t = 0; t < 10; t++) {
            sw.start();
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < 500000; i++) list.add(i);
            log.info("ArrayList.add(500000)\t" + sw.getTime());
            sw.stop();
            sw.reset();
        }
    }
}
