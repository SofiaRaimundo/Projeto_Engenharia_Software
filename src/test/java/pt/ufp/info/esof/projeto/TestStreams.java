package pt.ufp.info.esof.projeto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestStreams {
    @Test
    public void testParallelism(){
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13);
        for(int i=0;i<10;i++){
            list.forEach(System.out::print);
            System.out.println();
        }
        System.out.println();
        System.out.println();
        for(int i=0;i<10;i++){
            list.parallelStream().forEach(System.out::print);
            System.out.println();
        }

        list.stream().filter(integer -> integer%2==0).map(integer -> integer*10).forEach(System.out::println);
    }
}