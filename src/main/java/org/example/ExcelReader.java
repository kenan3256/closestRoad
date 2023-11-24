package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class ExcelReader {
    public static void run() {
        List<Node> nodes = NodeReader.readNodesFromExcel("C:\\Users\\kenan\\OneDrive\\Desktop\\kenan.xlsx");

        if (nodes == null || nodes.isEmpty()) {
            System.out.println("No nodes found. Exiting...");
            return;
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Salam Programa xoş gəlmisiniz zəhmət olmasa seçiminizi edin (misal: 1 və ya 2)");
            System.out.println("(1)-Başlanğıc nöqtədən bütün nöqtələrə yolu yoxlayacaq");
            System.out.println("(2)-Başlanğıc nöqtədən daxil etdiyiniz nöqtə arasında yolu yoxlayacaq");
            System.out.println("=> ");
            int number = sc.nextInt();

            switch (number) {
                case 1:
                    processMenuOption1(nodes, sc);
                    break;

                case 2:
                    processMenuOption2(nodes, sc);
                    break;

                default:
                    System.out.println("Səhv menyu seçimi etmisiz Programi yenidən işə salın...");
            }
        } catch (InputMismatchException e) {
            System.out.println("Səhv nömrə daxil etmisiniz zəhmət olmasa düzgün nömrəni daxil edəsiz");
        }
    }

    private static void processMenuOption1(List<Node> nodes, Scanner sc) {
        System.out.print("Başlanğıc nöqtəni seçin: ");
        int start = sc.nextInt();

        if (isValidStartPoint(start, nodes)) {
            GraphCalculator.calculateShortestPaths(nodes.get(start - 1));
            GraphPrinter.printPath(nodes);
        } else {
            System.out.println("Daxil etdiyiniz nöqte yoxdur mövcud deyil Programi yenidən işə salaraq  düzgün nöqtəni daxil edin.");
        }
    }

    private static void processMenuOption2(List<Node> nodes, Scanner sc) {
        System.out.print("Başlanğıc nöqtəni daxil edin: ");
        int start = sc.nextInt();
        System.out.print("Son nöqtəni daxil edin: ");
        int end = sc.nextInt();

        GraphCalculator.calculateShortestPaths(nodes.get(start - 1));
        GraphPrinter.printPath(nodes, end - 1);
    }

    private static boolean isValidStartPoint(int start, List<Node> nodes) {
        return start >= 1 && start <= nodes.size();
    }
}
