package classes.scenarios.multithreaded;

public class Manufacturer_DTO {
     String brand = new String();
     String brandForXls = new String() ;
     String dir = new String();
     int startItemNumber=0;
     int finishItemNumber=0;

    public Manufacturer_DTO(String inputBrand, String inputBrandForXls, String inputDir) {
        brand=inputBrand;
        brandForXls=inputBrandForXls;
        dir=inputDir;
    }

    public Manufacturer_DTO(String brand, String brandForXls, String dir, int startItemNumber, int finishItemNumber) {
        this.brand = brand;
        this.brandForXls = brandForXls;
        this.dir = dir;
        this.startItemNumber = startItemNumber;
        this.finishItemNumber = finishItemNumber;
    }

    public String getBrand() { return brand; }

    public String getBrandForXls() {
        return brandForXls;
    }

    public String getDir() {
        return dir;
    }

    public int getStartItemNumber() {
        return startItemNumber;
    }

    public int getFinishItemNumber() {
        return finishItemNumber;
    }
}