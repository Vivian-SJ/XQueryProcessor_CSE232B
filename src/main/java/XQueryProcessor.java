import IO.IOHelper;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import xquery.CustomXQueryVisitor;
import xquery.xqueryLexer;
import xquery.xqueryParser;

public class XQueryProcessor {
    public static void main(String[] args) {
        String xquery = "<acts> { for $a in doc(\"j_caesar.xml\")//ACT\n" +
                "\n" +
                "              where $a/TITLE=\"ACT I \"\n" +
                "\n" +
                "              return <act>{$a/TITLE/text()}</act>\n" +
                "\n" +
                "}</acts>";
//        String xquery = "$a00";
        ANTLRInputStream input = new ANTLRInputStream(xquery);
        xqueryLexer lexer = new xqueryLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        xqueryParser parser = new xqueryParser(tokens);
        ParseTree tree = parser.xq();
//        System.out.println(tree.toStringTree());
        CustomXQueryVisitor xQueryVisitor = new CustomXQueryVisitor();
//        xQueryVisitor.visit(tree);
        IOHelper.outputResult(xQueryVisitor.visit(tree));
    }
}
