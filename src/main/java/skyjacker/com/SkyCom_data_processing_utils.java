package skyjacker.com;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class SkyCom_data_processing_utils {
    static ArrayList<Element> jsoupElementsList=new ArrayList<>();
    static String lineSeparator =System.lineSeparator();

    public static ArrayList<String> xpathStringsListInit() {
        ArrayList<String> xpathStringsList=new ArrayList<>();
        xpathStringsList.add("//nav[@class='woocommerce-breadcrumb']/*");
        xpathStringsList.add("(//div[@class='et_pb_module_inner']/p)[1]");
        xpathStringsList.add("//span[@class='sku']");
        xpathStringsList.add("//h1"); // title
        xpathStringsList.add("//table[@class='table table-striped table-responsive-sm']"); // table class="table table-striped table-responsive-sm"
        xpathStringsList.add("//table[@class='woocommerce-product-attributes shop_attributes']"); // table class="woocommerce-product-attributes shop_attributes"
        xpathStringsList.add("(//div[@class='et_pb_tab_content'])[1]");
        xpathStringsList.add("(//div[@class='et_pb_tab_content'])[4]//following-sibling::a/@href");
        xpathStringsList.add("//img[@class='wp-post-image']/@src");
        xpathStringsList.add("//*[contains(text(), 'in. Lift')]");

        return xpathStringsList;
    }



}
