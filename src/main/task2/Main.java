package main.task2;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    /**
     * Напишите программу, которая ответит на вопрос: подобны ли данные треугольники?
     * Координаты считываются из файла, путь к которому передается в качестве аргумента командной
     * строки. Формат файла:
     * {triangle1: {A: [0, 0, 0], B: [0, 5, 0], C: [5, 5, 0]}, triangle2: {A: [0, 0, 0], C: [0, 0, 5], B: [5, 0, 3]}}
     * Примечание: координата точки – массив [x, y, z].
     */

    public static void main(String[] args) {
        //String str = "{triangle1: {A: [0, 0, 0], B: [0, 5, 0], C: [5, 5, 0]}, triangle2: {A: [0, 0, 0], C: [0, 0, 5], B: [5, 0, 5]}}";

        ArrayList<Triangle> triangles = parseTriangle(args[0]);
        boolean similar = equalsTriangle(triangles.get(0), triangles.get(1));
        System.out.println(similar);
    }
    private static boolean equalsTriangle(Triangle a,Triangle b){
        int aAB = Math.abs((a.getX1() - a.getX2())*(a.getX1() - a.getX2()) + (a.getY1() - a.getY2())*(a.getY1() - a.getY2()) + (a.getZ1() - a.getZ2())*(a.getZ1() - a.getZ2()));
        int aAC = Math.abs((a.getX1() - a.getX3())*(a.getX1() - a.getX3()) + (a.getY1() - a.getY3())*(a.getY1() - a.getY3()) + (a.getZ1() - a.getZ3())*(a.getZ1() - a.getZ3()));
        int aBC = Math.abs((a.getX2() - a.getX3())*(a.getX2() - a.getX3()) + (a.getY2() - a.getY3())*(a.getY2() - a.getY3()) + (a.getZ2() - a.getZ3())*(a.getZ2() - a.getZ3()));
        int bAB = Math.abs((b.getX1() - b.getX2())*(b.getX1() - b.getX2()) + (b.getY1() - b.getY2())*(b.getY1() - b.getY2()) + (b.getZ1() - b.getZ2())*(b.getZ1() - b.getZ2()));
        int bAC = Math.abs((b.getX1() - b.getX3())*(b.getX1() - b.getX3()) + (b.getY1() - b.getY3())*(b.getY1() - b.getY3()) + (b.getZ1() - b.getZ3())*(b.getZ1() - b.getZ3()));
        int bBC = Math.abs((b.getX2() - b.getX3())*(b.getX2() - b.getX3()) + (b.getY2() - b.getY3())*(b.getY2() - b.getY3()) + (b.getZ2() - b.getZ3())*(b.getZ2() - b.getZ3()));
        if (aAB == bAB ){
            if ((aAC == bAC && aBC == bBC) || (aAC == bBC && aBC == bAC)){
                return true;
            }
        }else if (aAB == bAC){
            if ((aAC == bAB && aBC == bBC) || (aAC == bBC && aBC == bAB)){
                  return true;
            }
        }else if (aAB == bBC){
            if ((aAC == bAB && aBC == bAC) || (aAC == bAC && aBC == bAB)){
                return true;
            }
        }
        return false;
    }

    private static ArrayList<Triangle> parseTriangle(String param){
        String regexp = ".\\: \\[(\\d+)\\, (\\d+), (\\d+)\\]\\, .\\: \\[(\\d+)\\, (\\d+), (\\d+)\\], .\\: \\[(\\d+)\\, (\\d+), (\\d+)\\]";

        ArrayList<Triangle> arrayList = new ArrayList<>(2);

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(param);
        while (matcher.find()){
            arrayList.add(new Triangle(
                    Integer.valueOf(matcher.group(1)),
                    Integer.valueOf(matcher.group(2)),
                    Integer.valueOf(matcher.group(3)),
                    Integer.valueOf(matcher.group(4)),
                    Integer.valueOf(matcher.group(5)),
                    Integer.valueOf(matcher.group(6)),
                    Integer.valueOf(matcher.group(7)),
                    Integer.valueOf(matcher.group(8)),
                    Integer.valueOf(matcher.group(9))  ));
        }
        return arrayList;
    }
}
