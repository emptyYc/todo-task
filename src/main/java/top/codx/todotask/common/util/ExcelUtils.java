package top.codx.todotask.common.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Excel工具类
 *
 * @author Liuch
 * @since 2023-07-26 12:31
 */
public class ExcelUtils {

    /**
     * 读取Excel, 将数据转为实体类
     *
     * @param inputStream Excel文件
     * @param clazz       实体类型
     * @return T
     **/
    public static <T> List<T> readExcel(FileInputStream inputStream, Class<T> clazz) {
        try {
            List<T> result = new ArrayList<T>();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int numberOfRows = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(1);

            // 获取字段名
            List<String> key = new ArrayList<String>();
            // 获取字段名
            for (Cell cell : row) {
                String value = cell.getStringCellValue();
                key.add(value);
            }

            // 遍历正式数据
            for (int i = 2; i < numberOfRows; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    // 计数器 j
                    int j = 0;
                    // 保存数据的Map
                    Map<String, String> excelMap = new HashMap<String, String>();
                    for (Cell cell : row) {
                        String val = getString(cell);
                        if (val != null && !val.equals("")) {
                            excelMap.put(key.get(j), val);
                            j++;
                        }
                    }
                    // 创建对应实体类,并把数据转换到实体类
                    T t = mapToEntity(excelMap, clazz);
                    result.add(t);
                }
            }
            inputStream.close();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Map集合映射为实体类
     *
     * @param map   数据集合
     * @param clazz 实体类型
     * @return 实体类
     */
    public static <T> T mapToEntity(Map<String, String> map, Class<T> clazz) throws Exception {
        T t = null;
        t = clazz.newInstance();
        for (Field filed : clazz.getDeclaredFields()) {
            if (map.containsKey(filed.getName())) {
                boolean flag = filed.isAccessible();
                filed.setAccessible(true);
                String s = map.get(filed.getName());
                // 获取属性类型
                String type = filed.getGenericType().toString();
                if (s != null) {
                    if (type.equals("class java.lang.String")) {
                        filed.set(t, s);
                    } else if (type.equals("class java.lang.Double")) {
                        filed.set(t, Double.parseDouble(s));
                    } else if (type.equals("class java.lang.Integer")) {
                        filed.set(t, Integer.parseInt(s));
                    } else if (type.equals("class java.util.Date")) {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(s);
                        filed.set(t, date);
                    }
                }
                filed.setAccessible(false);
            }
        }

        return t;
    }

    public static NumberFormat nf = NumberFormat.getNumberInstance();

    static {
        nf.setGroupingUsed(false);
    }

    /**
     * 处理单元格数据
     *
     * @param cell 单元格对象
     * @return 单元格数据
     */
    private static String getString(Cell cell) {
        CellType cellType = cell.getCellType();
        String value = "";
        // 根据类型读取数据
        switch (cellType) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                // 判断是否为日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                } else {
                    value = nf.format(cell.getNumericCellValue());
                }
                break;
            case BLANK:
            case ERROR:
                value = "";
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
        }
        return value;
    }
}
