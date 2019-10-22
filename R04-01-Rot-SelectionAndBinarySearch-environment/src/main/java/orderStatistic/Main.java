package orderStatistic;

public class Main {

    public static void main(String[] args){
        Integer[] array1 = {1, 12, 5, 8, 2, 3};

        OrderStatisticsSelectionImpl<Integer> osi = new OrderStatisticsSelectionImpl<>();

        System.out.println(osi.getOrderStatistics(array1, -1));
    }

}
