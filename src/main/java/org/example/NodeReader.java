package org.example;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class NodeReader {
    public static List<Node> readNodesFromExcel(String filePath) {
        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = new XSSFWorkbook(file);

            List<Node> nodes = new ArrayList<>();
            Map<Integer, Node> nodeMap = new HashMap<>();

            Sheet sheet = workbook.getSheetAt(0);  // Data ilk vereqdedi
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int sourceNodeNum = (int) row.getCell(0).getNumericCellValue();
                int targetNodeNum = (int) row.getCell(1).getNumericCellValue();
                int weight = (int) row.getCell(2).getNumericCellValue();

                Node sourceNode = nodeMap.computeIfAbsent(sourceNodeNum, Node::new);
                Node targetNode = nodeMap.computeIfAbsent(targetNodeNum, Node::new);

                sourceNode.addAdjacentNode(targetNode, weight);
            }

            workbook.close();
            return new ArrayList<>(nodeMap.values());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
