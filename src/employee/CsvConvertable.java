package employee;

public interface CsvConvertable {
    String toCSV();
    static AbstractEmployee fromCSV(String csv) {
        throw new UnsupportedOperationException("fromCSV not implemented");
    }
}
