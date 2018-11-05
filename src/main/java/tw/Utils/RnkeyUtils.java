package tw.Utils;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.LinkedList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RnkeyUtils {
    private static LinkedList<String> keyPool = Lists.newLinkedList();

    private static final Logger logger = LoggerFactory.getLogger(RnkeyUtils.class);
    public static String nextKey() {


        if(keyPool.size() == 0 ) {
            return "8646947*10446509:2380729724:870338664:1";
        }else{
            int next = new Random().nextInt(keyPool.size());
            return keyPool.get(next);
        }

    }
    public static void clean (){
        keyPool.clear();
    }

    public static String html(String html) throws ScriptException {
        ScriptEngineManager factory = new ScriptEngineManager();

//        String html = "<html><head><script type=\"text/javascript\"><!--function leastFactor(n) { if (isNaN(n) || !isFinite(n)) return NaN; if (typeof phantom !== 'undefined') return 'phantom'; if (typeof module !== 'undefined' && module.exports) return 'node'; if (n==0) return 0; if (n%1 || n*n<2) return 1; if (n%2==0) return 2; if (n%3==0) return 3; if (n%5==0) return 5; var m=Math.sqrt(n); for (var i=7;i<=m;i+=30) {  if (n%i==0)      return i;  if (n%(i+4)==0)  return i+4;  if (n%(i+6)==0)  return i+6;  if (n%(i+10)==0) return i+10;  if (n%(i+12)==0) return i+12;  if (n%(i+16)==0) return i+16;  if (n%(i+22)==0) return i+22;  if (n%(i+24)==0) return i+24; } return n;}function go() { var p=11164913121683; var s=3875392113; var n;if ((s >> 10) & 1)/**13;*/p+=68458503*13; else /*p+= */p-=/*else p-=*/88105424*\t11;if ((s >> 8) & 1) p+=131914319*/*else p-=*/11;else \tp-=/* 120886108**/9112066*/*p+= */9;/*p+= */if ((s >> 14) & 1)p+=\t109456792*/*else p-=*/17;else \tp-=/**13;*/60075982*/**13;*/15;if ((s >> 10) & 1)\tp+=112452553*\t13;\telse /*p+= */p-= 107984963* 11;/*else p-=*/if ((s >> 6) & 1) p+=71401136*9;else  p-= 199686582*\t7;/* 120886108**/ p-=1135472229; n=leastFactor(p);{ document.cookie=\"RNKEY=\"+n+\"*\"+p/n+\":\"+s+\":1492907807:1\";  document.location.reload(true); }}//--></script></head><body onload=\"go()\">Loading ...</body></html>";
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        Matcher a = Pattern.compile("(.*)(function leastFactor\\(n\\).*)(function go\\(\\) \\{ )(.*)(n=leastFactor\\(p\\);\\{)(.*?=)(.*?;)(.*)").matcher(html);
        String functionP =  html.replaceAll("(.*)(function leastFactor\\(n\\).*)(function go\\(\\) \\{ )(.*)(n=leastFactor\\(p\\);\\{)(.*?=)(.*?;)(.*)", "$4");
        String functionF =  html.replaceAll("(.*)(function leastFactor\\(n\\).*)(function go\\(\\) \\{ )(.*)(n=leastFactor\\(p\\);\\{)(.*?=)(.*?;)(.*)", "$2");
        String functionRNKEY =  html.replaceAll("(.*)(function leastFactor\\(n\\).*)(function go\\(\\) \\{ )(.*)(n=leastFactor\\(p\\);\\{)(.*?=)(.*?;)(.*)", "$7");
//         System.out.printf("dexp: %f \n", engine.eval(functionP));
        String p = engine.eval(functionP).toString();
//        System.out.println( engine.eval(functionF+"leastFactor(" + p +")"));
        String rnkey = functionRNKEY.replace("p", engine.eval(functionP).toString())
                .replace("n", engine.eval(functionF+"leastFactor(" + p +")").toString());
//        logger.error("genKey:{}"+engine.eval(rnkey).toString());
        if(keyPool.size()!=0 && keyPool.size() > 20) {
            keyPool.removeLast();
        }
        keyPool.push(engine.eval(rnkey).toString().replace("RNKEY=", ""));
//        logger.error("first:{}"+keyPool.getFirst());
//        logger.error("last:{}"+keyPool.getLast());
        return engine.eval(rnkey).toString();
    }


}
