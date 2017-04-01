package com.tianque.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

public class StringUtil {

   public static boolean isEmpty(String value) {
       return value == null || "".equals(value.trim());
   }

   public static boolean isNotEmpty(String value) {
       return !isEmpty(value);
   }

   /**
    * 判断指定字符串是否包含中文字符
    * 
    * @param str 字符串
    * @return
    * @author 王振 Created on 2013-12-26
    * @since 1.0
    */
   public static boolean containsChinese(String str) {
       if (isEmpty(str)) {
           return false;
       }

       char[] charArray = str.toCharArray();
       for (int i = 0; i < charArray.length; i++) {
           if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
               return true;
           }
       }
       return false;
   }

   /**
    * 解析得到encoding的具体编码
    * 
    * @param encoding
    * @return 编码
    */
   public static String parseEncoding(String encoding) {

       String regexp = "encoding=\"(gBK|utf-8|gb2312|ISO-8859-1)\"";

       PatternCompiler compiler = new Perl5Compiler();
       org.apache.oro.text.regex.Pattern pattern = null;
       try {
           pattern = compiler.compile(regexp, Perl5Compiler.CASE_INSENSITIVE_MASK);
       }
       catch (MalformedPatternException e) {
           e.printStackTrace();
       }

       PatternMatcher matcher = new Perl5Matcher();
       if (matcher.contains(encoding, pattern)) {
           MatchResult result = matcher.getMatch();
           return result.group(1);
           // System.out.println("encoding:"+result.group(1));
       }

       return null;
   }

   /**
    * unicode编码转成中文 例子："&#20013;"-->"中"
    * 
    * @return
    */
   public static String unicode2Cn(String src) {
       String regexp = "&#\\d{5};";
       StringBuilder out = new StringBuilder();
       int last = 0;
       Pattern p = Pattern.compile(regexp);
       Matcher m = p.matcher(src);

       while (m.find()) {
           int start = m.start();
           int end = m.end();
           String match = src.substring(start, end);

           String unicode = match.substring(2, 7);
           Character c = Character.valueOf((char) Integer.valueOf(unicode).intValue());

           String pre = "";
           if (start != 0)
               pre = src.substring(last, start);
           out.append(pre);
           out.append(c.toString());
           last = end;
       }
       if (last != src.length())
           out.append(src.substring(last));
       return out.toString();
   }

   /**
    * 将数字格式化为前空位补0的字符串，如果数字位数大于允许最大位数，头部数字将被截去。<br>
    * eg :<br>
    * 输入 1,5 --- 输出 "00001"<br>
    * 输入 123,2 --- 输出 "23"
    * 
    * @param num 被转化数字
    * @param maxbit 允许最大位数（返回字符串最大长度)
    */
   public static final String formatToZeroBefore(int num, int maxbit) {
       StringBuffer buf = new StringBuffer(maxbit);
       String strNum = "" + num;
       if (strNum.length() < maxbit) {
           for (int i = 0; i < (maxbit - strNum.length()); i++)
               buf.append('0');
           buf.append(strNum);
       }
       else
           buf.append(strNum.substring(strNum.length() - maxbit, strNum.length()));
       return new String(buf);
   }

   public static final String replace(String line, String oldString, String newString) {
       if (line == null) {
           return null;
       }
       int i = 0;
       if ((i = line.indexOf(oldString, i)) >= 0) {
           char[] line2 = line.toCharArray();
           char[] newString2 = newString.toCharArray();
           int oLength = oldString.length();
           StringBuffer buf = new StringBuffer(line2.length);
           buf.append(line2, 0, i).append(newString2);
           i += oLength;
           int j = i;
           while ((i = line.indexOf(oldString, i)) > 0) {
               buf.append(line2, j, i - j).append(newString2);
               i += oLength;
               j = i;
           }
           buf.append(line2, j, line2.length - j);
           return buf.toString();
       }
       return line;
   }

   public static final String convertNumberToString(Number value) {
       DecimalFormat formatter = new DecimalFormat();
       formatter.setGroupingUsed(false);
       String result = formatter.format(value.doubleValue());
       return result;
   }

   /**
    * 将String压缩 example:com.jbbis.aic.alteration.entity.AlterRequisition 中
    * integrateOpinion,getIntegrateOpinionInBytes(),setIntegrateOpinionInBytes
    */
   public static byte[] zipString(String s) {
       if (s == null)
           return null;
       try {
           byte[] bytes = s.getBytes("GBK");
           BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(bytes));
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(baos));
           byte[] buffer = new byte[1024];
           int len = 0;
           while ((len = in.read(buffer)) != -1) {
               out.write(buffer, 0, len);
           }
           in.close();
           out.close();
           return baos.toByteArray();
       }
       catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   /**
    * 解压String
    */
   public static String unzipString(byte[] bytes) {
       if (bytes == null || bytes.length == 0)
           return null;
       try {
           BufferedInputStream in = new BufferedInputStream(new GZIPInputStream(new ByteArrayInputStream(bytes)));
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           BufferedOutputStream out = new BufferedOutputStream(baos);
           byte[] buffer = new byte[1024];
           int len = 0;
           while ((len = in.read(buffer)) != -1) {
               out.write(buffer, 0, len);
           }
           in.close();
           out.close();
           bytes = baos.toByteArray();
           return new String(bytes, "GBK");
       }
       catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }

   // 从表名中分解出schmea名。
   public static String getSchemaFromFullTbName(String strName) {
       int index = strName.indexOf(".");
       if (index < 0)
           return null;
       return strName.substring(0, index);
   }

   public static String[] split(String pama, char index) {
       boolean isStrEnd = false;
       String array[] = new String[0];
       pama = pama + index;
       String temp = "";
       ArrayList<String> list = new ArrayList<String>();
       while (!isStrEnd) {
           int beginIndex = pama.indexOf(index);
           if (beginIndex == -1) {
               isStrEnd = true;
           }
           else {
               temp = pama.substring(0, beginIndex);
               beginIndex++;
               pama = pama.substring(beginIndex, pama.length());
               list.add(temp);
           }
       }
       if (list.size() > 0) {
           array = new String[list.size()];
           for (int i = 0; i < list.size(); i++)
               array[i] = list.get(i);

       }
       return array;
   }

   public static String getCurrentTime() {
       DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CHINA);
       return df.format(new Date());
   }

   /**
    * 用于将字符串中的分隔符去掉重新组成字符串。
    * <p>
    * 例：<br>
    * String in = "this is a test";<br>
    * StringUtil.trimLine(in) returns "thisisatest"<br>
    * 
    * @param in 待处理的字符串
    * @return 处理后的字符串 <br>
    */
   public static String trimLine(String in) {
       StringTokenizer st = new StringTokenizer(in);
       StringBuffer sb = new StringBuffer();
       while (st.hasMoreTokens())
           sb.append(st.nextToken());
       return sb.toString();
   }


   /**
    * 用于返回指定字符串在字符串数组中首次出现的下标数。 <br>
    * <p>
    * 例： <br>
    * String str[] = {"abc","c1c","acc","cc"}; <br>
    * StringUtil.stringAt(str,"cc") returns 3 ;
    * 
    * @param strArray 字符串数组
    * @param item 字符串
    * @return 首次出现的下标值，如果没有，则返回-1 <br>
    */
   public static int stringAt(String[] strArray, String item) {
       for (int i = 0; i < strArray.length; i++)
           if (item.equals(strArray[i]))
               return i;
       return -1;
   }

   /**
    * 获得一个字符串数组的子数组, 返回数据的长度为指定(length)长度, 不足被空串("")
    * 
    * @param srcStrs 原字符串数组
    * @param start 子数组起始位置, 如小于0, 则以默认0作为起始位置
    * @param length 需要获得子数组的长度
    * @return add by shicy 2009-4-9
    */
   public static String[] subStrings(String[] srcStrs, int start, int length) {
       if (srcStrs == null)
           return new String[length];
       if (start < 0) // 小于0输入是非法的, 改为默认值0
           start = 0;
       String[] destStrs = new String[length];
       for (int i = 0; i < length; i++) {
           if (start > srcStrs.length)
               break;
           destStrs[i] = (String) srcStrs[start++];
       }
       return destStrs;
   }

   /**
    * 获得一个数组的子数组, 返回数据的长度为指定(length)长度, 不足被空串("")
    * 
    * @param srcStrs 原数组
    * @param start 子数组起始位置, 如小于0, 则以默认0作为起始位置
    * @param length 需要获得子数组的长度
    * @return add by shicy 2009-4-9
    */
   public static Object[] subArrays(Object[] srcStrs, int start, int length) {
       if (srcStrs == null)
           return new Object[length];
       if (start < 0) // 小于0输入是非法的, 改为默认值0
           start = 0;
       Object[] destStrs = new Object[length];
       for (int i = 0; i < length; i++) {
           if (start > srcStrs.length)
               break;
           destStrs[i] = srcStrs[start++];
       }
       return destStrs;
   }

   /**
    * 用于将两个排序后的二维数组按指定规则排序并合并成一个二维数组。
    * 
    * @param arr1[][] 二维数组1
    * @param arr2[][] 二维数组2
    * @param int 排序的列的下标值
    * @param kind 类型，提供3种排序类型（String,int,float）
    * @param asc 以升序或降序排列（true为升序）
    * @return 合并后的二维数组
    */
   public static String[][] sortTwoString(String[][] arr1, String[][] arr2, int number, String kind, boolean asc) {
       int len1 = 0;
       int len2 = 0;
       int list = 0;
       if (arr1 == null)
           return arr2;
       if (arr2 == null)
           return arr1;
       list = arr1.length;
       if (list != arr2.length || list - 1 < number || number < 0)
           return null;
       len1 = arr1[0].length;
       len2 = arr2[0].length;
       int i = 0, j = 0, k = 0;
       String[][] result = new String[list][len1 + len2];
       boolean side = true; //
       for (k = 0; k < len1 + len2;) {
           boolean compare = false;
           if (kind.equals("String"))
               compare = (arr1[number][i].toLowerCase().compareTo(arr2[number][j].toLowerCase()) < 0);
           else if (kind.equals("int"))
               compare = sToi(arr1[number][i], -1) < sToi(arr2[number][j], -1);
           else if (kind.equals("float"))
               compare = sTof(arr1[number][i]) < sTof(arr2[number][j]);
           if ((asc && compare) || ((!asc) && (!compare))) {
               result[number][k] = arr1[number][i];
               if (side)
                   side = false;
               int loop = 0;
               for (loop = 0; loop < number; loop++)
                   result[loop][k] = arr1[loop][i];
               for (loop = number + 1; loop < list; loop++)
                   result[loop][k] = arr1[loop][i];
               i++;
           }
           else {
               result[number][k] = arr2[number][j];
               if (!side)
                   side = true;
               int loop = 0;
               for (loop = 0; loop < number; loop++)
                   result[loop][k] = arr2[loop][j];
               for (loop = number + 1; loop < list; loop++)
                   result[loop][k] = arr2[loop][j];
               j++;
           }
           k++;
           if (i >= len1) {
               for (; j < len2; j++, k++)
                   for (int loop = 0; loop < list; loop++)
                       result[loop][k] = arr2[loop][j];
           }
           if (j >= len2 && i < len1) {
               for (; i < len1; i++, k++)
                   for (int loop = 0; loop < list; loop++)
                       result[loop][k] = arr1[loop][i];
           }
       }
       return result;
   }

   /**
    * 将字符串转换为浮点数
    * 
    * @param s 待转换的字符串
    * @return 浮点数 <br>
    */
   private static float sTof(String s) {
       float i = 0;
       if (s == null)
           return i;
       try {
           i = Float.valueOf(s).floatValue();
       }
       catch (NumberFormatException nfe) {
       }
       return i;
   }

   /**
    * 将字符串转换为数字 <br>
    * 
    * @param s 待转换的字符串
    * @param i 参数i
    * @return 整数 <br>
    */
   private static int sToi(String s, int i) {
       if (s == null)
           return i;
       try {
           i = Integer.parseInt(s);
       }
       catch (NumberFormatException nfe) {
       }
       return i;
   }

   /**
    * 判断是否为时间类类型
    * 
    * @param 输入的类型为：type date,timestamp,time等。
    * @return
    */
   public final static boolean isTimeType(String type) {
       if ((type.toLowerCase().indexOf("date") >= 0 || type.toLowerCase().indexOf("timestamp") >= 0 || type
               .toLowerCase().indexOf("time") >= 0))
           return true;
       return false;
   }

   /**
    * 是否数字
    * 
    * @param str
    * @return
    */
   static Pattern num_pattern = Pattern.compile("[0-9]*");

   public static boolean isNumeric(String str) {
       Matcher isNum = num_pattern.matcher(str);
       if (!isNum.matches()) {
           return false;
       }
       return true;
   }
   /**
    * 得到唯一的标式字符串
    * 
    * @return String
    */

   public static String getGuid() {
       long a = System.currentTimeMillis();
       java.util.Random r = new java.util.Random();
       int b = r.nextInt(100);
       return String.valueOf(a) + String.valueOf(b);
   }

   /**
    * 
    * @param lineData
    * @param seperator
    * @return
    */
   public static String[] split(String lineData, String seperator) {
       ArrayList<String> list = new ArrayList<String>();
       int begin = 0;
       int end = lineData.indexOf(seperator);
       while (end >= 0) {
           list.add(lineData.substring(begin, end));
           begin = end + seperator.length();
           end = lineData.indexOf(seperator, begin);
       }
       list.add(lineData.substring(begin));
       String[] arrStr = new String[list.size()];
       return list.toArray(arrStr);// (String[])list.toArray();
   }
   
   /**
    * 
    * @param lineData
    * @param seperator
    * @return
    */
   public static List<String> split4List(String lineData, String seperator) {
       ArrayList<String> list = new ArrayList<String>();
       int begin = 0;
       int end = lineData.indexOf(seperator);
       while (end >= 0) {
           list.add(lineData.substring(begin, end));
           begin = end + seperator.length();
           end = lineData.indexOf(seperator, begin);
       }
       list.add(lineData.substring(begin));
       return list;
   }

   /**
    * 把一个整形转换成字符串，但确保一定的长度，长度不足时在前面补0
    * 
    * @param value 待转换值
    * @param length 转换后的长度
    * @return
    */
   public static String intToStrByLength(int value, int length) {
       String ret = Integer.toString(value);
       if (ret.length() >= length)// 已经超出
           return ret;
       for (int ii = ret.length(); ii < length; ii++)
           ret = '0' + ret;
       return ret;
   }

   /**
    * 模拟apache高版本 StringUtils的endsWith，低版本中没有，此处提供
    * 
    * @param str
    * @param suffix
    * @param ignoreCase 是否忽略大小写，默认false
    * @return
    */
   public static boolean endsWith(String str, String suffix, boolean ignoreCase) {
       if (str == null || suffix == null)
           return str == null && suffix == null;
       if (suffix.length() > str.length()) {
           return false;
       }
       int strOffset = str.length() - suffix.length();
       return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
   }

   /**
    * MyBatis sqlCommondType: UNKNOWN = new SqlCommandType("UNKNOWN", 0); INSERT = new SqlCommandType("INSERT", 1);
    * UPDATE = new SqlCommandType("UPDATE", 2); DELETE = new SqlCommandType("DELETE", 3); SELECT = new
    * SqlCommandType("SELECT", 4);
    */
   private static String[] SQLKEYS = new String[] { "insert into ", "update ", " from " };

	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toUpperCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}

}
