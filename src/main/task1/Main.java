package main.task1;

import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    /**
     * Напишите программу, которая вычисляет и выводит в stdout сумму цифр всех чисел массива,
     * которые находятся в диапазоне между средним значением и 90 перцентилем. Массив чисел
     * считывается из файла, путь к которому передается в виде аргумента командной строки (не stdin).
     * Формат файла: каждое число (int_64) заканчивается символом новой строки.
     * Примечание: конечно, задание подразумевает реализацию методов сортировки массива, поиска
     * среднего значения и перцентиля.
     */
    // percentile 90% = 90/100*количество элементов в выборке
    private static int percentile = 90;
    private static int percentileCount;
    private static long percentileValue;
    private static long average;

    public static void main(String[] args) {

        LinkedList<Long> list = readFile(args[0]);
        ArrayList<Long> array = sortArray(list);
        average = getAverage(array);
         // узнаем какой элемент является 90% персентелем
        percentileCount = percentile * array.size()/100;
        percentileValue = array.get(percentileCount);

        System.out.println("Result == " + sumAvgPecentil(array, percentileCount));
    }

    /**
     *
     * @param array
     * @param percentile
     * @return  сумма в которую не входят среднее значение и проценталь.( я так понял)
     */
    private static BigInteger sumAvgPecentil(ArrayList<Long> array, int percentile){
        BigInteger sum = new BigInteger("0");
        for (int i = 0; i < percentile-1; i++) {
            if (array.get(i) > average ){
                sum = sum.add(BigInteger.valueOf(array.get(i)));
            }
        }
        return sum;
    }

    private static long getAverage(ArrayList<Long> array){
        BigInteger sum = new BigInteger("0");
        BigInteger sizeArray = new BigInteger("" + array.size());
        for (int i = 0; i < array.size(); i++) {
            sum = sum.add(BigInteger.valueOf(array.get(i)));
        }
        return sum.divide(sizeArray).longValue();
    }

    private static ArrayList<Long> sortArray(LinkedList<Long> longList) {
        ArrayList<Long> array = new ArrayList<Long>(longList);
        Collections.sort(array);
        return array;
    }

    private static LinkedList<Long> readFile(String nameFile) {
        LinkedList<Long> longList = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nameFile));
            while (reader.ready()){
                longList.add(Long.valueOf(reader.readLine()));
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return longList;
    }
}
