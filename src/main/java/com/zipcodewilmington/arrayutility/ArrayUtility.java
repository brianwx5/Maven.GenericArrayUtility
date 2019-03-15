package com.zipcodewilmington.arrayutility;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {

    T[] inputarray;

    public ArrayUtility(T[] inputArray) {
        this.inputarray = inputArray;
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        Stream<T> stream1 = Arrays.stream(inputarray);
        Stream<T> stream2 = Arrays.stream(arrayToMerge);
        Stream<T> mergedStream = Stream.concat(stream1,stream2);
        Long longValue =mergedStream.filter(x -> x == valueToEvaluate).count();
        Integer value = longValue.intValue();
        return value;
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        Stream<T> stream1 = Arrays.stream(inputarray);
        Stream<T> stream2 = Arrays.stream(arrayToMerge);
        Stream<T> mergedStream = Stream.concat(stream1,stream2);
        T value =mergedStream.collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).entrySet().stream().
                max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
        return value;
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        Stream<T> stream = Arrays.stream(inputarray);
        Long longValue = stream.filter(x -> x == valueToEvaluate).count();
        Integer value = longValue.intValue();
        return value;
    }

    public T[] removeValue(T valueToRemove) {
        List<T> arrayToList = Arrays.stream(inputarray).filter(x -> x != valueToRemove).collect(Collectors.toList());
        T[] arrayWithValueRemoved = (T[]) Array.newInstance(inputarray.getClass().getComponentType(),arrayToList.size());
        return arrayToList.toArray(arrayWithValueRemoved);

    }
}
