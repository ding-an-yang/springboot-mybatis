package com.kuang.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：yangan
 * @date ：2022/11/7 下午1:50
 * @description：Excel导出工具
 * @version: 1.0.0
 */
@Slf4j
public class ExcelUtil {

    /**
     * @Description: excel导入
     * @UpdateDate: 2019/5/10 13:52
     * @Version: 1.0
     */
    public static <T> List<T> excelToList(Class<T> clazz, InputStream is, String excelFileName) {
        List<T> list = new ArrayList<>();
        try {
            //创建工作簿
            Workbook workbook = createWorkbook(is, excelFileName);
            System.out.println("workbook:" + workbook);
            //创建工作表sheet
            Sheet sheet = getSheet(workbook, 0);
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取表头单元格个数
            int cells = sheet.getRow(1).getPhysicalNumberOfCells();
            //利用反射，给JavaBean的属性进行赋值
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
                Row row = sheet.getRow(i);
                int index = 0;
                T object = clazz.getConstructor(new Class[]{}).newInstance(new Object[]{});
                while (index <= cells) {
                    Cell cell = row.getCell(index);
                    if (null == cell) {
                        cell = row.createCell(index);
                    }
                    String value = null;
                    if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        if (DateUtil.isCellDateFormatted(cell)) {
                            //用于转化为日期格式
                            Date d = cell.getDateCellValue();
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            value = df.format(d);
                        } else {
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            value = null == cell.getStringCellValue() ? "" : cell.getStringCellValue();
                        }
                    } else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        value = null == cell.getStringCellValue() ? "" : cell.getStringCellValue();
                    }
                    if (index < fields.length) {
                        Field field = fields[index];
                        String fieldName = field.getName();
                        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        Method setMethod = clazz.getMethod(methodName, new Class[]{String.class});
                        setMethod.invoke(object, new Object[]{value});
                    }
                    index++;
                }
                if (isHasValues(object)) {
                    list.add(object);
                }
            }
        } catch (Exception e) {
            System.out.println("e:" + e);
            log.error(e.getMessage());
        } finally {
            try {
                is.close();//关闭流
            } catch (Exception e2) {
                System.out.println("e2:" + e2);
                log.error(e2.getMessage());
            }
        }
        return list;

    }

    /**
     * @Description: excel模板导出
     * @Author: vdi100
     * @CreateDate: 2019/5/10 13:57
     * @Version: 1.0
     */
    public static void excelModuleDownload(HttpServletResponse res, String pathName, String fileName) throws Exception {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(new File("E://" + fileName)));
            bis = new BufferedInputStream(new FileInputStream(new File(pathName + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    /**
     * @Description: excel导出
     * @UpdateDate: 2019/5/10 13:53
     */
    public static <T> void listToExcel(List<T> list, String[] headers, String title, OutputStream os) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽15个字节
        sheet.setDefaultColumnWidth(15);
        //生成一个样式
        HSSFCellStyle style = getCellStyle(workbook);
        //生成一个字体
        HSSFFont font = getFont(workbook);
        //把字体应用到当前样式
        style.setFont(font);

        //生成表格标题
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 300);
        HSSFCell cell = null;
        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //将数据放入sheet中
        int index = 0;//记录额外创建的sheet数量
        for (int i = 0; i < list.size(); i++) {
            if ((i + 1) % 65535 == 0) {
                sheet = workbook.createSheet(title + index);
                //设置表格默认列宽15个字节
                sheet.setDefaultColumnWidth(15);
                row = sheet.createRow(0);
                row.setHeight((short) 300);
                for (int j = 0; j < headers.length; j++) {
                    cell = row.createCell(j);
                    cell.setCellStyle(style);
                    HSSFRichTextString text = new HSSFRichTextString(headers[j]);
                    cell.setCellValue(text);
                }
                index++;
            }
            if (i >= 65534) {
                row = sheet.createRow((i + 2) - (index * 65535));
            } else {
                row = sheet.createRow(i + 1);
            }
            T t = list.get(i);
            //利用反射，根据JavaBean属性的先后顺序，动态调用get方法得到属性的值
            Field[] fields = t.getClass().getFields();
            try {
                for (int j = 0; j < fields.length; j++) {
                    cell = row.createCell(j);
                    Field field = fields[j];
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method getMethod = t.getClass().getMethod(methodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    if (null == value) {
                        value = "";
                    }
                    cell.setCellValue(value.toString());
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        try {
//            FileOutputStream fos = new FileOutputStream("E:/TSBrowserDownloads/test1.xls");
//            workbook.write(fos);
            workbook.write(os);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

    }

    /**
     * 将excel转换为二进制输入流
     */
    public static <T> void listToExcelByte(List<T> list, String[] headers, String title, ByteArrayOutputStream os) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽15个字节
        sheet.setDefaultColumnWidth(15);
        //生成一个样式
        HSSFCellStyle style = getCellStyle(workbook);
        //生成一个字体
        HSSFFont font = getFont(workbook);
        //把字体应用到当前样式
        style.setFont(font);

        //生成表格标题
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 300);
        HSSFCell cell = null;

        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //将数据放入sheet中
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            T t = list.get(i);
            //利用反射，根据JavaBean属性的先后顺序，动态调用get方法得到属性的值
            Field[] fields = t.getClass().getFields();
            try {
                for (int j = 0; j < fields.length; j++) {
                    cell = row.createCell(j);
                    Field field = fields[j];
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method getMethod = t.getClass().getMethod(methodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});

                    if (null == value) {
                        value = "";
                    }
                    cell.setCellValue(value.toString());

                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }

        try {
            workbook.write(os);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

    }

    /**
     * @param @param  object
     * @param @return
     * @return boolean
     * @throws
     * @Title: isHasValues
     * @Description: 判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
     */
    private static boolean isHasValues(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);
                if (null != obj && !"".equals(obj)) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }

        }
        return flag;
    }

    /**
     * @param @param  is
     * @param @param  excelFileName
     * @param @return
     * @param @throws IOException
     * @return Workbook
     * @throws
     * @Title: createWorkbook
     * @Description: 判断excel文件后缀名，生成不同的workbook
     */
    private static Workbook createWorkbook(InputStream is, String excelFileName) throws IOException {
        System.out.println(excelFileName);
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);
        } else if (excelFileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    /**
     * @param @param  workbook
     * @param @param  sheetIndex
     * @param @return
     * @return Sheet
     * @throws
     * @Title: getSheet
     * @Description: 根据sheet索引号获取对应的sheet
     */
    private static Sheet getSheet(Workbook workbook, int sheetIndex) {
        return workbook.getSheetAt(0);
    }

    /**
     * @param @param  workbook
     * @param @return
     * @return HSSFCellStyle
     * @throws
     * @Title: getCellStyle
     * @Description: 获取单元格格式
     */
    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        return style;
    }

    /**
     * @param @param  workbook
     * @param @return
     * @return HSSFFont
     * @throws
     * @Title: getFont
     * @Description: 生成字体样式
     */
    public static HSSFFont getFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 24);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        return font;
    }

    public static boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 ? true : false;
    }


    /**
     * @param sheetName 工作表的名字
     * @param column    列名
     * @param data      需要导出的数据    ( map的键定义为列的名字 一定要和column中的列明保持一致  )
     * @param response
     */
    public static void exportExcel(String sheetName, List<String> column, List<Map<String, Object>> data, HttpServletRequest request, HttpServletResponse response) {
        // 创建工作薄
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
//        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        headStyle.setAlignment(HorizontalAlignment.CENTER);

        // 创建sheet
        HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);
        // 表头
        HSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < column.size(); i++) {
            headRow.createCell(i).setCellValue(column.get(i));
        }

        // 数据写入excel表里
        for (Map<String, Object> datum : data) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            for (int x = 0; x < column.size(); x++) {
                dataRow.createCell(x).setCellValue(datum.get(column.get(x)) == null ? "" : datum.get(column.get(x)).toString());
            }
        }

        response.setContentType("application/vnd.ms-excel");

        // 通过浏览器判断修改文件名称
        try {
            //获取浏览器名称
            String agent = request.getHeader("user-agent");
            String filename = sheetName + ".xlsx";
            //不同浏览器需要对文件名做特殊处理
            if (agent.contains("Firefox")) { // 火狐浏览器
                filename = "=?UTF-8?B?"
                        + new BASE64Encoder().encode(filename.getBytes("utf-8"))
                        + "?=";
                filename = filename.replaceAll("\r\n", "");
            } else { // IE及其他浏览器
                filename = URLEncoder.encode(filename, "utf-8");
                filename = filename.replace("+", " ");
            }
            //推送浏览器
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            hssfWorkbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
