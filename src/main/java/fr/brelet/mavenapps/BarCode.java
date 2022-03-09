package fr.brelet.mavenapps;

import java.util.*;
import java.util.stream.Collectors;

public class BarCode {

    Map<String, String> codeArray = new LinkedHashMap<>();

    List codeKey = new ArrayList<>();
    Map<String, Integer> codeValue = new LinkedHashMap<>();

    public String code(String text){
        String codeString = "";

        codeArray.put("00","212222");
        codeArray.put("01","222122");
        codeArray.put("02","222221");
        codeArray.put("03","121223");
        codeArray.put("04","121322");
        codeArray.put("05","131222");
        codeArray.put("06","122213");
        codeArray.put("07","122312");
        codeArray.put("08","132212");
        codeArray.put("09","221213");
        codeArray.put("10","221312");
        codeArray.put("11","231212");
        codeArray.put("12","112232");
        codeArray.put("13","122132");
        codeArray.put("14","122231");
        codeArray.put("15","113222");
        codeArray.put("16","123122");
        codeArray.put("17","123221");
        codeArray.put("18","223211");
        codeArray.put("19","221132");
        codeArray.put("20","221231");
        codeArray.put("21","213212");
        codeArray.put("22","223112");
        codeArray.put("23","312131");
        codeArray.put("24","311222");
        codeArray.put("25","321122");
        codeArray.put("26","321221");
        codeArray.put("27","312212");
        codeArray.put("28","322112");
        codeArray.put("29","322211");
        codeArray.put("30","212123");
        codeArray.put("31","212321");
        codeArray.put("32","232121");
        codeArray.put("33","111323");
        codeArray.put("34","131123");
        codeArray.put("35","131321");
        codeArray.put("36","112313");
        codeArray.put("37","132113");
        codeArray.put("38","132311");
        codeArray.put("39","211313");
        codeArray.put("40","231113");
        codeArray.put("41","231311");
        codeArray.put("42","112133");
        codeArray.put("43","112331");
        codeArray.put("44","132131");
        codeArray.put("45","113123");
        codeArray.put("46","113321");
        codeArray.put("47","133121");
        codeArray.put("48","313121");
        codeArray.put("49","211331");
        codeArray.put("50","231131");
        codeArray.put("51","213113");
        codeArray.put("52","213311");
        codeArray.put("53","213131");
        codeArray.put("54","311123");
        codeArray.put("55","311321");
        codeArray.put("56","331121");
        codeArray.put("57","312113");
        codeArray.put("58","312311");
        codeArray.put("59","332111");
        codeArray.put("60","314111");
        codeArray.put("61","221411");
        codeArray.put("62","431111");
        codeArray.put("63","111224");
        codeArray.put("64","111422");
        codeArray.put("65","121124");
        codeArray.put("66","121421");
        codeArray.put("67","141122");
        codeArray.put("68","141221");
        codeArray.put("69","112214");
        codeArray.put("70","112412");
        codeArray.put("71","122114");
        codeArray.put("72","122411");
        codeArray.put("73","142112");
        codeArray.put("74","142211");
        codeArray.put("75","241211");
        codeArray.put("76","221114");
        codeArray.put("77","413111");
        codeArray.put("78","241112");
        codeArray.put("79","134111");
        codeArray.put("80","111242");
        codeArray.put("81","121142");
        codeArray.put("82","121241");
        codeArray.put("83","114212");
        codeArray.put("84","124112");
        codeArray.put("85","124211");
        codeArray.put("86","411212");
        codeArray.put("87","421112");
        codeArray.put("88","421211");
        codeArray.put("89","212141");
        codeArray.put("90","214121");
        codeArray.put("91","412121");
        codeArray.put("92","111143");
        codeArray.put("93","111341");
        codeArray.put("94","131141");
        codeArray.put("95","114113");
        codeArray.put("96","114311");
        codeArray.put("97","411113");
        codeArray.put("98","411311");
        codeArray.put("99","113141");
        codeArray.put("CODE B","114131");
        codeArray.put("CODE A","311141");
        codeArray.put("Fnc 1","411131");
        codeArray.put("Start A","211412");
        codeArray.put("Start B","211214");
        codeArray.put("Start C","211232");
        codeArray.put("Stop","2331112");
        int i = 0;
        for (Map.Entry<String, String> entry : codeArray.entrySet()) {

            codeKey.add(entry.getKey());
            codeValue.put(entry.getKey(), i);
            i++;
        }

        int chksum = 105;
        int mult = 1;
        for (int X = 2; X <= text.length(); X= X+2) {

            String activeKey = text.substring(X-2, X);
            codeString += codeArray.get(activeKey);
            int oui = codeValue.get(activeKey);
            chksum = chksum + (oui*mult);
            mult++;

        }
        int rest = chksum%103;
        codeString += codeArray.get(codeKey.get(rest));
        codeString = "211232"+codeString+"2331112";
        return codeString;
    }
    public static <V, K> Map<V, K> invert(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}
