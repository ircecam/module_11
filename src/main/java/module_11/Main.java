package module_11;

import javax.print.DocFlavor;
import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String [] names = new String[] {"Adam","Gregor","Bob","James","Harry","Sid","Zed","Dilan"};
        String [] numbers = new String[] {"1, 2, 0","4, 5","66, 14, 3"};

        long seed = 1l;
        long a = 25214903917l;
        long c = 11l;
        long m = (long) Math.pow(2,48);
        //Ex1
        System.out.println(getOddNames(names));
        //Ex2
        System.out.println(getReversedNames(names));
        //Ex3
        System.out.println(getIntegers(numbers));
        //Ex4
        System.out.println(iterateStream(seed, a, c, m).toList());
        //Ex5
        System.out.println(zip(Stream.of(1, 2, 3, 4), Stream.of(5, 6, 7, 8)).toList());

    }
    public static List <String> getOddNames(String [] namesArr){
        List <String> names = Arrays.asList(namesArr);
        Stream<String> namesStream = names.stream().filter(name -> names.indexOf(name) % 2 == 0).map(name ->  names.indexOf(name) + 1  + ". " + name);

        List<String> filteredNames = namesStream.collect(Collectors.toList());
        return filteredNames;
    }
    public static List <String> getReversedNames(String [] namesArr){
        List <String> names = Arrays.asList(namesArr);
        Stream<String> namesStream = names.stream().sorted(Comparator.reverseOrder()).map(name ->  name.toUpperCase());

        List<String> filteredNames = namesStream.collect(Collectors.toList());
        return filteredNames;
    }
    public static String getIntegers(String [] numbersArr){
        List <String> numbers = Arrays.asList(numbersArr);
        Stream<String> numbersStream = numbers.stream()
                                        .map(str -> List.of(str.split(", ")))
                                        .flatMap(Collection::stream);

        List<Integer> integers = numbersStream.map(str -> Integer.valueOf(str)).collect(Collectors.toList());
        integers.sort(Comparator.naturalOrder());

        return integers.stream().map(it -> Integer.toString(it)).collect(Collectors.joining(", "));
    }

    public static Stream<Long> iterateStream(long seed, long a, long c, long m){
        return Stream.iterate(seed, x -> (x * a + c)%m).limit(10);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        List<T> res = new ArrayList<>();
        Iterator<T> iterator1 = first.iterator();
        Iterator<T> iterator2 = second.iterator();
        while (iterator1.hasNext() && iterator2.hasNext()){
            res.add(iterator1.next());
            res.add(iterator2.next());
        }

        return res.stream();
    }




}
